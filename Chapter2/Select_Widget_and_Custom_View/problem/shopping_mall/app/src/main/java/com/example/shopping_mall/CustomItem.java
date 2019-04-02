package com.example.shopping_mall;

// 고객 클래스 구현
public class CustomItem {

    String name;
    String mobile;
    String birthDate;

    public CustomItem(String name, String mobile, String birthDate) {
        this.name = name;
        this.mobile = mobile;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
