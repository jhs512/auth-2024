package com.ll.auth2024.domain.post.post.service;

import com.ll.auth2024.domain.member.member.entity.Member;
import com.ll.auth2024.domain.post.post.entity.Post;
import com.ll.auth2024.domain.post.post.repository.PostRepository;
import com.ll.auth2024.global.rsData.RsData;
import com.ll.auth2024.standard.base.KwTypeV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post write(Member author, String title) {
        Post post = Post.builder()
                .author(author)
                .title(title)
                .build();

        postRepository.save(post);

        return post;
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public void edit(Post post, String title) {
        post.setTitle(title);
    }

    public boolean canRead(Member actor, Post post) {
        return true;
    }

    public boolean canEdit(Member actor, Post post) {
        if (actor == null) return false;
        if (post == null) return false;

        return actor.equals(post.getAuthor()); // 무조건 본인만 가능
    }

    @Transactional
    public RsData<Post> findTempOrMake(Member author) {
        AtomicBoolean isNew = new AtomicBoolean(false);

        Post post = postRepository.findTop1ByAuthorAndTitleOrderByIdDesc(
                author,
                "임시글"
        ).orElseGet(() -> {
            isNew.set(true);
            return write(author, "임시글");
        });

        return RsData.of(
                isNew.get() ? "임시글이 생성되었습니다." : "%d번 임시글을 불러왔습니다.".formatted(post.getId()),
                post
        );
    }

    public Page<Post> findByKw(KwTypeV1 kwType, String kw, Member author, Pageable pageable) {
        return postRepository.findByKw(kwType, kw, author, pageable);
    }
}
