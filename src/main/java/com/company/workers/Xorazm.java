package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Xorazm extends TimerTask {
    private static final Service service = new Service("@vacancy_xorazm", 1733, 15);
    @Override
    public void run() {
        service.run();
    }
}
