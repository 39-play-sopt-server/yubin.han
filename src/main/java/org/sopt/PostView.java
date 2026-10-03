package org.sopt;

import java.util.List;
import java.util.Scanner;

public class PostView {
    private final Scanner scanner = new Scanner(System.in);

    public int inputCommand() {
        System.out.print("\n=== 게시판 ===");
        System.out.print("1. 게시글 작성");
        System.out.print("2. 게시글 목록 조회");
        System.out.print("3. 게시글 단건 조회");
        System.out.print("4. 게시글 수정");
        System.out.print("5. 게시글 삭제");
        System.out.print("6. 종료");
        System.out.print("선택: ");
        return inputNumber();
    }

    public String inputTitle() {
        System.out.print("제목: ");
        return scanner.nextLine();
    }

    public String inputContent() {
        System.out.print("내용: ");
        return scanner.nextLine();
    }

    public String inputNewTitle() {
        System.out.print("새로운 제목: ");
        return scanner.nextLine();
    }

    public String inputNewContent() {
        System.out.print("새로운 내용: ");
        return scanner.nextLine();
    }

    public int inputPostIndex(String action) {
        System.out.print(action + "할 게시글 번호: ");
        return inputNumber() - 1;
    }

    public void printPosts(List<Post> posts) {
        System.out.println("\n=== 게시글 목록 ===");
        for (int i = 0; i < posts.size(); i++) {
            System.out.println((i + 1) + ". " + posts.get(i).getTitle());
        }
    }

    public void printPost(Post post) {
        System.out.print("\n=== 게시글 ===");
        System.out.print("제목: " + post.getTitle());
        System.out.print("내용: " + post.getContent());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    private int inputNumber() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}