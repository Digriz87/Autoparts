package com.sales.autoparts.autoparts;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Digriz on 21.07.2016.
 */
public class VinRequest extends AppCompatActivity {
    AwesomeValidation formValidation;
    EditText userid;
    LinearLayout form;
    EditText name,email,ip_addreess,phone,year,height;
    DatabaseReference mDataBaseChild;
    Button clearBtn, sendBtn;
    ImageView shadow,shadow_sink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vin_request);
        ImageView  shadow_top= (ImageView) findViewById(R.id.shadow_top);
        shadow = (ImageView) findViewById(R.id.shadow);
        shadow_sink = (ImageView) findViewById(R.id.shadow_sink);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        ip_addreess = (EditText) findViewById(R.id.ip_addreess);
        phone = (EditText) findViewById(R.id.phone);
        year = (EditText) findViewById(R.id.year);
        height = (EditText) findViewById(R.id.height);
        userid = (EditText) findViewById(R.id.userid);
        ImageView mainLogo=(ImageView) findViewById(R.id.mainLogo);
        ImageView backspace = (ImageView) findViewById(R.id.backspace);
        clearBtn = (Button) findViewById(R.id.clear);
        sendBtn= (Button) findViewById(R.id.submit);


        // Установка только портретной ориентации
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Get our`s height and wight of display
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels ;
        int wight = metrics.widthPixels ;

        //Получаем наш размер в 10 пикселях любого экрана
        int tenPixelsHeight = height/128;
        int tenPixelsWight = wight/77;

        //Установка Статус Бара определенного цвета
        Window window = this.getWindow();

        window.setStatusBarColor(this.

                getResources().getColor(R.color.colorPrimary));


        //Подключение к Фаербейсу
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDataBaseChild = mDatabase.child("autoparts-20dd4");


        mDataBaseChild.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FrameLayout.LayoutParams backspaceParams = new FrameLayout.LayoutParams(tenPixelsWight*9, tenPixelsHeight*9);
        backspaceParams.setMargins(0, tenPixelsHeight / 2, 0, 0);
        backspace.setLayoutParams(backspaceParams);                     // Кнопка назад
        backspace.setBackgroundResource(R.drawable.left);
        backspace.setClickable(true);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        FrameLayout.LayoutParams shadowParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,tenPixelsHeight*10);
        shadowParams.setMargins(0, 0, 0, 0);
        shadow.setLayoutParams(shadowParams);


        FrameLayout.LayoutParams shadowSinkParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1);
        shadowSinkParams.setMargins(0, tenPixelsHeight * 10, 0, 0);
        shadow_sink.setLayoutParams(shadowSinkParams);


        FrameLayout.LayoutParams mainLogoParams = new FrameLayout.LayoutParams(tenPixelsWight*35,tenPixelsHeight*9);
        mainLogoParams.setMargins(tenPixelsWight * 10, tenPixelsHeight / 4, 0, 0);
        mainLogo.setLayoutParams(mainLogoParams);

        FrameLayout.LayoutParams shadowTopParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,tenPixelsHeight*5);
        shadowTopParams.setMargins(0, 0, 0, 0);
        shadow_top.setLayoutParams(shadowTopParams);

        RelativeLayout.LayoutParams clearBrnParams = new RelativeLayout.LayoutParams(tenPixelsWight*30,RelativeLayout.LayoutParams.WRAP_CONTENT);
        clearBrnParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        clearBrnParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        clearBtn.setLayoutParams(clearBrnParams);

        RelativeLayout.LayoutParams sendBrnParams = new RelativeLayout.LayoutParams(tenPixelsWight*30,RelativeLayout.LayoutParams.WRAP_CONTENT);
        sendBrnParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE );
        sendBrnParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        sendBtn.setLayoutParams(sendBrnParams);
    }



public void sendBtn (View v) {
                mDataBaseChild.child("Запросы").push().child("ВИН Код: ").setValue(userid.getText().toString());
                mDataBaseChild.child("Запросы").push().child("Марка автомобиля: ").setValue(name.getText().toString());
                mDataBaseChild.child("Запросы").push().child("Электронная почта: ").setValue(email.getText().toString());
                mDataBaseChild.child("Запросы").push().child("Имя: ").setValue(ip_addreess.getText().toString());
                mDataBaseChild.child("Запросы").push().child("Телефон: ").setValue(phone.getText().toString());
                mDataBaseChild.child("Запросы").push().child("Автозапчасть 1: ").setValue(year.getText().toString());
                mDataBaseChild.child("Запросы").push().child("Автозапчасть 2: ").setValue(height.getText().toString());

                Toast toast3 = Toast.makeText(getApplicationContext(),
                        "Спасибо за ваш заказ, с вами свяжуться в течении 2 часов", Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastContainer = (LinearLayout) toast3.getView();
                ImageView catImageView = new ImageView(getApplicationContext());
                catImageView.setImageResource(R.drawable.phone);
                toastContainer.addView(catImageView, 0);
                toast3.show();
}
    public void clearBtn (View v) {
                userid.setText("");
                name.setText("");
                email.setText("");
                ip_addreess.setText("");
                phone.setText("");
                year.setText("");
                height.setText("");





            }

}