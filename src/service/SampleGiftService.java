package service;

import model.dao.SampleGiftDao;
import model.dto.SampleGiftDto;

import java.util.List;

public class SampleGiftService {
    private final SampleGiftDao sampleGiftDao;

    public SampleGiftService() {
        this.sampleGiftDao = new SampleGiftDao();
    }

    /**
     * 상품 업데이트를 처리
     *
     * @param name   상품 이름
     * @param gStart 시작 값
     * @param gEnd   끝 값
     * @param gno    상품 번호
     */
    public int updateGift(String name, int gStart, int gEnd, int gno) {
        // 비즈니스 로직을 이곳에서 추가할 수 있습니다.
        // 예: 유효성 검사, 값 체크 등
        return sampleGiftDao.updateGift(name, gStart, gEnd, gno);
    }

    /**
     * 상품 생성을 처리
     *
     * @param sampleGiftDto 상품 이름
     */
    public int insertGift(SampleGiftDto sampleGiftDto) {
        // 비즈니스 로직을 이곳에서 추가할 수 있습니다.
        // 예: 유효성 검사, 값 체크 등
        if (sampleGiftDto.getName() == null || sampleGiftDto.getName().isBlank()) {
            System.out.println("❌ 상품 이름이 비어있습니다.");
            return 0;
        }

        return sampleGiftDao.insertGift(sampleGiftDto);
    }

    /**
     * 상품 조회를 처리
     *
     * @param
     */
    public List<SampleGiftDto> getAllGifts() {
        // 비즈니스 로직을 이곳에서 추가할 수 있습니다.
        // 예: 유효성 검사, 값 체크 등
        return sampleGiftDao.getAllGifts();
    }
}
