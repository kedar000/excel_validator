package org.file_validator.common.dto;

import java.time.LocalDateTime;

public class FileVersionDto {
    private Integer version;
    private String status;
    private LocalDateTime createdAt;

    public FileVersionDto(Integer version , String status , LocalDateTime createdAt) {
        this.version = version;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
