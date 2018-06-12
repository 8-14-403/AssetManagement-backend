package com.htjn.assetManagement.util;

import com.htjn.assetManagement.entity.Asset;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * create by caojy on 2018/6/3
 */
public class CommonUtils {

    public static List<Asset> readXls(InputStream in) throws IOException {

        Workbook book = null;
        try {
            book = new XSSFWorkbook(in);
        } catch (Exception ex) {
            book = new HSSFWorkbook(in);
        }
        Asset asset = null;
        List<Asset> list = new ArrayList<>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < book.getNumberOfSheets(); numSheet++) {
            Sheet sheet = book.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                asset = new Asset();
                // 循环列Cell
                // 0学号 1姓名 2学院 3课程名 4 成绩
                // for (int cellNum = 0; cellNum <=4; cellNum++) {
                Cell type = row.getCell(0);
                if (type != null) {
                    asset.setAssetType(type.getStringCellValue());
                }

                Cell code = row.getCell(1);
                if (code != null) {
                    asset.setAssetCode(code.getStringCellValue());
                }

                Cell company = row.getCell(2);
                if (company != null) {
                    asset.setCompanyNumber(company.getStringCellValue());
                }

                Cell device = row.getCell(3);
                if (device != null) {
                    asset.setDeviceName(device.getStringCellValue());
                }

                Cell model = row.getCell(4);
                if (model != null) {
                    asset.setModel(model.getStringCellValue());
                }

                Cell country = row.getCell(5);
                if (country != null) {
                    asset.setCountry(country.getStringCellValue());
                }

                Cell manufacturer = row.getCell(6);
                if (manufacturer != null) {
                    asset.setManufacturer(manufacturer.getStringCellValue());
                }

                Cell factoryNumber = row.getCell(7);
                if (factoryNumber != null) {
                    asset.setFactoryNumber(factoryNumber.getStringCellValue());
                }

                Cell department = row.getCell(8);
                if (department != null) {
                    asset.setDepartment(department.getStringCellValue());
                }

                Cell user = row.getCell(9);
                if (user != null) {
                    asset.setUser(user.getStringCellValue());
                }

                Cell originalValue = row.getCell(10);
                if (originalValue != null) {
                    asset.setOriginalValue(originalValue.getStringCellValue());
                }

                Cell project = row.getCell(11);
                if (project != null) {
                    asset.setProject(project.getStringCellValue());
                }

                Cell number = row.getCell(12);
                if (number != null) {
                    asset.setNumber((int) number.getNumericCellValue());
                }

                Cell comment = row.getCell(13);
                if (comment != null) {
                    asset.setComment(comment.getStringCellValue());
                }

                list.add(asset);
            }
        }
        return list;
    }

    public static String transference(String input) {
        input = input.replaceAll("\\\\" , "\\\\\\\\");
        input = input.replaceAll("%", "\\%");
        input = input.replaceAll("_", "\\_");
        return input;
    }
}
