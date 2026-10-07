package org.sopt;

import org.sopt.common.ApiResponse;
import org.sopt.config.AppConfig;
import org.sopt.controller.PostController;
import org.sopt.dto.CategoryResponse;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostUpdateRequest;
import org.sopt.view.InputView;
import org.sopt.view.OutputView;

import java.util.List;
import java.util.function.Consumer;

/**
 * 클라이언트. 서버(PostController)에 요청을 보내고 ApiResponse를 받아 View로 출력한다.
 */
public class Main {
    private final PostController server;
    private final InputView inputView;
    private final OutputView outputView;

    public Main(PostController server, InputView inputView, OutputView outputView) {
        this.server = server;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static void main(String[] args) {
        new Main(new AppConfig().postController(), new InputView(), new OutputView()).run();
    }

    public void run() {
        while (true) {
            switch (inputView.inputCommand()) {
                case 1 -> createPost();
                case 2 -> readPosts();
                case 3 -> readPost();
                case 4 -> updatePost();
                case 5 -> deletePost();
                case 6 -> {
                    outputView.printMessage("프로그램을 종료합니다.");
                    return;
                }
                default -> outputView.printMessage("잘못된 메뉴 선택입니다.");
            }
        }
    }

    private void createPost() {
        ApiResponse<List<CategoryResponse>> categories = server.getCategories();
        if (isFailed(categories)) {
            return;
        }
        int category = inputView.inputCategory(categories.data());
        String title = inputView.inputTitle();
        String content = inputView.inputContent();
        String author = inputView.inputAuthor();

        ApiResponse<Long> response = server.createPost(new PostCreateRequest(category, title, content, author));
        handleResponse(response, id -> outputView.printMessage(id + "번 " + response.message()));
    }

    private void readPosts() {
        handleResponse(server.getPosts(), outputView::printPosts);
    }

    private void readPost() {
        long id = inputView.inputPostId("조회");
        handleResponse(server.getPost(id), outputView::printPost);
    }

    private void updatePost() {
        long id = inputView.inputPostId("수정");
        ApiResponse<List<CategoryResponse>> categories = server.getCategories();
        if (isFailed(server.checkPostExists(id)) || isFailed(categories)) {
            return;
        }
        int category = inputView.inputCategory(categories.data());
        String title = inputView.inputTitle();
        String content = inputView.inputContent();

        ApiResponse<Void> response = server.updatePost(id, new PostUpdateRequest(category, title, content));
        handleResponse(response, ignored -> outputView.printMessage(response.message()));
    }

    private void deletePost() {
        long id = inputView.inputPostId("삭제");
        ApiResponse<Void> response = server.deletePost(id);
        handleResponse(response, ignored -> outputView.printMessage(response.message()));
    }

    private <T> void handleResponse(ApiResponse<T> response, Consumer<T> onSuccess) {
        if (isFailed(response)) {
            return;
        }
        onSuccess.accept(response.data());
    }

    private boolean isFailed(ApiResponse<?> response) {
        if (response.success()) {
            return false;
        }
        outputView.printError(response.status(), response.message());
        return true;
    }
}
