package Demo.utils;

public enum ResponseCode {

    /**
     * 成功
     */

    SUCCESS(0, "SUCCESS"),

    /**
     * 一般错误
     */

    ERROR(-1, "ERROR"),

    /**
     * 权限不足
     */

    PERMISSION_DENIED(-2, "PERMISSION_DENIED"),

    /**
     * 缺少参数
     */

    MISSING_ARGUMENT(-3, "MISSING_ARGUMENT"),

    /**
     * 参数非法
     */

    ILLEGAL_ARGUMENT(-4, "ILLEGAL_ARGUMENT");



    private int status;
    private String msg;

    ResponseCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    ResponseCode() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
