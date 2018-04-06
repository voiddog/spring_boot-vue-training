package org.voiddog.spring.test.xls.reader;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XlsReadUtils {

    public static String readString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellTypeEnum()) {
            //数字
            case NUMERIC:{
                //日期格式的处理
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                }
                return String.valueOf(cell.getNumericCellValue());
            }
            //字符串
            case STRING: {
                return cell.getStringCellValue();
            }
            //公式
            case FORMULA: {
                return cell.getCellFormula();
            }
            //空白
            case BLANK: {
                return "";
            }
            //布尔取值
            case BOOLEAN: {
                return cell.getBooleanCellValue() + "";
            }
            //错误类型
            case ERROR: {
                return cell.getErrorCellValue() + "";
            }
            default:{
                return "";
            }
        }
    }

    public static float readFloat(Cell cell) {
        return readFloat(cell, true, 0);
    }

    public static float readFloat(Cell cell, float failureValue) {
        return readFloat(cell, false, failureValue);
    }

    public static float readFloat(Cell cell, boolean failureThrow, float failureValue) {
        return (float) readDouble(cell, failureThrow, failureValue);
    }

    public static double readDouble(Cell cell) {
        return readDouble(cell, true, 0);
    }

    public static double readDouble(Cell cell, double failureValue) {
        return readDouble(cell, false, failureValue);
    }

    public static double readDouble(Cell cell, boolean failureThrow, double failureValue) {
        if (cell == null) {
            if (failureThrow) {
                throw new XlsReadException("cell is null");
            }
            return failureValue;
        }
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date data = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    return data.getTime();
                }
                return cell.getNumericCellValue();
            case STRING:
                String value = cell.getStringCellValue();
                try {
                    return Double.parseDouble(value);
                } catch (Exception ignore) {
                    if (failureThrow) {
                        throw new XlsReadException("read: " + value + " to double error");
                    }
                    return failureValue;
                }
            default:
                if (failureThrow) {
                    throw new XlsReadException("cell type: " + cell.getCellTypeEnum() + " not support");
                }
                return failureValue;
        }
    }
}
