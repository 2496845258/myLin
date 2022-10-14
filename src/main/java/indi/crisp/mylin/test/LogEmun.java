package indi.crisp.mylin.test;

public enum LogEmun {
    LOGIN_SUCCESS("2001","登录成功"),
    LOGIN_FAIL("2002","登录失败"),
    ;

    private String code;
    private String msg;

    LogEmun(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
