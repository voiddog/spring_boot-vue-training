package org.voiddog.spring.test.xls.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public abstract class XlsWriter<T> {

    protected List<XlsColumn<T>> xlsColumnList;

    public XlsWriter() {
        xlsColumnList = registerColumnReader();
    }

    public void writeAll(Sheet sheet, List<T> dataList) {
        // write name
        Row row = sheet.createRow(0);
        for (int i = 0, size = xlsColumnList.size(); i < size; ++i) {
            Cell cell = row.createCell(i);
            cell.setCellValue(xlsColumnList.get(i).getColumnName());
        }
        for (int i = 1, size = dataList.size(); i <= size; ++i) {
            row = sheet.createRow(i);
            T data = dataList.get(i - 1);
            for (int j = 0, cSize = xlsColumnList.size(); j < cSize; ++j) {
                Cell cell = row.createCell(j);
                xlsColumnList.get(j).writeCell(data, cell);
            }
        }
    }

    protected abstract List<XlsColumn<T>> registerColumnReader();
}
