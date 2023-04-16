package com.example.navigationview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.navigationview.R;
import com.example.navigationview.api.MyRetrofit;
import com.example.navigationview.api.WebApiService;
import com.example.navigationview.adapter.QuestionAdapter;
import com.example.navigationview.entity.Question;
import com.example.navigationview.result.BaseResult;
import com.example.navigationview.utils.RequestUtil;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUsernameEdt;
    private EditText mPasswordEdt;
    private EditText mNickNameEdt;
    private EditText mDateOfBirthEdt;
    private EditText mAnswerEdt;
    private int mSex;
    private int mPwdProtectionId = -1;
    private Spinner mSpinner;

    private List<Question> mQuestions;
    private QuestionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initData();
    }

    private void initView() {
        mUsernameEdt = findViewById(R.id.edt_username);
        mPasswordEdt = findViewById(R.id.edt_password);
        mNickNameEdt = findViewById(R.id.edt_nickName);
        RadioGroup radioGroup = findViewById(R.id.rg_sex);
        mDateOfBirthEdt = findViewById(R.id.edt_dateOfBirth);
        mSpinner = findViewById(R.id.spinner);
        mAnswerEdt = findViewById(R.id.edt_answer);
        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        //性别单选组事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_man://男
                        mSex = 1;
                        break;
                    case R.id.radio_woman://女
                        mSex = 2;
                        break;
                }
            }
        });

        mAdapter = new QuestionAdapter(this);

        mSpinner.setAdapter(mAdapter);
        //下拉列表
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPwdProtectionId = mQuestions.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void initData() {
        getQuestionList();
    }

    private RequestBody getValidData() {
        String username = mUsernameEdt.getText().toString();
        String password = mPasswordEdt.getText().toString();
        String nickName = mNickNameEdt.getText().toString();
        String dateOfBirth = mDateOfBirthEdt.getText().toString();
        String pwdProtectionVal = mAnswerEdt.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return null;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
            return null;
        }
        if (TextUtils.isEmpty(nickName)) {
            Toast.makeText(this, "昵称不能为空", Toast.LENGTH_LONG).show();
            return null;
        }
        if (TextUtils.isEmpty(dateOfBirth)) {
            Toast.makeText(this, "出生日期不能为空", Toast.LENGTH_LONG).show();
            return null;
        }
        if (mPwdProtectionId == -1) {
            Toast.makeText(this, "请选择密保问题", Toast.LENGTH_LONG).show();
            return null;
        }
        if (TextUtils.isEmpty(pwdProtectionVal)) {
            Toast.makeText(this, "密保答案", Toast.LENGTH_LONG).show();
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("nickName", nickName);
        map.put("sex", mSex);
        map.put("dateOfBirth", dateOfBirth);
        map.put("pwdProtectionId", mPwdProtectionId);
        map.put("pwdProtectionVal", pwdProtectionVal);

        return RequestUtil.getRequestBody(map);
    }

    //密保问题
    private void getQuestionList() {
        WebApiService service = MyRetrofit.getRetrofit().create(WebApiService.class);
        Call<BaseResult<JsonObject>> call = service.getQuestions();
        call.enqueue(new Callback<BaseResult<JsonObject>>() {
            @Override
            public void onResponse(Call<BaseResult<JsonObject>> call, Response<BaseResult<JsonObject>> response) {
                BaseResult<JsonObject> result = response.body();
                if (result == null) {
                    return;
                }
                if (result.getCode() == 0) {
                    JsonObject data = result.getData();
                    if (data != null && data.has("list")) {
                        String json = data.get("list").getAsJsonArray().toString();
                        mQuestions = new Gson().fromJson(json, new TypeToken<List<Question>>() {
                        }.getType());

                        mAdapter.refreshUi(mQuestions);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResult<JsonObject>> call, Throwable t) {

            }
        });
    }


    //去api取得结果
    private void register() {
        RequestBody body = getValidData();
        if (body == null) {
            return;
        }
        WebApiService service = MyRetrofit.getRetrofit().create(WebApiService.class);
        Call<BaseResult<Object>> call = service.register(body);
        call.enqueue(new Callback<BaseResult<Object>>() {
            @Override
            public void onResponse(Call<BaseResult<Object>> call, Response<BaseResult<Object>> response) {
                BaseResult<Object> result = response.body();
                assert result != null;
                if (result.getCode() == 0) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "" + result.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResult<Object>> call, Throwable t) {

            }
        });
    }
}