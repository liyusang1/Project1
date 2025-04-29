package view;

import controller.LendingBookController;
import controller.SampleGiftController;
import model.dto.SampleGiftDto;
import util.DateConverter;

public class Main {
    public static void main(String[] args) {


        //TODO 콘솔로 view형식에 맞게 바꿔야함

        // 상품컨트롤러 연결
        SampleGiftController controller = new SampleGiftController();

        // 상품 업데이트
        //controller.updateGift("엄마는외계인", 6666, 6666, 1);

        // 상품 생성
        SampleGiftDto sampleGiftDto = new SampleGiftDto(44, "레인보우샤베트", 652, 442);
        //controller.insertGift(sampleGiftDto);

        //상품조회
        //controller.getAllGift();

        //대출하기
        LendingBookController lendingBookController = new LendingBookController();
        lendingBookController.insertLending(2L, 1L, DateConverter.parseToLocalDateTime("2025.04.29"));

    }
}
