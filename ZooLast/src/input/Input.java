package input;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import animals.Animal;
import main.Main;

public class Input {
	public static double inputNumber(String str) {
		System.out.println(str);
		while (true) {// бесконечный цикл
			String input = Main.sc.nextLine();
			try {
				return Double.parseDouble(input.replace(",", "."));
			} catch (NumberFormatException e) {
				System.out.println("Incorrect iput, please try again");
			}
		}
	}
	public static String inputFromConsole() {
		int tmp;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while ((tmp = br.read()) != -1) {
				sb.append((char) tmp);
			}
		} catch (IOException e) {
			System.out.println("Input/output error");
		}
		return "";
	}
	 public static List<Animal> readFromFile() {
	        File file = new File("animals.csv");
	        if (file.exists()) {
	            System.out.println("This file is exists");
	        } else {
	            System.out.println("Not found");
	        }

	        try {
	            int tmp;
	            BufferedInputStream br =
	                    new BufferedInputStream(new FileInputStream(file));
	            while ((tmp = br.read()) != -1) {
	                System.out.println(tmp);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;

		
			}
	}
