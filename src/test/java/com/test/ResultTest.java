package com.test;

import com.application.controller.MainController;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ResultTest {
    @Test
    public void getResult(){
        MainController.INSTANCE.execute("/Users/zaher/Desktop/1.txt");
        String expected = "Posh 10:15 11:10\n" +
                "Posh 10:10 11:00\n" +
                "Posh 12:05 12:30\n" +
                "Posh 17:25 18:01\n" +
                "\n" +
                "Grotty 12:45 13:25";
        try {
            FileReader fr = new FileReader("/Users/zaher/Desktop/output.txt");
            Scanner sc = new Scanner(fr);
            String actual = "";

            while(sc.hasNext()){
                actual += sc.nextLine();
                if(sc.hasNext()){
                    actual += "\n";
                }
            }
            
            Assert.assertEquals(expected, actual);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
