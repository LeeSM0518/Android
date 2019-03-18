package com.example.shopping_mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerManament extends AppCompatActivity {

    EditText customNameEdit;
    EditText customBirthDateEdit;
    EditText customPhoneNumberEdit;
    TextView customCount;

    ListView customListView;
    CustomAdapter customAdapter;

    Button addCustomDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_manament);

        customBirthDateEdit = findViewById(R.id.customBirthDateEdit);
        customCount = findViewById(R.id.customCount);
        customPhoneNumberEdit = findViewById(R.id.customPhoneNumberEdit);
        customNameEdit = findViewById(R.id.customNameEdit);

        addCustomDataButton = findViewById(R.id.customDataAddButton);

        customListView = findViewById(R.id.customListView);

        customAdapter = new CustomAdapter();

        customCount.setText(customAdapter.getCount() + " 명");

        addCustomDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAdapter.addItem(new CustomItem(
                        customNameEdit.getText().toString(),
                        customPhoneNumberEdit.getText().toString(),
                        customBirthDateEdit.getText().toString()
                ));

                customAdapter.notifyDataSetChanged();
                customCount.setText(customAdapter.getCount() + " 명");

//                customBirthDateEdit.setText("");
//                customNameEdit.setText("");
//                customPhoneNumberEdit.setText("");
            }
        });

        customListView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter {
        ArrayList<CustomItem> customItems = new ArrayList<CustomItem>();

        @Override
        public int getCount() {
            return customItems.size();
        }

        public void addItem(CustomItem item) {
            customItems.add(item);
        }

        @Override
        public Object getItem(int position) {
            return customItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CustomItemView view = new CustomItemView(getApplicationContext());
            CustomItem item = customItems.get(position);
            view.setCustomName(item.getName());
            view.setCustomBirthDate(item.getBirthDate());
            view.setCustomPhoneNumber(item.getMobile());

            return view;
        }
    }
}
