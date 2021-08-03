package com.cookandroid.withmetabbar.navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.withmetabbar.R;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    //데이터 베이스 배울 때 수정하기
    Integer[] postingID = {
            R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
            R.drawable.photo4, R.drawable.photo5, R.drawable.photo6,
            R.drawable.photo7, R.drawable.photo8, R.drawable.photo9,
            R.drawable.photo10, R.drawable.photo11, R.drawable.photo12,
            R.drawable.photo13, R.drawable.photo14
    };

    //연관검색어 textview에 띄울 내용
    private List<String> items = Arrays.asList("부산", "부산바다", "부산어묵", "서울", "서울경복궁");

    private SearchView searchBar;
    private TextView resultTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup vGroup = (ViewGroup) inflater.inflate(R.layout.fragment_grid, container, false);
        final GridView gv = vGroup.findViewById(R.id.gridView);
        MyGridAdapter gAdapter = new MyGridAdapter(getContext());
        gv.setAdapter(gAdapter);

        resultTextView = vGroup.findViewById(R.id.textView3);//연관검색어

        searchBar = vGroup.findViewById(R.id.searchView);

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBar.setIconified(false);
                gv.setVisibility(View.INVISIBLE);
                resultTextView.setVisibility(View.VISIBLE);
            }
        });

        searchBar.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                gv.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.INVISIBLE);
                return false;
            }
        });



        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                resultTextView.setText(search(nextText));
                return true;
            }
        });
        return vGroup;
    }

    private String search(String query){
        StringBuilder sb = new StringBuilder();//for append 사용
        for(int i = 0; i<items.size(); i++){
            String item = items.get(i);

            if(item.toLowerCase().trim().contains(query.toLowerCase())) {
                sb.append(item);
                if(i != items.size() - 1) {
                    sb.append("  ");
                }
            }
        }
        return sb.toString();
    }


    private String getResult(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<items.size(); i++){
            String item = items.get(i);
            sb.append(item);
            if(i != items.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return postingID.length; //데이터 베이스 배울 때 수정
        }

        public Object getItem(int arg0) {

            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview. setLayoutParams(new ViewGroup.LayoutParams(350,350));
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageview.setImageResource(postingID[position]); //데이터 베이스 배울 때 수정

            return imageview;
        }
    }



}
