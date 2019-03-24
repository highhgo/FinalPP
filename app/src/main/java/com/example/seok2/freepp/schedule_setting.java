package com.example.seok2.freepp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class schedule_setting extends Activity {
    EditText editText1;
    EditText editText2;
    static int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_schedule);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);

        int request = getIntent().getIntExtra("request", -1);
        Button button = (Button) findViewById(R.id.save_button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                count++;
                intent.putExtra("data1", editText1.getText().toString());
                intent.putExtra("data2", editText2.getText().toString());
                intent.putExtra("count", count);

                setResult(0, intent);
                finish();
            }
        });




    }



}