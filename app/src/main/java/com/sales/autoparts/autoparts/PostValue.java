package com.sales.autoparts.autoparts;
/**
 * A class for getters and setters for post
 * */
public class PostValue {
    String title, guid, date, price, description, deliveryTime, article;

    public String getBrand() {
        return title;
    }

    public void setBrand(String title) {
        this.title = title;
    }
     //Адрес и ссылка
    public String getNumber() {
        return guid;
    }



    public void setNumber(String guid) {
        this.guid = guid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;

    }
    public String getArticle() {   //Артикул нашей автозапчасти
        return article;
    }

    public void setArticle(String article) {
        this.article = article;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

}