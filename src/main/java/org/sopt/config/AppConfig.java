package org.sopt.config;

import org.sopt.controller.PostController;
import org.sopt.repository.PostRepository;
import org.sopt.service.PostService;

public class AppConfig {
    private final PostRepository postRepository = new PostRepository();
    private final PostService postService = new PostService(postRepository);
    private final PostController postController = new PostController(postService);

    public PostController postController() {
        return postController;
    }
}
