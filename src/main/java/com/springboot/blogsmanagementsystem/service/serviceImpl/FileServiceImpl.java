package com.springboot.blogsmanagementsystem.service.serviceImpl;

import com.springboot.blogsmanagementsystem.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
        // get file name
        String name = multipartFile.getOriginalFilename();

        // create random file name
        String randomString = UUID.randomUUID().toString();
        String newFileName = randomString.concat(name.substring(name.indexOf('.')));

        // create full path with image name
        String fullPath = path + File.separator + newFileName;

        // now create folder for given path if not exists
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }

        // now copy the file to our full path
        Files.copy(multipartFile.getInputStream(), Paths.get(fullPath));

        return newFileName;
    }
}
