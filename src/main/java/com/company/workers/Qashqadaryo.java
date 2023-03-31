package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Qashqadaryo extends TimerTask {
    private static final Service service = new Service("@vacancy_qashqadaryo",  1710, 20);
    @Override
    public void run() {
        service.run();
    }
}
