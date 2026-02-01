package org.file_validator.api;


import org.file_validator.common.dto.FileVersionDto;
import org.file_validator.services.FileService;
import org.file_validator.storage.entity.OriginalFileEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    //upload new excel file
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
        OriginalFileEntity savedFile = fileService.uploadNewFile(
                file.getOriginalFilename(),
                file.getBytes(),
                "intern-user"
        );

        return ResponseEntity.ok(savedFile.getLogicalFileId());
    }

    @GetMapping("/{logicalFileId}/download")
    public ResponseEntity<byte[]>  downloadFile(
            @PathVariable("logicalFileId") UUID logicalFileId) throws Exception{

        OriginalFileEntity file = fileService.getLatestFile(logicalFileId);

        return ResponseEntity.ok()
                .header("Content-Disposition",
                        "attachment; filename=\"" +file.getFileName() + "\"")
                .body(file.getFileBlob());

    }

    @GetMapping("/{logicalFileId}/versions")
    public ResponseEntity<List<FileVersionDto>>  listVersions(
            @PathVariable("logicalFileId") UUID logicalFileId){
        return ResponseEntity.ok(fileService.getLastThreeVersion(logicalFileId));
    }

    @PutMapping("/{logicalFileId}/update")
    public ResponseEntity<String> updateFile(
            @PathVariable("logicalFileId") UUID logicalFileId,
            @RequestParam("file") MultipartFile file) throws Exception{
        fileService.updateFile(logicalFileId, file);

        return  ResponseEntity.ok("File updated");
    }

    @GetMapping("/{logicalFileId}/versions/{version}/download")
    public ResponseEntity<byte[]>  downloadFile(
            @PathVariable("logicalFileId") UUID logicalFileId,
            @PathVariable("version") Integer version
    ){
        OriginalFileEntity file = fileService.getFileByVersion(logicalFileId , version);

                return ResponseEntity.ok()
                        .header("Content-Disposition",
                                "attachment; filename=\"" + file.getFileName() + "\"")
                        .header("Content-Type", "application/octet-stream")
                        .body(file.getFileBlob());
    }



}
