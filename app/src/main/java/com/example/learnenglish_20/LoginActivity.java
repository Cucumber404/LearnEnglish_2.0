package com.example.learnenglish_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edLogin, edPassword;
    Button btSignIn, btSignUp, btForgotPass, btResetPass, btBack;
    private FirebaseAuth mAuth;
    public static FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void setResetBtnsListeners() {
        btForgotPass.setOnClickListener(b -> {
            btSignIn.setVisibility(View.INVISIBLE);
            btSignUp.setVisibility(View.INVISIBLE);
            edPassword.setVisibility(View.INVISIBLE);
            btForgotPass.setVisibility(View.INVISIBLE);
            btResetPass.setVisibility(View.VISIBLE);
            btBack.setVisibility(View.VISIBLE);
        });

        btResetPass.setOnClickListener(b -> {
            String email = edLogin.getText().toString();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Введите email", Toast.LENGTH_SHORT).show();
                return;
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "На email отправлено письмо с инструкциями", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Ошибка при отправке письма", Toast.LENGTH_SHORT).show();
                }
            });
        });
        btBack.setOnClickListener(b->{
            btSignIn.setVisibility(View.VISIBLE);
            btSignUp.setVisibility(View.VISIBLE);
            edPassword.setVisibility(View.VISIBLE);
            btForgotPass.setVisibility(View.VISIBLE);
            btResetPass.setVisibility(View.INVISIBLE);
            btBack.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser(); // Если не зареган, то null
        if(currentUser!=null && currentUser.isEmailVerified()){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra(Constants.LAUNCHED_WITHOUT_SIGN,true);
            startActivity(intent);
        }
    }

    public void onClickSignUp(View view){
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())){
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(),edPassword.getText().toString()).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    sendEmailVer();
                    DataBase.pushUserInDB(edLogin.getText().toString());
                }else {
                    Toast.makeText(getApplicationContext(),"Ошибка при регистрации",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void onClickSighIn(View view){
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())) {
            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(task -> {
                FirebaseUser user = mAuth.getCurrentUser();
                try {
                    assert user != null;
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Вы успешно вошли!", Toast.LENGTH_LONG).show();
                        if(user.isEmailVerified()) {
                            startActivity(new Intent(this, MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Подтвердите почту", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Ошибка при входе", Toast.LENGTH_LONG).show();
                    }
                }catch (AssertionError e){
                    Toast.makeText(this, "Неверные данные для входа", Toast.LENGTH_LONG).show();
                }
            });

        }else{
            Toast.makeText(getApplicationContext(), "Заполните поля", Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        edLogin = findViewById(R.id.login_login);
        edPassword = findViewById(R.id.login_password);
//        edPassword.setTransformationMethod(null);
        btSignIn=findViewById(R.id.login_bt_sign_in);
        btSignUp=findViewById(R.id.login_bt_sign_up);
        btForgotPass = findViewById(R.id.bt_forgot_pass);
        btResetPass = findViewById(R.id.bt_reset_pass);
        btBack = findViewById(R.id.bt_login_back);
        btResetPass.setVisibility(View.INVISIBLE);
        btBack.setVisibility(View.INVISIBLE);
        mAuth=FirebaseAuth.getInstance();//Получили инстанцию Authentication
        setResetBtnsListeners();
    }

    private void sendEmailVer(){
        FirebaseUser user = mAuth.getCurrentUser();
        assert user!=null;
        user.sendEmailVerification().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(getApplicationContext(),"Подтвердите почту по ссылке в письме",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Ошибка при отправке письма",Toast.LENGTH_LONG).show();
            }
        });
    }
}