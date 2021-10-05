package com.admin.bootstudy.aws.repository;

import com.admin.bootstudy.aws.domain.posts.Posts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("brand13@naver.com")
                        .build()
        );

        //When
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2021,9,29,0,0,0);
        postsRepository.save(
                Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate="+posts.getCreatedData()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedData()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}