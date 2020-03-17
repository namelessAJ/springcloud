package com.test.jnm;

public class Singleton implements Runnable {

    private static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread t1 = new Thread(new Singleton());
            t1.start();
        }
    }

    @Override
    public void run() {
        Singleton s = getInstance();
        System.out.println(s.toString());
    }
}
