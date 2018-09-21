package com.bookstore.bean;

public class DownloadFile {

    private Long id;
    private String fileUri;
    private String filename;

    public DownloadFile() {
    }

    public DownloadFile(String fileUri, String filename) {
        this.fileUri = fileUri;
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
