package animals;

public class RoboCat extends Feline {

	private static final double DEFAUTL_CAT_SIZE = 21.2;
	private static int catCount;
	private VoiceModule module = new VoiceModule();

	public RoboCat() {

		super("RoboCat #" + catCount++, DEFAUTL_CAT_SIZE);
		this.type = "robotCat";
	}

	public VoiceModule getModule() {
		return module;
	}

	public void setModule(VoiceModule module) {
		this.module = module;
	}

	@Override
	public void makeSound() {
		System.out.println(module.getRobosound());
	}

	public static class VoiceModule {
		private String robosound = "0000";

		public String getRobosound() {
			return robosound;
		}

		public void setRobosound(String robosound) {
			this.robosound = robosound;
		}

	}
}
