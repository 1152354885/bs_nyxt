package com.example.navigationview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationview.MainActivity;
import com.example.navigationview.R;
import com.example.navigationview.api.MyRetrofit;
import com.example.navigationview.api.WebApiService;
import com.example.navigationview.entity.User;
import com.example.navigationview.result.BaseResult;
import com.example.navigationview.utils.RequestUtil;
import com.example.navigationview.utils.SPUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mUsernameEdt;
    private EditText mPasswordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mUsernameEdt = findViewById(R.id.edt_username);
        mPasswordEdt = findViewById(R.id.edt_password);
        TextView tv_forgetPwd = findViewById(R.id.tv_forget_pwd);
        TextView tv_register = findViewById(R.id.tv_register);
        Button btn_login = findViewById(R.id.btn_login);

        tv_forgetPwd.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, com.example.navigationview.activity.RegisterActivity.class));
                break;
            case R.id.btn_login:
                String username = mUsernameEdt.getText().toString();
                String password = mPasswordEdt.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                login(username, password);
                break;
        }
    }

    //去api取得结果
    private void login(String username, String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        RequestBody body = RequestUtil.getRequestBody(map);
        WebApiService service = MyRetrofit.getRetrofit().create(WebApiService.class);
        Call<BaseResult<User>> call = service.login(body);
        call.enqueue(new Callback<BaseResult<User>>() {
            @Override
            public void onResponse(Call<BaseResult<User>> call, Response<BaseResult<User>> response) {
                BaseResult<User> result = response.body();
                assert result != null;
                if (result.getCode() == 0){
                    User user = result.getData();
                    if (user != null){
                        Integer id = user.getId();
                        String  name=user.getUsername();
                        SPUtil.saveInt(LoginActivity.this,"userId",id);
                    }
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,result.getMessage() + "",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<BaseResult<User>> call, Throwable t) {

            }
        });
    }
}