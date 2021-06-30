package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pos.fragments.FragmentApplicationform;
import com.example.pos.fragments.FragmentSSN;
import com.example.pos.fragments.OtpScreen;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextView textViewverifymnumber,terms,textViewverify2,textView2,textviewPhoneNumber;
    TextInputLayout textInputLayout4;
    EditText editPhoneNumber;
    Button btnContinue;
    LinearLayout linearLayout2,linearMain;
    FrameLayout frame1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContinue = (Button)findViewById(R.id.btnContinue);
        terms = (TextView) findViewById(R.id.terms);
        textView2 = (TextView)findViewById(R.id.textView2);
        textViewverify2 = (TextView) findViewById(R.id.textViewverify2);
        textviewPhoneNumber = (TextView)findViewById(R.id.textviewPhoneNumber);
        textInputLayout4 = (TextInputLayout)findViewById(R.id.textInputLayout4);
        textViewverifymnumber = (TextView)findViewById(R.id.textViewverifymnumber);
        editPhoneNumber = (EditText) findViewById(R.id.editPhoneNumber);
        linearLayout2 = (LinearLayout)findViewById(R.id.linearLayout2);
        linearMain = (LinearLayout)findViewById(R.id.linearMain);
        frame1 = (FrameLayout)findViewById(R.id.frame1);
        frame1.setVisibility(View.GONE);

        terms.setMovementMethod(LinkMovementMethod.getInstance());
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
                int length = editPhoneNumber.getText().length();
                if (length ==14){
                    textViewverifymnumber.setText("We just texted you");
                    textView2.setText("Enter the verification code sent to");
                    /*textViewverify2.setVisibility(View.GONE);
                    textInputLayout4.setVisibility(View.GONE);
                    btnContinue.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);*/
                    linearMain.setVisibility(View.GONE);
                    frame1.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
                    FragmentManager fm = getSupportFragmentManager();
                    OtpScreen fragment = new OtpScreen();
                    fm.beginTransaction().add(R.id.frame1,fragment).commit();
                }else {
                    Toast.makeText(getApplicationContext(),"fill valid number",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}