package org.voiddog.spring.test.xls.reader.teacher;

import org.voiddog.spring.test.teacher.TeacherDAO;
import org.voiddog.spring.test.xls.reader.XlsColumn;
import org.voiddog.spring.test.xls.reader.XlsReader;

import java.util.ArrayList;
import java.util.List;

public class XlsTeacherReader extends XlsReader<TeacherDAO> {

    @Override
    protected TeacherDAO newRowInstance() {
        return new TeacherDAO();
    }

    @Override
    protected List<XlsColumn<TeacherDAO>> registerColumnReader() {
        List<XlsColumn<TeacherDAO>> readerList = new ArrayList<>();
        readerList.add(new XlsNameColumn());
        readerList.add(new XlsGenderColumn());
        readerList.add(new XlsAgeColumn());
        readerList.add(new XlsLevelColumn());
        readerList.add(new XlsOgzColumn());
        readerList.add(new XlsPhoneColumn());
        readerList.add(new XlsTypeColumn());
        readerList.add(new XlsRecordColumn());
        readerList.add(new XlsCostColumn());
        return readerList;
    }
}
