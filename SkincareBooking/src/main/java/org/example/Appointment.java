package org.example;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int appointmentID;
    private int userID;
    private Date appointmentDate;
    private Time appointmentTime;
    private String serviceName;
    private String notes;

    public Appointment() {}

    public Appointment(int appointmentID, int userID, Date appointmentDate, Time appointmentTime, String serviceName, String notes) {
        this.appointmentID = appointmentID;
        this.userID = userID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.serviceName = serviceName;
        this.notes = notes;
    }

    public Appointment(int userID, Date appointmentDate, Time appointmentTime, String serviceName, String notes) {
        this.userID = userID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.serviceName = serviceName;
        this.notes = notes;
    }
    // Getters và Setters
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
