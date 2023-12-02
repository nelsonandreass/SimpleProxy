package com.proxy.proxy.helper;

import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
	public LogWriter() {};
	public void Writer(String content) {
		String currentDirectory = System.getProperty("user.dir");
		String fileName = currentDirectory+"/logs.txt";
		try (FileWriter writer = new FileWriter(fileName, true)){
			writer.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
