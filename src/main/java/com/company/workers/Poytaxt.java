package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Poytaxt extends TimerTask {
    private static final Service service = new Service("@vacancy_poytaxt", 1726, 40);
    @Override
    public void run() {
        service.run();
    }
}
