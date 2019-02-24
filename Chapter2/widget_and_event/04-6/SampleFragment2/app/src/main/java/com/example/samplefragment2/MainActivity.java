package com.example.samplefragment2;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// MainActivity 는 ImageSelectionCallback 인터페이스를 구현하도록 만들고
// 이 인터페이스에 정의된 onImageSelected() 메소드도 구현한다.
public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback {

    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        // 두 개의 프래그먼트를 찾아 변수에 할당한다
        listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
        viewerFragment = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);
    }


    @Override
    // onImageSelected() 메소드는 리스트 프래그먼트에서 호출하게 되며
    // onImageSelected() 메소드가 호출되면 뷰어 프래그먼트의 setImage() 메소드를 호출하여
    // 이미지가 바뀌도록 한다.
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }
}
