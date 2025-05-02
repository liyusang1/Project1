package service;

import constants.ResultCode;
import model.dao.BookRegisterDao;
import model.dao.CheckCateoryExistDao;
import model.dao.CheckMangerDao;
import model.dto.BookDto;
import model.dto.CategoryDto;
import model.dto.SelectBookDto;
import model.dto.UserDto;

import java.awt.print.Book;
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
            return ResultCode.IS_FAIL;
        }

        // category_id 카테고리 테이블에 없는 경우 0 반환
        if (!checkCateoryExistDao.checkCategoryExist(bookDto.getCategoryId())) {
            System.out.print("[존재하지 않는 카테고리]");
            return ResultCode.IS_FAIL;
        }

        // title: 1~100자, 한글/영문/숫자/공백/특수문자 허용
        if (!bookDto.getTitle().matches("^.{1,100}$")) {
            if (bookDto.getTitle().trim().isEmpty()) {
                System.out.print("[책이름 입력값이 없습니다]");
            } else if (bookDto.getTitle().length() > 100) {
                System.out.print("[책이름이 너무 깁니다]");
            } else {
                System.out.print("[책이름 형식이 올바르지 않습니다]");
            }
            return ResultCode.IS_FAIL;
        }

        // author: 2~50자, 한글/영문/공백만 허용
        if (!bookDto.getAuthor().matches("^[a-zA-Z가-힣\\s]{2,50}$")) {
            if (bookDto.getAuthor().trim().isEmpty()) {
                System.out.print("[저자이름 입력값이 없습니다]");
            } else if (bookDto.getAuthor().length() > 50) {
                System.out.print("[저자이름이 너무 깁니다]");
            } else if (bookDto.getAuthor().length() < 2) {
                System.out.print("[저자이름이 너무 짧습니다]");
            } else {
                System.out.print("[저자이름 형식이 올바르지 않습니다]");
            }
            return ResultCode.IS_FAIL;
        }

        // publisher: 1~50자, 한글/영문/숫자/공백/특수문자 허용
        if (!bookDto.getPublisher().matches("^.{1,50}$")) {
            if (bookDto.getPublisher().trim().isEmpty()) {
                System.out.print("[출판사이름 입력값이 없습니다]");
            } else if (bookDto.getPublisher().length() > 50) {
                System.out.print("[출판사이름이 너무 깁니다]");
            } else {
                System.out.print("[출판사이름 형식이 올바르지 않습니다]");
            }
            return ResultCode.IS_FAIL;
        }

        // price: 1000만원 이하, 0보다 큰 값만 허용
        if (bookDto.getPrice() > 10000000 || bookDto.getPrice() <= 0) {
            if (bookDto.getPrice() > 10000000) {
                System.out.print("[가격 입력값이 너무 큽니다]");
            } else {
                System.out.print("[가격 입력값이 0 이하입니다]");
            }
            return ResultCode.IS_FAIL;
        }

        return bookRegisterDao.registerBook(bookDto);
    }

    /**
     * 조회 메서드
     */

    public List<SelectBookDto> getAllBooks() {

        return bookRegisterDao.getAllBooks();
    }

    public List<SelectBookDto> getAvailableBooks() {

        return bookRegisterDao.getAvailableBooks();
    }

    public List<SelectBookDto> getBookById(Long targetId) {

        return bookRegisterDao.getBookById(targetId);
    }

    public List<SelectBookDto> getBookByCategoryId(Long targetId) {

        return bookRegisterDao.getBookByCategoryId(targetId);
    }

    public List<CategoryDto> getAllCategory() {

        return bookRegisterDao.getAllCategory();
    }

}
