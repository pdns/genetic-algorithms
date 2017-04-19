package projectsrc.projectsrc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class Problem {
//	private int numGenes;
	private int[] eqConstants;
	private EquationTerm[] eqTerms;
	private int equality;
	private int minVal;
	private int maxVal;
	private Random random;
	
	public Problem(int min, int max, EquationTerm[] et, int eq) {
		eqConstants = new int[]{1, 2, 3, 4};
		equality = eq;
		minVal = min;
		maxVal = max;
		random = new Random();
		eqTerms = et;
		
		int total = 0;
		for (int i = 0; i < eqTerms.length; i++) {
			total += (eqTerms[i].getConstant() * maxVal);
		}
		if (total < equality) System.out.println("WARNING: Solution might be impossible (Max Total: " + total + ")");
	}
	
	public int[][] getRandomPop(int num) {
//		int numGenes = eqConstants.length;
		int numGenes = eqTerms.length;
		int[][] retval = new int[num][numGenes];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < numGenes; j++) {
				retval[i][j] = randomValue();
			}
		}
		return retval;
	}

	public int[][] getRandomPop() {
		return predeterminedPop();
	}
	
	private int[][] predeterminedPop() {
		return new int [][] {
			{12, 5, 23, 8},
			{2, 21, 18, 3},
			{10, 4, 13, 14},
			{20, 1, 10, 6},
			{1, 4, 13, 19},
			{20, 5, 17, 1},
		};
	}
	
	public int objectiveFunc(int[] chromo) {
		int retval = 0;
		for (int i = 0; i < eqTerms.length; i++) {
			retval += (eqTerms[i].getConstant() * chromo[i]);
		}
		retval -= equality;
		return Math.abs(retval);
	}
	
	public double fitness(int[] chromo) {
		return (double) 1 / (double) ((1 + objectiveFunc(chromo)));
	}

	public int randomValue() {
		return ThreadLocalRandom.current().nextInt(minVal, maxVal+1);
	}
	
	public void printPop(int[][] chromos) {
		for (int i = 0; i < chromos.length; i++) {
			System.out.println(Arrays.toString(chromos[i]) + " - " + fitness(chromos[i]));
		}
	}
	
	public void populateData(int[][] pop, Generation g) {
		int num = pop.length;
		double avg = 0;
		double max = 0;
		int[] maxChromo = null;
		for (int i = 0; i < num; i++) {
			double f = fitness(pop[i]);
			avg += f;
			if (f > max) {
				max = f;
				maxChromo = pop[i];
			}
		}
		avg /= (double) num;
		g.setAvg(avg);
		g.setMax(max);
		g.setMaxStr(maxChromo);
	}
	
	public int[][] getElite(int[][] pop, int num) {
		int[][] elite = new int[num][pop[0].length];
		Arrays.fill(elite, null);
		
		for (int i = 0; i < pop.length; i++) {
			int[] c = pop[i];
			double f = fitness(c);
			
			for (int e = 0; e < num; e++) {
				if (Arrays.equals(c, elite[e])) {
					break;
				}
				if (elite[e] == null || f > fitness(elite[e])) {
					for (int z = num-1; z > e; z--) {
						elite[z] = elite[z-1];
					}
					elite[e] = c;
					break;
				}
			}
		}
		return elite;
	}
	
	public boolean equalChromos(int[] c1, int[] c2) {
		return Arrays.equals(c1, c2);
	}

}
