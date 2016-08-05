package com.sales.autoparts.autoparts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.paperdb.Paper;

/**
 * Created by Digriz on 06.07.2016.
 */
public class Order extends Activity {






    ImageView backspace;
    DatabaseReference mDataBaseChild;
    ScrollView mainScrollView;
   LinearLayout mainLinearLayout;
    ImageView sendOrder;
    ArrayList<String> price;
    ArrayList<String> brand;
    ArrayList<String> description ;
    ArrayList<String> delivery;
    ArrayList<String> article;
    ArrayList<String> ourTags;
    ArrayList<Integer> intList;
    ImageView shadow,shadow_sink;
    String  numberOfClient;
    FrameLayout mainFrame;
    ImageView deleteOfPart;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        ImageView  mainLogo=(ImageView) findViewById(R.id.mainLogo);
        backspace = (ImageView) findViewById(R.id.backspace);
        mainScrollView = (ScrollView) findViewById(R.id.main_scroll_view);
        mainLinearLayout = (LinearLayout) findViewById(R.id.main_linear_layout);
        sendOrder = (ImageView) findViewById (R.id.orderSend);
        ImageView  shadow_top= (ImageView) findViewById(R.id.shadow_top);
        shadow = (ImageView) findViewById(R.id.shadow);
        shadow_sink = (ImageView) findViewById(R.id.shadow_sink);

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

         price = new ArrayList<String>();
      brand = new ArrayList<String>();
         description = new ArrayList<String>();
        delivery = new ArrayList<String>();
       article = new ArrayList<String>();
        ourTags = new ArrayList<String>();
        intList = new ArrayList<Integer>();

        price = getIntent().getStringArrayListExtra("price");
        brand = getIntent().getStringArrayListExtra("brand");
        description= getIntent().getStringArrayListExtra("description");
        delivery= getIntent().getStringArrayListExtra("delivery");
        article= getIntent().getStringArrayListExtra("article");
        ourTags= getIntent().getStringArrayListExtra("ourTags");
        int counter = getIntent().getIntExtra("counter", 0);






