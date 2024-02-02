package com.ll.auth2024.domain.post.post.repository;

import com.ll.auth2024.domain.member.member.entity.Member;
import com.ll.auth2024.domain.post.post.entity.Post;
import com.ll.auth2024.standard.base.KwTypeV1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> findByKw(KwTypeV1 kwType, String kw, Member author, Pageable pageable);
}
