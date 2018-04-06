package org.voiddog.spring.test.xls.reader;

import org.apache.poi.ss.usermodel.Cell;

public interface XlsColumn<T> {

    /**
     * 该列是否为必须列
     * @return
     */
    boolean isNecessary();

    /**
     * 读取名字
     * @return
     */
    boolean isMatchName(Cell cell);

    /**
     * 写回名字
     * @return
     */
    void writeName(Cell to);

    /**
     * 列名, log 用
     * @return
     */
    String getColumnName();

    /**
     * read from cell to T
     * @param from
     * @param to
     */
    void readCell(Cell from, T to);

    /**
     * write T to cell
     * @param from
     * @return
     */
    void writeCell(T from, Cell cell);
}
