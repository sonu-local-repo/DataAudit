package com.benefitalign.audit.com.benefitalign.audit.service.util;

import com.benefitalign.audit.com.benefitalign.audit.entity.Customer;
import com.benefitalign.audit.com.benefitalign.audit.entity.Member;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ReadExcelFile {

    public static final String SAMPLE_XLSX_FILE_PATH = "D:\\Siebel Extract_06.04.19_Group_Active-copy.xlsx";

    public Map<String,Customer> readFile(){

        XSSFWorkbook workbook;
        List headerColName = new ArrayList<>(30);
        Map<String, Customer> customers = new HashMap<>();

        {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(SAMPLE_XLSX_FILE_PATH));
                workbook = new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet = workbook.getSheetAt(0);
                XSSFRow xssfRow = sheet.getRow(0);
                xssfRow.forEach(cell -> headerColName.add(cell));
                System.out.println(headerColName);
                Iterator rowIterator = sheet.iterator();
                int i =0;
                for(Row row: sheet){

                    if(i>0){
                        String groupNumber=null;
                        Customer customer = new Member();
                        row.forEach(cell -> {
                            if (cell.getColumnIndex()==0){

                                customer.setGroupNumber(cell.getStringCellValue());
                            }
                            else if(cell.getColumnIndex() == 1){
                                customer.setGroupName(cell.getStringCellValue());
                            }
                            else if(cell.getColumnIndex()==2){
                                customer.setAddress(cell.getStringCellValue());
                            }
                            else if(cell.getColumnIndex()==3){
                                if(cell.getCellType()== CellType.STRING) {
                                    customer.setCity(cell.getStringCellValue());
                                }
                            }
                            else if(cell.getColumnIndex()==4){
                                if(cell.getCellType()== CellType.STRING) {
                                    customer.setState(cell.getStringCellValue());
                                }
                            }
                            else if(cell.getColumnIndex()==5){
                                if(cell.getCellType()== CellType.NUMERIC){
                                    customer.setZip(Double.toString(cell.getNumericCellValue()));
                                }

                            }
                            else if(cell.getColumnIndex()==7){
                                if(cell.getCellType()== CellType.STRING) {
                                    customer.setCustomerNumber(cell.getStringCellValue());
                                }
                            }
                            else if(cell.getColumnIndex()==9){
                                if(cell.getCellType()== CellType.STRING) {
                                    customer.setEffectiveDate(cell.getDateCellValue());
                                }else
                                System.out.println("effectove"+cell.getCellType());
                            }
                            else if(cell.getColumnIndex()==10){
                                if(cell.getCellType()== CellType.STRING) {
                                    customer.setTerminatoinDate(cell.getDateCellValue());
                                }else
                                System.out.println("term"+cell.getCellType());
                            }
                            else if(cell.getColumnIndex()==12){
                                if(cell.getCellType()== CellType.STRING) {
                                    customer.setBaseBenefit(cell.getStringCellValue());
                                }
                            }
                            else if(cell.getColumnIndex()==14){
                                if(cell.getCellType()== CellType.STRING) {
                                    customer.setBroker(cell.getStringCellValue());
                                }
                            }
                            else if(cell.getColumnIndex()==21){
                                if(cell.getCellType()== CellType.STRING) {
                                    customer.setHios(cell.getStringCellValue());
                                }else
                                System.out.println("term"+cell.getCellType());

                            }

                        });
                        customers.put(customer.getGroupNumber(),customer);
                    }
                    i++;


                }

                for(Map.Entry<String, Customer> entry : customers.entrySet()){
                    System.out.println(entry.getKey()+" , "+ entry.getValue());

                }
                System.out.println(customers.size());

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return customers;
    }


}
