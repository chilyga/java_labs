package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class SampleFileLoader {

    private static final String EXPECTED_HEADER =
            "id;label;source;featureCount;status";

    public LoadResult load(Path path) {
        List<String> lines;

        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException | SecurityException cause) {
            throw new DataLoadingException("Cannot read " + path, cause);
        }

        if (lines.isEmpty()) {
            throw new DataLoadingException(
                    "Invalid file header: " + path,
                    new IllegalArgumentException("File is empty")
            );
        }

        if (!EXPECTED_HEADER.equals(lines.get(0).trim())) {
            throw new DataLoadingException(
                    "Invalid file header: " + path,
                    new IllegalArgumentException("Unexpected header")
            );
        }

        List<Sample> samples = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.isBlank()) {
                continue;
            }

            parseLine(line, i + 1, samples, errors);
        }

        return new LoadResult(samples, errors);
    }

    private void parseLine(
            String line,
            int lineNumber,
            List<Sample> samples,
            List<ValidationError> errors
    ) {
        String[] fields = line.split(";", -1);

        if (fields.length != 5) {
            errors.add(new ValidationError(
                    lineNumber,
                    "columns",
                    "Expected 5 fields, but got " + fields.length
            ));
            return;
        }

        String id = fields[0].trim();
        String label = fields[1].trim();
        String source = fields[2].trim();
        String featureCountText = fields[3].trim();
        String statusText = fields[4].trim();

        boolean valid = true;

        if (id.isEmpty()) {
            errors.add(new ValidationError(
                    lineNumber, "id", "ID cannot be empty"
            ));
            valid = false;
        }

        if (label.isEmpty()) {
            errors.add(new ValidationError(
                    lineNumber, "label", "Label cannot be empty"
            ));
            valid = false;
        }

        if (source.isEmpty()) {
            errors.add(new ValidationError(
                    lineNumber, "source", "Source cannot be empty"
            ));
            valid = false;
        }

        Integer featureCount = null;

        if (featureCountText.isEmpty()) {
            errors.add(new ValidationError(
                    lineNumber, "featureCount", "Feature count cannot be empty"
            ));
            valid = false;
        } else {
            try {
                featureCount = Integer.parseInt(featureCountText);

                if (featureCount <= 0) {
                    errors.add(new ValidationError(
                            lineNumber,
                            "featureCount",
                            "Feature count must be positive"
                    ));
                    valid = false;
                }
            } catch (NumberFormatException exception) {
                errors.add(new ValidationError(
                        lineNumber,
                        "featureCount",
                        "Feature count must be an integer"
                ));
                valid = false;
            }
        }

        SampleStatus status = null;

        if (statusText.isEmpty()) {
            errors.add(new ValidationError(
                    lineNumber, "status", "Status cannot be empty"
            ));
            valid = false;
        } else {
            try {
                status = SampleStatus.valueOf(
                        statusText.toUpperCase(Locale.ROOT)
                );
            } catch (IllegalArgumentException exception) {
                errors.add(new ValidationError(
                        lineNumber,
                        "status",
                        "Unknown status: " + statusText
                ));
                valid = false;
            }
        }

        if (valid) {
            samples.add(new Sample(
                    id,
                    label,
                    source,
                    featureCount,
                    status
            ));
        }
    }
}