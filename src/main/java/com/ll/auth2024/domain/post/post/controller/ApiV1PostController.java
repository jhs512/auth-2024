package com.ll.auth2024.domain.post.post.controller;

import com.ll.auth2024.domain.member.member.service.MemberService;
import com.ll.auth2024.domain.post.post.dto.PostDto;
import com.ll.auth2024.domain.post.post.entity.Post;
import com.ll.auth2024.domain.post.post.service.PostService;
import com.ll.auth2024.global.app.AppConfig;
import com.ll.auth2024.global.exceptions.GlobalException;
import com.ll.auth2024.global.rq.Rq;
import com.ll.auth2024.global.rsData.RsData;
import com.ll.auth2024.standard.base.KwTypeV1;
import com.ll.auth2024.standard.base.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/v1/posts", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "ApiV1PostController", description = "글 CRUD 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiV1PostController {
    private final PostService postService;
    private final MemberService memberService;
    private final Rq rq;


    public record MakeTempResponseBody(@NonNull PostDto item) {
    }

    @Transactional
    @PostMapping(value = "/temp", consumes = ALL_VALUE)
    @Operation(summary = "임시 글 생성")
    public RsData<MakeTempResponseBody> makeTemp() {
        RsData<Post> findTempOrMakeRsData = postService.findTempOrMake(rq.getMember());

        return findTempOrMakeRsData.newDataOf(
                new MakeTempResponseBody(
                        postToDto(findTempOrMakeRsData.getData())
                )
        );
    }


    public record GetPostsResponseBody(@NonNull PageDto<PostDto> itemPage) {
    }

    @GetMapping(value = "", consumes = ALL_VALUE)
    @Operation(summary = "글 다건조회")
    public RsData<GetPostsResponseBody> getPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "ALL") KwTypeV1 kwType
    ) {

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));
        Page<Post> itemPage = postService.findByKw(kwType, kw, null, pageable);

        Page<PostDto> _itemPage = itemPage.map(this::postToDto);

        return RsData.of(
                new GetPostsResponseBody(
                        new PageDto<>(_itemPage)
                )
        );
    }


    public record GetPostResponseBody(@NonNull PostDto item) {
    }

    @GetMapping(value = "/{id}", consumes = ALL_VALUE)
    @Operation(summary = "글 단건조회")
    public RsData<GetPostResponseBody> getPost(
            @PathVariable long id
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canRead(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        PostDto dto = postToDto(post);

        return RsData.of(
                new GetPostResponseBody(dto)
        );
    }


    public record EditRequestBody(
            @NotBlank String title
    ) {
    }

    public record EditResponseBody(@NonNull PostDto item) {
    }

    @PutMapping("/{id}")
    @Operation(summary = "글 편집")
    @Transactional
    public RsData<EditResponseBody> edit(
            @PathVariable long id,
            @Valid @RequestBody EditRequestBody requestBody
    ) {
        Post post = postService.findById(id).orElseThrow(GlobalException.E404::new);

        if (!postService.canEdit(rq.getMember(), post))
            throw new GlobalException("403-1", "권한이 없습니다.");

        postService.edit(post, requestBody.title);

        return RsData.of(
                "%d번 글이 수정되었습니다.".formatted(id),
                new EditResponseBody(postToDto(post))
        );
    }

    private PostDto postToDto(Post post) {
        PostDto dto = new PostDto(post);

        return dto;
    }
}
