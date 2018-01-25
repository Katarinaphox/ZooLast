package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	public static void log(String logStr) {
		DateFormat df = new SimpleDateFormat("dd_MM_YYYY ");
		DateFormat timeFormat = new SimpleDateFormat("YYYY_MM_dd HH:mm:ss.SSS");
		Date date = new Date();
		String fileName = "log_" + df.format(date);
		File file = new File("log.txt");
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(file));
			new FileWriter(file, true);
			pw.println(timeFormat.format(date) + " " + logStr);
			pw.flush();// скинуть с буфера
			pw.close();

		} catch (IOException e) {
			System.out.println("Can't create file");
		}
	}
}