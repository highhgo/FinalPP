package com.example.seok2.freepp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Expense_Add extends Activity {

    int mYear, mMonth, mDay;
    final int RESULT_STORE = 0;
    final int RESULT_CANCELED = 50;

    RadioButton radio1, radio2;
    RadioGroup rg;
    Button addbtn, addmny, infomny;
    EditText etdate, etname, ettel;
    Intent intent;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);

        GregorianCalendar calendar = new GregorianCalendar();
        //일정추가 변수
        mYear = calendar.get(Calendar.YEAR); //연
        mMonth = calendar.get(Calendar.MONTH); //월
        mDay = calendar.get(Calendar.DAY_OF_MONTH); //일

        //추가버튼
        addbtn = (Button) findViewById(R.id.addbtn); // 추가버튼
        addmny = (Button) findViewById(R.id.addmny); // 환율 선택버튼


        etdate = (EditText) findViewById(R.id.etdate); //일자 Text
        etname = (EditText) findViewById(R.id.etname); //내용 Text
        ettel = (EditText) findViewById(R.id.ettel); //
        rg = (RadioGroup) findViewById(R.id.rg); //종류(카드,현금)
        radio1 = (RadioButton) findViewById(R.id.radio1); //
        radio2 = (RadioButton) findViewById(R.id.radio2); //

        tv = (TextView) findViewById(R.id.texttv);

        // 일정 추가 버튼 이벤트
        addbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(Expense_Add.this, dateSetListener, mYear, mMonth, mDay).show();
            }
        }); //addbtn.setOnClickListener

        // 한화 선택 다이얼로그
        addmny.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] items = new String[] {"한화","달러","일본","중국"}; //선택가능한 한화 종류
                final int[] selectIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(Expense_Add.this);
                dialog.setTitle("환화 선택").setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //타이틀 이름
                        selectIndex[0] = i;
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //확인버튼
                        Toast.makeText(Expense_Add.this, items[selectIndex[0]], Toast.LENGTH_SHORT).show(); //확인 후 선택한 환화를 보여주는 메시지.
                    }
                }).create().show();//setPositiveButton


            }
        });//addmny.setOnClickListener
    }//onCreate


    // 일정
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            // TODO Auto-generated method stub
            mYear = year; //년
            mMonth = monthOfYear; //월
            mDay = dayOfMonth; //일

            UpdateNow();
        }
    };//DatePickerDialog.OnDateSetListener


    // 일정 양식
    void UpdateNow() {
        etdate.setText(String.format("%d/%d/%d", mYear, mMonth+1, mDay));
    }

    public void onClick(View v) {
        intent = new Intent(Expense_Add.this, Expense_Personal.class);

        // 저장 버튼 이벤트
        if (v.getId() == R.id.btnAdd) {
            add();
        }//if

        // 취소 버튼 이벤트
        else if(v.getId() == R.id.btnCancel){
            setResult(RESULT_CANCELED, intent);
            finish();
        }//else if

    }//onClick

    //저장 버튼 메소드
    private void add() {
        intent = new Intent(Expense_Add.this, Expense_Personal.class);
        Expense store;

        if (radio1.isChecked()) {
            store = new Expense(etdate.getText().toString(), etname.getText().toString(), ettel.getText().toString(), 1);
        }//if

        else if(radio2.isChecked()) {
            store = new Expense(etdate.getText().toString(), etname.getText().toString(), ettel.getText().toString(), 2);
        } //elseif

        else {
            store = new Expense(etdate.getText().toString(), etname.getText().toString(), ettel.getText().toString(),  3);
        }//else

        intent.putExtra("store", store);
        setResult(RESULT_STORE, intent);
        finish();
    }//add()
}//Expense_Add