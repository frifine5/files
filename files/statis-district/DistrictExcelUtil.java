package com.poi.t;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DistrictExcelUtil {

    public static void chg2sqlFile(List<String[]> list, String sqlFile){
        if(null == list || list.size()<0){
            System.out.println("入参为空");
            return;
        }
        int pageSize = 100;
        int llen = list.size();
        int pages = llen/pageSize==0?1:llen%pageSize>0?(llen/pageSize)+1:llen/pageSize;
        String[] sqlRow = new String[pages];
        String sqlAhead = "insert into district values "; // ('', '')
        for (int i = 0; i < pages; i++) {
            StringBuffer sqlRowBf = new StringBuffer(sqlAhead);
            int end = i<(pages-1)?(i+1)*pageSize:llen;      // 确定循环的边界索引
            for (int j = i*pageSize; j < end; j++) {
                String[] ecn = list.get(j);
                if(ecn.length < 2){
                    continue;
                }else{
                    sqlRowBf.append("('").append(ecn[0].substring(0,6)).append("', '").append(ecn[1]).append("')");
                    if(j == (end-1)){
                        sqlRowBf.append(";");
                    }else{
                        sqlRowBf.append(", ");
                    }
                }
            }
            sqlRow[i] = sqlRowBf.toString();
        }

        StringBuffer finalBuff = new StringBuffer();
        for (int i = 0; i < pages; i++) {
            finalBuff.append(sqlRow[i]).append("\n");
        }

        // 写入指定文件
        WriteStringToFile(finalBuff.toString(),sqlFile);
    }

    /*
        原测试数据路径     String fph = "D:\\workspace\\gitrp\\gitfiles\\a\\files\\statis-district.xlsx";
        // c://Users//49762//Desktop//statis-district.xlsx";
        // D:\workspace\gitrp\gitfiles\a\files
     */
    /**
     * 传入excel文件全路径名，读取其中所有地区项：excel格式制式[代码，地区]
     */
    public static List getDistrictList(String fileWhileName) {

        File f = new File(fileWhileName);
        if (!checkFile(f)) {
            System.out.println("文件["+fileWhileName+"]不存在");
            return null;
        }
        Workbook wkbk = getWorkBook(f);
        List<String[]> list = new ArrayList<>();
        if (null != wkbk) {
            int page = wkbk.getNumberOfSheets();
            for (int p = 0; p < page; p++) {
                Sheet sheet = wkbk.getSheetAt(p);
                if (null == sheet) continue;
                int firRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                for (int i = firRowNum + 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (null == row) continue;
                    int firCellNum = row.getFirstCellNum();
                    int lastCellNum = row.getLastCellNum();
                    String[] cells = new String[row.getLastCellNum()];
                    for (int j = firCellNum; j < lastCellNum; j++) {
                        Cell cell = row.getCell(j);
                        cells[j] = getCellEValue(cell);
                    }
//                    System.out.println(Arrays.toString(cells));
                    list.add(cells);
                }
            }
        }
        System.out.println("current district mount: " + list.size());
        return list;
    }

    /**
     * 检查文件
     */
    public static boolean checkFile(File file){
        //判断文件是否存在
        if (null == file) {
            System.out.println("文件不存在！");
            return false;
        }
        //获得文件名
        String fileName = file.getName();
        //判断文件是否是excel文件
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            System.out.println(fileName + "不是excel文件");
            return false;
        }
        return true;
    }


    /**
     * 根据后缀获得workbook对象
     */
    public static Workbook getWorkBook(File file) {
        //获得文件名
        String fileName = file.getName();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith("xls")) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                //2007 及2007以上
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


    /**
     * 匹配cell的值格式 - enum
     */
    public static String getCellEValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: //数字
                cellValue = stringDateProcess(cell);
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            case ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /**
     * 时间格式处理
     */
    public static String stringDateProcess(Cell cell) {
        String result = new String();
        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
            SimpleDateFormat sdf = null;
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                sdf = new SimpleDateFormat("HH:mm");
            } else {// 日期
                sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            }
            Date date = cell.getDateCellValue();
            result = sdf.format(date);
        } else if (cell.getCellStyle().getDataFormat() == 58) {
            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            double value = cell.getNumericCellValue();
            Date date = org.apache.poi.ss.usermodel.DateUtil
                    .getJavaDate(value);
            result = sdf.format(date);
        } else {
            double value = cell.getNumericCellValue();
            CellStyle style = cell.getCellStyle();
            DecimalFormat format = new DecimalFormat();
            String temp = style.getDataFormatString();
            // 单元格设置成常规
            if (temp.equals("General")) {
                format.applyPattern("#");
            }
            result = format.format(value);
        }

        return result;
    }


    public static void WriteStringToFile(String text, String filePath) {
        File file = null;
        PrintStream ps = null;
        try {
            file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            ps = new PrintStream(new FileOutputStream(file));
            ps.println(text);// 写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != ps) ps.close();
            } catch (Exception e2) {
            }
        }
    }
}
