package controller;

import model.SampleGift;
import service.SampleGiftService;

import java.util.List;

public class SampleGiftController {
    private final SampleGiftService sampleGiftService;

    public SampleGiftController() {
        this.sampleGiftService = new SampleGiftService();
    }

    // 상품 업데이트 처리
    public void updateGift(String name, int gStart, int gEnd, int gno) {
        int result = sampleGiftService.updateGift(name, gStart, gEnd, gno);
        if (result == 1) {
            System.out.println("🎉 상품이 성공적으로 수정 되었습니다!");
        } else if (result == 0) {
            System.out.println("⚠️ 상품 수정에 실패했습니다. 다시 시도해보세요.");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 상품 생성 처리
    public void insertGift(SampleGift sampleGift) {
        int result = sampleGiftService.insertGift(sampleGift);
        if (result == 1) {
            System.out.println("🎉 상품이 성공적으로 등록되었습니다!");
        } else if (result == 0) {
            System.out.println("⚠️ 상품 등록에 실패했습니다. 다시 시도해보세요.");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 전체 상품 조회
    public void getAllGift() {
        List<SampleGift> giftList = sampleGiftService.getAllGifts();
        giftList.forEach(g -> System.out.printf("%d %s %d %d\n",
                g.getGno(), g.getName(), g.getG_start(), g.getG_end()));
    }
}
