package com.codeElevate.blogServer.service;
import com.codeElevate.blogServer.entity.Image;
import com.codeElevate.blogServer.exception.ImageNotFoundException;
import com.codeElevate.blogServer.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image getImage(String filename) {
        return imageRepository.findByFilename(filename)
                .orElseThrow(ImageNotFoundException::new);
    }

    @Override
    public Image save(MultipartFile file) throws Exception {
        if (imageRepository.existsByFilename(file.getOriginalFilename())) {
            return Image.builder().filename(file.getOriginalFilename()).build();
        }

        var image = Image.builder()
                .filename(file.getOriginalFilename())
                .mimeType(file.getContentType())
                .data(file.getBytes())
                .build();

        return imageRepository.save(image);
    }
}
