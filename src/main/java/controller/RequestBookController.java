package controller;

import model.dto.RequestsDto;
import model.dto.SampleGiftDto;
import service.RequestBookService;

import java.util.List;
import java.util.Scanner;

public class RequestBookController {
    private final RequestBookService requestBookService;

    public RequestBookController() {
        this.requestBookService = new RequestBookService();
    }

    public void requestBook() {
        Scanner sc = new Scanner(System.in);

        System.out.print("신청할 도서명을 입력해주세요. ");
        String title = sc.next();
        System.out.print("신청할 도서 저자명을 입력해주세요. ");
        String author = sc.next();
        System.out.print("신청할 도서 출판사를 입력해주세요. ");
        String publisher = sc.next();

        requestBookService.requestBook(title, author, publisher);
    }

    public void getMyRequestedBooks() {
        List<RequestsDto> myRequstList = requestBookService.getMyRequestedBooks();
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

    private String truncate(String str, int maxLength) {
        return str.length() > maxLength ? str.substring(0, maxLength - 1) + "…" : str;
    }

    public void updateRequestedBookStatus() {
        Scanner sc = new Scanner(System.in);

        System.out.print("희망도서 샅태를 변경할 도서번호를 입력해주세요. ");
        int reqId = sc.nextInt();
        System.out.print("처리 상태를 입력해주세요.(0: 반려, 1: 승인) ");
        int status = sc.nextInt();

        requestBookService.updateRequestedBookStatus(reqId, status);
    }

}
