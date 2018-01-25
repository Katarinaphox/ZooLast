package animals;

import main.Main;

public class Raven extends Bird {

	public Raven(String nickName, double size) {
		super(nickName, size);

	}

	public static Raven createRaven() {
		System.out.println("Create Raven");
		System.out.println("Enter the nickname");
		String name = Main.sc.nextLine();
		System.out.println("Enter the size");
		double size = Main.sc.nextDouble();
		Main.sc.nextLine();
		return new Raven(name, size);
	}

	@Override
	public void makeSound() {
		super.makeSound();
	}
}
