package indi.crisp.mylin.util;

import java.util.List;

/**
 * 分页对象
 * @param <T>
 */
public class Paginate<T> {
    //携带的消息
    private List<T> list;
    //当前页数
    private int index;
    //总页数
    private int countAll;
    //步长
    private int step;

    public List<T> getList() {
        return list;
    }

    public Paginate<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public Paginate<T> setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getCountAll() {
        return countAll;
    }

    public Paginate<T> setCountAll(int countAll) {
        this.countAll = countAll;
        return this;
    }

    public int getStep() {
        return step;
    }

    public Paginate<T> setStep(int step) {
        this.step = step;
        return this;
    }
}
