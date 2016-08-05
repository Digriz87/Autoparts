package com.sales.autoparts.autoparts;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Digriz on 21.07.2016.
 */
public class splash extends AppCompatActivity {

    ImageView mainLogo;
    ImageView poloska;
    ImageView engine;
    ImageView  steering;
    ImageView  garage;
    ImageView   wheel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        mainLogo = (ImageView) findViewById(R.id.mainLogo);
        poloska = (ImageView) findViewById(R.id.poloska);
        engine = (ImageView) findViewById(R.id.engine);
        steering = (ImageView) findViewById(R.id.steering);
        garage = (ImageView) findViewById(R.id.garage);
        wheel = (ImageView) findViewById(R.id.wheel);

        //Адаптация под экраны
        //Get our`s height and wight of display
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels ;
        int wight = metrics.widthPixels ;

        //Получаем наш размер в 10 пикселях любого экрана
        int tenPixelsHeight = height/128;
        int tenPixelsWight = wight/77;


        // Установка только портретной ориентации
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FrameLayout.LayoutParams mainLogoParams = new FrameLayout.LayoutParams(tenPixelsWight*45, tenPixelsHeight*12, Gravity.CENTER_HORIZONTAL);
        mainLogoParams.setMargins(0, tenPixelsHeight*40, 0,0 );
        mainLogo.setLayoutParams(mainLogoParams);

        FrameLayout.LayoutParams poloskaParams = new FrameLayout.LayoutParams(tenPixelsWight*45, 1, Gravity.CENTER_HORIZONTAL);
        poloskaParams.setMargins(0, tenPixelsHeight*54, 0,0 );
        poloska.setLayoutParams(poloskaParams);

        FrameLayout.LayoutParams engineParams = new FrameLayout.LayoutParams(tenPixelsWight*9, tenPixelsHeight*9);
        engineParams.setMargins(tenPixelsWight*20, tenPixelsHeight*55, 0,0 );
        engine.setLayoutParams(engineParams);

        FrameLayout.LayoutParams steeringParams = new FrameLayout.LayoutParams(tenPixelsWight*9, tenPixelsHeight*9);
        steeringParams.setMargins(tenPixelsWight*30, tenPixelsHeight*55, 0,0 );
        steering.setLayoutParams(steeringParams);

        FrameLayout.LayoutParams garageParams = new FrameLayout.LayoutParams(tenPixelsWight*9, tenPixelsHeight*9);
        garageParams.setMargins(tenPixelsWight*40, tenPixelsHeight*55, 0,0 );
        garage.setLayoutParams(garageParams);

        FrameLayout.LayoutParams wheelParams = new FrameLayout.LayoutParams(tenPixelsWight*9, tenPixelsHeight*9);
        wheelParams.setMargins(tenPixelsWight*50, tenPixelsHeight*55, 0,0 );
        wheel.setLayoutParams(wheelParams);

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(5000);  //Delay of 10 seconds
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(splash.this,
                            NextClass.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }

}
