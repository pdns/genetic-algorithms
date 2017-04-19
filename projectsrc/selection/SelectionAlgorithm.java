package projectsrc.projectsrc.selection;

import projectsrc.projectsrc.Problem;

public abstract class SelectionAlgorithm {
	protected Problem problem;
	int numSelect;
	
	public SelectionAlgorithm(Problem p, int n) {
		problem = p;
		numSelect = n;
	}
	
	public int[][] select(int[][] chromos) {
		System.out.println("SHOULD NOT BE HERE -- ABSTRACT CLASS FUNCTION CALL");
		return null;
	}
	
	protected double getTotalAndFitnesses(int[][] chromos, double retval[]) {
		double total = 0;
		for (int i = 0; i < chromos.length; i++) {
			retval[i] = problem.fitness(chromos[i]);
			total += retval[i];
		}
		return total;
	}
	
	protected double[] getProbabilities(int[][] chromos) {
		double[] retval = new double[chromos.length];
		double fitnesses[] = new double[chromos.length];
		double total = this.getTotalAndFitnesses(chromos, fitnesses);
		
		for (int i = 0; i < chromos.length; i++) {
			retval[i] = fitnesses[i] / total;
		}
		
		return retval;
	}
	
	protected double[] getAccumProb(int[][] chromos) {
		double[] retval = new double[chromos.length];
		double[] probs = this.getProbabilities(chromos);
		
		double total = 0;
		for (int i = 0; i < retval.length; i++) {
			total += probs[i];
			retval[i] = total;
		}
		return retval;
	}

}
