package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Toshkent extends TimerTask {
    private static final Service service = new Service("@vacancies_tashkent", 1727, 40);

    @Override
    public void run() {
        service.run();
    }
}
