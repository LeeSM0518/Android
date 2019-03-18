package com.example.shopping_mall;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
