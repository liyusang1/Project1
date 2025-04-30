package service;

import model.dao.RequestBookDao;
import model.dto.RequestsDto;

import java.sql.Date;
import java.util.List;

public class RequestBookService {
    private final RequestBookDao requestBookDao;

    public RequestBookService() { this.requestBookDao = new RequestBookDao(); }

    public void requestBook(String title, String author, String publisher) {
        Date today =  new Date(System.currentTimeMillis());
        RequestsDto dto = new RequestsDto();
        Long userId = 2L;
        dto.setUserId(userId);
        dto.setRequestBook(title);
        dto.setRequestAuthor(author);
        dto.setRequestPublisher(publisher);
        dto.setCreateAt(today);
        dto.setStatus(2);

        int result = requestBookDao.requestBook(dto);

        if( result > 0 ){
            System.out.println("희망도서 신청 완료!");
        } else {
            System.out.println("신청 실패, 다시 신청해주세요.");
        }
    }

    public List<RequestsDto> getMyRequestedBooks() {
        RequestsDto dto = new RequestsDto();
        Long userId = 2L;

        return requestBookDao.getMyRequestedBooks(userId);
    }

    public List<RequestsDto> getAllRequestedBooks() {

        return requestBookDao.getAllRequestedBooks();
    }

    public int updateRequestedBookStatus(int requestId, int status) {
        return requestBookDao.updateRequestedBookStatus(requestId, status);
    }
}
