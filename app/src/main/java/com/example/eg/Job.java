package com.example.eg;

public class Job {
    public Job() {

    }
    private String workId;
    private String workTitle;
    private String workDesc;
    private String location;
    private String contact;
    private String salary;

    public Job(String workId, String workTitle, String workDesc, String location, String contact, String salary) {
        this.workId = workId;
        this.workTitle = workTitle;
        this.workDesc = workDesc;
        this.location = location;
        this.contact = contact;
        this.salary = salary;
    }

    public String getWorkId() {
        return workId;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getSalary() {
        return salary;
    }
}
