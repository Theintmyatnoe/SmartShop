package com.example.smartshop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartshop.MainActivity;
import com.example.smartshop.R;
import com.example.smartshop.database.AppDatabase;
import com.example.smartshop.database.AppDatabaseUtility;
import com.example.smartshop.database.dao.UsersDAO;
import com.example.smartshop.database.model.Users;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.layout_login)
    RelativeLayout layoutLogin;

    @BindView(R.id.tv_sign_up)
    TextView tvSingUp;

    @BindView(R.id.edt_userName)
    EditText edtUserName;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    private String strPhoneno,strPassword;

    private AppDatabase appDatabase;
    private UsersDAO usersDAO;

    private SharedPreferences spf;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).hide();

        appDatabase= AppDatabaseUtility.getAppDatabase(this);
        usersDAO=appDatabase.getUsersDAO();

        spf=getSharedPreferences("user_detail",MODE_PRIVATE);

        Animation splashani= AnimationUtils.loadAnimation(this, R.anim.bounce_anim);
        layoutLogin.setAnimation(splashani);

        tvSingUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sign_up:
                Intent intent=new Intent(this,SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                strPhoneno=edtUserName.getText().toString().trim();
                strPassword=edtPassword.getText().toString().trim();
                if (!strPhoneno.equals("") && !strPassword.equals("")){
                    List<Users> usersList=usersDAO.selectUserByPhonenoAndPass(strPhoneno,strPassword);
                    if (usersList.size()>0){
                        Intent intent1=new Intent(this, MainActivity.class);
                        SharedPreferences.Editor editor = spf.edit();
                        String strUserID=usersList.get(0).getUserID();
                        editor.putString("userID",strUserID);
                        editor.commit();
                        startActivity(intent1);
                        finish();
                    }
                    else {
                        Toast.makeText(this, "Don't have account.Please sign Up!", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }
}
