package sample.model;

import com.google.gson.Gson;

import java.io.File;

public class Load
{
    public static void loadGame(File game) {
        Gson gson = new Gson();
        System.out.println(gson);
    }
}






