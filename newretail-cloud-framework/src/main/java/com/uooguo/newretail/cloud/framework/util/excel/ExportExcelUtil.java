package com.uooguo.newretail.cloud.framework.util.excel;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Cheung on 2017/12/19.
 * <p>
 * Apache POI操作Excel对象
 * HSSF：操作Excel 2007之前版本(.xls)格式,生成的EXCEL不经过压缩直接导出
 * XSSF：操作Excel 2007及之后版本(.xlsx)格式,内存占用高于HSSF
 * SXSSF:从POI3.8 beta3开始支持,基于XSSF,低内存占用,专门处理大数据量(建议)。
 * <p>
 * 注意:
 * 值得注意的是SXSSFWorkbook只能写(导出)不能读(导入)
 * <p>
 * 说明:
 * .xls格式的excel(最大行数65536行,最大列数256列)
 * .xlsx格式的excel(最大行数1048576行,最大列数16384列)
 */
public class ExportExcelUtil {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";// 默认日期格式
    public static final int DEFAULT_COLUMN_WIDTH = 17;// 默认列宽
    public static final int DEFAULT_COUNT = 500000; //每个sheet显示50W行

    public static void exportExcel(HttpServletResponse response, LinkedHashMap<String, String> headMap, JSONArray dataArray) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setContentType("application/vnd.ms-excel");
        //防止ajax接受到的中文信息乱码
        response.setCharacterEncoding("utf-8");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(UUID.randomUUID().toString() + ".xlsx", "utf-8"));
        exportExcel(headMap, dataArray, response.getOutputStream());
    }

    /**
     * 导出Excel(.xlsx)格式
     *
     * @param headMap   表格头信息集合
     * @param dataArray 数据数组
     * @param os        文件输出流
     */
    public static void exportExcel(LinkedHashMap<String, String> headMap, JSONArray dataArray, OutputStream os) throws IOException {
        String datePattern = DEFAULT_DATE_PATTERN;
        int minBytes = DEFAULT_COLUMN_WIDTH;

        int rowaccess = 1000;//内存中缓存记录行数
        /**
         * 声明一个工作薄
         */
        SXSSFWorkbook workbook = new SXSSFWorkbook(rowaccess);// 大于1000行时会把之前的行写入硬盘
        workbook.setCompressTempFiles(true);

        // head样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        headerStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());// 设置颜色
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);// 前景色纯色填充
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        // 单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        Font cellFont = workbook.createFont();
        cellFont.setBold(false);
        cellStyle.setFont(cellFont);

        SXSSFSheet sheet = null;
        int[] colWidthArr = null;// 列宽数组
        String[] headKeyArr = null;// headKey数组
        String[] headValArr = null;// headVal数组
        /**
         * 遍历数据集合，产生Excel行数据
         */
        int rowIndex = 0;
        int pageRowNo = 0; // 页行号

        for (Object obj : dataArray) {
            // 每50W一个sheet
            if (rowIndex % DEFAULT_COUNT == 0) {
                /**
                 * 生成一个(带名称)表格
                 */
                sheet = workbook.createSheet("sheet" + (rowIndex / DEFAULT_COUNT + 1));
                // 指定当前sheet
                workbook.setActiveSheet(rowIndex / DEFAULT_COUNT);
                sheet.createFreezePane(0, 1, 1, 1);// (单独)冻结前三行
                // 每当新建了工作表就将当前工作表的行号重置为1
                pageRowNo = 1;

                /**
                 * 生成head相关信息+设置每列宽度
                 */
                colWidthArr = new int[headMap.size()];// 列宽数组
                headKeyArr = new String[headMap.size()];// headKey数组
                headValArr = new String[headMap.size()];// headVal数组
                int i = 0;
                for (Map.Entry<String, String> entry : headMap.entrySet()) {
                    headKeyArr[i] = entry.getKey();
                    headValArr[i] = entry.getValue();
                    int bytes = headKeyArr[i].getBytes().length;
                    colWidthArr[i] = bytes < minBytes ? minBytes : bytes;
                    i++;
                }
                // 生成title+head信息
                SXSSFRow headerRow = sheet.createRow(0);// head行
                for (int j = 0; j < headValArr.length; j++) {
                    headerRow.createCell(j).setCellValue(headValArr[j]);
                    headerRow.getCell(j).setCellStyle(headerStyle);
                }
            }

            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
            // 生成数据
            SXSSFRow dataRow = sheet.createRow(pageRowNo++);// 创建行
            for (int k = 0; k < headKeyArr.length; k++) {
                SXSSFCell cell = dataRow.createCell(k);// 创建单元格
                Object o = jo.get(headKeyArr[k]);
                String cellValue = "";

                if (o == null) {
                    cellValue = "";
                } else if (o instanceof Date) {
                    cellValue = new SimpleDateFormat(datePattern).format(o);
                } else if (o instanceof Float || o instanceof Double) {
                    cellValue = new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                } else {
                    cellValue = o.toString();
                }
                cell.setCellValue(cellValue);
                colWidthArr[k] = colWidthArr[k] < cellValue.length() ? cellValue.length() : colWidthArr[k];
                sheet.setColumnWidth(k, (colWidthArr[k] + 3) * 256);// 设置列宽
                cell.setCellStyle(cellStyle);
            }

            //每当行数达到设置的值就刷新数据到硬盘,以清理内存
            if (rowIndex % rowaccess == 0) {
                sheet.flushRows();
            }
            rowIndex++;
        }

        try {
            workbook.write(os);
            workbook.dispose();// 释放workbook所占用的所有windows资源
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.flush();// 刷新此输出流并强制将所有缓冲的输出字节写出
                    os.close();// 关闭流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
