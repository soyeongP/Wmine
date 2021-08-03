package com.cookandroid.withmetabbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cookandroid.withmetabbar.navigation.AlarmFragment;
import com.cookandroid.withmetabbar.navigation.CoinMainFragment;
import com.cookandroid.withmetabbar.navigation.DetailViewFragment;
import com.cookandroid.withmetabbar.navigation.MyPageFragment;
import com.cookandroid.withmetabbar.navigation.FragmentPlus;
import com.cookandroid.withmetabbar.navigation.HomeFragment;
import com.cookandroid.withmetabbar.navigation.OndoFragment;
import com.cookandroid.withmetabbar.navigation.PlaceCheckFragment;
import com.cookandroid.withmetabbar.navigation.TalkPlaceFragment;
import com.cookandroid.withmetabbar.navigation.UserFragment;
import com.cookandroid.withmetabbar.toolbar.MainActivity3;
import com.cookandroid.withmetabbar.toolbar.MainActivity4;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity  {
    UserFragment userFragment= new UserFragment();
    AlarmFragment alarmFragment= new AlarmFragment();
    HomeFragment homeFragment= new HomeFragment();
    FragmentPlus fragmentPlus= new FragmentPlus();
    MyPageFragment myPageFragment = new MyPageFragment();
    DetailViewFragment detailViewFragment = new DetailViewFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();//for 프래그먼트 전환
    CoinMainFragment coinMainFragment = new CoinMainFragment(); //코인 충전 프레그 먼트
    PlaceCheckFragment placeCheckFragment = new PlaceCheckFragment();//위치인증 프레그 먼트
    TalkPlaceFragment talkPlaceFragment = new TalkPlaceFragment();//톡방 프레그먼트 아직 xml없음

    Toolbar tb;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = fragmentManager.beginTransaction();//트랜잭션 생성
        //FragmentTransaction.add(R.id.main_content, MyPageFragment.newInstance()).commit();//new

        //transaction.replace(R.id.main_content, homeFragment).commitAllowingStateLoss();//처음에 띄우는 fragment
        transaction.replace(R.id.main_content, homeFragment).commitAllowingStateLoss();//처음에 띄우는 fragment



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()   {



            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Intent intent= new Intent(this,HomeActivity.class);
                        transaction.replace(R.id.main_content, homeFragment).commitAllowingStateLoss();
                        //transaction.replace(R.id.main_content, homeFragment).commitAllowingStateLoss();
                        //transaction.addToBackStack(null);
                        //transaction.commit();
                        //fragmentManager.beginTransaction().remove(detailViewFragment.this).commit;
                        //fragmentManager.popBackStack();

                        break;
                    case R.id.action_love:
                        transaction.replace(R.id.main_content, talkPlaceFragment).commitAllowingStateLoss();
                        break;
                    case R.id.action_plus:
                        transaction.replace(R.id.main_content, fragmentPlus).commitAllowingStateLoss();
                        break;
                    case R.id.action_chatroom:
                        transaction.replace(R.id.main_content, detailViewFragment).commitAllowingStateLoss();
                        break;
                    case R.id.action_mypage:
                        transaction.replace(R.id.main_content, myPageFragment).commitAllowingStateLoss();
                        break;

                }


                return true;
            }
        });


        //toolbar선언
        Toolbar tb = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(tb);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로 가기 버튼 생성


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (item.getItemId()){
            case R.id.action_category:
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
                return  true;
            case R.id.action_alarm:
                Intent intent1 = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent1);

            default:
                return super.onOptionsItemSelected(item);

        }

    }













    public void replaceFragment(Fragment fragment){
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_content2, fragment).commit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment).commit();
        
    }


}

    /*

    implements BottomNavigationView.OnNavigationItemSelectedListener


    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_home:
                //DetailViewFragment detailViewFragment = null;
                detailViewFragment = new DetailViewFragment();
                this.replaceFragment(detailViewFragment);
                //getSupportFragmentManager().beginTransaction().replace(R.id.main_content, detailViewFragment).commit();
                return true;
            case R.id.action_love:
                //GridFragment gridFragment = null;
                gridFragment = new GridFragment();
                this.replaceFragment(gridFragment);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, gridFragment).commit();
                return true;

            case R.id.action_plus:

                return true;

            case R.id.action_chatroom:
                //AlarmFragment alarmFragment = null;
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, alarmFragment).commit();
                return true;

            case R.id.action_mypage:
                //UserFragment userFragment = null;
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, userFragment).commit();
                return true;
            //break;
        }

        return false;
    }

 */