package com.benefitalign.audit.com.benefitalign.audit.entity;

import java.util.Date;

public abstract class Customer {
    String groupNumber;
    String memberNumber;
    String groupName;
    String address;
    String city;
    String state;
    String zip;
    String customerNumber;
    String baseBenefit;
    String broker;
    String hios;
    Date effectiveDate;
    Date terminationDate;
    String comments;
    String firstName;
    String lastName;
    double totalRate;
    double resAmount;
    double csrAmount;
    String applicationID;
    String policyID;
    String ffmSubscriberID;
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getTerminatoinDate() {
        return terminationDate;
    }

    public void setTerminatoinDate(Date terminatoinDate) {
        this.terminationDate = terminatoinDate;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getBaseBenefit() {
        return baseBenefit;
    }

    public void setBaseBenefit(String baseBenefit) {
        this.baseBenefit = baseBenefit;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }

    public double getResAmount() {
        return resAmount;
    }

    public void setResAmount(double resAmount) {
        this.resAmount = resAmount;
    }

    public double getCsrAmount() {
        return csrAmount;
    }

    public void setCsrAmount(double csrAmount) {
        this.csrAmount = csrAmount;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    public String getFfmSubscriberID() {
        return ffmSubscriberID;
    }

    public void setFfmSubscriberID(String ffmSubscriberID) {
        this.ffmSubscriberID = ffmSubscriberID;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }
}
