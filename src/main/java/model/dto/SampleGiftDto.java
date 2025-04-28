package model.dto;

import lombok.Data;

@Data
public class SampleGiftDto {
    private int gno;
    private String name;
    private int g_start;
    private int g_end;

    public SampleGiftDto() {
    }

    public SampleGiftDto(int gno, String name, int g_start, int g_end) {
        this.gno = gno;
        this.name = name;
        this.g_start = g_start;
        this.g_end = g_end;
    }
}
