package com.application.controller;

import com.application.model.service.SearchBetterBusService;
import com.application.model.service.WorkWithFilesService;

import java.util.Scanner;

public enum MainController {
    INSTANCE;

    public void execute(String path){
        Scanner sc = WorkWithFilesService.INSTANCE.findFile(path);
        String answer = SearchBetterBusService.INSTANCE.execute(sc);

        WorkWithFilesService.INSTANCE.createAnswer(answer, path);
    }
}
