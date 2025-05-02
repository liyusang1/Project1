package controller;

import constants.ResultCode;
import model.dto.LendingBookDto;
import service.LendingBookService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static util.StringTruncate.truncate;


public class LendingBookController {
    private final LendingBookService lendingBookService;

    public LendingBookController() {
        this.lendingBookService = new LendingBookService();
    }

    // 대출처리
    public void insertLending(Long bookId, Long userId, LocalDateTime dueDate) {
        int result = lendingBookService.insertLending(bookId, userId, dueDate);
        if (result == ResultCode.IS_SUCCESS) {
            System.out.println("🎉 대출 성공");
        } else if (result == ResultCode.IS_FAIL) {
            System.out.println("⚠️ 대출 실패");
        } else if (result == ResultCode.IS_ERROR) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 반납 처리
    public void returnBook(Long lendingId, Long userId) {
        int result = lendingBookService.returnBook(lendingId, userId);
        if (result == ResultCode.IS_SUCCESS) {
            System.out.println("🎉 반납 성공");
        } else if (result == ResultCode.IS_FAIL) {
            System.out.println("⚠️ 반납 실패");
        } else if (result == ResultCode.IS_ERROR) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 대출 목록 조회
    public void getAllLending(Long userId) {
        List<LendingBookDto> lendingBookDtoList = lendingBookService.getLendingList(userId);
        if (lendingBookDtoList.isEmpty()) {
            System.out.println("⚠️ 대출 목록이 없습니다!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.printf("| %-10s | %-30s | %-20s | %-20s | %-12s |\n",
                "\uD83D\uDCDA대출번호",   // 📚 대출번호
                "\uD83D\uDCD6책이름",   // 📖 책이름
                "✍️ 저자",              // ✍️ 저자
                "\uD83D\uDCC4 출판사",  // 📄 출판사
                "\uD83D\uDD50 반납예정일" // 🔐 반납예정일
        );

        System.out.println("|-------------------------------------------------------------------------------------------------------------------------");

        lendingBookDtoList.forEach(g -> {
            System.out.printf("| %-10d | %-30s | %-20s | %-20s | %-12s |\n",
                    g.getLendingId(),
                    truncate(g.getTitle(), 30),
                    truncate(g.getAuthor(), 20),
                    truncate(g.getPublisher(), 20),
                    g.getDueDate().format(formatter)
            );
        });
    }

    // 연체액 조회
    public void getAllLateFee(Long userId) {

        int lateFee = lendingBookService.getAllLateFee(userId);
        if (lateFee == ResultCode.IS_ERROR) {
            System.out.println("⚠️ 관리자는 연체액이 부과되지 않습니다!\n");
        } else {
            System.out.printf("🎉 납부해야할 연체 벌금 : %d\n", lateFee);
        }
    }

    // 연체액 납부
    public void deleteAllLateFeeLogs(Long loginId, Long targetId) {

        int result = lendingBookService.deleteAllLateFeeLogs(loginId, targetId);

        if (result >= ResultCode.LATE_FEE_LOGS_DELETE_SUCCESS) {
            System.out.println("🎉 연체액 납부 성공 ");
        } else if (result == ResultCode.LATE_FEE_LOGS_DELETE_FAIL) {
            System.out.println("⚠️ 연체액 납부 실패");
        } else if (result == ResultCode.LATE_FEE_LOGS_DELETE_ERROR) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    //책 대출/반납 통합 시스템 -> 로그인한 사용자의 userid를 받는다
    public void lendBookSystem(Long userId) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("========================================");
            System.out.println("📚           대출 및 반납 기능           ");
            System.out.println("----------------------------------------");
            System.out.println("🟢  1 : 📖 도서 대출");
            System.out.println("🔴  2 : 📕 도서 반납");
            System.out.println("📋  3 : 📚 대출 목록 조회");
            System.out.println("💰  4 : ⏰ 연체액 조회");
            System.out.println("🛠️  5 : 💳 연체액 납부 (관리자만 가능)");
            System.out.println("🚪  exit : 종료");
            System.out.println("----------------------------------------");
            System.out.println("⌨️  명령어를 입력해 주세요.");
            System.out.println("========================================");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("⚠️ 대출 및 반납 기능을 종료 합니다.");
                break;
            } else if (input.equals("1")) {
                System.out.println("📖 대여 하실 책 번호를 입력해 주세요. : ");
                Long bookId = scanner.nextLong();
                scanner.nextLine(); // 버퍼에 남은 줄바꿈 제거

                insertLending(bookId, userId, LocalDateTime.now().plusDays(7));
            } else if (input.equals("2")) {
                System.out.println("📕 반납처리 하실 대여 번호를 입력해 주세요. : ");
                Long lendingId = scanner.nextLong();
                scanner.nextLine(); // 버퍼에 남은 줄바꿈 제거

                returnBook(lendingId, userId);
            } else if (input.equals("3")) {
                System.out.println("📚 대출 목록을 출력합니다.");
                getAllLending(userId);
            } else if (input.equals("4")) {
                System.out.println("⏰ 연체액을 조회 합니다.");
                getAllLateFee(userId);
            } else if (input.equals("5")) {
                System.out.println("💳 연체액을 납부할 유저의 userId를 입력해 주세요 : ");
                Long targetId = scanner.nextLong();
                scanner.nextLine(); // 버퍼에 남은 줄바꿈 제거

                deleteAllLateFeeLogs(userId, targetId);
            }
        }
    }
}
