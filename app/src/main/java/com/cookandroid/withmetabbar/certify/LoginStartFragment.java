package com.cookandroid.withmetabbar.certify;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.withmetabbar.MainActivity;
import com.cookandroid.withmetabbar.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginStartFragment extends Fragment {

    Button btnLogin, btnJoin;
    EditText etId, etPw;
    private View view;//new




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup vGroup = (ViewGroup) inflater.inflate(R.layout.login_start_fragment, container, false);

        etId = vGroup.findViewById(R.id.etId);
        etPw = vGroup.findViewById(R.id.etPassword);
        btnLogin= vGroup.findViewById(R.id.btnLogin);
        btnJoin= vGroup.findViewById(R.id.btnJoin);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgreeStartFragment agreeStartFragment= new AgreeStartFragment();
                ((MainActivity2)getActivity()).replaceFragment(agreeStartFragment);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String memberId = etId.getText().toString().trim();
                    String password = etPw.getText().toString().trim();
                    //fragment에서 액티비티로 전환
                    //Intent intent = new Intent(getContext(), MainActivity.class);
                    //intent.putExtra("memberId",memberId);
                    //startActivity(intent);

                    String rst = new TaskLogin().execute(memberId,password).get();
                    JSONObject json = new JSONObject(rst);

                    String msg=json.getString("login");
                    String errToast = "";

                    switch (msg){
                        case "err:ID":
                            errToast = "존재하지 않는 아이디입니다";
                            Toast.makeText(getContext(),""+errToast,Toast.LENGTH_SHORT).show();
                            break;
                        case "err:PASSWORD":
                            errToast = "아이디와 비밀번호가 일치하지 않습니다.";
                            Toast.makeText(getContext(),""+errToast,Toast.LENGTH_SHORT).show();
                            break;
                        case "success":
                            errToast = "환영합니다";
                            Toast.makeText(getContext(),""+errToast,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.putExtra("memberId",memberId);
                            startActivity(intent);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"실패",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(MainActivity.this,"m",Toast.LENGTH_SHORT).show();
            }
        });//btnLogin

        return vGroup;
    }//onCreateView
    public class TaskLogin extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        String serverIp = "http://13.125.233.177:8080/AppWithMe/member/login.jsp";

        @Override
        protected String doInBackground(String... strings) {//뒤에서 돌아가는 가장 중요한 메소드
            try {
                String str;
                URL url = new URL(serverIp);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");//POST 방식, GET방식으로 넘겨주면 한글처리부터 문제가 생기게 됨
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "memberId="+ strings[0]+"&password="+ strings[1];

                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");//UTF-8로 데이터 읽어옴
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;//String 타입인데 안에 형식이 json임
        }
    }


}