package com.imooc.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegTest {

	private String ffmpegEXE;

	public FFMpegTest() {

	}

	public void convertor(String videoInputPath, String videoOutputPath) throws Exception {
//		ffmpeg -i input.mp4 -y output.avi
		// String avitoflv= "ffmpeg -i "+infile+"
		// -i "+inmp3+" -t " + time + " -c:v copy
		// -c:a aac -strict experimental -map 0:v:0 -map 1:a:0 " + outfile;
		List<String> command = new ArrayList<>();
		command.add("ffmpeg");
		command.add("-i");
		command.add(videoInputPath);
		command.add("-y");
		command.add(videoOutputPath);

		for (String c : command) {
			System.out.print(c + " ");
		}

		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();

		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);

		String line = "";
		while ( (line = br.readLine()) != null ) {
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

	public static void main(String[] args) {


	}

}
