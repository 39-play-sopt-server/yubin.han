package org.sopt;

import java.util.ArrayList;
import java.util.List;

public class PostController {
    private final List<Post> posts = new ArrayList<>();
    private final PostView view;

    public PostController(PostView view) {
        this.view = view;
    }

    public void run() {
        while (true) {
            int command = view.inputCommand();

            switch (command) {
                case 1 -> createPost();
                case 2 -> readPosts();
                case 3 -> readPost();
                case 4 -> updatePost();
                case 5 -> deletePost();
                case 6 -> {
                    view.printMessage("프로그램을 종료합니다.");
                    return;
                }
                default -> view.printMessage("잘못된 입력입니다.");
            }
        }
    }

    private void createPost() {
        String title = view.inputTitle();
        String content = view.inputContent();

        posts.add(new Post(title, content));

        view.printMessage("게시글이 작성되었습니다.");
    }

    private void readPosts() {
        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }
        view.printPosts(posts);
    }

    private void readPost() {
        Post post = findPost("조회");
        if (post == null) {
            return;
        }
        view.printPost(post);
    }

    private void updatePost() {
        Post post = findPost("수정");
        if (post == null) {
            return;
        }

        String newTitle = view.inputNewTitle();
        String newContent = view.inputNewContent();
        post.update(newTitle, newContent);

        view.printMessage("게시글이 수정되었습니다.");
    }

    private void deletePost() {
        Post post = findPost("삭제");
        if (post == null) {
            return;
        }

        posts.remove(post);

        view.printMessage("게시글이 삭제되었습니다.");
    }

    private Post findPost(String action) {
        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return null;
        }

        int index = view.inputPostIndex(action);
        if (index < 0 || index >= posts.size()) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return null;
        }
        return posts.get(index);
    }
}