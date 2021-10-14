package com.example.Assignment.ModelData;

public class ProductData {

    String productid,productphoto,productname,productdesc,productprice,providername,provideremail,providerphone,providerlocation;

    public ProductData() {
    }

    public ProductData(String productid, String productphoto, String productname, String productdesc, String productprice, String providername, String provideremail, String providerphone, String providerlocation) {
        this.productid = productid;
        this.productphoto = productphoto;
        this.productname = productname;
        this.productdesc = productdesc;
        this.productprice = productprice;
        this.providername = providername;
        this.provideremail = provideremail;
        this.providerphone = providerphone;
        this.providerlocation = providerlocation;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
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

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public String getProvideremail() {
        return provideremail;
    }

    public void setProvideremail(String provideremail) {
        this.provideremail = provideremail;
    }

    public String getProviderphone() {
        return providerphone;
    }

    public void setProviderphone(String providerphone) {
        this.providerphone = providerphone;
    }

    public String getProviderlocation() {
        return providerlocation;
    }

    public void setProviderlocation(String providerlocation) {
        this.providerlocation = providerlocation;
    }
}
