package com.benefitalign.audit.com.benefitalign.audit.entity;

public class DiscrepancyMember extends Customer {
    String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
    }
}
