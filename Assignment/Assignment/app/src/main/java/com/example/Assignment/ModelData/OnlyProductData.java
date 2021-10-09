package com.example.Assignment.ModelData;

public class OnlyProductData {
    String productphoto,productname,productdescription,productprice;

    public OnlyProductData() {
    }

    public OnlyProductData(String productphoto, String productname, String productdescription, String productprice) {
        this.productphoto = productphoto;
        this.productname = productname;
        this.productdescription = productdescription;
        this.productprice = productprice;
    }

    public String getProductphoto() {
        return productphoto;
    }

    public void setProductphoto(String productphoto) {
        this.productphoto = productphoto;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }
}
