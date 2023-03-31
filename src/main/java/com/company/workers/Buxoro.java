package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Buxoro extends TimerTask {
    private static final Service service = new Service("@vacancy_buxoro", 1706, 20);
    @Override
    public void run() {
        service.run();
    }
}
