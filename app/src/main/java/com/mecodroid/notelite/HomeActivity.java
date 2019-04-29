package com.mecodroid.notelite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.mecodroid.notelite.DbHelper.KEY_COLOR;
import static com.mecodroid.notelite.DbHelper.KEY_DATEEN;
import static com.mecodroid.notelite.DbHelper.KEY_ID;
import static com.mecodroid.notelite.DbHelper.KEY_SUBJECT;
import static com.mecodroid.notelite.DbHelper.KEY_ShOWDARABIC;
import static com.mecodroid.notelite.DbHelper.KEY_ShOWDENGLISH;
import static com.mecodroid.notelite.DbHelper.KEY_TITLE;
import static com.mecodroid.notelite.DbHelper.TABLE_NOTES;


public class HomeActivity extends AppCompatActivity {
    Intent p;
    RecyclerView Rv;
    RvAdapter adapter;
    DbHelper helper;
    SQLiteDatabase db;
    List<DataNote> noteList;
    TextView emptyText;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        helper = new DbHelper(this);
        db = helper.getWritableDatabase();
        emptyText = findViewById(R.id.textempty);
        setuprecyclerview();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setuprecyclerview() {
        Rv = findViewById(R.id.Rv);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        Rv.setLayoutManager(lm);
        adapter = new RvAdapter(this);
        Rv.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        view_notes();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void view_notes() {
        db = helper.getWritableDatabase();
        noteList = new ArrayList<>();
        try {
            Cursor cursor =
                    db.query(TABLE_NOTES, new String[]{KEY_ID, KEY_TITLE, KEY_SUBJECT, KEY_DATEEN, KEY_ShOWDENGLISH, KEY_ShOWDARABIC, KEY_COLOR},
                            null, null, null, null, KEY_DATEEN + " desc ");
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                    String ti = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                    String su = cursor.getString(cursor.getColumnIndex(KEY_SUBJECT));
                    String da = cursor.getString(cursor.getColumnIndex(KEY_DATEEN));
                    String daEN = cursor.getString(cursor.getColumnIndex(KEY_ShOWDENGLISH));
                    String daAr = cursor.getString(cursor.getColumnIndex(KEY_ShOWDARABIC));
                    int co = cursor.getInt(cursor.getColumnIndex(KEY_COLOR));

                    noteList.add(new DataNote(id, ti, su, da, daEN, daAr, co));
                } while (cursor.moveToNext());
                adapter.setDatanote(noteList);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {

        }
    }

    public void add_Notes(View view) {
        p = new Intent(HomeActivity.this, Edit_Note.class);
        startActivity(p);
    }
}
