package indi.crisp.mylin.util;

/**
 * 用于增强方法返回值的通信能力
 */
public class Feedback<T> implements Cloneable{
    private static Feedback feedback = new Feedback();
    private int statusCode;
    private String explain;
    private T result;


    /**
     * 通过对象复制一个空对象，进行消息传递，不走构造器增加效率
     * @return
     */
    public static Feedback get() {
        try {
            return (Feedback) feedback.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Feedback setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getExplain() {
        return explain;
    }

    public Feedback setExplain(String explain) {
        this.explain = explain;
        return this;
    }

    public T getResult() {
        return (T) result;
    }

    public Feedback setResult(T result) {
        this.result = result;
        return this;
    }
}
