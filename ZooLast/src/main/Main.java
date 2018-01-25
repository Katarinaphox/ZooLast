package main;

import java.util.Scanner;

import java.util.Set;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

import animals.*;
import animals.Animal.IAnimalDeadListener;
import error.AnimalCreationException;
import error.AnimalInvalidNameException;
import error.AnimalInvalidSizeException;
import input.Input;
import interfaces.Jumpable;
import interfaces.Soundable;
import io.FileImporter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.*;
import io.*;

public class Main implements IAnimalDeadListener {
	public static final Scanner sc = new Scanner(System.in);
	private ExtensibleCage<Mammal> mammalCage;
	private ExtensibleCage<Bird> birdCage;
	ExtensibleCage<Herbivore> herbivoreCage;
	Map<String, ExtensibleCage<? extends Animal>> cages = new HashMap<>();;

	private Main() {
		cages.put(Mammal.class.getSimpleName(), new ExtensibleCage<Mammal>());
		cages.put(Bird.class.getSimpleName(), new ExtensibleCage<Bird>());
		cages.put(Herbivore.class.getSimpleName(), new ExtensibleCage<Herbivore>());
		boolean userInput = true;
		while (userInput) {
			System.out.println("\nChoose the number to do anything...");
			System.out.println(
					"1) Info \n 2) To feed animal \n 3) To create animal \n 4) To start a competition \n 5) To remove animal \n 6) to import list of animals \n");
			String answer = sc.nextLine();
			switch (inputValidation(answer)) {
			case 1: {
				showAnimalInfo();
				break;
			}
			case 2: {
				feedAnimal();
				break;
			}
			case 3: {
				System.out.println("Creation of animal...");
				Animal ani = null;
				try {
				} catch (InputMismatchException e) {
					System.out.println("NumberFormatException. Enter int instead of string");
				} catch (AnimalInvalidNameException e) {
					System.out.println(e.getMessage());
				} catch (AnimalInvalidSizeException e) {
					System.out.println(e.getMessage());
				} catch (AnimalCreationException e) {
					System.out.println(e.getMessage());
				}
				if (ani == null) {
					continue;
				}
				sortToCage(ani);
				break;
			}
			case 4: {
				jumpAll();

				break;
			}
			case 5: {
				removeAnimal();
				break;
			}
			case 6: {
				System.out.println("Enter the path:");
				String directoryPath = sc.nextLine();
				searchCsvFile(directoryPath);
			}
				try {
					List<Animal> arrAnimal = FileImporter.importFromFile("animals.csv");
					for (Animal an : arrAnimal) {
						sortToCage(an);
						System.out.println(an.toString());
					}

				} catch (AnimalCreationException e) {
					System.out.println("File converting error");
				}
				break;
			}

		}
	}

	public static void main(String[] args) {
		Configurator.defaultConfig().writer(new ConsoleWriter()).addWriter(new FileWriter("log.txt")).level(Level.INFO)
				.activate();
		Logger.warn("Hello tinnylog");
		new Main();
		sc.close();
		// Input.readFromFile();
	}

	public void sortToCage(Animal animal) {
		if (animal instanceof Bird) {
			ExtensibleCage<Bird> cage = (ExtensibleCage<Bird>) cages.get(Bird.class.getSimpleName());
			cage.addAnimal((Bird) animal);
			System.out.println("The size of cageBird = " + cages.get(Bird.class.getSimpleName()).getSize());
		} else if (animal instanceof Predator) {
			((ExtensibleCage<Mammal>) cages.get(Mammal.class.getSimpleName())).addAnimal((Predator) animal);
			System.out.println("The size of cagePredator = " + cages.get(Mammal.class.getSimpleName()).getSize());
		} else {
			double rndCage = Math.random();
			if (rndCage >= 0.6 && rndCage < 1) {
				((ExtensibleCage<Herbivore>) cages.get(Herbivore.class.getSimpleName())).addAnimal((Herbivore) animal);
				System.out
						.println("The size of cageHerbivore = " + cages.get(Herbivore.class.getSimpleName()).getSize());
			} else {
				((ExtensibleCage<Mammal>) cages.get(Mammal.class.getSimpleName())).addAnimal((Mammal) animal);
				System.out.println("The size of cagePredator = " + cages.get(Mammal.class.getSimpleName()).getSize());
			}
		}
	}

