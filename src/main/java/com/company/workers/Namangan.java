package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Namangan extends TimerTask {
    private static final Service service = new Service("@vacancies_namangan", 1714, 20);
    @Override
    public void run() {
        service.run();
    }
}
