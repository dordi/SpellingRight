package com.example.douraid.spellingright;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class BDManager extends Activity implements OnClickListener {

    EditText editRollno, editPath, editwordspell;
    Button btnAdd, btnDelete, btnModify, btnView, btnViewAll, btnShowInfo;
    SQLiteDatabase db;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        editRollno = (EditText) findViewById(R.id.editRollno);
        editPath = (EditText) findViewById(R.id.editName);
        editwordspell = (EditText) findViewById(R.id.editMarks);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnModify = (Button) findViewById(R.id.btnModify);
        btnView = (Button) findViewById(R.id.btnView);
        btnViewAll = (Button) findViewById(R.id.btnViewAll);
        btnShowInfo = (Button) findViewById(R.id.btnShowInfo);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShowInfo.setOnClickListener(this);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
    }

    public void onClick(View view) {
        if (view == btnAdd) {
            if (editRollno.getText().toString().trim().length() == 0 ||
                    editPath.getText().toString().trim().length() == 0 ||
                    editwordspell.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO student VALUES('" + editRollno.getText() + "','" + editPath.getText() +
                    "','" + editwordspell.getText() + "');");
            showMessage("Success", "Record added");
            clearText();
        }
        if (view == btnDelete) {
            if (editRollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("DELETE FROM student WHERE rollno='" + editRollno.getText() + "'");
                showMessage("Success", "Record Deleted");
            } else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if (view == btnModify) {
            if (editRollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("UPDATE student SET name='" + editPath.getText() + "',marks='" + editwordspell.getText() +
                        "' WHERE rollno='" + editRollno.getText() + "'");
                showMessage("Success", "Record Modified");
            } else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if (view == btnView) {
            if (editRollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter id");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
            if (c.moveToFirst()) {
                editPath.setText(c.getString(1));
                editwordspell.setText(c.getString(2));
            } else {
                showMessage("Error", "Invalid id");
                clearText();
            }
        }
        if (view == btnViewAll) {
            Cursor c = db.rawQuery("SELECT * FROM student", null);
            if (c.getCount() == 0) {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("ID: " + c.getString(0) + "\n");
                buffer.append("Word Spell: " + c.getString(2) + "\n");
                buffer.append("Path: " + c.getString(1) + "\n\n");
            }
            showMessage("Words Details", buffer.toString());
        }
        if (view == btnShowInfo) {
            showMessage("Word Management Application", "Developed By Dordi");
        }
    }

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        editRollno.setText("");
        editPath.setText("");
        editwordspell.setText("");
        editRollno.requestFocus();
    }
}
