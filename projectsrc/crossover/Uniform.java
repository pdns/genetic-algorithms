package projectsrc.projectsrc.crossover;

import java.util.Random;

public class Uniform extends CrossoverAlgorithm {
	
	public Uniform(double r) {
		super(r);
		System.out.println("Uniform Crossover");
	}

	public void perform(int[] par1, int[] par2, Random random) {
		for (int i = 0; i < par1.length; i++) {
			if (random.nextBoolean()) {
				int tmp = par1[i];
				par1[i] = par2[i];
				par2[i] = tmp;
			}
		}
	}
}
