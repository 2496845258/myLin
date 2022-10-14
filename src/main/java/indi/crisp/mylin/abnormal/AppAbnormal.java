package indi.crisp.mylin.abnormal;

import indi.crisp.mylin.config.AppEnum;

/**
 * @author luzhao
 * 自定义异常类
 * 产生的异常最终在控制层收集，利于反馈前端和重定向之类的操作
 */
public class AppAbnormal extends Exception{

    private AppEnum appEnum;

    public AppAbnormal(AppEnum appEnum) {
        super(appEnum.getDescribe());
        this.appEnum = appEnum;
    }

    public AppEnum getAppEnum() {
        return appEnum;
    }
}
