package com.kmong.testproject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GON on 2015-03-09.
 */
public class TestActivity extends ActionBarActivity implements View.OnClickListener{

    private Button button;
    private LinearLayout layout;
//    @InjectView(R.id.quick_return_tv)
    TextView mQuickReturnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        layout = (LinearLayout)findViewById(R.id.Linear);
        button = (Button)findViewById(R.id.testbtn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.testbtn :

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1000);

                ListView listview = new ListView(this);
                List<String> array = new ArrayList<String>();
                for(int i = 1 ; i <= 30 ; i++){
                    array.add(""+i);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
                listview.setAdapter(adapter);
                layout.addView(listview, params);

                break;

        }

    }
}
