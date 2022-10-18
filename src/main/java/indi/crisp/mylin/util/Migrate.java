package indi.crisp.mylin.util;

import java.lang.reflect.InvocationTargetException;

public class Migrate {
    /**
     * 将子类和父类直接数据进行迁移
     * @param source-源
     * @param target-目标
     */
    public static void change(Object source, Object target) {
        for ( var mothed : source.getClass().getMethods() ) {
            if ( mothed.getName().matches("get.*") ) {
                var t1 = mothed.getName().substring(3);
                if (t1.equals("Class")){
                    continue;
                }
                try {
                    var t2 = target.getClass().getMethod("set"+t1, mothed.getReturnType());
                    t2.invoke(target, mothed.invoke(source));
                } catch (Exception e) {
                }
            }
        }
    }
}
