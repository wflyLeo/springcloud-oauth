package com.uooguo.newretail.cloud.framework.util.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportExcelUtil {
    private final static String Excel_2003 = ".xls"; //2003 版本的excel
    private final static String Excel_2007 = ".xlsx"; //2007 版本的excel

    /**
     * @param in
     * @param fileName
     * @return
     */
    public static List<List<Object>> importExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = null;

        //创建Excel工作簿
        Workbook work = getWorkbook(in, fileName);
        if (work == null) {
            throw new Exception("创建Excel工作簿为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<>();
        //遍历Excel中的所有sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            ////如果excel有格式，这种方式取值不准确
            int totalRow = sheet.getPhysicalNumberOfRows();
            //从第二行取值
            for (int j = sheet.getFirstRowNum() + 1; j < totalRow; j++) {
                row = sheet.getRow(j);
                if (row != null && !"".equals(row)) {
                    //获取单元格的数据是否存在
                    Cell fristCell = row.getCell(0);
                    if (fristCell != null) {
                        //遍历所有的列
                        List<Object> li = new ArrayList<>();
                        for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                            cell = row.getCell(y);
                            String callCal = getCellValue(cell) + "";
                            li.add(callCal);
                        }
                        list.add(li);
                    }
                }

            }
        }
        in.close();
        return list;
    }

    /**
     * 描述：根据文件后缀，自动适应上传文件的版本
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook work = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (Excel_2003.equals(fileType)) {
            work = new HSSFWorkbook(inStr);//2003 版本的excel
        } else if (Excel_2007.equals(fileType)) {
            work = new XSSFWorkbook(inStr);//2007 版本的excel
        } else {
            throw new Exception("解析文件格式有误！");
        }
        return work;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df1 = new DecimalFormat("0");//格式化number，string字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");//日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");//格式化数字
        if (cell != null && !"".equals(cell)) {
            switch (cell.getCellType()) {
                case STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case NUMERIC:
                    if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                        value = df1.format(cell.getNumericCellValue());
                    } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                        value = sdf.format(cell.getDateCellValue());
                    } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        value = sdf.format(date);
                    } else {
                        value = df2.format(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case BLANK:
                    value = "";
                    break;
                default:
                    break;
            }
        }
        return value;
    }

    public String getFormat(String str) {
        if (str.equals("null")) {
            str = "";
            return str;
        } else {
            return str;
        }
    }

    public static Integer getFormats(Integer str) {
        if (str == null) {
            str = 0;
            return str;
        } else {
            return str;
        }
    }

}
