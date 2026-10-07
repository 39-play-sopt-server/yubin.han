package org.sopt.controller;

import org.sopt.domain.Category;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostUpdateRequest;
import org.sopt.exception.BoardException;
import org.sopt.exception.ErrorMessage;
import org.sopt.exception.InvalidInputException;
import org.sopt.service.PostService;
import org.sopt.view.InputView;
import org.sopt.view.OutputView;

/**
 * 사용자의 메뉴 선택에 따라 흐름만 제어한다.
 * 실제 로직은 Service에, 입출력은 View에 맡긴다.
 */
public class PostController {
    private static final int EXIT = 6;

    private final PostService postService;
    private final InputView inputView;
    private final OutputView outputView;

    public PostController(PostService postService, InputView inputView, OutputView outputView) {
        this.postService = postService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            try {
                int command = inputView.inputCommand();
                if (command == EXIT) {
                    outputView.printMessage("프로그램을 종료합니다.");
                    return;
                }
                handle(command);
            } catch (BoardException e) {
                // 하위 계층에서 던진 예외를 한 곳에서 잡아 사용자에게 보여준다.
                outputView.printError(e.getMessage());
            }
        }
    }

    private void handle(int command) {
        switch (command) {
            case 1 -> createPost();
            case 2 -> readPosts();
            case 3 -> readPost();
            case 4 -> updatePost();
            case 5 -> deletePost();
            default -> throw new InvalidInputException(ErrorMessage.INVALID_COMMAND);
        }
    }

    private void createPost() {
        Category category = Category.from(inputView.inputCategory());
        String title = inputView.inputTitle();
        String content = inputView.inputContent();
        String author = inputView.inputAuthor();

        Long id = postService.createPost(new PostCreateRequest(category, title, content, author));
        outputView.printMessage(id + "번 게시글이 작성되었습니다.");
    }

    private void readPosts() {
        outputView.printPosts(postService.getPosts());
    }

    private void readPost() {
        long id = inputView.inputPostId("조회");
        outputView.printPost(postService.getPost(id));
    }

    private void updatePost() {
        long id = inputView.inputPostId("수정");
        postService.validatePostExists(id); // 없는 글이면 내용 입력 전에 바로 알려준다
        Category category = Category.from(inputView.inputCategory());
        String title = inputView.inputTitle();
        String content = inputView.inputContent();

        postService.updatePost(id, new PostUpdateRequest(category, title, content));
        outputView.printMessage("게시글이 수정되었습니다.");
    }

    private void deletePost() {
        long id = inputView.inputPostId("삭제");
        postService.deletePost(id);
        outputView.printMessage("게시글이 삭제되었습니다.");
    }
}
