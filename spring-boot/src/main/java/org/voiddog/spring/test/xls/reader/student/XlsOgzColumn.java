package org.voiddog.spring.test.xls.reader.student;

import org.apache.poi.ss.usermodel.Cell;
import org.voiddog.spring.test.student.StudentDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReadUtils;

import java.util.regex.Pattern;

public class XlsOgzColumn implements XlsColumn<StudentDAO> {

    private final String name = "单位";
    private Pattern pattern = Pattern.compile("^\\s*((单位)|(学员所在的单位)|(学员单位)|(学员所在单位))");

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
        to.setOrganization(XlsReadUtils.readString(from));
    }

    @Override
    public void writeCell(StudentDAO from, Cell cell) {
        cell.setCellValue(from.getOrganization());
    }
}
