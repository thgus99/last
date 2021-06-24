package com.example.tag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button mLoginBtn, mregisterBtn;
    EditText mEmailText, mPasswordText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();
        //버튼 등록하기
        mregisterBtn = findViewById(R.id.register_btn);
        mLoginBtn = findViewById(R.id.login_btn);
        mEmailText = findViewById(R.id.emailEt);
        mPasswordText = findViewById(R.id.passwordEdt);


        //가입 버튼이 눌리면
        mregisterBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //intent함수를 통해 register액티비티 함수를 호출한다.
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });


        //로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(MainActivity.this,"회원가입이 필요합니다.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
