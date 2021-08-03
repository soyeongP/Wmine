package com.cookandroid.withmetabbar.navigation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.net.PlatformVpnProfile;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.cookandroid.withmetabbar.MainActivity;
import com.cookandroid.withmetabbar.R;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;




public class PlusActivity extends AppCompatActivity {




    int PICK_IMAGE_FROM_ALBUM =0;
    FirebaseStorage storage=null;
    Uri photo=null;
    Button plusmeet_btn_upload;
    ImageView plusmeet_imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

    




    plusmeet_btn_upload=(Button)findViewById(R.id.plusmeet_btn_upload);
        plusmeet_imageView=(ImageView)findViewById(R.id.plusmeet_image);

        storage= FirebaseStorage.getInstance();//initiate strage
        //open the album
        Intent photoPickerIntent= new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM);

        //add image upload event
        plusmeet_btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentUpload();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)

    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_FROM_ALBUM){
            if(resultCode== Activity.RESULT_OK){
                if(data!=null){
                    //This is path to the selected image

                    String photoUri= Environment.getExternalStorageDirectory() +

                            ".jpg";
                    plusmeet_imageView.setImageResource(Integer.parseInt(photoUri));


                }else{
                    //Exit the addPhotoActivity if you leave the album without selecting it.
                    finish();
                }


            }
        }

    }
    public void contentUpload(){
        //make filename
        //String timestamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(d;
    }
}