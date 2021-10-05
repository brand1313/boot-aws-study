package com.admin.bootstudy.aws.service;

import com.admin.bootstudy.aws.domain.posts.Posts;
import com.admin.bootstudy.aws.dto.posts.PostSaveRequestDto;
import com.admin.bootstudy.aws.dto.posts.PostUpdateRequestDto;
import com.admin.bootstudy.aws.dto.posts.PostsListResponseDto;
import com.admin.bootstudy.aws.dto.posts.PostsResponseDto;
import com.admin.bootstudy.aws.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Posts posts =
                postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));

        //더티체킹
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDecs() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
