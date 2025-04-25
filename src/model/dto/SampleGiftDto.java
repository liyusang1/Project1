package model.dto;

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

    public int getGno() {
        return gno;
    }

    public void setGno(int gno) {
        this.gno = gno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getG_start() {
        return g_start;
    }

    public void setG_start(int g_start) {
        this.g_start = g_start;
    }

    public int getG_end() {
        return g_end;
    }

    public void setG_end(int g_end) {
        this.g_end = g_end;
    }
}
