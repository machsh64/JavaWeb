package test;

import java.io.*;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-07 14:41
 * @description:
 **/
public class ImgMove {
    public static void main(String[] args) {
        String src = "D:\\SteamGM\\steamapps\\workshop\\content\\431960\\2665624464\\scene";
        String dest = "D:\\machs\\Pictures\\album\\wallpaper\\album\\";
        JPGFinder(src, dest);
    }

    public static void JPGFinder(String src, String dest) {
        File[] files = new File(src).listFiles();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            if (files != null)
                for (File file : files) {
                    if (file.isDirectory()) {
                        JPGFinder(file.getAbsolutePath(), dest);
                    } else if (file.getName().endsWith(".jpg") || file.getName().endsWith("png") || file.getName().endsWith("gif") || file.getName().endsWith(".mp4")) {
                        if ((file.length() / 1024) > (3 * 1024)) {
                            bis = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));

                            String destOut = "";
                            if (file.getName().endsWith(".jpg")) {
                                int indexOfLastJpg = file.getName().lastIndexOf(".jpg");
                                String picName = file.getName().substring(0, indexOfLastJpg);
                                destOut = dest + picName + ".jpg";
                            } else if (file.getName().endsWith(".png")) {
                                int indexOfLastGif = file.getName().lastIndexOf(".png");
                                String picName = file.getName().substring(0, indexOfLastGif);
                                destOut = dest + picName + ".png";
                            } else if (file.getName().endsWith(".gif")) {
                                int indexOfLastGif = file.getName().lastIndexOf(".gif");
                                String picName = file.getName().substring(0, indexOfLastGif);
                                destOut = dest + picName + ".gif";
                            } else if (file.getName().endsWith(".mp4")) {
                                int indexOfLastMp4 = file.getName().lastIndexOf(".mp4");
                                String picName = file.getName().substring(0, indexOfLastMp4);
                                destOut = dest + picName + ".mp4";
                            }
                            bos = new BufferedOutputStream(new FileOutputStream(destOut));

                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = bis.read(buffer)) != -1) {
                                bos.write(buffer, 0, len);
                            }
                            System.out.println(file.getAbsolutePath() + "  大小：" + file.length() / 1024 / 1024 + "mb");
                        }
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
