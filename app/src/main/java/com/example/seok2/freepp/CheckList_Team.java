package com.example.seok2.freepp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckList_Team extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_checklist);


        final ArrayList<String> items = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);

        final EditText edtTitle = (EditText) findViewById(R.id.tc_addTitle);
        final ListView tclistview = (ListView) findViewById(R.id.tc_listview);

        tclistview.setAdapter(adapter);

        Button tc_screen = (Button) findViewById(R.id.tc_screen);
        tc_screen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent tc_intent = new Intent(getApplicationContext(), CheckList_Personal.class);
                startActivity(tc_intent);
            }
        });

        //추가
        Button tc_add = (Button) findViewById(R.id.tc_add);
        tc_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String getEdit = edtTitle.getText().toString();

                if(getEdit.getBytes().length <= 0) { //입력값이 없으면
                    Toast.makeText(CheckList_Team.this, "입력 값 없음", Toast.LENGTH_SHORT).show();//'입력 값 없음'메시지를 출력
                } //if

                else if(edtTitle.getText().toString().length() != 0) { //입력한게 잇으면
                    adapter.notifyDataSetChanged();
                    edtTitle.setText(null);
                    items.add(getEdit);//목록 추가
                }//else if
            }
        });//tc_add.setonClickListener

        //수정
        Button tc_modify = (Button) findViewById(R.id.tc_modify);
        tc_modify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int count_2, checked;
                count_2 = adapter.getCount();

                if(count_2>0) {
                    checked = tclistview.getCheckedItemPosition();
                    if(checked > -1 && checked < count_2) {
                        items.set(checked, Integer.toString(checked + 1) + "번 아이템 수정");

                        adapter.notifyDataSetChanged();
                    }//if
                }//if(count_2>0)
            }
        });//tc_modify.setOnClickListener


        //삭제
        Button tc_del = (Button) findViewById(R.id.tc_del);
        tc_del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SparseBooleanArray checkedItems = tclistview.getCheckedItemPositions();
                int count_3 = adapter.getCount();

                for(int i = count_3 - 1; i >= 0; i--) {
                    if(checkedItems.get(i)) {
                        items.remove(i);//목록 삭제
                    }//if
                }//for
                tclistview.clearChoices();
                adapter.notifyDataSetChanged();
            }//onClick
        });//tc_del.setOnClickListener

    }//onCreate


}//CheckList_Team