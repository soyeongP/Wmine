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

public class JoinStartFragment extends Fragment {

    Button btn_join,btn_live;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup vGroup = (ViewGroup) inflater.inflate(R.layout.join_start_fragment, container, false);
        EditText etId = vGroup.findViewById(R.id.etId);
        EditText etPw = vGroup.findViewById(R.id.etPw);
        EditText etName = vGroup.findViewById(R.id.etName);
        EditText etBirth = vGroup.findViewById(R.id.etBirth);
        EditText etAge = vGroup.findViewById(R.id.etAge);
        EditText etPhoneNum = vGroup.findViewById(R.id.etPhoneNum);
        btn_join= vGroup.findViewById(R.id.button6);
        btn_live = vGroup.findViewById(R.id.btn_live);


        btn_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivePlaceFragment livePlaceFragment= new LivePlaceFragment();
                ((MainActivity2)getActivity()).replaceFragment(livePlaceFragment);
            }
        });



        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //순서 이거대로 맞춰줘야 한다. 데이터순서와 같게.
                String memberId=etId.getText().toString().trim();
                String password=etPw.getText().toString().trim();
                String memberName=etName.getText().toString().trim();
                String phoneNum=etPhoneNum.getText().toString().trim();
                String birth=etBirth.getText().toString().trim();
                //int age=Integer.parseInt(etAge.getText().toString().trim());

                //fragment에서 액티비티로 전환
                //MainActivity로 이동.
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);


                try {
                    //String rst = new TaskJoin().execute(etId.getText().toString().trim(),etPw.getText().toString().trim()).get();
                    String rst = new TaskJoin().execute(memberId,password,memberName,phoneNum,birth).get();
                    JSONObject json = new JSONObject(rst);

                    String msg=json.getString("Join");
                    Toast.makeText(getContext(),""+msg,Toast.LENGTH_SHORT).show();




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return vGroup;
    }

    //이부분 데이터 TaskJOin 수정
    public class TaskJoin extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        String serverIp = "http://13.125.233.177:8080/AppWithMe/member/insert.jsp";

        @Override
        protected String doInBackground(String... strings) {//뒤에서 돌아가는 가장 중요한 메소드
            try {
                String str;
                URL url = new URL(serverIp);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");//POST 방식, GET방식으로 넘겨주면 한글처리부터 문제가 생기게 됨
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                //sendMsg = "memberId="+ strings[0]+"&password="+ strings[1];

                //sendMsg = "meetId="+ strings[0]+"&meetName="+ strings[1];
                sendMsg = "memberId="+ strings[0]+"&password="+ strings[1]+"&memberName="+ strings[2]+"&phoneNum="+ strings[3]
                        +"&birth="+ strings[4];
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