package ro.axonsoft.internship.impl;

public class WorkshopDescriptor extends Descriptor{
    private String name;
    private String topic;
    private String classroom;
    private String startTime;
    private int length;

    public WorkshopDescriptor(String name, String topic, String classroom, String startTime, int length) {
        this.name = name;
        this.topic = topic;
        this.classroom = classroom;
        this.startTime = startTime;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
