package com.example.shopping_mall;

// 상품 클래스 구현
public class ProductItem {

    private String name;
    private String price;
    private int resId;

    public ProductItem(final String name, final String price, final int resId) {
        this.name = name;
        this.price = price;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getResId() {
        return resId;
    }
}
