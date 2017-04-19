package projectsrc.projectsrc.selection;

import java.util.Random;

import projectsrc.projectsrc.Problem;

public class Tournament extends SelectionAlgorithm {
	
	private int tSize;
	
	public Tournament(Problem p, int n, int t) {
		super(p, n);
		tSize = t;
		System.out.println("Tournament Selection");
	}

	@Override
	public int[][] select(int[][] chromos) {
		int numGenes = chromos[0].length;
		int[][] selected = new int[numSelect][numGenes];
		Random r = new Random();
		
		for (int i = 0; i < numSelect; i++) {
			double bestFit = 0;
			int[] bestChromo = null;
			for (int c = 0; c < tSize; c++) {
				int idx = r.nextInt(chromos.length);
				double fitness = problem.fitness(chromos[idx]);
				if (fitness > bestFit) {
					bestFit = fitness;
					bestChromo = chromos[idx];
				}
			}
			for (int g = 0; g < numGenes; g++) selected[i][g] = bestChromo[g];
		}
		return selected;
	}
}
