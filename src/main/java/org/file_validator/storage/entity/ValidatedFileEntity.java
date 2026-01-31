package org.file_validator.storage.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "validated_files")
public class ValidatedFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //map to original file
    @ManyToOne
    @JoinColumn(name = "original_file_id" , nullable = false)
    private OriginalFileEntity originalFile;

    @Column(name = "original_version" , nullable = false)
    private Integer originalVersion;

    @Lob
    @Column(name = "validated_blob" , nullable = false)
    private byte[] validatedBlob;

    @Column(name = "validation_status" , nullable = false)
    private String validationStatus;

    @Column(name = "error_count")
    private Integer errorCount;

    @Column(name = "validated_at" , nullable = false)
    private LocalDateTime validatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OriginalFileEntity getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(OriginalFileEntity originalFile) {
        this.originalFile = originalFile;
    }

    public Integer getOriginalVersion() {
        return originalVersion;
    }

    public void setOriginalVersion(Integer originalVersion) {
        this.originalVersion = originalVersion;
    }

    public byte[] getValidatedBlob() {
        return validatedBlob;
    }

    public void setValidatedBlob(byte[] validatedBlob) {
        this.validatedBlob = validatedBlob;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public LocalDateTime getValidatedAt() {
        return validatedAt;
    }

    public void setValidatedAt(LocalDateTime validatedAt) {
        this.validatedAt = validatedAt;
    }
}
