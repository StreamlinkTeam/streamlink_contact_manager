package cl.streamlink.contact.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public final class MultipartFilesUtils {

    private MultipartFilesUtils() {

    }

    public static void removeFile(String basePath, String fileName) {

        File file = new File(basePath, fileName);
        file.delete();
    }

    public static File saveMultipartFile(String basePath, String fileName, MultipartFile multipartFile) throws IOException {

        multipartFile.transferTo(new File(basePath, fileName));
        return new File(basePath, fileName);
    }
}
