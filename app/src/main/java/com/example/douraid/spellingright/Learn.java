package com.example.douraid.spellingright;


import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Learn extends Activity implements OnClickListener {

    EditText input;
    TextView result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        result = (TextView) findViewById(R.id.txt);
        input = (EditText) findViewById(R.id.editText1);
        input.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        findViewById(R.id.btn1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //int x = new Random().nextInt(4) + 1;
                Uri myUri = Uri.parse("android.resource://com.example.douraid.spellingright/raw/w1");
                try {
                    MediaPlayer mp = MediaPlayer.create(this, myUri);
                    if (!mp.isPlaying()) {
                        input.setText(null);
                        mp.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "file doesn't exist", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn2:
                if (input.getText().toString().equals("fight") && !input.getText().toString().equals("")) {
                    result.setText("Good!!");
                } else if (input.getText().toString().equals("")) {
                    Toast.makeText(this, "Write something", Toast.LENGTH_SHORT).show();
                } else {
                    result.setText(checkInput(input.getText().toString(), "fight"));
                }
        }

    }

    public Spannable checkInput(String a, String b) {
        int i = 0, j = 0;
        for (int k = 0; k < Math.min(a.length(), b.length()); k++) {
            if (a.charAt(k) != b.charAt(k) && j == 0) {
                i = k;
                j = i;
            } else if (a.charAt(k) != b.charAt(k)) j = k;
            if (a.length() != b.length()) j = a.length() - 1;
        }
        Spannable WordtoSpan = new SpannableString(input.getText().toString());
        WordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), i, j + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return WordtoSpan;
    }
}
