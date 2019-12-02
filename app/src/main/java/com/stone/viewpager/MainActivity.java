package com.stone.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.stone.viewpager.base.DefaultBaseActivity;

/**
 * @author nnr
 */
public class MainActivity extends DefaultBaseActivity {

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public void Click0(View view){
        startActivity(new Intent(this,ClipChildrenNavigation.class));
    }
    public void Click1(View view) {
        Intent intent = new Intent(this,ViewPagerImg.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}
