package com.example.shopping_mall;

// 상품 클래스 구현
public class ProductItem {

    String name;
    String price;
    int resId;

    public ProductItem(String name, String price, int resId) {
        this.name = name;
        this.price = price;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
