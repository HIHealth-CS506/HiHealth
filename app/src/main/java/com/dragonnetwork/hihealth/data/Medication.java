package com.dragonnetwork.hihealth.data;


import android.text.format.Time;

import com.google.firebase.Timestamp;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Medication {
    private String MedID;
    private String Prescription;
    private String Type;

    public String getMedID() {
        return MedID;
    }

    public void setMedID(String medID) {
        MedID = medID;
    }

    private int TotalNum;
    private String Strength;
    private int Doses;
    private int Frequency;
    private Timestamp Starttime;
    private int IconType;

    public Medication(String medID, String prescription, int totalNum, String strength, int doses, int frequency,
                      Timestamp starttime, int iconType) {
        MedID = medID;
        Prescription = prescription;
        TotalNum = totalNum;
        Strength = strength;
        Doses = doses;
        Frequency = frequency;
        Starttime = starttime;
        IconType = iconType;
    }

    public Medication(String medID, String prescription, int totalNum, String strength, int doses, int frequency,
                      Timestamp starttime) {
        MedID = medID;
        Prescription = prescription;
        TotalNum = totalNum;
        Strength = strength;
        Doses = doses;
        Frequency = frequency;
        this.Starttime = starttime;
        IconType = 0;
    }

    public Medication(String medID, String prescription, int totalNum, String strength, int doses, int frequency) {
        this.MedID = medID;
        this.Prescription = prescription;
        this.TotalNum = totalNum;
        this.Strength = strength;
        this.Doses = doses;
        this.Frequency = frequency;
        Starttime=Timestamp.now();
        IconType = 0;
    }

    public int getIconType() {
        return IconType;
    }

    public void setIconType(int iconType) {
        IconType = iconType;
    }

    public String getPrescription() {
        return Prescription;
    }

    public void setPrescription(String prescription) {
        Prescription = prescription;
    }

    public int getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(int totalNum) {
        TotalNum = totalNum;
    }

    public String getStrength() {
        return Strength;
    }

    public void setStrength(String strength) {
        Strength = strength;
    }

    public int getDoses() {
        return Doses;
    }

    public void setDoses(int doses) {
        Doses = doses;
    }

    public int getFrequency() {
        return Frequency;
    }

    public void setFrequency(int frequency) {
        Frequency = frequency;
    }

    public Timestamp getStarttime() {
        return Starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.Starttime = starttime;
    }
}
