package com.benefitalign.audit.com.benefitalign.audit.service.util;

import com.benefitalign.audit.com.benefitalign.audit.entity.Customer;
import com.benefitalign.audit.com.benefitalign.audit.entity.Member;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReadSiebelFile implements ReadDataFile{
    
    public static final String SAMPLE_XLSX_FILE_PATH = "D:\\reportt.xlsx";

    public Multimap<String,Customer> readFile(){

        XSSFWorkbook workbook;
        List headerColName = new ArrayList<>(30);
//        Map<String, Customer> customers = new HashMap<>();
        Multimap<String, Customer> customers = ArrayListMultimap.create();

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

                        Customer customer = new Member();
                        for (Cell cell : row) {
                            if (cell.getColumnIndex() == 0) {
                                if (cell.getCellType() == CellType.STRING) {
                                    customer.setGroupNumber(cell.getStringCellValue());
                                }
                            } else if (cell.getColumnIndex() == 1) {
                                if (cell.getCellType() == CellType.STRING) {
                                    customer.setCustomerNumber(cell.getStringCellValue());
                                }
                            }  else if (cell.getColumnIndex() == 4) {
                                CellType dType = cell.getCellType();
                                if (cell.getCellType() == CellType.STRING) {
                                    String sDate = cell.getStringCellValue();
                                    Date dateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
                                    customer.setEffectiveDate(dateFormat);
                                }
                                else if (cell.getCellType() == CellType.NUMERIC) {
                                    Date nDate = cell.getDateCellValue();
                                    LocalDate localDate = nDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                    String sDate = formatDate(String.valueOf(localDate));

                                    Date dateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
                                    customer.setEffectiveDate(dateFormat);
                                }


                            } else if (cell.getColumnIndex() == 5) {
                               CellType dType = cell.getCellType();
                                if (cell.getCellType() == CellType.STRING) {
                                    String sDate = cell.getStringCellValue();
                                    Date dateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
                                    customer.setTerminatoinDate(dateFormat);
                                }
                                else if (cell.getCellType() == CellType.NUMERIC) {
                                    Date nDate = cell.getDateCellValue();
                                    LocalDate localDate = nDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                    String sDate = formatDate(String.valueOf(localDate));

                                    Date dateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
                                    customer.setTerminatoinDate(dateFormat);
                                }


                            } if (cell.getColumnIndex() == 6) {
                                if (cell.getCellType() == CellType.STRING) {
                                    customer.setMemberNumber(cell.getStringCellValue());
                                }
                            }  else if (cell.getColumnIndex() == 7) {
                                if (cell.getCellType() == CellType.STRING) {
                                    customer.setHios(cell.getStringCellValue());
                                }
                            }
                            else if (cell.getColumnIndex() == 10) {
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    customer.setTotalRate(cell.getNumericCellValue());
                                }
                            } else if (cell.getColumnIndex() == 11) {
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    customer.setResAmount(cell.getNumericCellValue());
                                }
                            }


                        }
                        customers.put(customer.getMemberNumber(),customer);
                    }
                    i++;


                }

                System.out.println(customers.size());

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return customers;
    }

    private String formatDate(String sDate) {

        String day = sDate.substring(0,2);
        String month = sDate.substring(3,6);
        String year = sDate.substring(7,11);
        String formattedDate = null;
        List<String> months = new ArrayList<>();
        if (month.equalsIgnoreCase("JAN")){
            month="01";
        } else if (month.equalsIgnoreCase("FEB")){
            month="02";
        }if (month.equalsIgnoreCase("MAR")){
            month="03";
        }if (month.equalsIgnoreCase("APR")){
            month="04";
        }if (month.equalsIgnoreCase("MAY")){
            month="05";
        }if (month.equalsIgnoreCase("JUN")){
            month="06";
        }if (month.equalsIgnoreCase("JUL")){
            month="07";
        }if (month.equalsIgnoreCase("AUG")){
            month="08";
        }if (month.equalsIgnoreCase("SEP")){
            month="09";
        }if (month.equalsIgnoreCase("OCT")){
            month="10";
        }if (month.equalsIgnoreCase("NOV")){
            month="11";
        }if (month.equalsIgnoreCase("DEC")){
            month="12";
        }
        formattedDate = month+'/'+day+'/'+year;



        return formattedDate;
    }


}
