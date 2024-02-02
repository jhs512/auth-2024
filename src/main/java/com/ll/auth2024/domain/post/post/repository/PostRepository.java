package com.ll.auth2024.domain.post.post.repository;

import com.ll.auth2024.domain.member.member.entity.Member;
import com.ll.auth2024.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    Optional<Post> findTop1ByAuthorAndTitleOrderByIdDesc(Member author, String title);
}
