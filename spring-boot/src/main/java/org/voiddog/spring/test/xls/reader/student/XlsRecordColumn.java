package org.voiddog.spring.test.xls.reader.student;

import org.apache.poi.ss.usermodel.Cell;
import org.voiddog.spring.test.student.StudentDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReadUtils;

import java.util.regex.Pattern;

public class XlsRecordColumn implements XlsColumn<StudentDAO> {

    private final String name = "上课记录";
    private final Pattern pattern = Pattern.compile("^\\s*((学生的?)?(上课|讲课)(记录))");

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
        to.setRecord(XlsReadUtils.readString(from));
    }

    @Override
    public void writeCell(StudentDAO from, Cell cell) {
        cell.setCellValue(from.getRecord());
    }
}
