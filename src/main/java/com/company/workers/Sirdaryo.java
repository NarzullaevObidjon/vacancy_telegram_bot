package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Sirdaryo extends TimerTask {
    private static final Service service = new Service("@vacancy_sirdaryo", 1724, 20);


    @Override
    public void run() {
        service.run();
    }
}
