package indi.crisp.mylin.config;

/**
 * 应用程序中的各种常量
 */
public enum AppEnum {
    REMARK_INSERT_YES(1001, "新增便签成功"),
    REMARK_INSERT_NO(1002, "无法新增便签"),
    REMARK_DELETE_YES(1003, "删除便签成功"),
    REMARK_DELETE_NO(1004, "删除便签失败，原因可能为 找不到需要删除的便签"),
    REMARK_UPDATE_YES(1005, "更新便签成功"),
    REMARK_UPDATE_NO(1006, "更新便签失败, 原因可能是找不到需要删除的便签"),




    ERROR_REMARK_NULL(1101, "需要持久化的便签为空"),
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
