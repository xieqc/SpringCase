package com.xie.javacase.net.socket.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 13-12-20
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
public class socketClientFrame extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    private JTextField socketKey,input;
    private JTextArea messages;
    private JButton startButton;
    private JButton cancelButton;
    private JButton sendButton;
    private Thread connectThread;
    private Scanner in;
    private PrintWriter out;
    private SocketChannel channel;
    private Boolean runStatus = true;

    public socketClientFrame() {
        setSize(WIDTH, HEIGHT);
        setTitle("客户端应用");
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        startButton = new JButton("联接");
        cancelButton = new JButton("退出");
        cancelButton.setEnabled(false);
        northPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                startButton.setEnabled(false);
                cancelButton.setEnabled(true);
                socketKey.setEnabled(true);
                input.setEnabled(true);
                sendButton.setEnabled(true);
                connectThread = new Thread(new Runnable() {
                        public void run() {
                            connect();
                        }
                    });
                connectThread.start();
            }
        });
        northPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if (out != null) out.close();
                try {
                    runStatus = false;
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                connectThread.interrupt();
                startButton.setEnabled(true);
                cancelButton.setEnabled(false);
                socketKey.setEnabled(false);
                input.setEnabled(false);
                sendButton.setEnabled(false);
            }
        });

        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.SOUTH);
        socketKey =new JTextField(5);
        socketKey.setSize(50,10);
        socketKey.setEnabled(false);
        centerPanel.add(socketKey);

        input = new JTextField(10);
        input.setSize(100,10);
        input.setEnabled(false);
        sendButton = new JButton("发送");
        sendButton.setEnabled(false);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    out = new PrintWriter(channel.socket().getOutputStream());	//由Socket对象得到输出流，并构造PrintWriter对象
                    out.println(socketKey.getText() + ":" + input.getText());	//将从系统标准输入读入的字符串输出到Server
                    out.flush();	//刷新输出流，使Server马上收到该字符串
                    input.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        centerPanel.add(input);
        centerPanel.add(sendButton);

        messages = new JTextArea();
        messages.setEnabled(false);
        add(new JScrollPane(messages));
    }

    /**
     Connects to the test server.
     */
    public void connect() {
        try {
            channel = SocketChannel.open(new InetSocketAddress("localhost", 4700));
            messages.append("new connect...\n");
            runStatus = true;
            try {
                in = new Scanner(channel);
                while (runStatus) {
                    if (in.hasNextLine())
                    {
                        String line = in.nextLine();
                        messages.append(line);
                        messages.append("\n");
                    }
                    else Thread.sleep(100);
                }
            }
            finally {
                channel.close();
                messages.append("Socket closed\n");
            }
        }
        catch (IOException e) {
            messages.append("\nIOException: " + e +"\n");
        }
        catch (InterruptedException e) {
            messages.append("\nInterruptibleSocketTest.connect: " + e +"\n");
        }
    }
}

