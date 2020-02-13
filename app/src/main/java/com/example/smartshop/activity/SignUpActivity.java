package com.example.smartshop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartshop.MainActivity;
import com.example.smartshop.R;
import com.example.smartshop.database.AppDatabase;
import com.example.smartshop.database.AppDatabaseUtility;
import com.example.smartshop.database.dao.UsersDAO;
import com.example.smartshop.database.model.Users;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.edt_userName_in_register)
    EditText edtUserName;

    @BindView(R.id.edt_email_in_register)
    EditText edtPhone;

    @BindView(R.id.edt_password_in_register)
    EditText edtPassword;

    @BindView(R.id.edt_confirm_password_in_register)
    EditText edtConfirmPass;

    @BindView(R.id.btn_register)
    Button btnRegister;

    private String strUserName,strPassword,strPhone;

    private AppDatabase appDatabase;
    private UsersDAO usersDAO;

    private SharedPreferences spf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        ButterKnife.bind(this);

        spf=getSharedPreferences("user_detail",MODE_PRIVATE);

        appDatabase= AppDatabaseUtility.getAppDatabase(this);
        usersDAO=appDatabase.getUsersDAO();
    }

    @OnClick(R.id.btn_register)
    public void onTapRegisterButton(){
        strUserName=edtUserName.getText().toString().trim();
        strPassword=edtPassword.getText().toString().trim();
        strPhone=edtPhone.getText().toString().trim();

        if (!strUserName.equals("") && !strPhone.equals("") && !strPassword.equals("")){
            Users users=new Users();
            String strUserID= UUID.randomUUID().toString();
            users.setUserName(strUserName);
            users.setUserID(strUserID);
            users.setPassword(strPassword);
            users.setPhoneNo(strPhone);

            usersDAO.add(users);
            Intent intent=new Intent(this, MainActivity.class);
            SharedPreferences.Editor editor = spf.edit();
            editor.putString("userID",strUserID);
            editor.commit();
            startActivity(intent);
            finish();
        }
    }
}