	public static void displayAnimals(Animal[] ani) {
		for (int i = 0; i < ani.length; i++)
			System.out.println(ani[i].toString());
	}

	public void SoundAll(Soundable[] cat) {
		for (int i = 0; i < cat.length; i++) {

			cat[i].makeSound();
		}
	}

	public void jumpAll() {
		if (cages.get(Mammal.class.getSimpleName()).isEmpty() && cages.get(Bird.class.getSimpleName()).isEmpty()
				&& cages.get(Herbivore.class.getSimpleName()).isEmpty()) {
			System.out.println("Error///In competition no one play!!");
			return;
		}
		int maxElement = 0;
		double maxLenght = 0;
		if (!cages.get(Mammal.class.getSimpleName()).isEmpty()) {
			for (int i = 0; i < cages.get(Mammal.class.getSimpleName()).getSize(); i++) {
				if (cages.get(Mammal.class.getSimpleName()).getAnimal(i).jump() > maxLenght) {
					maxLenght = cages.get(Mammal.class.getSimpleName()).getAnimal(i).jump();
					maxElement = i;
				}
			}
			System.out.println("The longest jump in cageMammal is of "
					+ cages.get(Mammal.class.getSimpleName()).getAnimal(maxElement).toString() + " jump = "
					+ maxLenght);
		} else {
			System.out.println("Among animals from cageMammal no one animal");
		}
		if (!cages.get(Bird.class.getSimpleName()).isEmpty()) {
			for (int i = 0; i < cages.get(Bird.class.getSimpleName()).getSize(); i++) {
				if (cages.get(Bird.class.getSimpleName()).getAnimal(i).jump() > maxLenght) {
					maxLenght = cages.get(Bird.class.getSimpleName()).getAnimal(i).jump();
					maxElement = i;
				}
			}
			System.out.println("The longest jump in cageBird is of "
					+ cages.get(Bird.class.getSimpleName()).getAnimal(maxElement).toString() + " jump = " + maxLenght);
		} else {
			System.out.println("Among animals from cageBird no one animal");
		}
		if (!cages.get(Herbivore.class.getSimpleName()).isEmpty()) {
			for (int i = 0; i < cages.get(Herbivore.class.getSimpleName()).getSize(); i++) {
				if (cages.get(Herbivore.class.getSimpleName()).getAnimal(i).jump() > maxLenght) {
					maxLenght = cages.get(Herbivore.class.getSimpleName()).getAnimal(i).jump();
					maxElement = i;
				}
			}
			System.out.println("The longest jumpin cageHerbivore is of"
					+ cages.get(Herbivore.class.getSimpleName()).getAnimal(maxElement).toString() + " jump = "
					+ maxLenght);
		} else {
			System.out.println("Among animals from cageHerbivore no one animal");
		}
	}

	public static int inputValidation(String answer) {

		if (answer.equals("1")) {
			return 1;
		} else if (answer.equals("2")) {
			return 2;
		} else if (answer.equals("3")) {
			return 3;
		} else if (answer.equals("4")) {
			return 4;
		} else if (answer.equals("5")) {
			return 5;
		} else {
			return 6;
		}

	}

