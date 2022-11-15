package test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-08 23:00
 * @description:
 **/
public class fooz {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader bfr = null;

        String tempString = null;
        try {
            fr = new FileReader(new File("D:\\VS Code\\WebCode\\code\\src\\test\\fooz.txt"));

            bfr = new BufferedReader(fr);

            while ((tempString = bfr.readLine()) != null)
                System.out.println(tempString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfr != null)
                    bfr.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
