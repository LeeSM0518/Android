package com.example.minsportfolio.noticustomview;

// NotificationsFragment 안에 있는 ListView 에 들어갈 Item 클래스
public class NotiCustomItem {

    int prizeResId;     // 수상 이미지
    String prizeName;   // 대회 이름
    String prizeDetail; // 수상의 자세한 내용

    // 생성자
    public NotiCustomItem(int prizeResId, String prizeName, String prizeDetail) {
        this.prizeResId = prizeResId;
        this.prizeName = prizeName;
        this.prizeDetail = prizeDetail;
    }

    // Getter, Setter 메소드
    public int getPrizeResId() {
        return prizeResId;
    }

    public void setPrizeResId(int prizeResId) {
        this.prizeResId = prizeResId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeDetail() {
        return prizeDetail;
    }

    public void setPrizeDetail(String prizeDetail) {
        this.prizeDetail = prizeDetail;
    }

}