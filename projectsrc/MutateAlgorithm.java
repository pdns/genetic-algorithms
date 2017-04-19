package projectsrc.projectsrc;

public class MutateAlgorithm {
	
	private double mutationRate;
	Problem problem;
	
	public MutateAlgorithm(Problem p, double rate) {
		problem = p;
		mutationRate = rate;
	}
	
	public void mutate(int[][] chromos) {
		int numChromos = chromos.length;
		int numGenes = chromos[0].length;
		for (int i = 0; i < numChromos; i++) {
			for (int j = 0; j < numGenes; j++) {
				if (Math.random() > mutationRate) continue;
				chromos[i][j] = problem.randomValue();
			}
		}
	}

}
