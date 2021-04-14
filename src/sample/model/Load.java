package sample.model;

import com.google.gson.Gson;

import java.io.*;

public class Load
{
    public static void loadGame(File game) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(game));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Object json = gson.fromJson(bufferedReader, Object.class);

        System.out.println(json.toString());
    }
}






