package ro.axonsoft.internship.impl;

public class StudentDescriptor extends Descriptor {
    private String name;
    private String startTime;
    private String endTime;
    private String[] interests;

    public StudentDescriptor(String name, String startTime, String endTime, String[] interests) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interests = interests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }
}
