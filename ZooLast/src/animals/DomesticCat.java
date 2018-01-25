package animals;

import interfaces.Soundable;
import interfaces.Jumpable;
import main.Main;

public final class DomesticCat extends Feline {
	private int swarmSize;

	public DomesticCat(String nickName, double size) {
		super(nickName, size);
		this.type = "cat";
	}

	public static DomesticCat createCat() {
		System.out.println("Create cat");
		System.out.println("Enter the nickname");
		String name = Main.sc.nextLine();
		System.out.println("Enter the size");
		double size = Main.sc.nextDouble();
		Main.sc.nextLine();
		return new DomesticCat(name, size);
	}

	@Override
	public void makeSound() {
		System.out.println("Meow....");
	}

	@Override
	public double jump() {
		return (2 + 3 * getSize());
	}
}
