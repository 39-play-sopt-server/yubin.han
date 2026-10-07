package org.sopt;

import org.sopt.controller.PostController;
import org.sopt.repository.PostRepository;
import org.sopt.service.PostService;
import org.sopt.view.InputView;
import org.sopt.view.OutputView;

public class Main {

    public static void main(String[] args) {
        PostRepository postRepository = new PostRepository();
        PostService postService = new PostService(postRepository);
        PostController controller = new PostController(postService, new InputView(), new OutputView());
        controller.run();
    }
}
