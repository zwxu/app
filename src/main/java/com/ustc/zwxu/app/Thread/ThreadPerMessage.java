package com.ustc.zwxu.app.Thread;
public class ThreadPerMessage {
    public static void main(String args[]) {
        System.out.println("BEGIN");
        Object obj = new Object();
        Blackhole.enter(obj);
        System.out.println("END");
    }
}


class Blackhole {
    public static void enter(Object obj) {
        System.out.println("Step 1");
        magic(obj);
        System.out.println("Step 2");
        synchronized (obj) {
            System.out.println("Step 3 (never reached here)");  
        }
    }

    //用新线程反复获取obj锁定
    public static void magic(final Object obj) {
  
        Thread thread = new Thread() {      // inner class
            public void run() {
                synchronized (obj) { // 在此取得obj的锁定
                    synchronized (this) {
                        this.setName("Locked"); // 不设置的话，magic方法跳不出来
                        this.notifyAll();       // 通知已经取得obj的锁定  让thread解除锁定
                    }
                    /*try {
                        this.join();//等待主线程结束，但是主线程卡死在obj上
                    } catch (InterruptedException e) {
                    }*/

                    while (true) {//导致主线程永远得不到obj
                        // 无穷循环
                    }
                }
            }
        };
        
        synchronized (thread) {
            thread.setName("");
            thread.start(); // 线程的启动
            // Guarded Suspension模式
            while (thread.getName().equals("")) {
                try {
                    thread.wait(); //  等待新的线程取得obj的锁定
                } catch (Exception e) {
                }
            }
        }
    }

}
