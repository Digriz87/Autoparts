package com.sales.autoparts.autoparts;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.paperdb.Paper;


public class PostBaseAdapter extends BaseAdapter {



    private LayoutInflater layoutInflater;
    public ArrayList<PostValue> postValueArrayList;

    public PostBaseAdapter(Context context, ArrayList<PostValue> postValueArrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.postValueArrayList = postValueArrayList;

    }

    @Override
    public int getCount() {
        return postValueArrayList.size();
    }

    @Override
    public PostValue getItem(int position) {
        return postValueArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_post, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PostValue postValue = getItem(position);




                 viewHolder.tvTitle.setText(postValue.getBrand());
        viewHolder.cost.setText(postValue.getPrice() +  postValue.getDate());

        viewHolder.descriptionOfItem.setText(postValue.getDescription());
        viewHolder.deliveryTime.setText("Cрок доставки: " + postValue.getDeliveryTime() + " дней");
        viewHolder.article.setText("Артикул автозапчасти: " + postValue.getArticle());
        viewHolder.checkBox.setChecked(false);



        viewHolder.checkBox.setTag(position);




        return convertView;
    }

    private class ViewHolder {
        Button order;
         CheckBox checkBox;
        TextView tvTitle,  cost, deliveryTime, descriptionOfItem, article, costTwo;

        public ViewHolder(View item) {

            tvTitle = (TextView) item.findViewById(R.id.tvTitle);

            cost = (TextView) item.findViewById(R.id.cost);
            deliveryTime = (TextView) item.findViewById(R.id.delivery_time);
            descriptionOfItem = (TextView) item.findViewById(R.id.description);
            checkBox = (CheckBox) item.findViewById(R.id.checkbox);
            costTwo = (TextView) item.findViewById(R.id.costTwo);
            article = (TextView) item.findViewById(R.id.article);



        }




    }







 }




