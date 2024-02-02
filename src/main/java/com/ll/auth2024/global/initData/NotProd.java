package com.ll.auth2024.global.initData;

import com.ll.auth2024.domain.member.member.entity.Member;
import com.ll.auth2024.domain.member.member.service.MemberService;
import com.ll.auth2024.domain.post.post.entity.Post;
import com.ll.auth2024.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Profile("!prod")
@Configuration
@Slf4j
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;
    private final PostService postService;

    @Bean
    @Order(3)
    public ApplicationRunner initNotProd() {
        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) {
                if (memberService.findByUsername("user1").isPresent()) return;

                Member memberUser1 = memberService.join("user1", "1234").getData();
                memberUser1.setRefreshToken("user1");

                Member memberUser2 = memberService.join("user2", "1234").getData();
                memberUser2.setRefreshToken("user2");

                Member memberUser3 = memberService.join("user3", "1234").getData();
                memberUser3.setRefreshToken("user3");

                Member memberUser4 = memberService.join("user4", "1234").getData();
                memberUser4.setRefreshToken("user4");

                Post post1 = postService.write(memberUser1, "제목 1");
                Post post2 = postService.write(memberUser1, "제목 2");
                Post post3 = postService.write(memberUser1, "제목 3");
                Post post4 = postService.write(memberUser1, "제목 4");
                Post post5 = postService.write(memberUser2, "제목 5");
                Post post6 = postService.write(memberUser2, "제목 6");

                IntStream.rangeClosed(7, 1200).forEach(i -> {
                    postService.write(memberUser3, "제목 " + i);
                });
            }
        };
    }
}
