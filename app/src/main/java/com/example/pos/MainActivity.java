package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView terms;
    EditText editPhoneNumber;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContinue = (Button)findViewById(R.id.btnContinue);
        TextView t2 = (TextView) findViewById(R.id.terms);
        editPhoneNumber = (EditText) findViewById(R.id.editPhoneNumber);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
        btnContinue.setClickable(false);
        editPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = editPhoneNumber.getText().length();
                if (length ==14){
                    btnContinue.setBackgroundResource(R.drawable.aftermobilefill);
                    btnContinue.setTextColor(Color.WHITE);
                    btnContinue.setClickable(true);
                }else {
                    btnContinue.setBackgroundResource(R.drawable.roundeditforbtn);
                    btnContinue.setTextColor(Color.parseColor("#0f9be7"));
                    btnContinue.setClickable(false);
                }
                //int length = editPhoneNumber.getText().length();
                Log.d("Onchange",String.valueOf(length));
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.d("MyCountBeforetextC",String.valueOf(count));
                int length = editPhoneNumber.getText().length();
                Log.d("Beforechange",String.valueOf(length));
            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = editPhoneNumber.getText().length();
                Log.d("AfterChange",String.valueOf(length));
                String text = editPhoneNumber.getText().toString();
                int textLength = editPhoneNumber.getText().length();
                if (text.endsWith("-") || text.endsWith(" ") || text.endsWith(" "))
                    return;
                if (textLength == 1) {
                    if (!text.contains("(")) {
                        editPhoneNumber.setText(new StringBuilder(text).insert(text.length() - 1, "(").toString());
                        editPhoneNumber.setSelection(editPhoneNumber.getText().length());
                    }
                } else if (textLength == 5) {
                    if (!text.contains(")")) {
                        editPhoneNumber.setText(new StringBuilder(text).insert(text.length() - 1, ")").toString());
                        editPhoneNumber.setSelection(editPhoneNumber.getText().length());
                    }
                } else if (textLength == 6) {
                    editPhoneNumber.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                    editPhoneNumber.setSelection(editPhoneNumber.getText().length());
                } else if (textLength == 10) {
                    if (!text.contains("-")) {
                        editPhoneNumber.setText(new StringBuilder(text).insert(text.length() - 1, "-").toString());
                        editPhoneNumber.setSelection(editPhoneNumber.getText().length());
                    }
                } else if (textLength == 15) {
                    if (text.contains("-")) {
                        editPhoneNumber.setText(new StringBuilder(text).insert(text.length() - 1, "-").toString());
                        editPhoneNumber.setSelection(editPhoneNumber.getText().length());
                    }
                } else if (textLength == 18) {
                    if (text.contains("-")) {
                        editPhoneNumber.setText(new StringBuilder(text).insert(text.length() - 1, "-").toString());
                        editPhoneNumber.setSelection(editPhoneNumber.getText().length());
                    }
                }
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
            }
        });
    }
}