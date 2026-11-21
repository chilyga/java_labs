package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SampleFileLoaderTest {

    @TempDir
    Path tempDir;

    private final SampleFileLoader loader = new SampleFileLoader();

    private static final String HEADER =
            "id;label;source;featureCount;status";

    private Path createFile(String fileName, String content)
            throws IOException {

        Path file = tempDir.resolve(fileName);

        Files.writeString(
                file,
                content,
                StandardCharsets.UTF_8
        );

        return file;
    }

    @Test
    void loadsCorrectFile() throws IOException {
        Path file = createFile(
                "samples.txt",
                HEADER + "\n" +
                        "S-101;cat;camera-1;128;READY\n"
        );

        LoadResult result = loader.load(file);

        assertEquals(1, result.getSamples().size());
        assertTrue(result.getErrors().isEmpty());

        Sample sample = result.getSamples().get(0);

        assertEquals("S-101", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals("camera-1", sample.getSource());
        assertEquals(128, sample.getFeatureCount());
        assertEquals(SampleStatus.READY, sample.getStatus());
    }

    @Test
    void supportsUnicodeText() throws IOException {
        Path file = createFile(
                "unicode.txt",
                HEADER + "\n" +
                        "S-102;кошка;камера-1;64;READY\n"
        );

        LoadResult result = loader.load(file);

        assertEquals(1, result.getSamples().size());
        assertEquals(
                "кошка",
                result.getSamples().get(0).getLabel()
        );
    }

    @Test
    void detectsEmptyField() throws IOException {
        Path file = createFile(
                "empty-field.txt",
                HEADER + "\n" +
                        "S-103;;camera-2;64;READY\n"
        );

        LoadResult result = loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(1, result.getErrors().size());

        ValidationError error = result.getErrors().get(0);

        assertEquals(2, error.getLineNumber());
        assertEquals("label", error.getField());
    }

    @Test
    void detectsInvalidNumber() throws IOException {
        Path file = createFile(
                "invalid-number.txt",
                HEADER + "\n" +
                        "S-104;dog;camera-3;many;READY\n"
        );

        LoadResult result = loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals(
                "featureCount",
                result.getErrors().get(0).getField()
        );
    }

    @Test
    void detectsUnknownStatus() throws IOException {
        Path file = createFile(
                "unknown-status.txt",
                HEADER + "\n" +
                        "S-105;dog;camera-4;128;UNKNOWN\n"
        );

        LoadResult result = loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals(
                "status",
                result.getErrors().get(0).getField()
        );
    }

    @Test
    void detectsIncorrectNumberOfColumns() throws IOException {
        Path file = createFile(
                "wrong-columns.txt",
                HEADER + "\n" +
                        "S-106;dog;camera-5;128\n"
        );

        LoadResult result = loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals(
                "columns",
                result.getErrors().get(0).getField()
        );
    }

    @Test
    void loadsCorrectRowsAndCollectsErrors() throws IOException {
        Path file = createFile(
                "mixed.txt",
                HEADER + "\n" +
                        "S-107;cat;camera-1;128;READY\n" +
                        "S-108;dog;camera-2;wrong;READY\n" +
                        "S-109;bird;camera-3;256;PROCESSING\n"
        );

        LoadResult result = loader.load(file);

        assertEquals(2, result.getSamples().size());
        assertEquals(1, result.getErrors().size());

        assertEquals(
                "S-107",
                result.getSamples().get(0).getId()
        );

        assertEquals(
                "S-109",
                result.getSamples().get(1).getId()
        );
    }

    @Test
    void missingFileThrowsDataLoadingException() {
        Path file = tempDir.resolve("missing.txt");

        DataLoadingException exception = assertThrows(
                DataLoadingException.class,
                () -> loader.load(file)
        );

        assertNotNull(exception.getCause());
    }

    @Test
    void incorrectHeaderThrowsDataLoadingException()
            throws IOException {

        Path file = createFile(
                "wrong-header.txt",
                "wrong;header\n" +
                        "S-110;cat;camera-1;128;READY\n"
        );

        DataLoadingException exception = assertThrows(
                DataLoadingException.class,
                () -> loader.load(file)
        );

        assertNotNull(exception.getCause());
    }

    @Test
    void readsNumbersFileAsUtf8() throws IOException {
        Path file = tempDir.resolve("numbers.txt");

        Files.write(
                file,
                List.of("10", "bad", "20"),
                StandardCharsets.UTF_8
        );

        List<String> lines = Files.readAllLines(
                file,
                StandardCharsets.UTF_8
        );

        assertEquals(
                List.of("10", "bad", "20"),
                lines
        );
    }

    @Test
    void badNumberThrowsNumberFormatException() throws IOException {
        Path file = tempDir.resolve("numbers.txt");

        Files.write(
                file,
                List.of("10", "bad", "20"),
                StandardCharsets.UTF_8
        );

        List<String> lines = Files.readAllLines(
                file,
                StandardCharsets.UTF_8
        );

        List<Integer> numbers = new ArrayList<>();

        assertThrows(
                NumberFormatException.class,
                () -> {
                    for (String line : lines) {
                        numbers.add(Integer.parseInt(line));
                    }
                }
        );
    }
}