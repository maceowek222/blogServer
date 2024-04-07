package com.codeElevate.blogServer.repository;

import com.codeElevate.blogServer.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByFilename(String filename);
    boolean existsByFilename(String filename);


}
