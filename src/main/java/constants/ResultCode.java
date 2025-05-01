package constants;

public class ResultCode {
    public static final int IS_SUCCESS = 1;      // 성공
    public static final int IS_FAIL = 0;         // 실패 (예: 조건 미충족)
    public static final int IS_ERROR = -1;       // 예외나 시스템 오류

    public static final int LATE_FEE_LOGS_DELETE_SUCCESS = 0;
    public static final int LATE_FEE_LOGS_DELETE_FAIL = -1;
    public static final int LATE_FEE_LOGS_DELETE_ERROR = -2;

    public static final int GET_LATE_FEE_LOGS_FAIL = -1;
}
