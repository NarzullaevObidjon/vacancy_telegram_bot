package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Andijon extends TimerTask {

    private static final Service service = new Service("@vacancy_andijon", 1703, 20);
    @Override
    public void run() {
        service.run();
    }
}
