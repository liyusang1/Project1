package controller;

import common.SessionStorage;

import model.dto.RequestsDto;
import service.RequestBookService;

import java.util.List;
import java.util.Scanner;

public class RequestBookController {
    private final RequestBookService requestBookService;

    public RequestBookController() {
        this.requestBookService = new RequestBookService();
    }

    // 희망도서 신청
    public void requestBook() {
        Scanner sc = new Scanner(System.in);
        Long userId = SessionStorage.getCurrentUser().getUserId();

        System.out.print("신청할 도서명을 입력해주세요. ");

        String title = sc.nextLine();
        System.out.print("신청할 도서 저자명을 입력해주세요. ");
        String author = sc.nextLine();
        System.out.print("신청할 도서 출판사를 입력해주세요. ");
        String publisher = sc.nextLine();

        requestBookService.requestBook(userId, title, author, publisher);
    }

    // 희망도서 신청 조회(회원)
    public void getMyRequestedBooks() {
        Long userId = SessionStorage.getCurrentUser().getUserId();
        List<RequestsDto> myRequstList = requestBookService.getMyRequestedBooks(userId);
        // 컬럼명 출력
        String format = "| %-8s | %-20s | %-15s | %-15s | %-12s | %-6s |\n";
        String line = "+----------+----------------------+-----------------+-----------------+--------------+--------+";
        System.out.println(line);
        System.out.printf(format, "회원ID", "도서명", "저자", "출판사", "신청일", "상태");
        System.out.println(line);

        myRequstList.forEach(req -> {
            String statusText;
            switch (req.getStatus()) {
                case 2 -> statusText = "대기중";
                case 1 -> statusText = "승인";
                case 0 -> statusText = "반려";
                default -> statusText = "알 수 없음";
            }

            System.out.printf(format,
                    req.getUserId(),
                    truncate(req.getRequestBook(), 20),
                    truncate(req.getRequestAuthor(), 15),
                    truncate(req.getRequestPublisher(), 15),
                    req.getCreateAt().toString(),
                    statusText
            );
        });
        System.out.println(line);
    }

    // 희망도서 신청 조회(관리자)
    public void getAllRequestedBooks() {
        List<RequestsDto> requestList = requestBookService.getAllRequestedBooks();

        // 컬럼명 출력
        String format = "| %-8s | %-8s | %-20s | %-15s | %-15s | %-12s | %-6s |\n";
        String line = "+----------+----------------------+-----------------+-----------------+--------------+--------+";
        System.out.println(line);
        System.out.printf(format, "신청번호", "회원ID", "도서명", "저자", "출판사", "신청일", "상태");
        System.out.println(line);

        requestList.forEach(req -> {
            String statusText;
            switch (req.getStatus()) {
                case 2 -> statusText = "대기중";
                case 1 -> statusText = "승인";
                case 0 -> statusText = "반려";
                default -> statusText = "알 수 없음";
            }
            System.out.printf(format,
                    req.getRequestId(),
                    req.getUserId(),
                    truncate(req.getRequestBook(), 20),
                    truncate(req.getRequestAuthor(), 15),
                    truncate(req.getRequestPublisher(), 15),
                    req.getCreateAt().toString(),
                    statusText
            );
        });
        System.out.println(line);
    }

    // 문자열 자르기
    private String truncate(String str, int maxLength) {
        return str.length() > maxLength ? str.substring(0, maxLength - 1) + "…" : str;
    }

    // 희망도서 상태 처리
    public void updateRequestedBookStatus() {
        Scanner sc = new Scanner(System.in);

        System.out.print("희망도서 샅태를 변경할 도서번호를 입력해주세요. ");
        int reqId = sc.nextInt();
        System.out.print("처리 상태를 입력해주세요.(0: 반려, 1: 승인) ");
        int status = sc.nextInt();

        requestBookService.updateRequestedBookStatus(reqId, status);
    }

    // 희망도서 시스템 - 회원
    public void requestBookSystem() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("========================================");
            System.out.println("🔍           희망도서 기능           ");
            System.out.println("----------------------------------------");
            System.out.println("✅  1 : 희망도서 신청");
            System.out.println("🧾  2 : 신청 내역 조회");
            System.out.println("📙  0 : 종료");
            System.out.println("----------------------------------------");
            System.out.println("⌨️  명령어를 입력해 주세요.");
            System.out.println("========================================");

            String input = scanner.nextLine();
            if (input.equals("0")) {
                System.out.println("️⚠️ 희망도서 기능을 종료 합니다.");
                break;
            } else if (input.equals("1")) {
                requestBook();
                break;
            } else if (input.equals("2")) {
                getMyRequestedBooks();
                break;
            } else {
                System.out.println("⚠️ 올바른 번호를 입력하세요.");
            }
        }
    }

    // 희망도서 시스템 - 관리자
    public void requestBookProcessSystem() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("========================================");
            System.out.println("🔍           희망도서 관리 기능           ");
            System.out.println("----------------------------------------");
            System.out.println("✅  1 : 희망도서 목록 조회");
            System.out.println("🧾  2 : 희망도서 상태 처리");
            System.out.println("📙  0 : 종료");
            System.out.println("----------------------------------------");
            System.out.println("⌨️  명령어를 입력해 주세요.");
            System.out.println("========================================");

            String input = scanner.nextLine();
            if (input.equals("0")) {
                System.out.println("️⚠️ 희망도서 기능을 종료 합니다.");
                break;
            } else if (input.equals("1")) {
                getAllRequestedBooks();
                break;
            } else if (input.equals("2")) {
                updateRequestedBookStatus();
                break;
            } else {
                System.out.println("⚠️ 올바른 번호를 입력하세요.");
            }
        }
    }
}
