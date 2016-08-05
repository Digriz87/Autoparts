package com.sales.autoparts.autoparts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.DisplayMetrics;
import android.util.EventLogTags;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;


public class MainActivity extends ActionBarActivity {

    ListView lvPcsPost;
    ArrayList<PostValue> postValueArrayList;
     ImageView order;
    CheckBox checkbox;
     int counter=0;
    ImageView shadow,shadow_sink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ImageView mainLogo = (ImageView) findViewById(R.id.mainLogo);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        ImageView  shadow_top= (ImageView) findViewById(R.id.shadow_top);
        shadow = (ImageView) findViewById(R.id.shadow);
        shadow_sink = (ImageView) findViewById(R.id.shadow_sink);

        // Установка только портретной ориентации
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView backspace = (ImageView) findViewById(R.id.backspace);
        lvPcsPost = (ListView) findViewById(R.id.lvPcsPost);
        lvPcsPost.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        order = (ImageView) findViewById(R.id.order);
        lvPcsPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (postValueArrayList != null && postValueArrayList.size() > 0) {


                }
            }
        });




        new PostAsync().execute();
        //Установка Статус Бара определенного цвета
        Window window = this.getWindow();

        window.setStatusBarColor(this.

                getResources().getColor(R.color.colorPrimary));

        Paper.init(this);


        //Get our`s height and wight of display
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels ;
        int wight = metrics.widthPixels ;

        //Получаем наш размер в 10 пикселях любого экрана
        int tenPixelsHeight = height/128;
        int tenPixelsWight = wight/77;



        //Оптимизация верхней шапки
        FrameLayout.LayoutParams shadowParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,tenPixelsHeight*10);
        shadowParams.setMargins(0, 0, 0, 0);
        shadow.setLayoutParams(shadowParams);


        FrameLayout.LayoutParams shadowSinkParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1);
        shadowSinkParams.setMargins(0, tenPixelsHeight * 10, 0, 0);
        shadow_sink.setLayoutParams(shadowSinkParams);

        FrameLayout.LayoutParams shadowTopParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,tenPixelsHeight*5);
        shadowTopParams.setMargins(0, 0, 0, 0);
        shadow_top.setLayoutParams(shadowTopParams);

        FrameLayout.LayoutParams orderParams = new FrameLayout.LayoutParams(tenPixelsWight*8,tenPixelsHeight*8, Gravity.RIGHT);
        orderParams.setMargins(0, tenPixelsHeight, tenPixelsWight*2, 0);
        order.setLayoutParams(orderParams);

        FrameLayout.LayoutParams mainLogoParams = new FrameLayout.LayoutParams(tenPixelsWight*35,tenPixelsHeight*9);
        mainLogoParams.setMargins(tenPixelsWight*10, tenPixelsHeight/4, 0, 0);
        mainLogo.setLayoutParams(mainLogoParams);

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
    }




    class PostAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        XMLHelper helper;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(MainActivity.this, "Загрузка", "Ждите, идет загрузка автозапчастей ...", true, false);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            helper = new XMLHelper();
            helper.get(getIntent().getStringExtra("level")); // Передаем Интент с артикулом из поиска
            postValueArrayList = helper.getPostsList();
            return null;




        }



        @Override
        protected void onPostExecute(Void result) {
            PostBaseAdapter postBaseAdapter = new PostBaseAdapter(MainActivity.this, postValueArrayList);
            lvPcsPost.setAdapter(postBaseAdapter);
            pd.dismiss();





        }

    }



    ArrayList<String> price = new ArrayList<String>();
    ArrayList description = new ArrayList();
    ArrayList brand = new ArrayList();
    ArrayList delivery = new ArrayList();
    ArrayList article = new ArrayList();
    ArrayList ourTags = new ArrayList<String>();

    public void checkBoxClick(View view) {
      boolean checked = ((CheckBox) view).isChecked();



        switch(view.getTag().toString()) {
            case "0":


               if (checked) {
                 counter++; //Тестовый счетчик
                   ourTags.add(view.getTag().toString()); //Записываем наш тег в АррейЛист


                   // Первое значение
                   description.add( postValueArrayList.get(0).getDescription());
                   price.add(postValueArrayList.get(0).getPrice());
                   brand.add(postValueArrayList.get(0).getBrand());
                   delivery.add(postValueArrayList.get(0).getDeliveryTime());
                   article.add(postValueArrayList.get(0).getArticle());

               } else {
                   counter--;

               }

                break;

            case "1":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());

                       //Второе значение
                    description.add(postValueArrayList.get(1).getDescription());
                    price.add(postValueArrayList.get(1).getPrice());
                    brand.add(postValueArrayList.get(1).getBrand());
                    delivery.add(postValueArrayList.get(1).getDeliveryTime());
                    article.add(postValueArrayList.get(1).getArticle());


                }
                else {
                    counter--;

                }
                break;
            case "2":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(2).getDescription());
                    price.add(postValueArrayList.get(2).getPrice());
                    brand.add(postValueArrayList.get(2).getBrand());
                    delivery.add(postValueArrayList.get(2).getDeliveryTime());
                    article.add(postValueArrayList.get(2).getArticle());


                }
                break;
            case "3":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(3).getDescription());
                    price.add(postValueArrayList.get(3).getPrice());
                    brand.add(postValueArrayList.get(3).getBrand());
                    delivery.add(postValueArrayList.get(3).getDeliveryTime());
                    article.add(postValueArrayList.get(3).getArticle());


                }
                break;
            case "4":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(4).getDescription());
                    price.add(postValueArrayList.get(4).getPrice());
                    brand.add(postValueArrayList.get(4).getBrand());
                    delivery.add(postValueArrayList.get(4).getDeliveryTime());
                    article.add(postValueArrayList.get(4).getArticle());


                }
                break;
            case "5":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(5).getDescription());
                    price.add(postValueArrayList.get(5).getPrice());
                    brand.add(postValueArrayList.get(5).getBrand());
                    delivery.add(postValueArrayList.get(5).getDeliveryTime());
                    article.add(postValueArrayList.get(5).getArticle());


                }
                break;
            case "6":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(6).getDescription());
                    price.add(postValueArrayList.get(6).getPrice());
                    brand.add(postValueArrayList.get(6).getBrand());
                    delivery.add(postValueArrayList.get(6).getDeliveryTime());
                    article.add(postValueArrayList.get(6).getArticle());


                }
                break;
            case "7":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(7).getDescription());
                    price.add(postValueArrayList.get(7).getPrice());
                    brand.add(postValueArrayList.get(7).getBrand());
                    delivery.add(postValueArrayList.get(7).getDeliveryTime());
                    article.add(postValueArrayList.get(7).getArticle());


                }
                break;
            case "8":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(8).getDescription());
                    price.add(postValueArrayList.get(8).getPrice());
                    brand.add(postValueArrayList.get(8).getBrand());
                    delivery.add(postValueArrayList.get(8).getDeliveryTime());
                    article.add(postValueArrayList.get(8).getArticle());


                }
                break;
            case "9":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(9).getDescription());
                    price.add(postValueArrayList.get(9).getPrice());
                    brand.add(postValueArrayList.get(9).getBrand());
                    delivery.add(postValueArrayList.get(9).getDeliveryTime());
                    article.add(postValueArrayList.get(9).getArticle());


                }
                break;
            case "10":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(10).getDescription());
                    price.add(postValueArrayList.get(10).getPrice());
                    brand.add(postValueArrayList.get(10).getBrand());
                    delivery.add(postValueArrayList.get(10).getDeliveryTime());
                    article.add(postValueArrayList.get(10).getArticle());


                }
                break;
            case "11":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(11).getDescription());
                    price.add(postValueArrayList.get(11).getPrice());
                    brand.add(postValueArrayList.get(11).getBrand());
                    delivery.add(postValueArrayList.get(11).getDeliveryTime());
                    article.add(postValueArrayList.get(11).getArticle());


                }
                break;
            case "12":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(12).getDescription());
                    price.add(postValueArrayList.get(12).getPrice());
                    brand.add(postValueArrayList.get(12).getBrand());
                    delivery.add(postValueArrayList.get(12).getDeliveryTime());
                    article.add(postValueArrayList.get(12).getArticle());


                }
                break;
            case "13":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(13).getDescription());
                    price.add(postValueArrayList.get(13).getPrice());
                    brand.add(postValueArrayList.get(13).getBrand());
                    delivery.add(postValueArrayList.get(13).getDeliveryTime());
                    article.add(postValueArrayList.get(13).getArticle());


                }
                break;
            case "14":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(14).getDescription());
                    price.add(postValueArrayList.get(14).getPrice());
                    brand.add(postValueArrayList.get(14).getBrand());
                    delivery.add(postValueArrayList.get(14).getDeliveryTime());
                    article.add(postValueArrayList.get(14).getArticle());


                }
                break;
            case "15":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(15).getDescription());
                    price.add(postValueArrayList.get(15).getPrice());
                    brand.add(postValueArrayList.get(15).getBrand());
                    delivery.add(postValueArrayList.get(15).getDeliveryTime());
                    article.add(postValueArrayList.get(15).getArticle());


                }
                break;
            case "16":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(16).getDescription());
                    price.add(postValueArrayList.get(16).getPrice());
                    brand.add(postValueArrayList.get(16).getBrand());
                    delivery.add(postValueArrayList.get(16).getDeliveryTime());
                    article.add(postValueArrayList.get(16).getArticle());


                }
                break;
            case "17":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(17).getDescription());
                    price.add(postValueArrayList.get(17).getPrice());
                    brand.add(postValueArrayList.get(17).getBrand());
                    delivery.add(postValueArrayList.get(17).getDeliveryTime());
                    article.add(postValueArrayList.get(17).getArticle());


                }
                break;
            case "18":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(18).getDescription());
                    price.add(postValueArrayList.get(18).getPrice());
                    brand.add(postValueArrayList.get(18).getBrand());
                    delivery.add(postValueArrayList.get(18).getDeliveryTime());
                    article.add(postValueArrayList.get(18).getArticle());


                }
                break;
            case "19":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(19).getDescription());
                    price.add(postValueArrayList.get(19).getPrice());
                    brand.add(postValueArrayList.get(19).getBrand());
                    delivery.add(postValueArrayList.get(19).getDeliveryTime());
                    article.add(postValueArrayList.get(19).getArticle());


                }
                break;
            case "20":


                if (checked) {
                    counter++;
                    ourTags.add(view.getTag().toString());
                    //Второе значение
                    description.add(postValueArrayList.get(20).getDescription());
                    price.add(postValueArrayList.get(20).getPrice());
                    brand.add(postValueArrayList.get(20).getBrand());
                    delivery.add(postValueArrayList.get(20).getDeliveryTime());
                    article.add(postValueArrayList.get(20).getArticle());


                }
                break;
        }





                }










    public void startOrder (View v){


        Intent newIntent = new Intent(this, Order.class);
        newIntent.putStringArrayListExtra("description", description);
        newIntent.putStringArrayListExtra("price", price);
        newIntent.putStringArrayListExtra("brand", brand);
        newIntent.putStringArrayListExtra("delivery", delivery);
        newIntent.putStringArrayListExtra("article", article);
        newIntent.putExtra("counter", counter);
        newIntent.putStringArrayListExtra("ourTags", ourTags);
               startActivity(newIntent);

    }




}
















