package service;

import model.dao.CheckBookExistDao;
import model.dao.CheckUserExistDao;
import model.dao.LendingBookDao;

import java.time.LocalDateTime;

public class LendingBookService {
    private final LendingBookDao lendingBookDao;
    private final CheckBookExistDao checkBookExistDao;
    private final CheckUserExistDao checkUserExistDao;

    public LendingBookService() {
        this.lendingBookDao = new LendingBookDao();
        this.checkBookExistDao = new CheckBookExistDao();
        this.checkUserExistDao = new CheckUserExistDao();
    }

    /**
     * 도서 대출
     *
     * @param bookId  책pk
     * @param userId  유저pk
     * @param dueDate 만기기한
     */
    public int insertLending(Long bookId, Long userId, LocalDateTime dueDate) {

        //책 유효성 검사
        if (!checkBookExist(bookId)) {
            System.out.println("⚠️ 존재하지 않는 책 번호 입니다.");
            return 0;
        }

        //유저 유효성 검사
        if (!checkUserExist(userId)) {
            System.out.println("⚠️ 존재하지 않는 유저 입니다.");
            return 0;
        }

        //현재 대출 중인지 체크
        if(!lendingBookDao.checkUserLendingExist(userId)) {
            System.out.println("⚠️ 해당 유저는 현재 대출 중 입니다.");
            return 0;
        }

        //연체로인한 벌금 지불 대상자인지 체크 -> 연체로 인해 발생한 벌금을 납부하지않았다면 도서 시스템 이용 불가
        if(!lendingBookDao.checkUsersLateFee(userId)) {
            System.out.println("⚠️ 해당 유저는 연체로 인한 벌금을 납부하지 않았으므로 대출이 불가능 합니다.");
            return 0;
        }

        return lendingBookDao.insertLending(bookId, userId, dueDate);
    }

    /**
     * 책이 존재 하는지 체크하는 함수
     */
    public boolean checkBookExist(Long bookId) {
        return checkBookExistDao.checkBookExist(bookId);
    }

    /**
     * 유저가 존재하는지 체크하는 함수
     */
    public boolean checkUserExist(Long userId) {
        return checkUserExistDao.checkUserExist(userId);
    }
}
