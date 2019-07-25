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
import java.util.*;

public class ReadCSCFile implements ReadDataFile {
    
    public static final String SAMPLE_XLSX_FILE_PATH = "D:\\CSC Member Extract from Subscriber Level_07.01.19.xlsx";

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
                        String memberNumber=null;
                        String formattedMemberNumber= null;
                        Customer customer = new Member();
                        for (Cell cell : row) {
                            try{
                                if (cell.getColumnIndex() == 0) {
                                    if (cell.getCellType() == CellType.STRING) {
                                        memberNumber=cell.getStringCellValue();

                                        formattedMemberNumber = memberNumber.replace("*","-");
                                        customer.setMemberNumber(formattedMemberNumber);
                                    }
                                } else if (cell.getColumnIndex() == 1) {
                                    if (cell.getCellType() == CellType.STRING) {
                                        customer.setFirstName(cell.getStringCellValue());
                                    }
                                } else if (cell.getColumnIndex() == 2) {
                                    if (cell.getCellType() == CellType.STRING) {
                                        customer.setLastName(cell.getStringCellValue());
                                    }
                                }  else if (cell.getColumnIndex() == 3) {
                                    if (cell.getCellType() == CellType.STRING) {
                                        customer.setAddress(cell.getStringCellValue());
                                    }
                                }else if (cell.getColumnIndex() == 4) {
                                    if (cell.getCellType() == CellType.STRING) {
                                        customer.setCity(cell.getStringCellValue());
                                    }
                                } else if (cell.getColumnIndex() == 5) {
                                    if (cell.getCellType() == CellType.STRING) {
                                        customer.setState(cell.getStringCellValue());
                                    }
                                } else if (cell.getColumnIndex() == 6) {
                                    if (cell.getCellType() == CellType.NUMERIC) {
                                        customer.setZip(Double.toString(cell.getNumericCellValue()));
                                    }

                                }else if (cell.getColumnIndex() == 9) {
                                    if (cell.getCellType() == CellType.STRING) {
                                        customer.setBaseBenefit(cell.getStringCellValue());
                                    }
                                }  else if (cell.getColumnIndex() == 15) {
                                    if (cell.getCellType() == CellType.NUMERIC) {
                                        customer.setTotalRate(cell.getNumericCellValue());
                                    }
                                } else if (cell.getColumnIndex() == 16) {
                                    if (cell.getCellType() == CellType.NUMERIC) {
                                        customer.setResAmount(cell.getNumericCellValue());
                                    }
                                }  else if (cell.getColumnIndex() == 17) {
                                    if (cell.getCellType() == CellType.NUMERIC) {
                                        customer.setCsrAmount(cell.getNumericCellValue());
                                    }
                                }else if (cell.getColumnIndex() == 22) {
                                    if (cell.getCellType() == CellType.STRING) {
                                        customer.setHios(cell.getStringCellValue());
                                    }


                                }else if (cell.getColumnIndex() == 12) {
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

                                } else if (cell.getColumnIndex() == 26) {
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

//                                } else if (cell.getColumnIndex() == 14) {
//                                    if (cell.getCellType() == CellType.STRING) {
//                                        customer.setBroker(cell.getStringCellValue());
//                                    }
                                }
                            }catch (Exception e){
                                System.out.println("Row#: "+row+" & Column#: "+cell.getColumnIndex());
                                e.printStackTrace();
                            }


                        }
                        customers.put(formattedMemberNumber,customer);
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

        String day = sDate.substring(8,10);
        String month = sDate.substring(5,7);
        String year = sDate.substring(0,4);
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
