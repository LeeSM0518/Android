package com.example.shopping_mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductItemView extends LinearLayout {
    ImageView productImage;
    TextView productName;
    TextView productPrice;

    public ProductItemView(Context context) {
        super(context);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        layoutInflater.inflate(R.layout.product_item, this, true);

        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
    }

    public void setProductImage(int resId) {
        productImage.setImageResource(resId);
    }

    public void setProductName(String name) {
        productName.setText(name);
    }

    public void setProductPrice(String price) {
        productPrice.setText(price + " 원");
    }
}
