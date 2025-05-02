package controller;

import common.BookListFormatter;
import model.dto.BookShowDto;
import service.SearchBookService;
import util.PaginationUtil;

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
        PaginationUtil.displayPagedList(bookList, 5, new BookListFormatter());
    }

    // 도서 검색(제목)
    public void findBooksByTitle(boolean status) {
        Scanner sc = new Scanner(System.in);

        System.out.print("검색할 도서명을 입력해주세요. ");
        String title = sc.next();

        List<BookShowDto> bookList = searchBookService.findBooksByTitle(title, status);
        PaginationUtil.displayPagedList(bookList, 5, new BookListFormatter());
    }

    // 도서 검색(작가)
    public void findBooksByAuthor(boolean status) {
        Scanner sc = new Scanner(System.in);

        System.out.print("검색할 저자명을 입력해주세요. ");
        String author = sc.next();

        List<BookShowDto> bookList = searchBookService.findBooksByAuthor(author, status);
        PaginationUtil.displayPagedList(bookList, 5, new BookListFormatter());
    }

    // 도서 검색(카테고리)
    public void findBooksByCategory(boolean status) {
        Scanner sc = new Scanner(System.in);

        System.out.print("검색할 카테고리을 입력해주세요. ");
        String category = sc.next();

        List<BookShowDto> bookList = searchBookService.findBooksByCategory(category, status);
        PaginationUtil.displayPagedList(bookList, 5, new BookListFormatter());
    }

    // 도서 목록 검색 시스템
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
