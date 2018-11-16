package candidate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class demo {

	public static void main(String[] args) throws Exception {
		demo d = new demo();
		
		for (int i = 0; i < 50; i++) {
			String mail = d.emailGererator(d.getemail());
			d.writelog(mail);
			System.out.println(mail);
		}
	}

	private void writelog(String log) throws Exception {
		File file = new File("./log.txt");
		FileWriter writer = new FileWriter(file);
		writer.write(log);
		writer.close();
	}

	private String getemail() throws IOException {
		String file = "./log.txt";
		List<String> lines = Files.readAllLines(Paths.get(file));
		String re =lines.get(0).toString().trim();
		return re;
	}

	private String emailGererator(String email) {
		int value = Integer.parseInt(email.replaceAll("[^0-9]", ""));
		String name = email.replaceAll("[0-9]", "");
		return name+Integer.toString(value+1);
	}
}
