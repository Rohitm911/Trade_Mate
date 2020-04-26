package com.example.trademate;

public class electronicshelper {
    String url;
    String price,name, short_desc;
    electronicshelper(String  url,String name,String short_desc,String price){
        this.url=url;
        this.name=name;
        this.short_desc=short_desc;
        this.price=price;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
