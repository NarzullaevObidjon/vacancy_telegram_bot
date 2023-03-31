package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Surxondaryo extends TimerTask {
    private static final Service service = new Service("@vacancy_surxondaryo",  1722, 20);
    @Override
    public void run() {
        service.run();
    }
}
