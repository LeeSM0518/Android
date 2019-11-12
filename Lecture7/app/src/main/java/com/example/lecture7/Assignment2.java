package com.example.lecture7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Assignment2 extends AppCompatActivity {

    ListView listView;
    String[] titles = {
            "The Wizard of Oz (1939)",
            "Citizen Kane (1941)",
            "All About Eve (1950)",
            "The Third Man (1949)",
            "A Hard Days's Night (1964)",
            "Modern Times (1936)",
            "Medern Times (1936)",
            "Metropois (1927)",
            "Metropois (1927)",
            "Metropois (1927)",
            "Metropois (1927)"};

    Integer[] images = {
            R.drawable.movie1,
            R.drawable.movie2,
            R.drawable.movie3,
            R.drawable.movie4,
            R.drawable.movie5,
            R.drawable.movie6,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);
        CustomList adapter = new CustomList(Assignment2.this);
        listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), titles[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;

        public CustomList(Activity context) {
            super(context, R.layout.listitem, titles);
            this.context = context;
        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = rowView.findViewById(R.id.imageView);

            TextView title = rowView.findViewById(R.id.title);
            TextView rating = rowView.findViewById(R.id.rating);
            TextView genre = rowView.findViewById(R.id.genre);
            TextView year = rowView.findViewById(R.id.releaseYear);
            title.setText(titles[position]);
            imageView.setImageResource(images[position]);
            rating.setText("9.0" + position);
            genre.setText("DRAMA");
            year.setText(1930 + position + "");
            return rowView;
        }
    }

}