        FrameLayout.LayoutParams shadowParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,tenPixelsHeight*10);
        shadowParams.setMargins(0, 0, 0, 0);
        shadow.setLayoutParams(shadowParams);


        FrameLayout.LayoutParams shadowSinkParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1);
        shadowSinkParams.setMargins(0, tenPixelsHeight * 10, 0, 0);
        shadow_sink.setLayoutParams(shadowSinkParams);

        FrameLayout.LayoutParams shadowTopParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,tenPixelsHeight*5);
        shadowTopParams.setMargins(0, 0, 0, 0);
        shadow_top.setLayoutParams(shadowTopParams);

        FrameLayout.LayoutParams mainLogoParams = new FrameLayout.LayoutParams(tenPixelsWight*35,tenPixelsHeight*9);
        mainLogoParams.setMargins(tenPixelsWight*10, tenPixelsHeight/4, 0, 0);
        mainLogo.setLayoutParams(mainLogoParams);

        FrameLayout.LayoutParams orderButtonParams = new FrameLayout.LayoutParams(tenPixelsWight*35, tenPixelsHeight*10);
        orderButtonParams.setMargins(tenPixelsWight*25, tenPixelsHeight*110, 0,0 );   //Параметры кнопки оформить заказ
        sendOrder.setLayoutParams(orderButtonParams);


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

        FrameLayout.LayoutParams scrollViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        scrollViewParams.setMargins(0, tenPixelsHeight * 12, 0, 0); //СкроллВью параметры
        mainScrollView.setLayoutParams(scrollViewParams);

        List<Integer> arrayOfInts = new ArrayList<Integer>();
        for (Object str : ourTags) {
            arrayOfInts.add(Integer.parseInt((String)str));

                    }



                // Заполняем нашими вьюшками и подставляем значения из РейЛиста с данными
        for (  i =0; i<ourTags.size();i++) {




            ImageView picture = new ImageView(this); // Фотография автозапчасти
            final EditText changeOfQuantity = new EditText(this); // Едит Текст с изменением количества автозапчастей в заказе
            ImageView editeOfQuantity = new ImageView(this); // Кнопка редактировать количество автозапчастей
            deleteOfPart = new ImageView(this); // Кнопка удалить автозапчасть из корзины
            TextView Number =  new TextView(this);   // Номер автозапчасти
            final TextView Description =  new TextView(this); //Создаем Вьюшку текста Описание автозапчасти
            TextView Price =  new TextView(this); //Создаем Вьюшку текста цены автозапчасти
            TextView Brand =  new TextView(this); //Создаем Вьюшку текста бренда автозапчасти
            TextView deliveryOfTime =  new TextView(this); //Срок доставки
             mainFrame = new FrameLayout(this);  //Параметры ФреймЛайаута
            TextView Tip =  new TextView(this); // Подсказка по изменению количества

            LinearLayout.LayoutParams frameLayoutFirstCartParams = new LinearLayout.LayoutParams(tenPixelsWight*80,tenPixelsHeight*50);
            frameLayoutFirstCartParams.setMargins(0, tenPixelsHeight * 2, 0, 0);         //ФреймЛайаут
            mainFrame.setTag(i);
            mainFrame.setLayoutParams(frameLayoutFirstCartParams);
            mainLinearLayout.addView(mainFrame);

             FrameLayout.LayoutParams frameLayoutPictureParams = new FrameLayout.LayoutParams(tenPixelsWight*15, tenPixelsHeight*15);
            frameLayoutPictureParams.setMargins(tenPixelsWight / 2, tenPixelsHeight, 0, 0);
            picture.setLayoutParams(frameLayoutPictureParams); // Фотография автозапчастей
            picture.setBackgroundResource(R.drawable.camera);
            picture.setTag(i);
            mainFrame.addView(picture);

            FrameLayout.LayoutParams numberOfPartParams = new FrameLayout.LayoutParams(tenPixelsWight*45, tenPixelsHeight*5);
            numberOfPartParams.setMargins(tenPixelsWight * 20, tenPixelsHeight * 11, 0, 0);
            Number.setLayoutParams(numberOfPartParams); // Артикул автозапчасти
            Number.setText("Номер запчасти: " + article.get(i));
            Number.setTag(i);
            mainFrame.addView(Number);

            FrameLayout.LayoutParams frameLayoutDeliveryTextParams = new FrameLayout.LayoutParams(tenPixelsWight * 45, tenPixelsHeight*5);
            frameLayoutDeliveryTextParams.setMargins(tenPixelsWight * 20, tenPixelsHeight * 6, 0, 0);
            deliveryOfTime.setTextSize(15);         //  Cрок доставки автозапчасти
            deliveryOfTime.setTag(i);
            deliveryOfTime.setText("Cрок доставки: " + delivery.get(i) + " дней");
            deliveryOfTime.setLayoutParams(frameLayoutDeliveryTextParams);
            mainFrame.addView(deliveryOfTime);

            FrameLayout.LayoutParams frameLayoutPriceTextParams = new FrameLayout.LayoutParams(tenPixelsWight * 25, tenPixelsHeight*5);
            frameLayoutPriceTextParams.setMargins(tenPixelsWight * 60, tenPixelsHeight * 10, 0, 0);
            Price.setTextColor(Color.parseColor("#D50000"));     //  Цена автозапчасти
            Price.setTextSize(20);
            Price.setTag(i);
            Price.setText(price.get(i));
            Price.setLayoutParams(frameLayoutPriceTextParams);
            mainFrame.addView(Price);

            FrameLayout.LayoutParams frameLayoutBrandTextParams = new FrameLayout.LayoutParams(tenPixelsWight*20, tenPixelsHeight*5);
            frameLayoutBrandTextParams.setMargins(tenPixelsWight * 20, tenPixelsHeight * 15, 0, 0);
            Brand.setTextColor(Color.parseColor("#D50000"));     //  Бренд автозапчасти
            Brand.setTextSize(20);
            Brand.setText(brand.get(i));
            Brand.setLayoutParams(frameLayoutBrandTextParams);
            mainFrame.addView(Brand);

            FrameLayout.LayoutParams frameLayoutEditQuantityParams = new FrameLayout.LayoutParams(tenPixelsWight*50,tenPixelsHeight*10);
            frameLayoutEditQuantityParams.setMargins(tenPixelsWight / 2, tenPixelsHeight * 20, 0, 0);
            changeOfQuantity.setTag(i);
            changeOfQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
            changeOfQuantity.setHint("Изменить количество");   // Изменение количества автозапчастей  в детали
            changeOfQuantity.setLayoutParams(frameLayoutEditQuantityParams);
            mainFrame.addView(changeOfQuantity);

            FrameLayout.LayoutParams frameLayoutTipParams = new FrameLayout.LayoutParams(tenPixelsWight*80,tenPixelsHeight*50);
            frameLayoutTipParams.setMargins(tenPixelsWight / 2, tenPixelsHeight * 30, 0, 0);
            Tip.setText("Для изменения количества: Введите количество и нажмите иконку Карандаша. После оформите заказ - указав ваш номер телефона");
            Tip.setLayoutParams(frameLayoutTipParams);      //Подсказка по оформлению заказа
            mainFrame.addView(Tip);

            FrameLayout.LayoutParams frameLayoutDescriptionParams = new FrameLayout.LayoutParams(tenPixelsWight*50,tenPixelsHeight*5);
            frameLayoutDescriptionParams.setMargins(tenPixelsWight * 20, tenPixelsHeight / 4, 0, 0);
            Description.setText(description.get(i));
            Description.setTag(i);
            Description.setTextSize(20);                                     // Описание автозапчасти
            Description.setTextColor(Color.parseColor("#000000"));
            Description.setLayoutParams(frameLayoutDescriptionParams);
            mainFrame.addView(Description);

            FrameLayout.LayoutParams EditQuantityParams = new FrameLayout.LayoutParams(tenPixelsWight*7,tenPixelsHeight*7);
            EditQuantityParams.setMargins(tenPixelsWight * 52, tenPixelsHeight * 20, 0, 0);
            editeOfQuantity.setClickable(true);
            editeOfQuantity.setTag(i);

            editeOfQuantity.setBackgroundResource(R.drawable.edit);           //Кнопка исправить количество
            editeOfQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = mainLinearLayout.getChildCount();
                    View view = null;
                    for(int countI=0; countI<count; countI++) {
                    view = mainLinearLayout.getChildAt(countI);
                    if(((int) view.getTag()) == ((int) v.getTag())){
                            mDataBaseChild.push().child("QuantityOfPart" + Description.getText().toString()).setValue(changeOfQuantity.getText().toString());
                    }

                    }


                }
            });
            editeOfQuantity.setLayoutParams(EditQuantityParams);
            mainFrame.addView(editeOfQuantity);


            FrameLayout.LayoutParams deletePartsParams = new FrameLayout.LayoutParams(tenPixelsWight*7,tenPixelsHeight*7);
            deletePartsParams.setMargins(tenPixelsWight * 60, tenPixelsHeight * 20, 0, 0);
            deleteOfPart.setBackgroundResource(R.drawable.delete);             //Кнопка удалить автозапчасть
            deleteOfPart.setClickable(true);
            deleteOfPart.setTag(i);

            deleteOfPart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = mainLinearLayout.getChildCount();
                    View view = null;
                    for(int countI=0; countI<count; countI++) {
                        view = mainLinearLayout.getChildAt(countI);
                        if(((int) view.getTag()) == ((int) v.getTag())){
                            mainLinearLayout.removeView(view);
                            ourTags.clear();
                          Paper.book().write("ourTags",ourTags) ;

                            break;
                        }
                    }



                }
            });

            deleteOfPart.setLayoutParams(deletePartsParams);
            mainFrame.addView(deleteOfPart);


        }

    }




   //Кнопка оформить заказ которая отправляет заказ в Фаербейс
