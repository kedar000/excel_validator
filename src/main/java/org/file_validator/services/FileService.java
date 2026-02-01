package org.file_validator.services;

import org.file_validator.common.dto.FileVersionDto;
import org.file_validator.storage.entity.OriginalFileEntity;
import org.file_validator.storage.repository.OriginalFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {


    private OriginalFileRepository originalFileRepository;

    public FileService(OriginalFileRepository originalFileRepository) {
        this.originalFileRepository = originalFileRepository;
    }


    //when a brand-new file is created
    public OriginalFileEntity uploadNewFile(
                                    String fileName,
                                    byte[] fileBytes,
                                    String uploadedBy){

        OriginalFileEntity entity = new OriginalFileEntity();

        entity.setLogicalFileId(UUID.randomUUID());
        entity.setFileName(fileName);
        entity.setFileBlob(fileBytes);
        entity.setVersion(1);
        entity.setStatus("ACTIVE");
        entity.setUploadedBy(uploadedBy);
        entity.setCreatedAt(LocalDateTime.now());

        return originalFileRepository.save(entity);

    }


    //new version of existing file
    public OriginalFileEntity uploadNewversion(
                                    UUID logicalFileId,
                                    String fileName,
                                    byte[] fileBytes,
                                    String uploadedBy){

        List<OriginalFileEntity> existingVersion = originalFileRepository.findByLogicalFileIdOrderByVersionDesc(logicalFileId);

        if(existingVersion.isEmpty()){
            throw new IllegalArgumentException("File not found");
        }

        OriginalFileEntity latest = existingVersion.get(0);

        //mark as superSeded
        latest.setStatus("SUPERSEDED");
        originalFileRepository.save(latest);

        OriginalFileEntity newVersion = new OriginalFileEntity();
        newVersion.setLogicalFileId(logicalFileId);
        newVersion.setFileName(fileName);
        newVersion.setFileBlob(fileBytes);
        newVersion.setVersion(latest.getVersion() + 1);
        newVersion.setStatus("ACTIVE");
        newVersion.setUploadedBy(uploadedBy);
        newVersion.setCreatedAt(LocalDateTime.now());

        return originalFileRepository.save(newVersion);


    }


    @Transactional(readOnly = true)
    public OriginalFileEntity getLatestFile(UUID logicalFileId){

        System.out.println("DEBUG: Entered getLatestFile()");
        System.out.println("DEBUG: logicalFileId = " + logicalFileId);

        OriginalFileEntity entity =
                originalFileRepository
                        .findTopByLogicalFileIdOrderByVersionDesc(logicalFileId);

        System.out.println("DEBUG: entity = " + entity);

        if (entity == null) {
            throw new RuntimeException(
                    "File not found for logicalFileId=" + logicalFileId
            );
        }

        System.out.println("DEBUG: entity id = " + entity.getId());
        System.out.println("DEBUG: entity filename = " + entity.getFileName());
        System.out.println("DEBUG: entity blob is null? " + (entity.getFileBlob() == null));

        return entity;
    }

    @Transactional(readOnly = true)
    public List<FileVersionDto> getLastThreeVersion(UUID logicalFileId){
        List<OriginalFileEntity> version = originalFileRepository.findByLogicalFileIdOrderByVersionDesc(logicalFileId);

        if(version.isEmpty()){
            throw new RuntimeException("File not found for logicalFileId=" + logicalFileId);
        }

        return version.stream()
                .limit(3)
                .map(file -> new FileVersionDto(
                        file.getVersion(),
                        file.getStatus(),
                        file.getCreatedAt()
                ))
                .toList();
    }
}
