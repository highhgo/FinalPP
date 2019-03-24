package com.example.seok2.freepp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Expense_Team extends Activity {

    final int te__REQ = 100;
    final int te_RESULT_STORE = 0;
    final int te_RESULT_CANCLED = 50;

    ListView te_lv;
    ArrayList<Expense> te_data_store = new ArrayList<Expense>();
    ArrayList<String> te_data_name = new ArrayList<String>();
    ArrayAdapter<String> te_adapter;
    Expense store;

    Button btnteam, te_addStore, te_setStore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_expense);
        setTitle("지출내역(팀)");

        te_setListView();

        btnteam = (Button)findViewById(R.id.btnteam);

        btnteam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Expense_Team.this, Expense_Personal.class);
                startActivity(intent);
            }
        });//btneam.setOnClickListener

        te_addStore = (Button)findViewById(R.id.te_addStore);

        te_addStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Expense_Team.this, Expense_Add.class);
                startActivityForResult(intent, te__REQ);
            }
        });//te_addStore.setOnClickListener

        te_setStore = (Button)findViewById(R.id.te_setStore);

        te_setStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                Intent intent = new Intent(Expense_Team.this, Expense_Setting.class);
                startActivityForResult(intent, te__REQ);
                */
            }
        });//te_setStore.setOnClickListener
    }//onCreate


    public void te_setListView(){

        te_lv = (ListView)findViewById(R.id.te_listview);
        te_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,te_data_name);
        te_lv.setAdapter(te_adapter);

        // 삭제 이벤트
        te_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int num, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("삭제확인")
                        .setMessage("선택한 지출내역을 삭제 하시겠습니까?")//삭제할것인지 물어보는 메시지를 출력
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { //확인버튼을 누르면
                                te_data_name.remove(num);//이름삭제
                                te_data_store.remove(num);//저장된내용삭제
                                te_adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT)//'삭제되었습니다.'메시지 출력
                                        .show();
                            }
                        })//setPositiveButton
                        .setNegativeButton("취소", null)
                        .show();

                return true;
            }
        });//te_lv.setOnItemLongClicklistener
    }//te_setListView


    //결과내용 출력화면.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data_) {
        super.onActivityResult(requestCode, resultCode, data_);
        if(requestCode == te__REQ){
            if(resultCode ==  te_RESULT_STORE){
                Expense store = data_.getParcelableExtra("store");
                te_data_store.add(store);
                te_data_name.add("일자 : " + store.date + "   내용 : " + store.name + "   지출내역 : " +store.tel);
                te_adapter.notifyDataSetChanged();
            }//if(resultCode ..)
            else if(resultCode == te_RESULT_CANCLED){
                finish();
            }//else if
        }//if(requestCode ..)
    }//onActivityResult

}//Expense_Team