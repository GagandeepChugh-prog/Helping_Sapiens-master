package com.example.mukulsharma.helping_sapiens;

public class seekerandprovider {
    String seekerrating,providerrating;

    seekerandprovider()
    {
        seekerrating="0.0";
        providerrating="0.0";
    }

    public String getProviderrating() {
        return providerrating;
    }

    public String getSeekerrating() {
        return seekerrating;
    }

    public void setProviderrating(String providerrating) {
        this.providerrating = providerrating;
    }

    public void setSeekerrating(String seekerrating) {
        this.seekerrating = seekerrating;
    }

}
