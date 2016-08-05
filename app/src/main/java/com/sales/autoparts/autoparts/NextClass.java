package com.sales.autoparts.autoparts;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Digriz on 04.07.2016.
 */
public class NextClass extends AppCompatActivity {


    EditText searchText;
    ImageView searchBtn;
    ImageView clearShearch;
    FrameLayout mainFrameLayout;
    ImageView shadow;
    ImageView shadow_sink;
    ImageView palochka_one;
    ImageView palochka_two;
    ImageView palochka_three;
    ImageView palochka_four;
    ImageView shadow_top;
     TextView  main_text;
    ImageView icon_one;
    TextView request_vin;
    TextView description_vin;
    ImageView contacts_icon;
    TextView contacts_description;
    TextView description_contacts;
    ImageView icon_bu ;
    TextView catalog_bu_description;
    TextView catalog_bu_des;
    ImageView visa;
    ImageView mastercard;
ImageView mainLogo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mainFrameLayout = (FrameLayout) findViewById(R.id.mainFrameLayout); // Главный Фрейм
        shadow = (ImageView) findViewById(R.id.shadow);
        shadow_sink = (ImageView) findViewById(R.id.shadow_sink);
        palochka_one = (ImageView) findViewById(R.id.palochka_one);
        palochka_two = (ImageView) findViewById(R.id.palochka_two);
        palochka_three = (ImageView) findViewById(R.id.palochka_three);
        palochka_four = (ImageView) findViewById(R.id.palochka_four);
        shadow_top= (ImageView) findViewById(R.id.shadow_top);

        icon_one= (ImageView) findViewById(R.id.icon_one);
        request_vin= (TextView) findViewById(R.id.request_vin);
        description_vin= (TextView) findViewById(R.id.description_vin);
        contacts_icon= (ImageView) findViewById(R.id.contacts_icon);
        contacts_description= (TextView) findViewById(R.id.contacts_description);
        description_contacts= (TextView) findViewById(R.id.description_contacts);
        icon_bu= (ImageView) findViewById(R.id.icon_bu);
        catalog_bu_description= (TextView) findViewById(R.id.catalog_bu_description);
        catalog_bu_des= (TextView) findViewById(R.id.catalog_bu_des);
        visa= (ImageView) findViewById(R.id.visa);
        mastercard= (ImageView) findViewById(R.id.mastercard);
        mainLogo=(ImageView) findViewById(R.id.mainLogo);
        searchText = (EditText) findViewById(R.id.editText);
        searchBtn = (ImageView) findViewById(R.id.SearchBtn);
        clearShearch = (ImageView) findViewById(R.id.clear_search);

