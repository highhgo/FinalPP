package com.example.seok2.freepp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class schedule_management extends Activity {

    public static List<String> list = null;
    //private ArrayAdapter simpleAdapter=null;
    private ListView listView = null;
    schedule_listviewadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_schedule);



        Button checklist_btn = (Button) findViewById(R.id.checklist_btn);
        Button expense_btn = (Button) findViewById(R.id.expense_btn);
        Button gallery_btn = (Button) findViewById(R.id.gallery_btn);
        Button map_btn = (Button) findViewById(R.id.map_btn);

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map_intent = new Intent(schedule_management.this, Maps.class);
                startActivity(map_intent);
            }
        });

        expense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent expense_intent = new Intent(schedule_management.this, Expense_Personal.class);
                startActivity(expense_intent);
            }
        });

        checklist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checklist_intent = new Intent(schedule_management.this, CheckList_Personal.class);
                startActivity(checklist_intent);
            }
        });

        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery_intent = new Intent(schedule_management.this, Gallery_main.class);
                startActivity(gallery_intent);
            }
        });

        list = new ArrayList<>();

        listView = (ListView) findViewById(R.id.ListView_AC);

        adapter = new schedule_listviewadapter();

        listView.setAdapter(adapter);

        View.OnClickListener listener1 = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(schedule_management.this, schedule_setting.class);
                intent2.putExtra("request", 1);
                startActivityForResult(intent2, 0);

            }
        };

        View.OnClickListener listener2 = new View.OnClickListener() {
            public void onClick(View v) {
                int pos = listView.getCheckedItemPosition();
                list.remove(pos);
            }
        };

        View.OnClickListener listener3 = new View.OnClickListener() {
            public void onClick(View v) {
                int count = adapter.getCount();
                int pos = listView.getCheckedItemPosition();

                if (pos > -1 && pos < count) {
                    // 아이템 수정
                    list.set(pos, Integer.toString(pos + 1) + "번 아이템 수정");

                    // listview 갱신
                    adapter.notifyDataSetChanged();
                }
            }
        };

        Button button1 = (Button) findViewById(R.id.add_button);
        button1.setOnClickListener(listener1);

        Button button2 = (Button) findViewById(R.id.del_button);
        button2.setOnClickListener(listener2);

        Button button3 = (Button) findViewById(R.id.refresh_button);
        button3.setOnClickListener(listener3);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String response1 = data.getStringExtra("data1");
        String response2 = data.getStringExtra("data2");
        int response3 = data.getIntExtra("count", 0);

        if (response3 == 1) {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.day1),
                    response1, response2);
        } else if (response3 == 2) {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.day2),
                    response1, response2);
        } else if (response3 == 3) {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.day3),
                    response1, response2);
        } else if (response3 == 4) {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.day4),
                    response1, response2);
        } else {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.blank),
                    response1, response2);
        }

        list.add(response1);
        adapter.notifyDataSetChanged();
    }
}