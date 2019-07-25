package com.benefitalign.audit;

import com.benefitalign.audit.com.benefitalign.audit.entity.Customer;
import com.benefitalign.audit.com.benefitalign.audit.entity.DiscrepancyMember;
import com.benefitalign.audit.com.benefitalign.audit.service.util.ReadCSCFile;
import com.benefitalign.audit.com.benefitalign.audit.service.util.ReadDataFile;
import com.benefitalign.audit.com.benefitalign.audit.service.util.ReadSiebelFile;
import com.google.common.collect.Multimap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DataAuditApplication {

	public static void main(String[] args) {

		SpringApplication.run(DataAuditApplication.class, args);
		ReadDataFile siebelFile = new ReadSiebelFile();
		ReadDataFile cscFile = new ReadCSCFile();
		DataComparison dataComparison = new DataComparison();
		NewFile discrepancyFile = new NewFile();

		Multimap<String, Customer> siebelCustomers =  siebelFile.readFile();
		Multimap<String, Customer> cscCustomers =  cscFile.readFile();
		List<DiscrepancyMember> discrepancyMemberList = dataComparison.compare(siebelCustomers, cscCustomers);
		discrepancyFile.create(discrepancyMemberList);

	}

}
