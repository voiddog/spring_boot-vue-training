package org.voiddog.spring.test.xls.reader.student;

import org.apache.poi.ss.usermodel.Cell;
import org.voiddog.spring.test.student.StudentDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReadUtils;

import java.util.regex.Pattern;

public class XlsNameColumn implements XlsColumn<StudentDAO> {

    private Pattern pattern;

    private final String name = "姓名";

    public XlsNameColumn() {
        pattern = Pattern.compile("^\\s*((姓名)|(学生姓名))");
    }

    @Override
    public boolean isNecessary() {
        return true;
    }

    @Override
    public boolean isMatchName(Cell cell) {
        return pattern.matcher(XlsReadUtils.readString(cell)).find();
    }

    @Override
    public void writeName(Cell to) {
        to.setCellValue(name);
    }

    @Override
    public String getColumnName() {
        return name;
    }

    @Override
    public void readCell(Cell from, StudentDAO to) {
        String value = XlsReadUtils.readString(from);
        to.setName(value);
    }

    @Override
    public void writeCell(StudentDAO from, Cell cell) {
        cell.setCellValue(from.getName());
    }
}
