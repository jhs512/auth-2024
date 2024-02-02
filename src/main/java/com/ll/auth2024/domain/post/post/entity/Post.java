package com.ll.auth2024.domain.post.post.entity;

import com.ll.auth2024.domain.member.member.entity.Member;
import com.ll.auth2024.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Post extends BaseTime {
    @ManyToOne(fetch = LAZY)
    private Member author;
    private String title;
}