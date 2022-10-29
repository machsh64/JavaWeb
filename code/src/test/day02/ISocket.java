package test.day02;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-24 10:09
 * @description:
 **/
public class ISocket {
    @Test
    public void Server() {
        InetAddress inet = null;
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;

        try {
            //创建服务器对象
            inet = InetAddress.getByName("192.168.156.27");
            socket = new Socket(inet, 9988);

            //获取输入输出流
            os = socket.getOutputStream();
            fis = new FileInputStream("D:\\VS Code\\WebCode\\code\\src\\day05\\img\\picc.jpg");

            //向服务器发送数据
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            //关闭数据输出
            socket.shutdownOutput();

            //接受来自服务器的数据
            is = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer1 = new byte[20];
            int len1;
            while ((len1 = is.read(buffer1)) != -1) {
                baos.write(buffer, 0, len1);
            }
            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
                if (fis != null)
                    fis.close();
                if (os != null)
                    os.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
