package util;

import java.util.List;
import java.util.Scanner;

public class PaginationUtil {

    public static <T> void displayPagedList(List<T> list, int pageSize, Formatter<T> formatter) {
        Scanner scanner = new Scanner(System.in);
        int totalItems = list.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        int currentPage = 1;

        while (true) {
            int start = (currentPage - 1) * pageSize;
            int end = Math.min(start + pageSize, totalItems);
            List<T> pageItems = list.subList(start, end);

            formatter.printHeader();
            for (T item : pageItems) {
                formatter.printRow(item);
            }
            formatter.printFooter();

            System.out.printf("페이지 %d / %d\n", currentPage, totalPages);
            System.out.println("1. 이전 | 2. 다음 | 0. 종료");
            int input = scanner.nextInt();

            if (input == 1) {
                if (currentPage > 1) {
                    currentPage--;
                } else {
                    System.out.println("⚠️ 더 이상 이전 페이지가 없습니다.");
                }
            } else if (input == 2) {
                if (currentPage < totalPages) {
                    currentPage++;
                } else {
                    System.out.println("⚠️ 더 이상 다음 페이지가 없습니다.");
                }
            } else if (input == 0) {
                break;
            } else {
                System.out.println("❌ 잘못된 입력입니다.");
            }
        }
    }


    // 항목 출력 방식을 캡슐화할 인터페이스
    public interface Formatter<T> {
        void printHeader();
        void printRow(T item);
        void printFooter();
    }
}
