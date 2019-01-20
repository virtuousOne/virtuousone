package com.demo.virtuousone.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.List;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/20/0020
 * Description: 导入导出CSV工具类
 */
public class ExportCsvUtil {

    /**
     * @throws Exception
     */
    public static void exportCsv() throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d:/abc.csv"), "GBK");

        CSVPrinter csvPrinter = new CSVPrinter(osw, CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡"));

        for (int i = 0; i < 10; i++) {

            csvPrinter.printRecord("张三" + i, 20 + i, "湖北");
        }

        csvPrinter.flush();
        csvPrinter.close();
    }

    /**
     * 导入CSV格式
     *
     * @throws Exception
     */
    public static void readCsv() throws Exception {
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream("d:/abc.csv"), "GBK"));

        CSVParser parser = CSVFormat.EXCEL.withHeader("name", "age", "province").parse(reader);

        List<CSVRecord> list = parser.getRecords();
        for (CSVRecord record : list) {
            System.out.println(record.getRecordNumber()
                    + ":" + record.get("name")
                    + ":" + record.get("age")
                    + ":" + record.get("province"));
        }

        parser.close();
    }


}
