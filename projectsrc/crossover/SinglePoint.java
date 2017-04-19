package projectsrc.projectsrc.crossover;

import java.util.Arrays;
import java.util.Random;

public class SinglePoint extends CrossoverAlgorithm {
	
	public SinglePoint(double r) {
		super(r);
		System.out.println("Single-Point Crossover");
	}
	
	@Override
	public void perform(int[] par1, int[] par2, Random random) {
		int len = par1.length;
		int point = random.nextInt(len - 1);
		int start = 0;
		int max = 0;
		start = point > (len-1)/2 ? point : 0;
		max = point > (len-1)/2 ? len : point + 1;
		
		for (int i = start; i < max; i++) {
			int tmp = par1[i];
			par1[i] = par2[i];
			par2[i] = tmp;
		}
	}
}
