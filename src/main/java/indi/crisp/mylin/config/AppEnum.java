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

    EMPLOYEE_ACCOUNT_SAME(1007,"该账号已被注册"),
    EMPLOYEE_ACCOUNT_NULL(1008,"该账号不存在"),
    EMPLOYEE_REGISTER_YES(1009,"注册成功"),
    EMPLOYEE_REGISTER_NO(1010,"注册失败"),

    EMPLOYEE_LOGIN_YES(1009,"登录成功"),
    EMPLOYEE_LOGIN_NO(1010,"登录失败"),
    EMPLOYEE_LEAVE(1011,"员工已离职"),
    EMPLOYEE_PASSWORD_WRONG(1012,"密码错误"),

    DEPT_INSERT_YES(1013,"新增部门成功"),
    DEPT_INSERT_NO(1014,"新增部门失败"),
    DEPT_UPDATE_YES(1015,"部门更新成功"),
    DEPT_UPDATE_NO(1016,"部门更新失败"),
    DEPT_FIND_YES(1017,"部门查询成功"),
    DEPT_FIND_NO(1018,"部门查询失败"),
    PERM_FIND_YES(1019,"权限查询成功"),
    PERM_FIND_NO(1020,"权限查询失败"),
    DICT_FIND_YES(1021, "字典表查询成功"),
    DICT_FIND_NO(1022, "字典表查询失败"),
    LEAVE_INSERT_YES(1023, "新增一条请假记录成功"),
    LEAVE_INSERT_NO(1024, "新增一条请假记录失败"),
    LEAVE_UPDATE_YES(1025, "更新请假记录成功"),
    LEAVE_UPDATE_NO(1026, "更新请假记录失败"),
    EMP_INSERT_YES(1027,"员工新增成功"),
    EMP_INSERT_NO(1028,"员工新增失败"),
    EMP_UPDATE_YES(1029,"员工信息更新成功"),
    EMP_UPDATE_NO(1030,"员工信息更新失败"),
    LOG_INSERT_YSE(1031, "日志新增成功"),
    LOG_INSERT_NO(1032, "日志新增失败"),
    NOTIFY_INSERT_YES(1033,"通知新增成功"),
    NOTIFY_INSERT_NO(1034,"通知新增失败"),
    EMP_FIND_ID_YES(1035,"根据员工id查看员工信息成功"),
    EMP_FIND_ID_NO(1036,"根据员工id查看员工信息失败"),



    ERROR_REMARK_NULL(2101, "需要持久化的便签为空"),
    ERROR_ACCOUNT_NULL(2102,"账号为空"),
    ERROR_PASSWORD_NULL(2103,"密码为空"),
    ERROR_DEPT_NULL(2104, "新增部门为空"),
    ERROR_DEPT_ID(2105,"部门编号异常"),
    ERROR_DEPT_FIND_LIST(2106,"部门分页查询，条件异常"),
    ERROR_ROLE_ID(2107, "角色id异常"),
    ERROR_DICT_ID(2108, "字典表查询id异常"),
    ERROR_LEAVE_NULL(2109, "需要持久化的请假记录为null"),
    ERROR_LEAVE_FIND_LIST(2110,"请假分页查询，条件异常"),
    ERROR_EMP_NULL(2111,"需要持久化的员工为null"),
    ERROR_LOG_FIND_LIST(2112,"日志分页查询，条件异常"),
    ERROR_LOG_NULL(2113,"需要持久化的日志为null"),
    ERROR_REMARK_FIND_LIST(2114,"便签分页查询，条件异常"),
    ERROR_NOTIFY_FIND_LIST(2115,"通知分页查询，条件异常"),
    ERROR_NOTIFY_NULL(2116,"需要持久化的通知为null"),
    ERROR_NOTIFY_FIND_TAR(2117,"查找两个人的消息，条件异常"),
    ERROR_EMP_FIND_ID(2118,"查找员工信息的id异常"),

    TEST(0001, "测试"),
    ;

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
