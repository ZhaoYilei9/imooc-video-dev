package com.imooc.utils;

import java.io.*;
import java.util.List;

public class MyFetchCover {
    public void getCover(String videoInputPath, String coverOutputPath) throws IOException, InterruptedException {
//		ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
        String command = "/usr/bin/ffmpeg -ss 00:00:02 -y -i " + videoInputPath + " -vframes 1 " + coverOutputPath;
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
//        OutputStream outputStream = new FileOutputStream("/home/zyl/imooc-video-dev/error.log");
//        OutputStreamWriter osw = new OutputStreamWriter(outputStream);
//        BufferedWriter bw = new BufferedWriter(osw);
        String line = "";
        while ( (line = br.readLine()) != null ) {
//            bw.write(line);
//            bw.flush();
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }
}
