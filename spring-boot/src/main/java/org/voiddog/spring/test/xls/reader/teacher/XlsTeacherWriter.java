package org.voiddog.spring.test.xls.reader.teacher;

import org.voiddog.spring.test.teacher.TeacherDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsWriter;

import java.util.ArrayList;
import java.util.List;

public class XlsTeacherWriter extends XlsWriter<TeacherDAO> {

    @Override
    protected List<XlsColumn<TeacherDAO>> registerColumnReader() {
        List<XlsColumn<TeacherDAO>> ret = new ArrayList<>();
        ret.add(new XlsNameColumn());
        ret.add(new XlsGenderColumn());
        ret.add(new XlsAgeColumn());
        ret.add(new XlsLevelColumn());
        ret.add(new XlsOgzColumn());
        ret.add(new XlsPhoneColumn());
        ret.add(new XlsTypeColumn());
        ret.add(new XlsRecordColumn());
        ret.add(new XlsCostColumn());
        return ret;
    }
}
