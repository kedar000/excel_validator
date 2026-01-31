package org.file_validator.storage.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "validation_errors")
public class ValidationErrorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "validated_file_id" , nullable = false)
    private ValidationErrorEntity validatedFile;

    @Column(name = "row_number" , nullable = false)
    private Integer rowNumber;

    @Column(name = "column_name" , nullable = false)
    private String columnName;

    @Column(name = "error_message" , nullable = false)
    private String errorMessage;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ValidationErrorEntity getValidatedFile() {
        return validatedFile;
    }

    public void setValidatedFile(ValidationErrorEntity validatedFile) {
        this.validatedFile = validatedFile;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
