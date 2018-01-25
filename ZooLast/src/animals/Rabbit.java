package animals;

import main.Main;

public class Rabbit extends Herbivore {

	public Rabbit(String nickName, double size) {
		super(nickName, size);
	}

	@Override
	public void makeSound() {
		super.makeSound();
	}

	@Override
	public double jump() {
		return (2 + 3 * getSize());
	}

	public static Rabbit createRabbit() {
		System.out.println("Create rabbit");
		System.out.println("Enter the nickname");
		String name = Main.sc.nextLine();
		System.out.println("Enter the size");
		double size = Main.sc.nextDouble();
		Main.sc.nextLine();
		return new Rabbit(name, size);
	}

}