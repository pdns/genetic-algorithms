package projectsrc.projectsrc.selection;

import java.util.Random;

import projectsrc.projectsrc.Problem;

public class RandomSelection extends SelectionAlgorithm {
	public RandomSelection(Problem p, int n) {
		super(p, n);
		System.out.println("Random Selection");
	}
	
	public int[][] select(int[][] chromos) {
		Random r = new Random();
		int numGenes = chromos[0].length;
		int[][] selected = new int[numSelect][numGenes];

		for (int i = 0; i < numSelect; i++) {
			int s = r.nextInt(chromos.length);
			for (int g = 0; g < numGenes; g++) selected[i][g] = chromos[s][g];
		}
		return selected;
	}

}
