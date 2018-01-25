package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import animals.Animal;
import error.AnimalCreationException;

public class FileImporter {

	public static List<Animal> importFromFile(String fileName) {
		List<Animal> animals = new LinkedList<>();
		File file = new File(fileName);
		int nextByte;
		int tmp = 0;
		try {
			FileReader fr = new FileReader(file);
			StringBuilder sb = new StringBuilder();
			while ((nextByte = fr.read()) != -1) {
				if (nextByte == 10 && tmp == 13) {
					String str = sb.toString();
					animals.add(Animal.convertFromString(str));
					sb.setLength(0);
				} else {
					sb.append((char) nextByte);
					System.out.println((char) nextByte);
				}
				tmp = nextByte;
			}
			String str = sb.toString();
			System.out.println(str);
		} catch (FileNotFoundException e) {
			System.out.println("File: " + fileName + "not found!");
			return animals;
		} catch (IOException e) {
			System.out.println("File reading error!");
			return animals;
		}
		return animals;
	}

	public void searchCsvFile(String parentDirectory) throws IOException {
	// File f = new File(parentDirectory);
	//	if(f.exists()&f.isFile()){
	//	    System.out.println("File is exist");
	//	}else{
	//	    System.out.println("File not found!");
		    File[] filesInDirectory = new File(parentDirectory).listFiles();
	        for(File f : filesInDirectory){
	            if(f.isDirectory()){
	                searchCsvFile(f.getAbsolutePath());
	                System.out.println(f.listFiles());
	            }
	            String filePath = f.getAbsolutePath();
	            String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
	            if("csv".equals(fileExtenstion)){
	                System.out.println("CSV file found -> " + filePath);
	               
	            }
	        }       
	               
		    }
		}
		
	

