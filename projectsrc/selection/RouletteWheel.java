package projectsrc.projectsrc.selection;

import projectsrc.projectsrc.Problem;

public class RouletteWheel extends SelectionAlgorithm {

	public RouletteWheel(Problem p, int n) {
		super(p, n);
		System.out.println("Roulette Wheel Selection");
	}
	
	private int getIndex(double[] ap, double rdm) {
		int low = 0;
		int high = ap.length - 1;
		
		while (high >= low) {
			int mid = (low + high) / 2;
			double p = ap[mid];
			if (mid == ap.length - 1 && rdm > ap[mid - 1]) return mid;
			if (mid == 0 && rdm < ap[mid]) return mid;
			
			if (p > rdm && ap[mid - 1] < rdm) return mid;

			if (ap[mid] < rdm) low = mid + 1;
			else if (ap[mid] > rdm) high = mid - 1;
		}
		System.out.println("GET INDEX URGENT ERROR");
		return 0;
	}
	
	@Override
	public int[][] select(int[][] chromos) {
		int numPop = chromos.length;
		int numGenes = chromos[0].length;
		int selected[][] = new int[numSelect][numGenes];
		double[] accumProb = this.getAccumProb(chromos);
		
		for (int i = 0; i < numPop; i++) {
		}
		
		for (int i = 0; i < numSelect; i++) {
			double rdm = Math.random();
			int idx = getIndex(accumProb, rdm);
			
			for (int g = 0; g < numGenes; g++) selected[i][g] = chromos[idx][g];
		}
		
		
		return selected;
	}
}
