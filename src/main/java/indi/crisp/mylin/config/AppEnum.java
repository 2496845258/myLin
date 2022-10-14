package indi.crisp.mylin.config;

/**
 * 应用程序中的各种常量
 */
public enum AppEnum {
    TEST(0001, "测试");

    private int code;
    private String describe;
    AppEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    public int getCode() {
        return code;
    }
    public String getDescribe() {
        return describe;
    }
}
