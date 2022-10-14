package indi.crisp.mylin.test;

public enum MyEmun {
    ADD_BOOK_YES(123,"添加书籍成功"),
    ADD_BOOK_NO(124,"添加书籍失败");

    private String msg;
    private int code;

    MyEmun(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
