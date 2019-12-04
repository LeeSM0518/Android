package com.example.minsportfolio.homecustomview;

// HomeFragment 안에 있는 ListView 에 들어갈 Item 클래스
public class HomeCustomItem {

    int languageResId;      // 언어 이미지 id
    String language;        // 언어 이름
    String career;          // 경력
    String Proficiency;     // 숙련도
    String doneList;        // 해본 것들

    // 생성자
    public HomeCustomItem(int languageResId, String language,
                          String career, String proficiency, String doneList) {
        this.languageResId = languageResId;
        this.language = language;
        this.career = career;
        Proficiency = proficiency;
        this.doneList = doneList;
    }

    // Getter, Setter 메소드들
    public int getLanguageResId() {
        return languageResId;
    }

    public void setLanguageResId(int languageResId) {
        this.languageResId = languageResId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getProficiency() {
        return Proficiency;
    }

    public void setProficiency(String proficiency) {
        Proficiency = proficiency;
    }

    public String getDoneList() {
        return doneList;
    }

    public void setDoneList(String doneList) {
        this.doneList = doneList;
    }

}