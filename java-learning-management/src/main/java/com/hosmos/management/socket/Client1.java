package com.hosmos.management.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

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
 * @date 2019年01月24日
 */
public class Client1 {
    private Socket socket;

    public Client1() {
        try {
            socket = new Socket("localhost", 8088);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        final Socket socket1 = this.socket;
        new Thread(new Runnable() {//创建一条线程，监听获取服务端发送的消息
            @Override
            public void run() {
                try {
                    InputStream in = socket1.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    //System.out.println("客户端说：" + br.readLine());
                    //不断读取客户端数据
                    while (true) {
                        System.out.println("服务端说：" + br.readLine());
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        try {//向服务端发送消息
            OutputStream out = socket.getOutputStream();
            //OutputStreamWriter osw = new OutputStreamWriter(out, true);
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            PrintWriter pw = new PrintWriter(osw, true);
            pw.println("你好！服务器！");
            //创建Scanner读取用户输入内容
            Scanner scanner = new Scanner(System.in);
            while (true) {
                //scan.nextLine();
                System.out.println("尊敬的用户，请您输入：");
                pw.println(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Client1 client = new Client1();
        client.start();
    }
}