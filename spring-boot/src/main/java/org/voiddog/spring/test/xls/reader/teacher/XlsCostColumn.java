package org.voiddog.spring.test.xls.reader.teacher;

import org.apache.poi.ss.usermodel.Cell;
import org.voiddog.spring.test.teacher.TeacherDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReadUtils;

import java.util.regex.Pattern;

public class XlsCostColumn implements XlsColumn<TeacherDAO> {

    private final String name = "讲课费";
    private Pattern pattern = Pattern.compile("^\\s*((老师的?)?(讲课)?(上课)?费用?)");

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
    public void readCell(Cell from, TeacherDAO to) {
        to.setCost(XlsReadUtils.readFloat(from));
    }

    @Override
    public void writeCell(TeacherDAO from, Cell cell) {
        cell.setCellValue(from.getCost());
    }
}
