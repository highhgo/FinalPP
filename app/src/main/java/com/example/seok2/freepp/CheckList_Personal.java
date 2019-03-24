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

public class CheckList_Personal extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_checklist);

        final ArrayList<String> items = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);
        final EditText edtTitle = (EditText) findViewById(R.id.pc_addTitle);
        final ListView pclistview = (ListView) findViewById(R.id.pc_listview);

        pclistview.setAdapter(adapter);

        Button pc_screen = (Button) findViewById(R.id.pc_screen);
        pc_screen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent pc_intent = new Intent(getApplicationContext(), CheckList_Team.class);
                startActivity(pc_intent);
            }//onClick
        });//pc_screen.setOnClickListener

        // 추가
        Button pc_add = (Button) findViewById(R.id.pc_add);
        pc_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String getEdit = edtTitle.getText().toString();

                if(getEdit.getBytes().length <= 0) { //입력값이 없으면
                    Toast.makeText(CheckList_Personal.this, "입력 값 없음", Toast.LENGTH_SHORT).show(); // '입력 값 없음'을 출력.
                }//if
                else if(edtTitle.getText().toString().length() != 0) {//입력한게 있으면
                    adapter.notifyDataSetChanged();
                    edtTitle.setText(null);
                    items.add(getEdit);//목록추가
                }//else if
            }
        });//pc_add.setOnClickListener

        //수정
        Button pc_modify = (Button) findViewById(R.id.pc_modify);
        pc_modify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int count_2, checked;
                count_2 = adapter.getCount();

                if(count_2>0) {
                    checked = pclistview.getCheckedItemPosition();
                    if(checked > -1 && checked < count_2) {
                        items.set(checked, Integer.toString(checked + 1) + "번 아이템 수정");
                        adapter.notifyDataSetChanged();
                    }//if
                }//if
            }
        });//pc_modify.setOnClickListener

        //삭제
        Button pc_del = (Button) findViewById(R.id.pc_del);
        pc_del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SparseBooleanArray checkedItems = pclistview.getCheckedItemPositions();
                int count_3 = adapter.getCount();

                for(int i = count_3 - 1; i >= 0; i--) {
                    if(checkedItems.get(i)) {
                        items.remove(i);//목록 삭제
                    }//if
                }//for
                pclistview.clearChoices();
                adapter.notifyDataSetChanged();
            }
        });//pc_del.setOnClickListener

    }//onCreate
}//CheckList_Personal