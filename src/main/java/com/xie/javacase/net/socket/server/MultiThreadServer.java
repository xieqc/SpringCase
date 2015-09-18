package com.xie.javacase.net.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
    private int port = 4700;    //socket端口号
    private int backlog = 10;   //The maximum queue length for incoming connection indications
    private ServerSocket serverSocket;
    private ExecutorService executorService;    //线程池
    private final int POOL_SIZE = 10;   //单个CPU线程池大小
    public static HashMap<Integer,Handler> socketMap;
    private Integer socketKey = 0;

    public MultiThreadServer() throws IOException {
        serverSocket = new ServerSocket(port, backlog);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE); //Runtime的availableProcessor()方法返回当前系统的CPU数目.
        socketMap = new HashMap<Integer,Handler>();
        System.out.println("server is started.");
    }

    public void service() throws IOException {
        while (true) {
            Socket socket = null;
            try{
                socket = serverSocket.accept();	//使用accept()阻塞等待客户请求，有客户请求到来则产生一个Socket对象，并继续执行
                Handler handler = new Handler(socket);
                executorService.execute(handler);
                socketMap.put(++socketKey,handler);
                System.out.println(socketKey+" is connection...");
            } catch(Exception e) {
                System.out.println("Error."+e);	//出错，打印出错信息
            }
        }
    }

    public static void main(String[] args) throws IOException{
        new MultiThreadServer().service();
    }
}

class Handler implements Runnable{
    private Socket socket;
    public BufferedReader is;
    public PrintWriter os;
    public Handler(Socket socket) throws IOException {
        this.socket = socket;
        is = new BufferedReader(new InputStreamReader(socket.getInputStream()));	//由Socket对象得到输入流，并构造相应的BufferedReader对象
        os = new PrintWriter(socket.getOutputStream());	//由Socket对象得到输出流，并构造PrintWriter对象
    }
    public void run(){
        try{
            String line;
            os.println("welcome logon.");	//向客户端输出该字符串
            os.flush();	//刷新输出流，使Client马上收到该字符串
            line = is.readLine();	//从标准输入读入一字符串
            while(line != null){	//如果该字符串为 "bye"，则停止循环
                String [] str = line.split(":");
                //向目标输出信息
                MultiThreadServer.socketMap.get(Integer.valueOf(str[0])).os.println("accpet:"+line);
                MultiThreadServer.socketMap.get(Integer.valueOf(str[0])).os.flush();

                //发送端显示回馈
                os.println("accpet:"+line);	//向客户端输出该字符串
                os.flush();	//刷新输出流，使Client马上收到该字符串
                line = is.readLine(); //从系统标准输入读入一字符串
            }	//继续循环
            os.println("connect close.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          try {
              if(socket != null)socket.close();
              os.close(); //关闭Socket输出流
              is.close(); //关闭Socket输入流
          } catch (IOException e) {
              e.printStackTrace();
          }
        }
    }
}