package com.onlinelibrary.utils;

import java.io.*;

public class FileUtil {

    public static void writeFromFileToOutputStream(File file, OutputStream outputStream) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bout = new BufferedOutputStream(outputStream);
        int num;
        while ((num = bin.read()) != -1) {
            bout.write(num);
        }
        bin.close();
        bout.close();
    }
}
