package com.rsk.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class App {

	File myDir = new File("C:\\Users\\HP\\OneDrive\\Desktop\\dir199");

	public void welcome() {
		System.out.println("Welcome to Company Lockers - LockedMe.com Application");
		System.out.println("Developer : Ramkrishna");
		System.out.println("-----------------------------------------------------");
		System.out.println("What you wish to do ? (Please select a option from the below) : ");
		System.out.println("(1) List out all the files (in ascending order)");
		System.out.println("(2) To operate with the files");
		System.out.println("(3) Close the Application");
		Scanner userInput1 = new Scanner(System.in);
		int userChoice1 = userInput1.nextInt();
		switch (userChoice1) {
		case 1:
			listAllFiles();
			break;
		case 2:
			operateFiles();
			break;
		case 3:
			closeApp();
			break;
		default:
			System.out.println("Please enter the valid option :");
			break;
		}

	}

	public void listAllFiles() {
		System.out.println("Here are all the existing files : ");
		// create a new folder - dir199
		myDir.mkdir();
		// to list all files present in dir199 folder in ascending order
		String[] allFiles = myDir.list();
		Arrays.sort(allFiles);
		for (String s : allFiles) {
			System.out.println(s);
		}

		mainMenu();

	}

	public void operateFiles() {
		System.out.println("To Operate with files, please select an option from below : ");
		System.out.println("(1) Add a new file");
		System.out.println("(2) Delete a file");
		System.out.println("(3) Search a file");
		Scanner input = new Scanner(System.in);
		int choicez = input.nextInt();
		switch (choicez) {
		case 1:
			addFile();
			break;
		case 2:
			deleteFile();
			break;
		case 3:
			searchFile();
			break;
		default:
			System.out.println("Please enter valid option");
			break;
		}

	}

	public void closeApp() {
		System.exit(0);
	}

	public void addFile() {
		System.out.println("Enter the file to be added : ");
		Scanner usrChoicez = new Scanner(System.in);
		String fileToBeAdded = usrChoicez.next();
		try {
			File file = new File(myDir, fileToBeAdded);
			if (file.exists()) {
				System.out.println("file already exists");
			} else {
				file.createNewFile();
				System.out.println("New file created successfully..!!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Please choose from the below options : ");
		System.out.println("(1) To Read a File");
		System.out.println("(2) To write a File");
		Scanner input = new Scanner(System.in);
		int usrInp = input.nextInt();
		if (usrInp == 1) {
			readFromFile();
		} else if (usrInp == 2) {
			writeToFile();
		} else {
			System.out.println("Please provide valid option ");
		}

		mainMenu();
	}

	public void deleteFile() {
		System.out.println("Please enter filename to be deleted :");
		Scanner userInput = new Scanner(System.in);
		String userFile = userInput.next();
		// to delete a file
		File f1 = new File(myDir, userFile);
		if (f1.exists()) {
			f1.delete();
			System.out.println("file deleted successfully!");
		} else {
			System.out.println("file doesn't exist");
		}

		mainMenu();
	}

	public void searchFile() {
		int flag = 0;
		String[] files = myDir.list();
		if (files == null) {
			System.out.println("Empty Directory");
		} else {
			Scanner usrInp = new Scanner(System.in);
			System.out.println("Enter File to be searched for : ");
			String fileToSearch = usrInp.next();
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i];
				if (fileName.equals(fileToSearch) || fileName.startsWith(fileToSearch)
						|| fileName.endsWith(fileToSearch)) {
					System.out.println(fileName + " found!");
					flag = 1;
				}
			}
		}
		if (flag == 0) {
			System.out.println("File Not Found!");
		}

		mainMenu();
	}

	public void mainMenu() {
		System.out.println("--------------------------------------");
		System.out.println("Press 0 to goto main menu");
		Scanner back = new Scanner(System.in);
		if (back.nextInt() == 0) {
			welcome();
		} else {
			System.exit(0);
		}
	}

	public void readFromFile() {
		System.out.println("Enter filename you wish to read :");
		Scanner input = new Scanner(System.in);
		String usrChoice = input.next();
		File filez = new File(myDir, usrChoice);
		if (filez.exists()) {
			// to read data from file
			try {
				FileReader fr = new FileReader(myDir + "\\" + usrChoice);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				while (line != null) {
					System.out.println(line);
					line = br.readLine();
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else {
			System.out.println("File does not exist!");
		}

		mainMenu();
	}

	public void writeToFile() {
		System.out.println("Enter filename you wish to write :");
		Scanner input = new Scanner(System.in);
		String usrChoice = input.next();
		File filez = new File(myDir, usrChoice);
		if (filez.exists()) {
			System.out.println("Enter number of lines you wish to write : ");
			Scanner inputz = new Scanner(System.in);
			int numOfLines = inputz.nextInt();
			for (int i = 0; i < numOfLines; i++) {
				System.out.println("Enter line to be written : ");
				Scanner data = new Scanner(System.in);
				String userData = data.nextLine();
				// to write some data into file
				try { 
					FileWriter fw = new FileWriter(myDir + "\\" + usrChoice, true);
					PrintWriter pw = new PrintWriter(fw);
					pw.println(userData);
					pw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("File does not exist!"); 
		}

		mainMenu();
	}

	public static void main(String[] args) {
		App app = new App();
		app.welcome(); 
 
	}

}
