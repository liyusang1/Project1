package service;

import model.dao.BookRegisterDao;
import model.dao.CheckCateoryExistDao;
import model.dao.CheckMangerDao;
import model.dto.BookDto;

import java.util.List;

public class BookRegisterService {
    private final BookRegisterDao bookRegisterDao;
    private final CheckCateoryExistDao checkCateoryExistDao;
    private final CheckMangerDao checkMangerDao;

    public BookRegisterService() {
        this.bookRegisterDao = new BookRegisterDao();
        this.checkCateoryExistDao = new CheckCateoryExistDao();
        this.checkMangerDao = new CheckMangerDao();
    }

    /**
     * 도서 정보 입력 method
     */
    public int registerBook(BookDto bookDto, Long userId) {

        /**
         *  [유효성 검사]
         *  1. UserType이 1일 경우에만 실행
         *  2. 존재하는 category_id인지 확인
         *  3. String 속성 글자제한
         *  4. 가격 상한 제한
         */

        // 관리자 유저인지 체크
        if (!checkMangerDao.checkManager(userId)) {
            System.out.println("[관리자만 접근 가능합니다]");
            return 0;
        }

        // category_id 카테고리 테이블에 없는 경우 0 반환
        if (!checkCateoryExistDao.checkCategoryExist(bookDto.getCategoryId())) {
            System.out.print("[존재하지 않는 카테고리]");
            return 0;
        }

        // title 비어있거나 255글자 이상일 경우 0 반환
        if (!bookDto.getTitle().matches("^.{0,255}$")) {
            System.out.print("[책이름이 너무 깁니다]");
            return 0;
        } else if (bookDto.getTitle().trim().isEmpty()) {
            System.out.print("[책이름 입력값이 없습니다]");
            return 0;
        }

        //  author 255글자 이상일 경우 0 반환
        if (!bookDto.getAuthor().matches("^.{0,255}$")) {
            System.out.print("[저자이름이 너무 깁니다]");
            return 0;
        } else if (bookDto.getAuthor().trim().isEmpty()) {
            System.out.print("[저자이름 입력값이 없습니다]");
            return 0;
        }

        // publisher 비어있거나 255글자 이상일 경우 0 반환
        if (!bookDto.getPublisher().matches("^.{0,255}$")) {
            System.out.print("[출판사이름 너무 깁니다]");
            return 0;
        } else if (bookDto.getPublisher().trim().isEmpty()) {
            System.out.print("[출판사이름 입력값이 없습니다]");
            return 0;
        }

        // price 입력값
        if (bookDto.getPrice() > 100000000) {
            System.out.print("[가격 입력값이 너무 큽니다]");
            return 0;
        }

        return bookRegisterDao.registerBook(bookDto);
    }

    /**
     * 조회 메서드
     */

    public List<BookDto> getAllBooks() {

        return bookRegisterDao.getAllBooks();
    }

    public List<BookDto> getAvailableBooks() {

        return bookRegisterDao.getAvailableBooks();
    }
}
