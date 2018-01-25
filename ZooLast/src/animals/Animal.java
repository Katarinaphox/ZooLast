package animals;

import interfaces.Soundable;
import io.Logger;
import error.AnimalCreationException;
import interfaces.Jumpable;

abstract public class Animal extends Object implements Soundable, Jumpable, Comparable<Animal> {
	private String nickName;
	private double size;// size in meters
	private String gender;// male or female
	private long age;// milliseconds
	public String type;
	private double fill;
	private long lastFeedTime;
	private boolean isAlive;
	private IAnimalDeadListener animalDeadListener;

	public Animal(String nickName, double size) {
		super();
		this.nickName = nickName;
		this.size = size;
		this.fill = 120;
		lastFeedTime = System.currentTimeMillis();
		isAlive = true;
	}

	public int compareTo(Animal o) {
		return (int) Math.ceil(this.getSize() - o.getSize());
	}

	public void setAnimalDeadListener(IAnimalDeadListener animalDeadListener) {
		this.animalDeadListener = animalDeadListener;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public double getFill() {
		long timeToDeath = (System.currentTimeMillis() - lastFeedTime) / 1000;
		if (timeToDeath >= fill) {
			die();
		}
		return fill;
	}

	public long getLastFeedTime() {
		return lastFeedTime;
	}

	public void setLastFeedTime(long lastFeedTime) {
		this.lastFeedTime = lastFeedTime;
	}

	public void setFill(double fill) {
		this.fill = fill;
		this.lastFeedTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return "Animal " + type + " " + getNickName() + " size " + getSize() + " fill " + getFill();

	}

	@Override
	public double jump() {
		return size * 10 / (size * size);
	}

	@Override
	public void makeSound() {
	}

	public interface IAnimalDeadListener {
		void onAnimalDead(Animal animal);
	}

	public void sound() {

	}

	 public void feed(double countOfFood) {
	        setFill(getFill() + countOfFood);

	        Logger.log(this+" fed "+countOfFood);
	    }


	protected void die() {

		if (animalDeadListener != null && isAlive) {

			animalDeadListener.onAnimalDead(this);
		}
		isAlive = false;
	}

	public static Animal convertFromString(String str) throws AnimalCreationException {
		String[] arrStr = str.split(",");
		switch (arrStr[0]) {
		case "wolf":
			return new ForestWolf(arrStr[1], Double.parseDouble(arrStr[2]));
		case "cat":
			return new DomesticCat(arrStr[1], new Double(arrStr[2]));
		case "rabbit":
			return new Rabbit(arrStr[1], new Double(arrStr[2]));
		case "bird":
			return new Bird(arrStr[1], new Double(arrStr[2]));
		}
		throw new AnimalCreationException();
	}
}
