package com.hosmos.management.threadPool;

/**
 * Code is far away from bug with the animal protected
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author chenhuayang
 * @description
 * @date 2019年01月29日
 */
public class ThreadTask implements Runnable {
    private int taskName;
    private boolean isStopped = false;
    public ThreadTask(int taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.err.println("线程open");
        while (!isStopped) {
            try {
                Thread.currentThread().sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
