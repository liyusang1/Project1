package service;

import model.dao.SearchBookDao;
import model.dto.BookShowDto;

import java.util.List;

public class SearchBookService {
    private final SearchBookDao searchBookDao;

    public SearchBookService() { this.searchBookDao = new SearchBookDao(); }

    // 도서 전체 목록 조회
    public List<BookShowDto> getAllBooks(boolean status) {
        return searchBookDao.getAllBooks(status);
    }

    // 도서 검색(제목)
    public List<BookShowDto> findBooksByTitle(String title, boolean status) {
        return searchBookDao.findBooksByTitle(title, status);
    }

    // 도서 검색(작가)
    public List<BookShowDto> findBooksByAuthor(String author, boolean status) {
        return searchBookDao.findBooksByAuthor(author, status);
    }

    // 도서 검색(카테고리)
    public List<BookShowDto> findBooksByCategory(String category, boolean status) {
        return searchBookDao.findBooksByCategory(category, status);
    }

}
