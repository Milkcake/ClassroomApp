package com.example.riko.classroomapplication.Model;

public class Subject {

    private String SubjectID;
    private String Subjectname;


    public Subject() {
    }

    public Subject(String subjectID, String subjectname) {
        SubjectID = subjectID;
        Subjectname = subjectname;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public String getSubjectname() {
        return Subjectname;
    }

    public void setSubjectname(String subjectname) {
        Subjectname = subjectname;
    }

}
