package com.example.trademate;

public class All_ItemsHelper {
    String image,price,name,short_desc,full_desc,category;

    All_ItemsHelper(){

    }

    All_ItemsHelper(String image,String name,String short_desc,String full_desc,String price,String category){
        this.image=image;
        this.name=name;
        this.short_desc=short_desc;
        this.full_desc=full_desc;
        this.price=price;
        this.category=category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getFull_desc() {
        return full_desc;
    }

    public void setFull_desc(String long_desc) {
        this.full_desc = long_desc;
    }
}
