package com.example.douraid.spellingright;


import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Learn extends Activity implements OnClickListener {

    EditText input;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        input = (EditText) findViewById(R.id.editText1);
        input.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        findViewById(R.id.btn1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                int x = new Random().nextInt(4) + 1;
                Uri myUri = Uri.parse("android.resource://com.example.douraid.spellingright/raw/w" + x);
                try {
                    MediaPlayer mp = MediaPlayer.create(this, myUri);
                    Log.w("Learn", "hana bech nabdou");
                    if (!mp.isPlaying()) {
                        input.setText(null);
                        mp.start();
                        while (mp.isPlaying()) {

                        }
                        mp.reset();
                        mp.release();
                        Log.w("Learn", "oumour cv");
                    }else mp.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "file doesn't exist", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
