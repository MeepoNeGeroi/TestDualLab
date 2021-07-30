package com.application.model.service;

import com.application.model.StaticFields;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public enum WorkWithFilesService {
    INSTANCE;

    public Scanner findFile(String path){
        try {
            FileReader fr = new FileReader(path);
            Scanner sc = new Scanner(fr);

            return sc;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new Scanner(System.in);
        }
    }

    public void createAnswer(String answer, String path){
        path = convertPath(path);

        try {
            FileWriter fw = new FileWriter(path);

            fw.write(answer);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String convertPath(String path){
        StringBuilder sb = new StringBuilder(path);

        if(System.getProperty("os.name").startsWith("Windows")) {
            sb.reverse();
            sb.delete(0, sb.indexOf("\\"));
            sb.reverse();
            sb.append("output.txt");
        }
        else{
            sb.reverse();
            sb.delete(0, sb.indexOf("/"));
            sb.reverse();
            sb.append("output.txt");
        }

        return sb.toString();
    }
}
