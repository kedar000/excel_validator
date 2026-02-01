package org.file_validator.storage.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "original_files")
public class OriginalFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //version of the same file
    @Column(name = "logical_file_id" , nullable = false)
    private UUID logicalFileId;

    @Column(name = "file_name" , nullable = false)
    private String fileName;

    //excel file stored in bits
    @Lob //large obj
    @Column(name = "file_blob" , nullable = false)
    private byte[] fileBlob;

    @Column(name = "version" , nullable = false)
    private Integer version;

    @Column(name = "status" , nullable = false)
    private String status;

    @Column(name = "uploaded_by")
    private String uploadedBy;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getLogicalFileId() {
        return logicalFileId;
    }

    public void setLogicalFileId(UUID logicalFileId) {
        this.logicalFileId = logicalFileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileBlob() {
        return fileBlob;
    }

    public void setFileBlob(byte[] fileBlob) {
        this.fileBlob = fileBlob;
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

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OriginalFileEntity{" +
                "id=" + id +
                ", logicalFileId=" + logicalFileId +
                ", fileName='" + fileName + '\'' +
                ", fileBlob=" + Arrays.toString(fileBlob) +
                ", version=" + version +
                ", status='" + status + '\'' +
                ", uploadedBy='" + uploadedBy + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
