package controller;

import model.dto.BookShowDto;
import service.SearchBookService;

import java.util.List;
import java.util.Scanner;

public class SearchBookController {
    private final SearchBookService searchBookService;

    public SearchBookController() {
        this.searchBookService = new SearchBookService();
    }

    // 도서 전체 목록 조회
    public void getAllBooks(boolean status) {
        List<BookShowDto> bookList = searchBookService.getAllBooks(status);

        // 컬럼명 출력
        String format = "| %-8s | %-15s | %-15s | %-12s | %-6s |\n";
        String line = "+----------+----------------------+-----------------+-----------------+--------------+";
        System.out.println(line);
        System.out.printf(format, "도서명", "저자", "출판사", "카테고리", "대출상태");
        System.out.println(line);

        bookList.forEach(b -> {
            String statusText;
            switch (b.getStatus()) {
                case 1 -> statusText = "대출가능";
                case 0 -> statusText = "대출중";
                default -> statusText = "알 수 없음";
            }

            System.out.printf(format,
                    truncate(b.getTitle(), 20),
                    truncate(b.getAuthor(), 15),
                    truncate(b.getPublisher(), 15),
                    b.getCategory(),
                    statusText
            );
        });
        System.out.println(line);
    }

    // 도서 검색(제목)
    public void findBooksByTitle(boolean status) {
        Scanner sc = new Scanner(System.in);

        System.out.print("검색할 도서명을 입력해주세요. ");
        String title = sc.next();

        List<BookShowDto> bookList = searchBookService.findBooksByTitle(title, status);
        // 컬럼명 출력
        String format = "| %-8s | %-15s | %-15s | %-12s | %-6s |\n";
        String line = "+----------+----------------------+-----------------+-----------------+--------------+";
        System.out.println(line);
        System.out.printf(format,  "도서명", "저자", "출판사", "카테고리", "대출상태");
        System.out.println(line);

        bookList.forEach(b -> {
            String statusText;
            switch (b.getStatus()) {
                case 1 -> statusText = "대출가능";
                case 0 -> statusText = "대출중";
                default -> statusText = "알 수 없음";
            }

            System.out.printf(format,
                    truncate(b.getTitle(), 20),
                    truncate(b.getAuthor(), 15),
                    truncate(b.getPublisher(), 15),
                    b.getCategory(),
                    statusText
            );
        });
        System.out.println(line);
    }

    // 도서 검색(작가)
    public void findBooksByAuthor(boolean status) {
        Scanner sc = new Scanner(System.in);

        System.out.print("검색할 저자명을 입력해주세요. ");
        String author = sc.next();

        List<BookShowDto> bookList = searchBookService.findBooksByAuthor(author, status);
        // 컬럼명 출력
        String format = "| %-8s | %-15s | %-15s | %-12s | %-6s |\n";
        String line = "+----------+----------------------+-----------------+-----------------+--------------+";
        System.out.println(line);
        System.out.printf(format,  "도서명", "저자", "출판사", "카테고리", "대출상태");
        System.out.println(line);

        bookList.forEach(b -> {
            String statusText;
            switch (b.getStatus()) {
                case 1 -> statusText = "대출가능";
                case 0 -> statusText = "대출중";
                default -> statusText = "알 수 없음";
            }

            System.out.printf(format,
                    truncate(b.getTitle(), 20),
                    truncate(b.getAuthor(), 15),
                    truncate(b.getPublisher(), 15),
                    b.getCategory(),
                    statusText
            );
        });
        System.out.println(line);
    }

    // 도서 검색(카테고리)
    public void findBooksByCategory(boolean status) {
        Scanner sc = new Scanner(System.in);

        System.out.print("검색할 카테고리을 입력해주세요. ");
        String category = sc.next();

        List<BookShowDto> bookList = searchBookService.findBooksByCategory(category, status);
        // 컬럼명 출력
        String format = "| %-8s | %-15s | %-15s | %-12s | %-6s |\n";
        String line = "+----------+----------------------+-----------------+-----------------+--------------+";
        System.out.println(line);
        System.out.printf(format,  "도서명", "저자", "출판사", "카테고리", "대출상태");
        System.out.println(line);

        bookList.forEach(b -> {
            String statusText;
            switch (b.getStatus()) {
                case 1 -> statusText = "대출가능";
                case 0 -> statusText = "대출중";
                default -> statusText = "알 수 없음";
            }

            System.out.printf(format,
                    truncate(b.getTitle(), 20),
                    truncate(b.getAuthor(), 15),
                    truncate(b.getPublisher(), 15),
                    b.getCategory(),
                    statusText
            );
        });
        System.out.println(line);
    }
    
    private String truncate(String str, int maxLength) {
        return str.length() > maxLength ? str.substring(0, maxLength - 1) + "…" : str;
    }
    
    
    // 도서 목록 검색 시스텝
    public void searchBookSystem() {
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            System.out.println("========================================");
            System.out.println("🔍           도서 검색 기능           ");
            System.out.println("----------------------------------------");
            System.out.println("✅  1 : 대출 가능 목록 조회");
            System.out.println("🧾  2 : 전체 목록 조회");
            System.out.println("📙  0 : 종료");
            System.out.println("----------------------------------------");
            System.out.println("⌨️  명령어를 입력해 주세요.");
            System.out.println("========================================");

            String input = scanner.nextLine();
            boolean status = false;
            if (input.equals("0")) {
                System.out.println("️⚠️ 도서 검색 기능을 종료 합니다.");
                break;
            } else if (input.equals(("1"))) {
                status = true;
            }

            while(true) {
                System.out.println("========================================");
                System.out.println("🔍           도서 검색 기능           ");
                System.out.println("----------------------------------------");
                System.out.println("📚  1 : 도서 목록 조회");
                System.out.println("📓  2 : 도서명으로 검색");
                System.out.println("👨‍💼  3 : 저자명으로 검색");
                System.out.println("🧩  4 : 카테고리로 검색");
                System.out.println("↩  0 : 이전");
                System.out.println("----------------------------------------");
                System.out.println("⌨️  명령어를 입력해 주세요.");
                System.out.println("========================================");

                String subInput = scanner.nextLine();
                if(subInput.equals("0")) break;

                switch(subInput) {
                    case "1":
                        getAllBooks(status);
                        break;
                    case "2":
                        findBooksByTitle(status);
                        break;
                    case "3":
                        findBooksByAuthor(status);
                        break;
                    case "4":
                        findBooksByCategory(status);
                        break;
                    default:
                        System.out.println("⚠️ 올바른 번호를 입력하세요.");
                }
            }
        }
    } // end while

}
