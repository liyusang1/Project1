package service;

import constants.LendingStatus;
import model.dao.CheckBookExistDao;
import model.dao.CheckUserExistDao;
import model.dao.LendingBookDao;
import model.dto.LendingBookDto;

import java.time.LocalDateTime;
import java.util.List;

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

        // bookId, userId, dueDate 유효성 검사
        if (bookId == null) {
            System.out.println("⚠️ 책 번호가 입력되지 않았습니다.");
            return 0;
        }

        if (userId == null) {
            System.out.println("⚠️ 유저 번호가 입력되지 않았습니다.");
            return 0;
        }

        if (dueDate == null) {
            System.out.println("⚠️ 만기 기한이 입력되지 않았습니다.");
            return 0;
        }

        //책 유효성 검사
        if (!checkBookExist(bookId)) {
            System.out.println("⚠️ 존재하지 않는 책 번호이거나 대출 중인 책 입니다.");
            return 0;
        }

        //유저 유효성 검사
        if (!checkUserExist(userId)) {
            System.out.println("⚠️ 존재하지 않는 유저 입니다.");
            return 0;
        }

        //현재 대출 중인지 체크
        if (!lendingBookDao.checkUserLendingExist(userId)) {
            System.out.println("⚠️ 해당 유저는 현재 대출 중 입니다. 책은 최대 4권까지 대출할 수 있습니다.");
            return 0;
        }

        //연체로인한 벌금 지불 대상자인지 체크 -> 연체로 인해 발생한 벌금을 납부하지않았다면 도서 시스템 이용 불가
        if (!lendingBookDao.checkUsersLateFee(userId)) {
            System.out.println("⚠️ 해당 유저는 연체로 인한 벌금을 납부하지 않았으므로 대출이 불가능 합니다.");
            return 0;
        }

        //대여 상태를 대출불가능 상태로 변경
        lendingBookDao.updateBookStatus(bookId, LendingStatus.IS_BORROWED);
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

    /**
     * 도서 반납
     *
     * @param userId    유저pk
     * @param lendingId 대출pk
     */
    public int returnBook(Long lendingId, Long userId) {

        if (lendingId == null) {
            System.out.println("⚠️ 대출 번호가 입력되지 않았습니다.");
            return 0;
        }

        if (userId == null) {
            System.out.println("⚠️ 유저 번호가 입력되지 않았습니다.");
            return 0;
        }

        if (!lendingBookDao.checkLendingExist(userId, lendingId)) {
            System.out.println("⚠️ 올바른 lendingId 입력하세요.");
            return 0;
        }

        Long bookId = lendingBookDao.getBookIdFromLendingId(lendingId);
        if (bookId == -1L) {
            return -1;
        }

        //대여 상태를 대출가능 상태로 변경
        lendingBookDao.updateBookStatus(bookId, LendingStatus.IS_RETURNED);

        //도서 연체로 인해 벌금을 부과해야 하는지
        int penaltyRequired = lendingBookDao.isPenaltyRequired(lendingId);
        if (penaltyRequired > 1) {
            lendingBookDao.applyPenalty(userId,penaltyRequired);
            System.out.println("⚠️ 연체로 인해 벌금이 부과됩니다. 벌금을 납부하기 전까지 대여를 하실 수 없습니다.");
        }

        return lendingBookDao.returnBooks(lendingId);
    }

    /**
     * 대여중인 책 조회
     *
     * @param
     */
    public List<LendingBookDto> getLendingList(Long userId) {
        return lendingBookDao.getLendingList(userId);
    }

    /**
     * 연체금액 조회
     *
     * @param
     */
    public int getAllLateFee(Long userId) {
        return lendingBookDao.getAllLateFee(userId);
    }
}
