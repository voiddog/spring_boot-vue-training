package org.voiddog.spring.test.xls.reader.teacher;

import org.apache.poi.ss.usermodel.Cell;
import org.voiddog.spring.test.teacher.TeacherDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReadUtils;

import java.util.regex.Pattern;

public class XlsRecordColumn implements XlsColumn<TeacherDAO> {

    private final String name = "上课记录";
    private final Pattern pattern = Pattern.compile("^\\s*((老师的?)?(上课|讲课)(记录|主题|内容))");

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
    public void readCell(Cell from, TeacherDAO to) {
        to.setRecord(XlsReadUtils.readString(from));
    }

    @Override
    public void writeCell(TeacherDAO from, Cell cell) {
        cell.setCellValue(from.getRecord());
    }
}
