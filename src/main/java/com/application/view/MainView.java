package com.application.view;

import com.application.controller.MainController;

import java.util.Scanner;

public class MainView {
    public void execute(){
        Scanner sc = new Scanner(System.in);

        greetings();

        String path = sc.nextLine();

        MainController.INSTANCE.execute(path);

        ending();
    }

    private void greetings(){
        System.out.println("Hello, input path to the file:");
    }

    private void ending(){
        System.out.println("All is ready!");
    }
}
