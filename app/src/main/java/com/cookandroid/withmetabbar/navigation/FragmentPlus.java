package com.cookandroid.withmetabbar.navigation;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cookandroid.withmetabbar.MainActivity;
import com.cookandroid.withmetabbar.R;
import com.cookandroid.withmetabbar.certify.MainActivity2;
import com.cookandroid.withmetabbar.toolbar.MainActivity3;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;



@RequiresApi(api = Build.VERSION_CODES.N)
@SuppressWarnings("deprecation")
public class FragmentPlus extends Fragment {
    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageView, imageView7;
    private ImageButton imageButton;
    private ToggleButton toggleButton, toggleButton1,toggleButton2,toggleButton3,toggleButton4;
    private Button button2;
    private MediaPlayer MP;









    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup vGroup = (ViewGroup) inflater.inflate(R.layout.fragment_plus, container, false);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);
        //setTitle("레이아웃 선택");

        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

                EditText et_date = (EditText) vGroup.findViewById(R.id.Date);
                et_date.setText(sdf.format(myCalendar.getTime()));
            }


        };


        EditText et_Date = (EditText) vGroup.findViewById(R.id.Date);
        et_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final EditText et_time = (EditText) vGroup.findViewById(R.id.Time);
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        // EditText에 출력할 형식 지정
                        et_time.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }

        });






















        imageView = (ImageView)vGroup.findViewById(R.id.imageView7);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, GET_GALLERY_IMAGE);


            }
        });

        return vGroup;
    }



    //갤러리로 가는 법
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getActivity().getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getContext(), "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    } //갤러리에서 사진 불러와서 넣기




    protected void onMainActivity(int requestCode, int resultCode, Intent data) {
        if(requestCode == GET_GALLERY_IMAGE&&resultCode == RESULT_OK&&data !=
                null && data.getData()!=null){
            Uri selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
        }
    }


    /*public class MyGalleryAdapter extends BaseAdapter {

        Context context;
        Integer[] posterID = { R.drawable.layout1, R.drawable.layout2,
                R.drawable.layout3, R.drawable.layout4, R.drawable.layout5,
                R.drawable.layout6, R.drawable.layout7, R.drawable.layout8,
                R.drawable.layout9, R.drawable.layout10};
        String[] title = {"레이아웃1","레이아웃2", "레이아웃3",
                "레이아웃4","레이아웃5","레이아웃6","레이아웃7","레이아웃8","레이아웃9","레이아웃10"};

        public MyGalleryAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return posterID.length;
        }



        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        /*public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(posterID[position]);


            final int pos = position;
            imageview.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch (View v, MotionEvent event) {
                    ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[pos]);
                    Toast.makeText(getApplicationContext(),title[pos],Toast.LENGTH_SHORT).show();

                    //dlg.setIcon(R.drawable.movie_icon);

                    return false;
                }
            });*/

    //return imageview
}
