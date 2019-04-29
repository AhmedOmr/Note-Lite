package com.mecodroid.notelite;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.mecodroid.notelite.DbHelper.KEY_COLOR;
import static com.mecodroid.notelite.DbHelper.KEY_DATEEN;
import static com.mecodroid.notelite.DbHelper.KEY_ID;
import static com.mecodroid.notelite.DbHelper.KEY_SUBJECT;
import static com.mecodroid.notelite.DbHelper.KEY_ShOWDARABIC;
import static com.mecodroid.notelite.DbHelper.KEY_ShOWDENGLISH;
import static com.mecodroid.notelite.DbHelper.KEY_TITLE;
import static com.mecodroid.notelite.DbHelper.TABLE_NOTES;

public class Edit_Note extends AppCompatActivity {
    EditText tex1, tex2;
    DbHelper helper;
    SQLiteDatabase db, dg;
    HomeActivity homeActivity;
    DataNote dataNote;
    String tit, sub;
    Intent rec;
    String format, formateng, formatarb;
    private Dialog builder;
    RadioButton r1, r2, r3, r4;
    int scolor;
    Button btn;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__note);
        tex1 = findViewById(R.id.text_note_title);
        tex2 = findViewById(R.id.text_note_content);
        setDailogdesign();
        scolor = selected(R.drawable.nstar);
        helper = new DbHelper(this);
        format = new SimpleDateFormat("YYYY-MM-d HH:mm:ss.sss", Locale.ENGLISH).format(new Date());
        formateng = new SimpleDateFormat(" hh:mm a / d-MM-YYYY  ", Locale.ENGLISH).format(new Date());
        formatarb = new SimpleDateFormat(" hh:mm a / d-MM-YYYY ", new Locale("ar")).format(new Date());
        db = helper.getWritableDatabase();
        rec = getIntent();
        if (rec != null && rec.hasExtra("note")) {
           try
           {dataNote = rec.getExtras().getParcelable("note");
            tex1.setText(dataNote.getTitle());
            tex2.setText(dataNote.getSubject());
        }catch (NullPointerException e)
           {
               e.printStackTrace();
           }
        }
        tex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.conf);
                if (currentFragment instanceof Fragment_noteformating) {
                    getSupportFragmentManager().beginTransaction().
                            hide(getSupportFragmentManager().findFragmentById(R.id.conf)).commit();

                }
            }
        });
        tex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.conf);
                if (currentFragment instanceof Fragment_noteformating) {
                    getSupportFragmentManager().beginTransaction().
                            hide(getSupportFragmentManager().findFragmentById(R.id.conf)).commit();

                }
            }
        });

        tex1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.conf);
                if (currentFragment instanceof Fragment_noteformating) {
                    getSupportFragmentManager().beginTransaction().
                            hide(getSupportFragmentManager().findFragmentById(R.id.conf)).commit();
                }
                return false;
            }
        });
        tex2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.conf);
                if (currentFragment instanceof Fragment_noteformating) {
                    getSupportFragmentManager().beginTransaction().
                            hide(getSupportFragmentManager().findFragmentById(R.id.conf)).commit();
                }
                return false;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saved_notes(View view) {
        tit = tex1.getText().toString();
        sub = tex2.getText().toString();
        Intent rec = getIntent();
        if (rec != null && rec.hasExtra("note")) {
            dataNote = rec.getExtras().getParcelable("note");

            LayoutInflater lay = getLayoutInflater();
            View v = lay.inflate(R.layout.toastedit, (ViewGroup) findViewById(R.id.lineartoast));
            TextView to = v.findViewById(R.id.toast);
            Toast toast = new Toast(this);
            to.setText("Click Edit Icon to Save Updating Note ");
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(v);
            toast.show();
        } else if (!TextUtils.isEmpty(tit) || !TextUtils.isEmpty(sub)) {
            setDailogdesign();
            builder.show();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scolor = selected(R.drawable.nstar);
                    builder.dismiss();
                    ContentValues values = new ContentValues();
                    if (Locale.getDefault().getLanguage().equals("ar")) {
                        db = helper.getWritableDatabase();
                        values.put(KEY_TITLE, tit);
                        values.put(KEY_SUBJECT, sub);
                        values.put(KEY_DATEEN, format);
                        values.put(KEY_ShOWDENGLISH, formateng);
                        values.put(KEY_ShOWDARABIC, formatarb);
                        values.put(KEY_COLOR, scolor);

                    } else {
                        db = helper.getWritableDatabase();
                        values.put(KEY_TITLE, tit);
                        values.put(KEY_SUBJECT, sub);
                        values.put(KEY_DATEEN, format);
                        values.put(KEY_ShOWDENGLISH, formateng);
                        values.put(KEY_ShOWDARABIC, formatarb);
                        values.put(KEY_COLOR, scolor);

                    }
                    long row = db.insert(TABLE_NOTES, null, values);
                    if (row > 0) {
                        Toast.makeText(Edit_Note.this, "Note Saved ", Toast.LENGTH_SHORT).show();
                        tex1.setText("");
                        tex2.setText("");
                        finish();
                    }
                }
            });
        } else if (TextUtils.isEmpty(tit) && TextUtils.isEmpty(sub)) {
            Toast.makeText(this, "please write any Title or subject before saving ", Toast.LENGTH_LONG).show();
        }
    }

    public void edited_notes(View view) {
        String tite = tex1.getText().toString();
        String sube = tex2.getText().toString();
        db = helper.getWritableDatabase();
        tit = tex1.getText().toString();
        sub = tex2.getText().toString();
        rec = getIntent();
        if (rec != null && rec.hasExtra("note")) {
            if (!TextUtils.isEmpty(tite) || !TextUtils.isEmpty(sube)) {
                setDailogdesign();
                builder.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scolor = selected(R.drawable.mstar);
                        builder.dismiss();
                        ContentValues values = new ContentValues();
                        if (Locale.getDefault().getLanguage().equals("ar")) {
                            db = helper.getWritableDatabase();
                            values.put(KEY_TITLE, tit);
                            values.put(KEY_SUBJECT, sub);
                            values.put(KEY_DATEEN, format);
                            values.put(KEY_ShOWDENGLISH, formateng);
                            values.put(KEY_ShOWDARABIC, formatarb);
                            values.put(KEY_COLOR, scolor);
                        } else {
                            db = helper.getWritableDatabase();
                            values.put(KEY_TITLE, tit);
                            values.put(KEY_SUBJECT, sub);
                            values.put(KEY_DATEEN, format);
                            values.put(KEY_ShOWDENGLISH, formateng);
                            values.put(KEY_ShOWDARABIC, formatarb);
                            values.put(KEY_COLOR, scolor);
                        }
                        int rowf = db.update(TABLE_NOTES, values, KEY_ID + "=?", new String[]{String.valueOf(dataNote.getId())});
                        if (rowf > 0) {
                            finish();
                        }
                    }
                });
            } else {
            Toast.makeText(this, "please write any Title or subject before updating note ", Toast.LENGTH_LONG).show();
        }
    } else {
            LayoutInflater lay = getLayoutInflater();
            View v = lay.inflate(R.layout.toastsave, (ViewGroup) findViewById(R.id.lineartoast));
            TextView to = v.findViewById(R.id.toast);
            Toast toast = new Toast(this);
            to.setText("Click save Icon to Save New Note");
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(v);
            toast.show();
        }
    }

    public void deleted_notes(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Note")
                .setMessage("Are you sure to delete this note ?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = helper.getWritableDatabase();
                        int row = db.delete(TABLE_NOTES, KEY_ID + "=?", new String[]{String.valueOf(dataNote.getId())});
                        if (row > 0) {
                            homeActivity.view_notes();
                            finish();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public void canceled_notes(View view) {
        finish();

    }

    public void formats_notes(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.conf, new Fragment_noteformating()).addToBackStack(null).commit();

    }


    public void setDailogdesign() {
        builder = new Dialog(Edit_Note.this);
        View vw = LayoutInflater.from(Edit_Note.this).inflate(R.layout.dialogway1, (ViewGroup) this.findViewById(R.id.cardpr));
        r1 = vw.findViewById(R.id.Rneg);
        r2 = vw.findViewById(R.id.Rlo);
        r3 = vw.findViewById(R.id.Rme);
        r4 = vw.findViewById(R.id.Rhi);
        btn = vw.findViewById(R.id.bt);
        if (vw.getParent() != null) {
            ((ViewGroup) vw.getParent()).removeView(vw);
        }
        builder.setContentView(vw, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        builder.setCancelable(false);
    }

    public int selected(int pr) {
        if (r1.isChecked()) {
            pr = R.drawable.nstar;
        } else if (r2.isChecked()) {
            pr = R.drawable.lostar;
        } else if (r3.isChecked()) {
            pr = R.drawable.mdstar;
        } else if (r4.isChecked()) {
            pr = R.drawable.hstar;
        }
        return pr;
    }


}
