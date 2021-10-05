package com.admin.bootstudy.aws.controller;

import com.admin.bootstudy.aws.domain.posts.Posts;
import com.admin.bootstudy.aws.dto.posts.PostSaveRequestDto;
import com.admin.bootstudy.aws.dto.posts.PostUpdateRequestDto;
import com.admin.bootstudy.aws.dto.posts.PostsResponseDto;
import com.admin.bootstudy.aws.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        System.out.println(requestDto);
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
