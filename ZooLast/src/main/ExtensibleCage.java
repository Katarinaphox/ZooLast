package main;

import java.util.ArrayList;
import java.util.List;
import animals.Animal;
import animals.Herbivore;
import animals.Predator;
import animals.Animal.IAnimalDeadListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import animals.Animal;
import animals.Herbivore;
import animals.Predator;

public class ExtensibleCage<T extends Animal> implements Animal.IAnimalDeadListener {
	private static SizeComparator sizeComparator = new SizeComparator();
	private List<T> cage = new LinkedList<>();
	private IAnimalDeadListener animalDeadListener;

	public int addAnimal(T ani) {
		cage.add(ani);
		if (cage.get(0) instanceof Predator) {
			checkHuntCondition(ani);
		}
		return cage.size();
	}

	public boolean removeAnimal(int index) {
		if (index < 0 || index >= cage.size()) {
			return false;
		} else {

			cage.remove(index);
			return true;
		}
	}

	public void checkHuntCondition(Animal animal) {
		if (animal instanceof Herbivore) {
			for (T t : cage) {
				if (t instanceof Predator) {
					((Predator) t).consume((Herbivore) animal);
					return;
				}

			}
		} else if (animal instanceof Predator) {
			for (T t : cage) {
				if (t instanceof Herbivore) {
					((Predator) animal).consume((Herbivore) t);
				}
			}
		}
	}
	public void setCage(List<T> cage) {
		this.cage = cage;
	}

	public List<T> getCage() {
		return cage;
	}

	public void setAnimalDeadListener(IAnimalDeadListener animalDeadListener) {
		this.animalDeadListener = animalDeadListener;
	}

	public IAnimalDeadListener getAnimalDeadListener() {
		return animalDeadListener;
	}

	public void onAnimalDead(Animal animal) {
	}

	public String toString() {
		Collections.sort(cage, ExtensibleCage.sizeComparator);
		// Collections.sort(cage, (Comparator<Animal>) (arg0,arg1) ->
		// (int) Math.ceil(arg1.get.Size()-arg0.get.Size()));

		StringBuilder sb = new StringBuilder();
		if (cage.size() == 0) {
			sb.append("Cage of ");
			sb.append(getClass().getSimpleName());
			sb.append(" is empty\n");
		} else {
			sb.append("Cage for : " + getClass().getSimpleName());
			sb.append("\n");
			Iterator<? extends Animal> it = cage.iterator();
			while (it.hasNext()) {
				Animal animal = it.next();
				if (animal.getNickName() == null || animal.getNickName().length() == 0) {
					it.remove();
				} else {
					sb.append(animal.toString());
					sb.append("\t");
				}
			}
		}
		return sb.toString();
	}

	private static class SizeComparator implements Comparator<Animal> {

		@Override
		public int compare(Animal arg0, Animal arg1) {

			return (int) (arg1.getSize() - arg0.getSize());
		}

	}

	public int getSize() {
		return this.cage.size();
	}

	public boolean isEmpty() {
		return this.cage.isEmpty();
	}

	public T getAnimal(int i) {
		return this.cage.get(i);
	}
}
