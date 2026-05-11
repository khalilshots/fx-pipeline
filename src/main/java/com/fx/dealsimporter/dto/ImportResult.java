package com.fx.dealsimporter.dto;

import java.util.List;

public class ImportResult {

    private int importedCount;
    private int failedCount;
    private List<String> errors;

    public ImportResult(int importedCount, int failedCount, List<String> errors) {
        this.importedCount = importedCount;
        this.failedCount = failedCount;
        this.errors = errors;
    }

    public int getImportedCount() { return importedCount; }
    public int getFailedCount() { return failedCount; }
    public List<String> getErrors() { return errors; }
}
