package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Navoiy extends TimerTask {
    private static final Service service = new Service("@vacancy_navoiy",  1712, 20);
    @Override
    public void run() {
        service.run();
    }
}
