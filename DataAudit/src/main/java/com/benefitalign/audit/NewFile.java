package com.benefitalign.audit;

import com.benefitalign.audit.com.benefitalign.audit.entity.DiscrepancyMember;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class NewFile {

    public void create(List<DiscrepancyMember> discrepancyMemberList){

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Discrepancy");
        XSSFRow headderRow = sheet.createRow(0);
        headderRow.createCell(0).setCellValue("Group#");
        headderRow.createCell(1).setCellValue("Group Name");
        headderRow.createCell(2).setCellValue("Customer Number");
        headderRow.createCell(3).setCellValue("Effective Date");
        headderRow.createCell(4).setCellValue("Termination Date");
        headderRow.createCell(5).setCellValue("Plan Benefit Code");
        headderRow.createCell(6).setCellValue("HIOS Code");
        headderRow.createCell(7).setCellValue("Commnets");
        int rowCount = 0;
        for(DiscrepancyMember discrepancyMember : discrepancyMemberList){
            XSSFRow row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(discrepancyMember.getGroupNumber());
            row.createCell(1).setCellValue(discrepancyMember.getGroupName());
            row.createCell(2).setCellValue(discrepancyMember.getCustomerNumber());
            row.createCell(3).setCellValue(discrepancyMember.getEffectiveDate());
            row.createCell(4).setCellValue(discrepancyMember.getTerminationDate());
            row.createCell(5).setCellValue(discrepancyMember.getBaseBenefit());
            row.createCell(6).setCellValue(discrepancyMember.getHios());
            row.createCell(7).setCellValue(discrepancyMember.getComment());

            }

           // writeData(discrepancyMember, row);



        try  {
            FileOutputStream outputStream = new FileOutputStream("D:\\demo\\discrepancy.xlsx");
            workbook.write(outputStream);
            outputStream.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
