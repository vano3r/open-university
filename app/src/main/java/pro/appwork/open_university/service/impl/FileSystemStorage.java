package pro.appwork.open_university.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.service.FileStorage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileSystemStorage implements FileStorage {
    private final Path ROOT = Paths.get("data");

    @Override
    public void upload(Path path, MultipartFile file) {
        try {
            path = ROOT.resolve(path);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            log.info("Success uploading file - {}, to path - {}", file.getOriginalFilename(), path);
        } catch (IOException e) {
            log.error("Could not store the file - {}", file, e);
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource download(Path path) {
        try {
            path = ROOT.resolve(path);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                log.info("Download file - {}", resource.getFilename());
                return resource;
            }

            throw new RuntimeException("Could not read the file!");

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean exists(Path path) {
        return Files.exists(ROOT.resolve(path));
    }

    @Override
    public boolean notExists(Path path) {
        return Files.notExists(ROOT.resolve(path));
    }

    @Override
    public void delete(Path path) {
        try {
            Files.delete(ROOT.resolve(path));
        } catch (IOException e) {
            throw new RuntimeException("Could not delete the file! " + e.getMessage());
        }
    }
}
