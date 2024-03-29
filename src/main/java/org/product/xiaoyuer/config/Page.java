package org.product.xiaoyuer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 封装分页相关信息
 */
public class Page {
//    当前页码
    private int current;
//    显示上限
    private int limit;
//    总数据数
    private int rows;
//    查询路径
    private String path;


    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current =current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 50) {
            this.limit = limit;
        }

    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
//    获取当前页起始行
    public int getOffset() {
        return (current-1) * limit;
    }
//    获取总页数
    public int getTotal() {
        if (rows % limit > 0) {
            return rows % limit +1;
        }else{
            return rows % limit;
        }
    }
//  获取开始页码
    public int getFrom() {
        int from = current - 3;
        return from > 1 ? from : 1;
    }
//    获取结束页码
    public int getTo() {
        int to = current + 3;
        int total = getTotal();
        return to >total ? total : to;
    }
}
