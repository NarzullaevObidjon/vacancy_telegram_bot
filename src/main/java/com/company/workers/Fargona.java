package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Fargona extends TimerTask {
    private static final Service service = new Service("@vacancy_fargona",  1730, 20);
    @Override
    public void run() {
        service.run();
    }
}
