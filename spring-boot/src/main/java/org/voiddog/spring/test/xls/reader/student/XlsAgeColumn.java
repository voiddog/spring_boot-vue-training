package org.voiddog.spring.test.xls.reader.student;

import org.apache.poi.ss.usermodel.Cell;
import org.voiddog.spring.test.student.StudentDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReadUtils;

import java.util.regex.Pattern;

public class XlsAgeColumn implements XlsColumn<StudentDAO> {

    private final String name = "年龄";
    private final Pattern pattern = Pattern.compile("^\\s*((学员的?)?年龄)");

    @Override
    public boolean isNecessary() {
        return false;
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
        to.setAge((int)XlsReadUtils.readDouble(from));
    }

    @Override
    public void writeCell(StudentDAO from, Cell cell) {
        cell.setCellValue(from.getAge());
    }
}