        // Установка только портретной ориентации
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Установка тулбара в нужный цвет
        Window window = this.getWindow();

        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));



        //Get our`s height and wight of display
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels ;
        int wight = metrics.widthPixels ;

        //Получаем наш размер в 10 пикселях любого экрана
        int tenPixelsHeight = height/128;
        int tenPixelsWight = wight/77;

           // Оптимизация под экраны:
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
         frameLayoutParams.setMargins(0, 0, 0, 0);         //ФреймЛайаут
         mainFrameLayout.setLayoutParams(frameLayoutParams);

        FrameLayout.LayoutParams shadowParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,tenPixelsHeight*10);
        shadowParams.setMargins(0, 0, 0, 0);
        shadow.setLayoutParams(shadowParams);

        FrameLayout.LayoutParams mainLogoParams = new FrameLayout.LayoutParams(tenPixelsWight*35,tenPixelsHeight*9);
        mainLogoParams.setMargins(tenPixelsWight/3, tenPixelsHeight/4, 0, 0);
        mainLogo.setLayoutParams(mainLogoParams);


        FrameLayout.LayoutParams shadowSinkParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1);
        shadowSinkParams.setMargins(0, tenPixelsHeight * 10, 0, 0);
        shadow_sink.setLayoutParams(shadowSinkParams);


        FrameLayout.LayoutParams palochkaOneParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1);
        palochkaOneParams.setMargins(0, tenPixelsHeight * 28, 0, 0);
        palochka_one.setLayoutParams(palochkaOneParams);


        FrameLayout.LayoutParams palochkaTwoParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1);
        palochkaTwoParams.setMargins(0, tenPixelsHeight * 40, 0, 0);
        palochka_two.setLayoutParams(palochkaTwoParams);


        FrameLayout.LayoutParams palochkaThreeParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1);
        palochkaThreeParams.setMargins(0, tenPixelsHeight * 52, 0, 0);
        palochka_three.setLayoutParams(palochkaThreeParams);


        FrameLayout.LayoutParams palochkaFourParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1);
        palochkaFourParams.setMargins(0, tenPixelsHeight * 68, 0, 0);
        palochka_four.setLayoutParams(palochkaFourParams);


        FrameLayout.LayoutParams shadowTopParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,tenPixelsHeight*5);
        shadowTopParams.setMargins(0, 0, 0, 0);
        shadow_top.setLayoutParams(shadowTopParams);



        FrameLayout.LayoutParams iconOneParams = new FrameLayout.LayoutParams(tenPixelsWight*9,tenPixelsHeight*9);
        iconOneParams.setMargins(tenPixelsWight * 2, tenPixelsHeight * 30, 0, 0);
        icon_one.setLayoutParams(iconOneParams);

        FrameLayout.LayoutParams requastVinParams = new FrameLayout.LayoutParams(tenPixelsWight*60,tenPixelsHeight*4);
        requastVinParams.setMargins(tenPixelsWight * 12, tenPixelsHeight * 30, 0, 0);
        request_vin.setLayoutParams(requastVinParams);

        FrameLayout.LayoutParams descriptionVinParams = new FrameLayout.LayoutParams(tenPixelsWight*60,tenPixelsHeight*4);
        descriptionVinParams.setMargins(tenPixelsWight * 12, tenPixelsHeight * 34, 0, 0);
        description_vin.setLayoutParams(descriptionVinParams);

        FrameLayout.LayoutParams contactsIconParams = new FrameLayout.LayoutParams(tenPixelsWight*9,tenPixelsHeight*9);
        contactsIconParams.setMargins(tenPixelsWight * 2, tenPixelsHeight * 42, 0, 0);
        contacts_icon.setLayoutParams(contactsIconParams);

        FrameLayout.LayoutParams contactsDesParams = new FrameLayout.LayoutParams(tenPixelsWight*60,tenPixelsHeight*4);
        contactsDesParams.setMargins(tenPixelsWight * 12, tenPixelsHeight * 42, 0, 0);
        contacts_description.setLayoutParams(contactsDesParams);

        FrameLayout.LayoutParams contactsDesTwoParams = new FrameLayout.LayoutParams(tenPixelsWight*60,tenPixelsHeight*4);
        contactsDesTwoParams.setMargins(tenPixelsWight * 12, tenPixelsHeight * 46, 0, 0);
        description_contacts.setLayoutParams(contactsDesTwoParams);

        FrameLayout.LayoutParams iconBuParams = new FrameLayout.LayoutParams(tenPixelsWight*9,tenPixelsHeight*9);
        iconBuParams.setMargins(tenPixelsWight * 2, tenPixelsHeight * 54, 0, 0);
        icon_bu.setLayoutParams(iconBuParams);

        FrameLayout.LayoutParams catalogBuDesParams = new FrameLayout.LayoutParams(tenPixelsWight*60,tenPixelsHeight*4);
        catalogBuDesParams.setMargins(tenPixelsWight * 12, tenPixelsHeight * 54, 0, 0);
        catalog_bu_description.setLayoutParams(catalogBuDesParams);

        FrameLayout.LayoutParams catalogBuDParams = new FrameLayout.LayoutParams(tenPixelsWight*60,tenPixelsHeight*12);
        catalogBuDParams.setMargins(tenPixelsWight * 12, tenPixelsHeight * 58, 0, 0);
        catalog_bu_des.setLayoutParams(catalogBuDParams);

        FrameLayout.LayoutParams visaParams = new FrameLayout.LayoutParams(tenPixelsWight*10,tenPixelsHeight*10, Gravity.BOTTOM);
        visaParams.setMargins(tenPixelsWight * 28, tenPixelsHeight * 4, 0, 0);
        visa.setLayoutParams(visaParams);

        FrameLayout.LayoutParams masterParams = new FrameLayout.LayoutParams(tenPixelsWight*10,tenPixelsHeight*10, Gravity.BOTTOM);
        masterParams.setMargins(tenPixelsWight * 38, tenPixelsHeight * 4, 0, 0);

        mastercard.setLayoutParams(masterParams);

        FrameLayout.LayoutParams searchParams = new FrameLayout.LayoutParams(tenPixelsWight*60,tenPixelsHeight*12);
        searchParams.setMargins(tenPixelsWight * 2, tenPixelsHeight * 10, 0, 0);
        searchText.setLayoutParams(searchParams);

        FrameLayout.LayoutParams searchBtnParams = new FrameLayout.LayoutParams(tenPixelsWight*8,tenPixelsHeight*8, Gravity.RIGHT);
        searchBtnParams.setMargins(0, tenPixelsHeight * 12, tenPixelsWight*8, 0);
        searchBtn.setLayoutParams(searchBtnParams);

        FrameLayout.LayoutParams clearBtnParams = new FrameLayout.LayoutParams(tenPixelsWight*8,tenPixelsHeight*8, Gravity.RIGHT);
        clearBtnParams.setMargins(0, tenPixelsHeight * 12, tenPixelsWight*1, 0);
        clearShearch.setLayoutParams(clearBtnParams);


    }

    public void requestVinBtn (View v){
        Intent newIntent = new Intent(this, VinRequest.class);

        startActivity(newIntent);


    }


    public void buttonClick (View v) {
        Intent newIntent = new Intent(this, MainActivity.class);
        newIntent.putExtra("level", searchText.getText().toString());
        startActivity(newIntent);




    }


    public void clearSearch (View v) {
        if(v == clearShearch){
            searchText.setText("");
        }

    }


}
