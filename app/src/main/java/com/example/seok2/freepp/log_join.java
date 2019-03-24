package com.example.seok2.freepp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class log_join extends Activity {

    EditText idText, pwdText, emailText;//아이디, 비밀번호, 이메일 입력란 변수선언
    String id, pwd, email;//입력할 문자열 변수선언
    Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_log);

        idText = (EditText) findViewById(R.id.idText); //아이디 입력란
        pwdText = (EditText) findViewById(R.id.pwdText); //비밀번호 입력란
        emailText = (EditText) findViewById(R.id.emailText); //이메일 입력란
        registerbtn = (Button) findViewById(R.id.registerButton);

        id = idText.getText().toString(); //문자열 아이디
        pwd = pwdText.getText().toString(); //문자열 비밀번호
        email = emailText.getText().toString(); //문자열 이메일

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_intent = new Intent(log_join.this , MainActivity.class);
                startActivity(reg_intent);
            }
        });
    }

    public void onClick(View view)     {

        id = idText.getText().toString();
        pwd = pwdText.getText().toString();
        email = emailText.getText().toString();

    }//onClick
}
