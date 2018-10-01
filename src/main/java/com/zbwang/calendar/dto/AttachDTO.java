package com.zbwang.calendar.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by bobomeilin on 2018/8/16.
 */
public class AttachDTO {

    private MultipartFile file;

    private String fileName;

    private Long fileLen;

    private String belongType;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
    }

    public Long getFileLen() {
        return fileLen;
    }

    public void setFileLen(Long fileLen) {
        this.fileLen = fileLen;
    }

    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
    }

    @Override
    public String toString() {
        return "AttachDTO{" +
                "file=" + file +
                ", filename='" + fileName + '\'' +
                ", fileLen=" + fileLen +
                ", belongType='" + belongType + '\'' +
                '}';
    }
}
