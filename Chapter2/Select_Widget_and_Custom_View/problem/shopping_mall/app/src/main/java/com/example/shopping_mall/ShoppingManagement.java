package com.example.shopping_mall;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingManagement extends AppCompatActivity {

    GridView gridView;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_management);

        gridView = findViewById(R.id.gridView);
        productAdapter = new ProductAdapter();

        productAdapter.addItem(new ProductItem(
                "FILA 보라색 맨투맨", "30000", R.drawable.image1));
        productAdapter.addItem(new ProductItem(
                "FILA 흰색 맨투맨", "40000", R.drawable.image2));
        productAdapter.addItem(new ProductItem(
                "FILA 검은색 맨투맨", "25000", R.drawable.image3));
        productAdapter.addItem(new ProductItem(
                "FILA 남색 맨투맨", "10000", R.drawable.image4));

        gridView.setAdapter(productAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductItem item = (ProductItem) productAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), "옷 이름 : " + item.getName() +
                        "\n옷 가격 : " + item.getPrice(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class ProductAdapter extends BaseAdapter {

        ArrayList<ProductItem> products = new ArrayList<ProductItem>();

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int position) {
            return products.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(ProductItem item) {
            products.add(item);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ProductItemView view = new ProductItemView(getApplicationContext());
            ProductItem item = products.get(position);
            view.setProductImage(item.getResId());
            view.setProductName(item.getName());
            view.setProductPrice(item.getPrice());

            return view;
        }
    }
}
