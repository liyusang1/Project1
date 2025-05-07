package service;

import model.dao.RequestBookDao;
import model.dto.BookDto;
import model.dto.RequestsDto;

import java.sql.Date;
import java.util.List;

public class RequestBookService {
    private final RequestBookDao requestBookDao;

    public RequestBookService() { this.requestBookDao = new RequestBookDao(); }

    // 희망도서 신청
    public int requestBook(Long userId, String title, String author, String publisher) {
        int existsInBooks = requestBookDao.confirmBook(title);
        int existsInRequests = requestBookDao.existRequstBook(title, userId);

        if (existsInBooks > 0) {
            System.out.println("❗ 이미 보유 중인 도서입니다. 신청할 수 없습니다.");
            return 0;
        }

        if (existsInRequests > 0) {
            System.out.println("❗ 이미 신청된 도서입니다. 신청할 수 없습니다.");
            return 0;
        }

        Date today =  new Date(System.currentTimeMillis());
        RequestsDto dto = new RequestsDto();

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

        return result;
    }

    // 희망도서 신청 조회(회원)
    public List<RequestsDto> getMyRequestedBooks(Long userId) {
        RequestsDto dto = new RequestsDto();

        return requestBookDao.getMyRequestedBooks(userId);
    }

    // 희망도서 신청 조회(관리자)
    public List<RequestsDto> getAllRequestedBooks() {
        return requestBookDao.getAllRequestedBooks();
    }

    // 희망도서 상태 처리
    public int updateRequestedBookStatus(int requestId, int status) {
        return requestBookDao.updateRequestedBookStatus(requestId, status);
    }
}
