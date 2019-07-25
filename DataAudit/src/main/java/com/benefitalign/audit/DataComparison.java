package com.benefitalign.audit;

import com.benefitalign.audit.com.benefitalign.audit.entity.Customer;
import com.benefitalign.audit.com.benefitalign.audit.entity.DiscrepancyMember;
import com.google.common.collect.Multimap;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class DataComparison {

    public List<DiscrepancyMember> compare(Multimap<String, Customer> siebelData, Multimap<String, Customer> cscData) {

        int dataSize = 0;
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Customer> customerDiscrepancy = new HashMap<>();
        List<DiscrepancyMember> discrepancyMemberList = new ArrayList<>();
        Set<String> siebelGroupNumber = siebelData.keySet();
        Set<String> cscGroupNumber = cscData.keySet();
        if (siebelGroupNumber.size() > cscGroupNumber.size())
            dataSize = siebelGroupNumber.size();
        else
            dataSize = cscGroupNumber.size();

        Set<String> groupNumber = new HashSet<>(dataSize);
        groupNumber.addAll(siebelGroupNumber);
        groupNumber.addAll(cscGroupNumber);
        for (String groupId: groupNumber){
            DiscrepancyMember discrepancyMember = null;
            Customer siebelCustomer = siebelData.get(groupId);
            Customer cscCustomer = cscData.get(groupId);
           if(siebelGroupNumber.contains(groupId)&&cscGroupNumber.contains(groupId)) {
               //Member exist in both Siebel and CSC
               LocalDate sDate = siebelCustomer.getEffectiveDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               LocalDate cDate = cscCustomer.getEffectiveDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               int dateDiffer = sDate.getDayOfYear();
               if (!(siebelCustomer.getEffectiveDate().compareTo(cscCustomer.getEffectiveDate())==0)){
                   if((sDate.getDayOfYear()!=cDate.getDayOfYear())&& sDate.getYear()==cDate.getYear()) {
                       discrepancyMember = new DiscrepancyMember();
                       discrepancyMember = modelMapper.map(siebelCustomer, DiscrepancyMember.class);
                       discrepancyMember.setComment(sDate + "=>" + cDate + " effective dates are not matching ");
                   }
               }

               if (!(siebelCustomer.getHios().equalsIgnoreCase(cscCustomer.getHios()))) {
                   if(discrepancyMember!=null) {
                       discrepancyMember = new DiscrepancyMember();
                   }
                   discrepancyMember = modelMapper.map(siebelCustomer, DiscrepancyMember.class);
                   discrepancyMember.setComment(discrepancyMember.getComment()!=null ? discrepancyMember.getComment() : ""
                           +siebelCustomer.getHios()+"=>"+cscCustomer.getHios()+".HIOS Code is not matching.");


               }
               if(!(siebelCustomer.getTotalRate()-cscCustomer.getTotalRate()==0)){
                   if(discrepancyMember!=null) {
                       discrepancyMember = new DiscrepancyMember();
                   }
                   discrepancyMember = modelMapper.map(siebelCustomer, DiscrepancyMember.class);
                   discrepancyMember.setComment(discrepancyMember.getComment()!=null ? discrepancyMember.getComment() : ""+
                           siebelCustomer.getTotalRate()+"=>"+cscCustomer.getTotalRate()+".Total Rate is not matching.");

               }
               if(!(siebelCustomer.getResAmount()-cscCustomer.getResAmount()==0)){
                   if(discrepancyMember!=null) {
                       discrepancyMember = new DiscrepancyMember();
                   }
                   discrepancyMember = modelMapper.map(siebelCustomer, DiscrepancyMember.class);
                   discrepancyMember.setComment(discrepancyMember.getComment()!=null ? discrepancyMember.getComment() : ""+
                           siebelCustomer.getResAmount()+"=>"+cscCustomer.getResAmount()+".Total responsibility amount is not matching.");

               }


           }else if (siebelGroupNumber.contains(groupId)) {
                    //only in siebel
                    discrepancyMember = new DiscrepancyMember();
                    discrepancyMember.setMemberNumber(siebelCustomer.getMemberNumber());
                    discrepancyMember = modelMapper.map(siebelCustomer, DiscrepancyMember.class);
                    discrepancyMember.setComment(groupId+"=>"+"Member missing in CSC");
           } else if(cscGroupNumber.contains(groupId)){
                    //only in CSC
                    discrepancyMember = new DiscrepancyMember();
                    discrepancyMember = modelMapper.map(cscCustomer, DiscrepancyMember.class);
                    discrepancyMember.setComment(groupId+"=>"+"Member missing in Siebel");
           }
           if(discrepancyMember!=null){
               discrepancyMemberList.add(discrepancyMember);
           }

        }


        return discrepancyMemberList;
    }
}
