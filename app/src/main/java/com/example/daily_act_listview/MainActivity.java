package com.example.daily_act_listview;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
public class MainActivity extends AppCompatActivity {
    TextInputEditText email,password;
    MaterialButton login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.emailtext);
        password=findViewById(R.id.passtext);
        login=findViewById(R.id.material_text_button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidity();
            }
        });

    }

    private void checkValidity() {
        String emailtext= String.valueOf(email.getText());
        String passwordtext=String.valueOf(password.getText());
        boolean isOk=true;
        View focusview=null;

        if(TextUtils.isEmpty(emailtext)){
            isOk=false;
            email.setError("Field Can Not Be Empty!");
            focusview=email;

        }else if(!emailtext.equals("G.K.Snigdha")){
            isOk=false;
            email.setError("Please Provide A Valid Email Address!");
            focusview=email;

        }

        else if (TextUtils.isEmpty(passwordtext)){
            isOk=false;
            password.setError("Field Can Not Be Empty!");
            focusview=password;
        }else if (!passwordtext.equals("1234")){
            Log.e("password",passwordtext);
            isOk=false;
            password.setError("Wrong Password");
            focusview=password;
        }

        if(isOk){
            startActivity(new Intent(this,ListViewActivity.class));
        }else {
            focusview.requestFocus();
            isOk=true;
        }


    }
}

