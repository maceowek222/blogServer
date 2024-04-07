package com.codeElevate.blogServer.service;

import com.codeElevate.blogServer.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
   Image getImage(String filename);

   Image save(MultipartFile file) throws Exception;
}