public void orderSend (View v){
    // AlertDialog который спрашивает номер телефона заказчика
    AlertDialog.Builder builder = new AlertDialog.Builder(Order.this);
    builder.setTitle("Оформление заказа");
    View viewInflated = LayoutInflater.from(Order.this).inflate(R.layout.edit_text_for_orders, (ViewGroup) findViewById(android.R.id.content), false);
    final EditText input = (EditText) viewInflated.findViewById(R.id.input);

    builder.setView(viewInflated);

    // Установка кнопок
    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            numberOfClient = input.getText().toString();
            mDataBaseChild.child("ORDER" + (i)).push().child("numberOfClient").setValue(numberOfClient);

            Toast toast3 = Toast.makeText(getApplicationContext(),
                    "Спасибо за ваш заказ, с вами свяжуться в течении 2 часов", Toast.LENGTH_LONG);
            toast3.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout toastContainer = (LinearLayout) toast3.getView();
            ImageView catImageView = new ImageView(getApplicationContext());
            catImageView.setImageResource(R.drawable.phone);
            toastContainer.addView(catImageView, 0);
            toast3.show();

        }
    });
    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });

    builder.show();

        //Записываем заказ в Фаербейс
    for (int i =0 ; i<price.size(); i++){

    mDataBaseChild.child("ORDER" + (i)).push().child("orderPrice" + (i)).setValue(price.get(i));
    mDataBaseChild.child("ORDER" + (i)).push().child("orderDescription" + (i)).setValue(description.get(i));
    mDataBaseChild.child("ORDER" + (i)).push().child("orderNumber" + (i)).setValue(article.get(i));
    mDataBaseChild.child("ORDER" + (i)).push().child("orderBrand" + (i)).setValue(brand.get(i));
    mDataBaseChild.child("ORDER" + (i)).push().child("orderDelivery"+(i)).setValue(delivery.get(i));






}




}
}
























