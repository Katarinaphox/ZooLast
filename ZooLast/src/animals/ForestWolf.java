package animals;

import main.Main;

public class ForestWolf extends Canine {

	public ForestWolf(String nickName, double size) {
		super(nickName, size);
		this.type = "wolf";
	}

	public static ForestWolf createWolf() {
		System.out.println("Create wolf");
		System.out.println("Enter the nickname");
		String name = Main.sc.nextLine();
		System.out.println("Enter the size");
		double size = Main.sc.nextDouble();
		Main.sc.nextLine();
		return new ForestWolf(name, size);
	}

	@Override
	public void makeSound() {
		System.out.println("Auuu");
	}

	@Override
	public double jump() {
		return super.jump();
	}
}
