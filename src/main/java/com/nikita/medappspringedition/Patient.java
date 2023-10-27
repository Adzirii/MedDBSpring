package com.nikita.medappspringedition;

public class Patient {
    private String id;
    private String fullName;
    private String male;
    private String birthDate;
    private String age;
    private String locality;
    private String homeAdress;
    private String socialStatus;
    private String diagnos;
    private String lastVisitDate;
    private String firstVisitDate;
    private String treatment;


    public Patient(String id, String fullName, String male, String birthDate, String age,
                   String locality, String homeAdress, String socialStatus, String diagnos, String lastVisitDate,
                   String firstVisitDate, String treatment) {
        this.id = id;
        this.fullName = fullName;
        this.male = male;
        this.birthDate = birthDate;
        this.age = age;
        this.locality = locality;
        this.homeAdress = homeAdress;
        this.socialStatus = socialStatus;
        this.diagnos = diagnos;
        this.lastVisitDate = lastVisitDate;
        this.firstVisitDate = firstVisitDate;
        this.treatment = treatment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getDiagnos() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos = diagnos;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getFirstVisitDate() {
        return firstVisitDate;
    }

    public void setFirstVisitDate(String firstVisitDate) {
        this.firstVisitDate = firstVisitDate;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
