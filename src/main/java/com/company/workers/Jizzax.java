package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Jizzax extends TimerTask {
    private static final Service service = new Service("@vacancy_jizzax",  1708, 20);

    @Override
    public void run() {
        service.run();
    }
}
