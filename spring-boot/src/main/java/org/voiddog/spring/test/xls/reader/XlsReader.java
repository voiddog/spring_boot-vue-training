package org.voiddog.spring.test.xls.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.voiddog.spring.test.model.MessageException;

import java.util.ArrayList;
import java.util.List;

public abstract class XlsReader<T> {

    private List<XlsColumn<T>> columnReaderList;

    public XlsReader() {
        columnReaderList = registerColumnReader();
    }

    public List<T> parseAll(Sheet sheet) {
        Row row = sheet.getRow(0);
        if (row == null) {
            throw new MessageException("属性栏为空");
        }
        XlsColumn<T>[] columnReaders = new XlsColumn[sheet.getRow(0).getLastCellNum()];

        // match column readers
        for (int i = row.getFirstCellNum(), cSize = row.getLastCellNum(); i < cSize; ++i) {
            Cell cell = row.getCell(i);
            for (XlsColumn<T> reader : columnReaderList) {
                if (reader.isMatchName(cell)) {
                    columnReaders[i] = reader;
                    columnReaderList.remove(reader);
                    break;
                }
            }
            if (columnReaders[i] == null) {
                // not found
                throw new MessageException("属性栏: " + XlsReadUtils.readString(cell) + " 无法解析");
            }
        }

        for (XlsColumn<T> reader : columnReaderList) {
            if (reader.isNecessary()) {
                throw new MessageException("缺少属性: " + reader.getColumnName());
            }
        }

        List<T> ret = new ArrayList<>();
        for (int i = sheet.getFirstRowNum() + 1, rSize = sheet.getLastRowNum(); i <= rSize; ++i) {
            T t= newRowInstance();
            row = sheet.getRow(i);
            for (int j = 0; j < columnReaders.length; ++j) {
                XlsColumn<T> reader = columnReaders[j];
                try {
                    reader.readCell(row.getCell(j), t);
                } catch (XlsReadException e) {
                    throw new MessageException(String.format("读取(%d, %d)出错：%s", i+1, j+1, e.getMessage()));
                }
            }
            ret.add(t);
        }
        return ret;
    }

    protected abstract T newRowInstance();

    protected abstract List<XlsColumn<T>> registerColumnReader();
}
