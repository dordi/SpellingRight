package com.example.douraid.spellingright;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Play_me extends Activity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playme);
        findViewById(R.id.learn).setOnClickListener(this);
        findViewById(R.id.manDB).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.learn:
                i = new Intent(Play_me.this, Learn.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.manDB:
                Intent a = new Intent(getApplicationContext(), BDManager.class);
                startActivity(a);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }
}