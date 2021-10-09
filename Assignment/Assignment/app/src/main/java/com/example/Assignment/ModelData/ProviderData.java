package com.example.Assignment.ModelData;

public class ProviderData {
    String providername,provideremail,providerphone,providerlocation,totalitems;

    public ProviderData() {
    }

    public ProviderData(String providername, String provideremail, String providerphone, String providerlocation, String totalitems) {
        this.providername = providername;
        this.provideremail = provideremail;
        this.providerphone = providerphone;
        this.providerlocation = providerlocation;
        this.totalitems = totalitems;
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

    public String getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(String totalitems) {
        this.totalitems = totalitems;
    }
}
