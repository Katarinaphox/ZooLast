package animals;

import interfaces.Soundable;

public abstract class Predator extends Mammal {
	boolean isScavender;

	public Predator(String nickName, double size) {
		super(nickName, size);
	}

	public void consume(Herbivore prey) {
		this.feed(prey.getSize() * 5);
		prey.onConsumed();
		System.out.println(this + "���� " + prey);

	}
}
