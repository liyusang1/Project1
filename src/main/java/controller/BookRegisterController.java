package controller;

import constants.BookUserConstant;
import model.dto.BookDto;
import service.BookRegisterService;

import java.util.List;
import java.util.Scanner;

public class BookRegisterController {

    private final BookRegisterService bookRegisterService;

    public BookRegisterController() {
        this.bookRegisterService = new BookRegisterService();
    }

    // 책 등록 처리
    public void resigterBook(BookDto bookDto, Long userId) {
        int result = bookRegisterService.registerBook(bookDto, userId);

        if (result == 1) {
            System.out.println("🎉 책이 성공적으로 등록되었습니다!");
        } else if (result == 0) {
            System.out.println("⚠️ 책 등록에 실패했습니다. 다시 시도해보세요.");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    public void getAllBook() {
        List<BookDto> bookList = bookRegisterService.getAllBooks();

        // 1. 표 헤더 출력
        String headerFormat = "| %-6s | %-20s | %-10s | %-7s | %-8s | %-15s | %-8s | %-19s |\n";
        String line = "+--------+----------------------+------------+---------+----------+-----------------+----------+---------------------+";
        System.out.println(line);
        System.out.printf(headerFormat,
                "책ID", "제목", "저자", "가격", "카테고리", "출판사", "상태", "등록일자");
        System.out.println(line);

        // 2. 데이터 출력
        for (BookDto book : bookList) {
            System.out.printf(headerFormat,
                    book.getBookId() != null ? book.getBookId() : "",
                    book.getTitle() != null ? book.getTitle() : "",
                    book.getAuthor() != null ? book.getAuthor() : "",
                    book.getPrice() > 0 ? book.getPrice() : "",
                    book.getCategoryId() != null ? book.getCategoryId() : "",
                    book.getPublisher() != null ? book.getPublisher() : "",
                    book.getStatus() == 1 ? "대출가능" : "대출 중",
                    book.getCreatedAt() != null ? book.getCreatedAt().toString() : ""
            );
        }
        System.out.println(line);
    }


    public void getAvailableBooks() {
        List<BookDto> bookList = bookRegisterService.getAvailableBooks();

        // 1. 표 헤더 출력
        String headerFormat = "| %-6s | %-20s | %-10s | %-7s | %-8s | %-15s | %-8s | %-19s |\n";
        String line = "+--------+----------------------+------------+---------+----------+-----------------+----------+---------------------+";
        System.out.println(line);
        System.out.printf(headerFormat,
                "책ID", "제목", "저자", "가격", "카테고리", "출판사", "상태", "등록일자");
        System.out.println(line);

        // 2. 데이터 출력
        for (BookDto book : bookList) {
            System.out.printf(headerFormat,
                    book.getBookId() != null ? book.getBookId() : "",
                    book.getTitle() != null ? book.getTitle() : "",
                    book.getAuthor() != null ? book.getAuthor() : "",
                    book.getPrice() > 0 ? book.getPrice() : "",
                    book.getCategoryId() != null ? book.getCategoryId() : "",
                    book.getPublisher() != null ? book.getPublisher() : "",
                    book.getStatus() == 1 ? "대출가능" : "대출 중",
                    book.getCreatedAt() != null ? book.getCreatedAt().toString() : ""
            );
        }
        System.out.println(line);
    }

    public void bookRegisterSystem() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 📚 도서 등록/조회 시스템 ===");
            System.out.println("1. 책 등록");
            System.out.println("2. 전체 책 목록 조회");
            System.out.println("3. 대출 가능 책 목록 조회");
            System.out.println("0. 종료");
            System.out.print("메뉴를 선택하세요: ");

            String input = sc.nextLine();

            switch (input) {
                case "1" -> { // 책 등록
                    BookDto bookDto = new BookDto();

                    System.out.print("책 제목: ");
                    bookDto.setTitle(sc.nextLine());

                    System.out.print("저자: ");
                    bookDto.setAuthor(sc.nextLine());

                    System.out.print("가격: ");
                    try {
                        bookDto.setPrice(Integer.parseInt(sc.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("가격은 숫자로 입력하세요.");
                        break;
                    }

                    System.out.print("카테고리ID: ");
                    try {
                        bookDto.setCategoryId(Long.parseLong(sc.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("카테고리ID는 숫자로 입력하세요.");
                        break;
                    }

                    System.out.print("출판사: ");
                    bookDto.setPublisher(sc.nextLine());

                    // 상태 입력 (1: 대출가능, 0: 대출중) - 일단 1 입력
                    bookDto.setStatus(BookUserConstant.IS_AVAILABLE);

                    System.out.print("관리자 ID: ");
                    Long userId;
                    try {
                        userId = Long.parseLong(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("유저ID는 숫자로 입력하세요.");
                        break;
                    }

                    resigterBook(bookDto, userId);
                }
                case "2" -> {
                    // 전체 책 목록 조회
                    getAllBook();
                }
                case "3" -> {
                    // 대출 가능 책 목록 조회
                    getAvailableBooks();
                }
                case "0" -> {
                    System.out.println("📕 시스템을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }


}
