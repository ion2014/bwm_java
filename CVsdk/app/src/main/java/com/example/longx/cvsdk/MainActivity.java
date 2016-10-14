package com.example.longx.cvsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button but_encode,but_decode;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but_encode=(Button)findViewById(R.id.button);
        but_decode=(Button)findViewById(R.id.button2);
        textView=(TextView)findViewById(R.id.output1);
    }
    public void encode(View view){
        String str="now encoding";
        textView.setText(str);
    }
    public void decode(View view){
        String str="now decoding";
        textView.setText(str);
    }
}
