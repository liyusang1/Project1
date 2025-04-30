package controller;

import model.dto.LendingBookDto;
import service.LendingBookService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class LendingBookController {
    private final LendingBookService lendingBookService;

    public LendingBookController() {
        this.lendingBookService = new LendingBookService();
    }

    // 대출처리
    public void insertLending(Long bookId, Long userId, LocalDateTime dueDate) {
        int result = lendingBookService.insertLending(bookId, userId, dueDate);
        if (result == 1) {
            System.out.println("🎉 대출 성공");
        } else if (result == 0) {
            System.out.println("⚠️ 대출 실패");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 반납 처리
    public void returnBook(Long lendingId, Long userId) {
        int result = lendingBookService.returnBook(lendingId, userId);
        if (result == 1) {
            System.out.println("🎉 반납 성공");
        } else if (result == 0) {
            System.out.println("⚠️ 반납 실패");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 대출 목록 조회
    public void getAllLending(Long userId) {
        List<LendingBookDto> lendingBookDtoList = lendingBookService.getLendingList(userId);
        if (lendingBookDtoList.isEmpty()) {
            System.out.println("⚠️ 대출 목록이 없습니다!");
        }
        lendingBookDtoList.forEach(g -> System.out.printf("🎉 대출번호 : %d -> 책 이름 : %s, 저자 : %s, 출판사:  %s\n",
                g.getLendingId(), g.getTitle(), g.getAuthor(), g.getPublisher()));
    }

    // 연체액 조회
    public void getAllLateFee(Long userId) {

        int lateEee = lendingBookService.getAllLateFee(userId);
        System.out.printf("🎉 납부해야할 연체 벌금 : %d\n", lateEee);
    }

    //책 대출/반납 통합 시스템 -> 로그인한 사용자의 userid를 받는다
    public void lendBookSystem(Long userId) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("========================================");
            System.out.println("📚           대출 및 반납 기능           ");
            System.out.println("----------------------------------------");
            System.out.println("📖  1 : 대출");
            System.out.println("📕  2 : 반납");
            System.out.println("📘  3 : 대출 목록 조회");
            System.out.println("📕  4 : 연체액 조회");
            System.out.println("📙  exit : 종료");
            System.out.println("----------------------------------------");
            System.out.println("⌨️  명령어를 입력해 주세요.");
            System.out.println("========================================");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("⚠️ 대출 및 반납 기능을 종료 합니다.");
                break;
            } else if (input.equals("1")) {
                System.out.println("\uD83D\uDCD9 대여 하실 책 번호를 입력해 주세요. : ");
                Long bookId = scanner.nextLong();
                scanner.nextLine(); // 버퍼에 남은 줄바꿈 제거

                insertLending(bookId, userId, LocalDateTime.now().plusDays(7));
            } else if (input.equals("2")) {
                System.out.println("\uD83D\uDCD5 반납처리 하실 대여 번호를 입력해 주세요. : ");
                Long lendingId = scanner.nextLong();
                scanner.nextLine(); // 버퍼에 남은 줄바꿈 제거

                returnBook(lendingId, userId);
            } else if (input.equals("3")) {
                System.out.println("\uD83D\uDCD8 대출 목록을 출력합니다.");
                getAllLending(userId);
            } else if (input.equals("4")) {
                getAllLateFee(userId);
            }
        }
    }
}
