package org.voiddog.spring.test.xls.reader.student;

import org.apache.poi.ss.usermodel.Cell;
import org.voiddog.spring.test.model.Constants;
import org.voiddog.spring.test.student.StudentDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReadException;
import org.voiddog.spring.test.xls.reader.XlsReadUtils;

import java.util.regex.Pattern;

public class XlsGenderColumn implements XlsColumn<StudentDAO> {

    private Pattern pattern;
    private final String name = "性别";

    public XlsGenderColumn() {
        pattern = Pattern.compile("^\\s*((性别)|(学生性别))");
    }

    @Override
    public boolean isNecessary() {
        return true;
    }

    @Override
    public boolean isMatchName(Cell cell) {
        String value = XlsReadUtils.readString(cell);
        return pattern.matcher(value).find();
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
        switch (value) {
            case "男": {
                to.setGender(Constants.MALE);
                break;
            }
            case "女": {
                to.setGender(Constants.FEMALE);
                break;
            }
            default:
                throw new XlsReadException("解析性别错误：" + value);
        }
    }

    @Override
    public void writeCell(StudentDAO from, Cell cell) {
        switch (from.getGender()) {
            case Constants.MALE: {
                cell.setCellValue("男");
                break;
            }
            case Constants.FEMALE: {
                cell.setCellValue("女");
                break;
            }
        }
    }
}
