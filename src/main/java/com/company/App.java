package com.company;

import com.company.workers.*;

import java.util.Timer;

public class App {
    private static final Toshkent toshkent = new Toshkent();
    private static final Andijon andijon = new Andijon();
    private static final Namangan namangan = new Namangan();
    private static final Fargona fargona = new Fargona();
    private static final Sirdaryo sirdaryo = new Sirdaryo();
    private static final Jizzax jizzax = new Jizzax();
    private static final Samarqand samarqand = new Samarqand();
    private static final Qashqadaryo qashqadaryo = new Qashqadaryo();
    private static final Surxondaryo surxondaryo = new Surxondaryo();
    private static final Navoiy navoiy = new Navoiy();
    private static final Buxoro buxoro = new Buxoro();
    private static final Xorazm xorazm = new Xorazm();
    private static final Qoraqalpoq qoraqalpoq = new Qoraqalpoq();
    private static final Poytaxt poytaxt = new Poytaxt();
    private static final Timer toshkentTimer = new Timer();
    private static final Timer poytaxtTimer = new Timer();
    private static final Timer andijonTimer = new Timer();
    private static final Timer fargonaTimer = new Timer();
    private static final Timer namanganTimer = new Timer();
    private static final Timer sirdaryoTimer = new Timer();
    private static final Timer jizzaxTimer = new Timer();
    private static final Timer samarqandTimer = new Timer();
    private static final Timer qashqadaryoTimer = new Timer();
    private static final Timer surxondaryoTimer = new Timer();
    private static final Timer navoiyTimer = new Timer();
    private static final Timer buxoroTimer = new Timer();
    private static final Timer xorazmTimer = new Timer();
    private static final Timer qoraqalpoqTimer = new Timer();

    public static void main(String[] args) {
        try {
            toshkentTimer.schedule(toshkent, 0, 10 * 60 * 1000);
            poytaxtTimer.schedule(poytaxt, 0, 10 * 60 * 1000);
            andijonTimer.schedule(andijon, 0, 10 * 60 * 1000);
            fargonaTimer.schedule(fargona, 0, 10 * 60 * 1000);
            namanganTimer.schedule(namangan, 0, 10 * 60 * 1000);
            sirdaryoTimer.schedule(sirdaryo, 0, 10 * 60 * 1000);
            jizzaxTimer.schedule(jizzax, 0, 10 * 60 * 1000);
            samarqandTimer.schedule(samarqand, 0, 10 * 60 * 1000);
            qashqadaryoTimer.schedule(qashqadaryo, 0, 10 * 60 * 1000);
            surxondaryoTimer.schedule(surxondaryo, 0, 10 * 60 * 1000);
            navoiyTimer.schedule(navoiy, 0, 10 * 60 * 1000);
            buxoroTimer.schedule(buxoro, 0, 10 * 60 * 1000);
            xorazmTimer.schedule(xorazm, 0, 10 * 60 * 1000);
            qoraqalpoqTimer.schedule(qoraqalpoq, 0, 10 * 60 * 1000);
        } catch (Exception e) {
            NotificationBot notificationBot = new NotificationBot();
            notificationBot.sendMessage("App da exception bo'ldi : " + e.getMessage());
        }
    }
}
