package com.company.workers;

import com.company.Service;

import java.util.TimerTask;

public class Qoraqalpoq extends TimerTask {
    private static final Service service = new Service("@vacancy_qoraqalpogiston", 1735, 15);

    @Override
    public void run() {
        service.run();
    }
}
