package org.file_validator.storage.repository;


import org.file_validator.storage.entity.OriginalFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//repo interface
public interface OriginalFileRepository
        extends JpaRepository<OriginalFileEntity, Long> {

    List<OriginalFileEntity> findByLogicalFileIdOrderByVersionDesc(UUID logicalFileId);
    OriginalFileEntity findTopByLogicalFileIdOrderByVersionDesc(UUID logicalFileId);
    List<OriginalFileEntity> findByLogicalFileIdOrderByVersionAsc(UUID logicalFileId);
    OriginalFileEntity findByLogicalFileIdAndVersion(UUID logicalFileId, Integer version);
}