	public Animal createAnimal() throws AnimalCreationException {
		Animal animal = null;
		for (;;) {
			System.out.println(
					"Choose the number to create...\n1- WOLF\n2- cat\n3-Raven\n4-rabbit" + "\nor enter r - to return");
			String answer = Main.sc.nextLine();
			switch (answer) {
			case "1":
				ForestWolf wolf = ForestWolf.createWolf();
				// wolf.setAnimalDeadListener(this);
				animal = wolf;
				if (animal != null && animal.getNickName().isEmpty()) {
					throw new AnimalInvalidNameException();
				}
				if (animal.getSize() <= 0) {
					throw new AnimalInvalidSizeException();
				}
				break;
			case "2":
				DomesticCat cat = DomesticCat.createCat();
				animal = cat;
				if (animal != null && animal.getNickName().isEmpty()) {
					throw new AnimalInvalidNameException();
				}
				if (animal.getSize() <= 0) {
					throw new AnimalInvalidSizeException();
				}
				break;
			case "3":
				Raven raven = Raven.createRaven();
				animal = raven;
				if (animal != null && animal.getNickName().isEmpty()) {
					throw new AnimalInvalidNameException();
				}
				if (animal.getSize() <= 0) {
					throw new AnimalInvalidSizeException();
				}
				break;
			case "4":
				Rabbit rabbit = Rabbit.createRabbit();
				animal = rabbit;
				if (animal != null && animal.getNickName().isEmpty()) {
					throw new AnimalInvalidNameException();
				}
				if (animal.getSize() <= 0) {
					throw new AnimalInvalidSizeException();
				}
				break;
			case "r":
				animal = null;
			}

			break;

		}

		return animal;
	}

	public void removeAnimal() {
		System.out.println("What animal to delete: 1-mammal, 2-bird, 3-rabbit?");

		int indRemoveAnimal;
		int numberCage = sc.nextInt();
		switch (numberCage) {
		case 1:

			if (cages.get(Mammal.class.getSimpleName()).getSize() == 0) {
				System.out.println("Error");
				return;
			} else {
				System.out.println(
						"Which animal to deleteü? from 1 to " + cages.get(Mammal.class.getSimpleName()).getSize());
				indRemoveAnimal = sc.nextInt() - 1;
				sc.nextLine();
				if (indRemoveAnimal + 1 > cages.get(Mammal.class.getSimpleName()).getSize()) {
					return;
				}
				cages.get(Mammal.class.getSimpleName()).removeAnimal(indRemoveAnimal);
			}

			System.out.println("New size of cage " + cages.get(Mammal.class.getSimpleName()).getSize());
			break;
		case 2:
			if (cages.get(Bird.class.getSimpleName()).getSize() == 0) {
				System.out.println("Error");
				return;
			} else {
				System.out.println(
						"Which animal to delete? from 1 to " + cages.get(Bird.class.getSimpleName()).getSize());
				indRemoveAnimal = sc.nextInt() - 1;
				sc.nextLine();
				if (indRemoveAnimal + 1 > cages.get(Bird.class.getSimpleName()).getSize()) {
					return;
				}
				cages.get(Bird.class.getSimpleName()).removeAnimal(indRemoveAnimal);
			}

			System.out.println("New size of cage " + cages.get(Bird.class.getSimpleName()).getSize());
			break;

		case 3:
			if (cages.get(Herbivore.class.getSimpleName()).getSize() == 0) {
				System.out.println("Error");
				return;
			} else {
				System.out.println(
						"Which animal to delete? from 1 to " + cages.get(Herbivore.class.getSimpleName()).getSize());
				indRemoveAnimal = sc.nextInt() - 1;
				sc.nextLine();
				if (indRemoveAnimal + 1 > cages.get(Herbivore.class.getSimpleName()).getSize()) {
					return;
				}
				cages.get(Bird.class.getSimpleName()).removeAnimal(indRemoveAnimal);
			}

			System.out.println("New size of cage " + cages.get(Herbivore.class.getSimpleName()).getSize());
			break;
		}
		showAnimalInfo();
		System.out.println("New size of cagePredator = " + cages.get(Mammal.class.getSimpleName()).getSize()
				+ "\nNew size of cageBird = " + cages.get(Bird.class.getSimpleName()).getSize()
				+ "\nNew size of cageHerbivore = " + cages.get(Herbivore.class.getSimpleName()).getSize());
	}

