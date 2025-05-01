package controller;

import constants.BookUserConstant;
import model.dto.BookDto;
import model.dto.CategoryDto;
import model.dto.SelectBookDto;
import service.BookRegisterService;
import util.BookTableHelper;

import java.awt.print.Book;
import java.util.Iterator;
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
        List<SelectBookDto> bookList = bookRegisterService.getAllBooks();
        BookTableHelper.displayBooks(bookList);
    }

    public void getAvailableBooks() {
        List<SelectBookDto> bookList = bookRegisterService.getAvailableBooks();
        BookTableHelper.displayBooks(bookList);
    }

    public void getAllCateogry() {
        List<CategoryDto> categoryList = bookRegisterService.getAllCategory();
        Iterator<CategoryDto> it = categoryList.iterator();
        while(it.hasNext()) {
            CategoryDto category = it.next();
            System.out.println(category.getCategory_id() + ": " + category.getCategory());
        }
    }

    /**
     * Id통해 찾는 메서드
     */
    public void getBookByCategoryId(Long CategoryId) {
        List<SelectBookDto> bookList = bookRegisterService.getBookByCategoryId(CategoryId);
        BookTableHelper.displayBooks(bookList);
    }

    public void getBookById(Long bookId) {
        List<SelectBookDto> bookList = bookRegisterService.getBookById(bookId);
        if (bookList == null || bookList.isEmpty()) {
            System.out.println("해당 ID의 도서를 찾을 수 없습니다.");
            return;
        }
        SelectBookDto book = bookList.getFirst();
        String statusStr = book.getStatus() == 1 ? "대출가능" : "대출 중";
        System.out.printf(
                "\n=====[도서 상세 정보]=====\n제목 : %s\n저자 : %s\n카테고리 : %" +
                        "s\n출판사 : %s\n가격 : %d\n상태 : %s\n\n",
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getPublisher(),
                book.getPrice(),
                statusStr
        );
    }


    public void bookRegisterSystem(Long inputId) {
        if (inputId == null) {
            System.out.println("⚠️로그인을 진행 해주세요");
            return;
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 📚 도서 등록/조회 시스템 ===");
            System.out.println("1. ➕ 책 등록");
            System.out.println("2. 📖 전체 책 목록 조회");
            System.out.println("3. ✅ 대출 가능 책 목록 조회");
            System.out.println("4. 🗂️ Category별 책 조회");
            System.out.println("5. 🔍 ID로 책 상세 조회");
            System.out.println("0. 🚪 종료");
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

                    getAllCateogry();
                    System.out.print("카테고리ID 입력(1~10): \n");
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


                    resigterBook(bookDto, inputId);
                }
                case "2" -> {
                    // 전체 책 목록 조회
                    getAllBook();
                }
                case "3" -> {
                    // 대출 가능 책 목록 조회
                    getAvailableBooks();
                }
                case "4" -> {
                    // 카테고리로 책 목록 조회
                    getAllCateogry();
                    System.out.println("카테고리 입력(1~10): ");
                    getBookByCategoryId(sc.nextLong());
                    sc.nextLine();
                }
                case "5" -> {
                    System.out.println("책 ID: ");
                    getBookById(sc.nextLong());
                    sc.nextLine();
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
