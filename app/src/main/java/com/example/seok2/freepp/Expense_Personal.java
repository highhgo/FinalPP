package com.example.seok2.freepp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Expense_Personal extends Activity {

    final int pe__REQ = 100;
    final int pe_RESULT_STORE = 0;
    final int pe_RESULT_CANCLED = 50;

    ListView pe_lv;
    ArrayList<Expense> pe_data_store = new ArrayList<Expense>();
    ArrayList<String> pe_data_name = new ArrayList<String>();
    ArrayAdapter<String> pe_adapter;
    Expense store;


    Button btnpersonal, pe_addStore, pe_setStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_expense);

        pe_setListView();

        btnpersonal = (Button)findViewById(R.id.btnpersonal);

        btnpersonal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Expense_Personal.this, Expense_Team.class);
                startActivity(intent);
            }
        });//btnpersonal.setOnClickListener

        pe_addStore = (Button)findViewById(R.id.pe_addStore);

        pe_addStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Expense_Personal.this, Expense_Add.class);
                startActivityForResult(intent, pe__REQ);
            }
        });//pe_addstore.setOnClickListener

        pe_setStore = (Button)findViewById(R.id.pe_setStore);

        pe_setStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                Intent intent = new Intent(Expense_Personal.this, Expense_Setting.class);
                startActivityForResult(intent, pe__REQ);
                */
            }
        });//pe_setStore.setOnClickListener
    }//onCreate


    public void pe_setListView(){

        pe_lv = (ListView)findViewById(R.id.pe_listview);
        pe_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,pe_data_name);
        pe_lv.setAdapter(pe_adapter);

        // 삭제 이벤트
        pe_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int num, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("삭제확인")
                        .setMessage("선택한 지출내역을 삭제 하시겠습니까?")//삭제할것인지 물어보는 메시지를 출력.
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { //확인버튼을 누르면
                                pe_data_name.remove(num);//이름삭제
                                pe_data_store.remove(num);//저장된내용삭제
                                pe_adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT)//'삭제되었습니다.'메시지 출력
                                        .show();
                            }
                        })//setPositiveButton
                        .setNegativeButton("취소", null)
                        .show();

                return true;
            }
        });//pe_lv.setOnItemLongClickListener
    }//pe_setListView()


    //결과내용 출력화면.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data_) {
        super.onActivityResult(requestCode, resultCode, data_);
        if(requestCode == pe__REQ){
            if(resultCode ==  pe_RESULT_STORE){
                Expense store = data_.getParcelableExtra("store");
                pe_data_store.add(store); //목록에 추가
                pe_data_name.add("일자 : " + store.date + "   내용 : " + store.name + "   지출내역 : " +store.tel+"원"); //지출일자, 내용,, 지출내역을 목록에 보여줌.
                pe_adapter.notifyDataSetChanged();
            }//if( resultcode..)

            else if(resultCode == pe_RESULT_CANCLED){
                finish();
            }//else if
        }//if(requestCode,,)
    }//onActivityResult


}//Expense_Personal
