package projectsrc.projectsrc.crossover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public abstract class CrossoverAlgorithm {
	
	protected final double crossoverRate;
	
	public CrossoverAlgorithm(double rate) {
		crossoverRate = rate;
	}
	
	public void crossover(int[][] chromos) {
		int numChromos = chromos.length;
		int numGenes = chromos[0].length;
		if ((numChromos & 1) == 1) System.out.println("WARNING: ODD NUMBER OF CHROMOSOMES");
		Random random = new Random();
//		int[][] retval = new int[numChromos][numGenes];
		ArrayList<int[]> parents = new ArrayList<int[]>(numChromos);
		for (int i = 0; i < numChromos; i++) parents.add(chromos[i]);
		Collections.shuffle(parents);
		
		for (int i = 1; i < numChromos; i += 2) {
			if (random.nextDouble() > crossoverRate) continue;
			
			int[] par1 = parents.get(i-1);
			int[] par2 = parents.get(i);
//			System.out.println("BEFORE: " + Arrays.toString(par1));
//			System.out.println("BEFORE: " + Arrays.toString(par2));
			perform(par1, par2, random);
//			System.out.println("AFTER: " + Arrays.toString(par1));
//			System.out.println("AFTER: " + Arrays.toString(par2));
		}
	}
	
	public void perform(int[] par1, int[] pa2, Random random) {
		System.out.println("SHOULD NOT BE HERE -- ABSTRACT CLASS FUNCTION CALL");
	}
}
