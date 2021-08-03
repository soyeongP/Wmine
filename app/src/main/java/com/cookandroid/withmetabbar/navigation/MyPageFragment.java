package com.cookandroid.withmetabbar.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.withmetabbar.MainActivity;
import com.cookandroid.withmetabbar.R;

public class MyPageFragment extends Fragment {


    //private FragmentPagerAdapter fragmentPagerAdapter;//코인충전어뎁터
    Button btn_charge, btn_ondo;
    ImageButton btn_block, btn_friends_invite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup vGroup = (ViewGroup) inflater.inflate(R.layout.mypage_fragment, container, false);

        btn_charge= vGroup.findViewById(R.id.charge);
        btn_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoinMainFragment coinMainFragment= new CoinMainFragment();
                ((MainActivity)getActivity()).replaceFragment(coinMainFragment);
            }
        });

        btn_ondo= vGroup.findViewById(R.id.btn_ondo);
        btn_ondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OndoFragment ondoFragment= new OndoFragment();

                ((MainActivity)getActivity()).replaceFragment(ondoFragment);
            }
        });

        btn_block= vGroup.findViewById(R.id.btn_block);
        btn_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendsBlockFragment friendsBlockFragment= new FriendsBlockFragment();
                ((MainActivity)getActivity()).replaceFragment(friendsBlockFragment);
            }
        });

        btn_friends_invite= vGroup.findViewById(R.id.btn_friends_invite);
        btn_friends_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendsInviteFragment friendsInviteFragment = new FriendsInviteFragment();
                ((MainActivity)getActivity()).replaceFragment(friendsInviteFragment);
            }
        });



        return vGroup;
    }
}
