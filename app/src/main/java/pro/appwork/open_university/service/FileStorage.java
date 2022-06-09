package pro.appwork.open_university.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorage {
    void upload(Path path, MultipartFile file);

    Resource download(Path path);

    boolean exists(Path path);

    boolean notExists(Path path);

    void delete(Path path);
}
