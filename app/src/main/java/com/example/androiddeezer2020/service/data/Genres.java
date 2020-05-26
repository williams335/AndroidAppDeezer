package com.example.androiddeezer2020.service.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genres {

    @SerializedName("data")
    @Expose
    private List<Genre> data = null;

    public List<Genre> getData() {
        return data;
    }

    public void setData(List<Genre> data) {
        this.data = data;
    }

}

