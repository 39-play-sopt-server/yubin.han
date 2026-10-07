package org.sopt.view;

import org.sopt.domain.Category;
import org.sopt.exception.ErrorMessage;
import org.sopt.exception.InvalidInputException;

import java.util.Scanner;

public class InputView {
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

    public int inputCategory() {
        StringBuilder builder = new StringBuilder("카테고리 (");
        for (Category category : Category.values()) {
            builder.append(category.getNumber()).append(".").append(category.getDisplayName()).append(" ");
        }
        System.out.print(builder.toString().trim() + "): ");
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

    public String inputAuthor() {
        System.out.print("작성자: ");
        return scanner.nextLine();
    }

    public long inputPostId(String action) {
        System.out.print(action + "할 게시글 번호: ");
        return inputNumber();
    }

    private int inputNumber() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_NUMBER);
        }
    }
}