	private void showAnimalInfo() {
		StringBuilder sb = new StringBuilder();

		Set<String> keys = cages.keySet();
		for (String key : keys) {
			sb.append(cages.get(key).toString());
			sb.append("\n");
		}
		System.out.println(sb.toString());
		dead();
	}

	private void dead() {
		for (int i = 0; i < cages.get("Mammal").getSize(); i++) {
			if (cages.get("Mammal").getAnimal(i).getFill() < 0) {
				onAnimalDead(cages.get("Mammal").getAnimal(i));
				cages.get("Mammal").removeAnimal(i);
			}
		}
		for (int i = 0; i < cages.get("Bird").getSize(); i++) {
			if (cages.get("Bird").getAnimal(i).getFill() < 0) {
				onAnimalDead(cages.get("Bird").getAnimal(i));
				cages.get("Bird").removeAnimal(i);
			}
		}
		for (int i = 0; i < cages.get("Herbivore").getSize(); i++) {
			if (cages.get("Herbivore").getAnimal(i).getFill() < 0) {
				onAnimalDead(cages.get("Herbivore").getAnimal(i));
				cages.get("Herbivore").removeAnimal(i);
			}
		}
	}

	public void feedAnimal() {
		double countOfFood;
		for (String key : cages.keySet()) {
			System.out.println("Size of " + key + " " + cages.get(key).getSize());
		}
		System.out.println("\nFrom which cage you want to feed animal? \n1-mammal \t2-bird \t3-herbivore");
		int numberCage = sc.nextInt();
		int numberAnimal;
		switch (numberCage) {
		case 1:
			if (cages.get(Mammal.class.getSimpleName()).getSize() == 0) {
				return;
			} else {
				System.out.println("\nWhich animal you want to feed? From 1 to "
						+ cages.get(Mammal.class.getSimpleName()).getSize());
				numberAnimal = sc.nextInt() - 1;
				if (numberAnimal + 1 > cages.get(Mammal.class.getSimpleName()).getSize()) {
					return;
				}
				System.out.println("\nHow much you want to feed the mammal");
				countOfFood = sc.nextDouble();
				sc.nextLine();
				cages.get(Mammal.class.getSimpleName()).getAnimal(numberAnimal).feed(countOfFood);
			}
			break;
		case 2:
			if (cages.get(Bird.class.getSimpleName()).getSize() == 0) {
				return;
			} else {
				System.out.println("\nWhich animal you want to feed? From 1 to "
						+ cages.get(Bird.class.getSimpleName()).getSize());
				numberAnimal = sc.nextInt() - 1;
				if (numberAnimal + 1 > cages.get(Bird.class.getSimpleName()).getSize()) {
					return;
				}
				System.out.println("\nHow much you want to feed the bird");
				countOfFood = sc.nextDouble();
				sc.nextLine();
				cages.get(Bird.class.getSimpleName()).getAnimal(numberAnimal).feed(countOfFood);
			}
			break;
		case 3:
			if (cages.get(Herbivore.class.getSimpleName()).isEmpty()) {
				return;
			} else {
				System.out.println("\nWhich animal you want to feed? From 1 to "
						+ cages.get(Herbivore.class.getSimpleName()).getSize());
				numberAnimal = sc.nextInt() - 1;
				if (numberAnimal + 1 > cages.get(Herbivore.class.getSimpleName()).getSize()) {
					return;
				}
				System.out.println("\nHow much you want to feed the herbivore");
				countOfFood = sc.nextDouble();
				sc.nextLine();
				cages.get(Herbivore.class.getSimpleName()).getAnimal(numberAnimal).feed(countOfFood);
			}
			break;
		}
	}

	@Override
	public void onAnimalDead(Animal animal) {
		System.out.println("It died " + animal.toString());
		ExtensibleCage cage = this.cages.get(animal.getClass().getSimpleName());

	}

	private void searchCsvFile(String directoryPath) {
		// TODO Auto-generated method stub

	}
}
