package entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装对象
 *
 * @Author Giovani
 * @Create: 2018/8/5 15:16
 */
public class PageResult implements Serializable {

    private long total; // 分页总记录数
    private List rows; // 当前页结果

    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
