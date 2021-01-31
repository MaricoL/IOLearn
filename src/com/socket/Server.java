package com.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final String QUIT = "quit";
        final int DEFAULT_PORT = 8888;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听 " + DEFAULT_PORT + " 端口！");
            while (true) {
                // 等待客户端连接
                Socket socket = serverSocket.accept();
                System.out.println("客户端[" + socket.getPort() + "]连接成功");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter((socket.getOutputStream())));

                // 改进QUIT
                String msg = null;
                while ((msg = bufferedReader.readLine()) != null) {
                    System.out.println("客户端[" + socket.getPort() + "]: " + msg);
                    // 返回客户端响应信息
                    bufferedWriter.write("服务端响应信息：" + msg + System.lineSeparator());
                    bufferedWriter.flush();

                    // 检查客户端是否QUIT
//                    if (QUIT.equals(msg)) {
//                        System.out.println("客户端[" + socket.getPort() + "]已断开连接！");
//                        break;
//                    }
                }

//                // 读取客户端发送过来的消息
//                String msg = bufferedReader.readLine();
//                if (msg != null) {
//                    System.out.println("客户端[" + socket.getPort() + "]: " + msg);
//
//                    // 返回客户端响应信息
//                    bufferedWriter.write("服务端响应信息：" + msg + System.lineSeparator());
//                    bufferedWriter.flush();
//                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("ServerSocket已关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
