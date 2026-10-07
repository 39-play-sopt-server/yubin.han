package org.sopt.view;

import org.sopt.dto.CategoryResponse;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int inputCommand() {
        System.out.println("\n=== 게시판 ===");
        System.out.println("1. 게시글 작성");
        System.out.println("2. 게시글 목록 조회");
        System.out.println("3. 게시글 단건 조회");
        System.out.println("4. 게시글 수정");
        System.out.println("5. 게시글 삭제");
        System.out.println("6. 종료");
        return inputNumber("선택: ");
    }

    public int inputCategory(List<CategoryResponse> categories) {
        String options = categories.stream()
                .map(category -> category.number() + "." + category.name())
                .collect(Collectors.joining(" "));
        return inputNumber("카테고리 (" + options + "): ");
    }

    public String inputTitle() {
        System.out.print("제목: ");
        return scanner.nextLine();
    }

    public String inputContent() {
        System.out.print("내용: ");
        return scanner.nextLine();
    }

    public String inputAuthor() {
        System.out.print("작성자: ");
        return scanner.nextLine();
    }

    public long inputPostId(String action) {
        return inputNumber(action + "할 게시글 번호: ");
    }

    private int inputNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
}
