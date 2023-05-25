package com.os467.management.entity;

public class Team {

    private Integer tid;

    private String subjectName;

    private String school;

    private String eventCategory;

    private String student;

    private String teacher;

    public Team(String[] teamInfos) {
        tid = Integer.parseInt(teamInfos[0]);
        subjectName = teamInfos[1];
        school = teamInfos[2];
        eventCategory = teamInfos[3];
        student = teamInfos[4];
        teacher = teamInfos[5];
    }

    public String[] getTeamInfos(){
        return new String[]{tid+"",subjectName,school,eventCategory,student,teacher};
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
