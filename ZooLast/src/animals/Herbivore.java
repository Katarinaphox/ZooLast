package animals;

public class Herbivore extends Mammal {

	public Herbivore(String nickName, double size) {
		super(nickName, size);

	}

	public void onConsumed() {
		die();
	}
}