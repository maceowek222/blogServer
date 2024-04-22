package com.codeElevate.blogServer.controller;

import com.codeElevate.blogServer.entity.Post;
import com.codeElevate.blogServer.response.SaveResult;
import com.codeElevate.blogServer.service.ImageService;
import com.codeElevate.blogServer.service.LoginVerify;
import com.codeElevate.blogServer.service.PostService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;
    private final ImageService imageService;

    public PostController(PostService postService, ImageService imageService) {
        this.postService = postService;
        this.imageService = imageService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            Post createdPost = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/login")
    public Boolean login(@RequestBody UserData userData){
        return LoginVerify.verify(userData.getUsername(), userData.getPassword());
    }
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> retrieve(@PathVariable String filename) {
      var image = imageService.getImage(filename);
      var body = new ByteArrayResource(image.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getMimeType())
                .body(body);
    }

    @PostMapping("/images/db/upload")
    public SaveResult upload(@RequestPart MultipartFile file) {
        try {
            var image = imageService.save(file);
            return SaveResult.builder()
                    .error(false)
                    .filename(image.getFilename())
                    .link(createImageLink(image.getFilename()))
                    .build();
        } catch (Exception e) {
            return SaveResult.builder().error(true).filename(file.getOriginalFilename()).build();
        }

    }
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    private String createImageLink(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/images/"+fileName)
                .toUriString();
    }

}