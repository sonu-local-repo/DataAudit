package com.benefitalign.audit.com.benefitalign.audit.service.util;

import com.benefitalign.audit.com.benefitalign.audit.entity.Customer;
import com.google.common.collect.Multimap;

public interface ReadDataFile {
    Multimap<String, Customer> readFile();
}
