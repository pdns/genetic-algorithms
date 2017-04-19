package projectsrc.projectsrc.crossover;

import java.util.Arrays;
import java.util.Random;

public class TwoPoint extends CrossoverAlgorithm {
	
	public TwoPoint(double r) {
		super(r);
		System.out.println("Two-Point Crossover");
	}

	@Override
	public void perform(int[] par1, int[] par2, Random random) {
		int len = par1.length;
		int point1 = random.nextInt(len);
		int point2 = random.nextInt(len);
		int start = Math.min(point1, point2);
		int end = Math.max(point1, point2);

		for (int i = start; i <= end; i++) {
			int tmp = par1[i];
			par1[i] = par2[i];
			par2[i] = tmp;
		}
	}

}
