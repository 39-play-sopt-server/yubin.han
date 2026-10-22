package org.sopt.service;

import org.sopt.domain.Category;
import org.sopt.domain.Member;
import org.sopt.domain.Post;
import org.sopt.dto.CategoryResponse;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostSummaryResponse;
import org.sopt.dto.PostUpdateRequest;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long createPost(PostCreateRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(MemberNotFoundException::new);
        Post post = new Post(
                member,
                Category.from(request.category()),
                request.title(),
                request.content()
        );
        return postRepository.save(post).getId();
    }

    public List<PostSummaryResponse> getPosts() {
        return postRepository.findAllByOrderByIdAsc().stream()
                .map(PostSummaryResponse::from)
                .toList();
    }

    @Transactional
    public PostResponse getPost(Long id) {
        Post post = findPost(id);
        post.increaseViewCount();
        return PostResponse.from(post);
    }

    @Transactional
    public void updatePost(Long id, PostUpdateRequest request) {
        Post post = findPost(id);
        post.update(Category.from(request.category()), request.title(), request.content());
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
    }

    public List<CategoryResponse> getCategories() {
        return Arrays.stream(Category.values())
                .map(CategoryResponse::from)
                .toList();
    }

    private Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }
}
