package com.example.kate.rentafriend.RetrofitMetroAreaID;

public class MetroAreaID {

    private static int id;

    public MetroAreaID(int id) {
        this.id = id;
    }

    public static String getId() {
        return id + "";
    }

    public static void setId(int id) {
        MetroAreaID.id = id;
    }
}
