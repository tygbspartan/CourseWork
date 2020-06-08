package com.example.eg;

public class ViewProfile {
    private String pFullName;
    private String pEmail;
    private String pContact;
    private String pLocation;

    public ViewProfile() {
    }

    public ViewProfile( String pFullName, String pEmail, String pContact, String pLocation) {
        this.pFullName = pFullName;
        this.pEmail = pEmail;
        this.pContact = pContact;
        this.pLocation = pLocation;
    }

    public String getpFullName() {
        return pFullName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public String getpContact() {
        return pContact;
    }

    public String getpLocation() {
        return pLocation;
    }
}
