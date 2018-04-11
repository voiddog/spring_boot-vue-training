package org.voiddog.spring.test.xls.reader.student;

import org.voiddog.spring.test.student.StudentDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReader;

import java.util.ArrayList;
import java.util.List;

public class XlsStudentReader extends XlsReader<StudentDAO> {

    @Override
    protected StudentDAO newRowInstance() {
        return new StudentDAO();
    }

    @Override
    protected List<XlsColumn<StudentDAO>> registerColumnReader() {
        List<XlsColumn<StudentDAO>> ret = new ArrayList<>();
        ret.add(new XlsAgeColumn());
        ret.add(new XlsGenderColumn());
        ret.add(new XlsJobColumn());
        ret.add(new XlsNameColumn());
        ret.add(new XlsOgzColumn());
        ret.add(new XlsPhoneColumn());
        ret.add(new XlsRecordColumn());
        return ret;
    }
}
