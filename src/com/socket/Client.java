package com.socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String QUIT = "quit";
        final String DEFAULT_SERVER_HOST = "127.0.0.1";
        final int DEFAULT_SERVER_PORT = 8888;
        Socket socket = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
            // 创建IO流
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(socket.getInputStream())));

            while (true) {
                // 等待用户输入
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                String inputInfo = consoleReader.readLine();

                // 发送信息给服务端
                bufferedWriter.write(inputInfo + System.lineSeparator());
                bufferedWriter.flush();

                // 读取服务器端响应的信息
                String responseStr = bufferedReader.readLine();
                System.out.println(responseStr);

                // 检查用户是否QUIT
                if (QUIT.equals(inputInfo)) {
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    // bufferedWriter.close() 会先 flush，再关闭 socket，
                    // 因此无需再关闭 socket
                    bufferedWriter.close();
                    System.out.println("Socket已关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
