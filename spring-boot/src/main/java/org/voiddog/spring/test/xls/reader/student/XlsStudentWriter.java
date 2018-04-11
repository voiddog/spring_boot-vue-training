package org.voiddog.spring.test.xls.reader.student;

import org.voiddog.spring.test.student.StudentDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsWriter;

import java.util.ArrayList;
import java.util.List;

public class XlsStudentWriter extends XlsWriter<StudentDAO> {
    @Override
    protected List<XlsColumn<StudentDAO>> registerColumnReader() {
        List<XlsColumn<StudentDAO>> ret = new ArrayList<>();
        ret.add(new XlsNameColumn());
        ret.add(new XlsGenderColumn());
        ret.add(new XlsAgeColumn());
        ret.add(new XlsPhoneColumn());
        ret.add(new XlsJobColumn());
        ret.add(new XlsOgzColumn());
        ret.add(new XlsRecordColumn());
        return ret;
    }
}
