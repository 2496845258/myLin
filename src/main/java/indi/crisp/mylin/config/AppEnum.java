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

    /**
     * 查询一个code对应的消息
     * @param code
     * @return
     */
    public String find(int code) {
        for ( var i : AppEnum.values() ) {
            if ( code == i.getCode() ) {
                return i.getDescribe();
            }
        }
        return null;
    }
}
