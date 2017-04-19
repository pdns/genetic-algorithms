package projectsrc.projectsrc.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import projectsrc.projectsrc.Generation;
import projectsrc.projectsrc.MutateAlgorithm;
import projectsrc.projectsrc.Problem;
import projectsrc.projectsrc.crossover.CrossoverAlgorithm;
import projectsrc.projectsrc.selection.SelectionAlgorithm;

public class AlgorithmTask extends Task<Void> {

	private static final char[] alpha = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	private final int maxGens;
	private int[][] initPop;
	private ObservableList<Generation> items;
	private int numElites;
	
	private final Problem problem;
	private final SelectionAlgorithm selection;
	private final CrossoverAlgorithm crossover;
	private final MutateAlgorithm mutation;
	
//	public AlgorithmTask(int mG, int[][] iP, Problem p, SelectionAlgorithm sA, CrossoverAlgorithm cA, MutateAlgorithm mA, ObservableList<Generation> i) {
	public AlgorithmTask(int mG, int[][] iP, Problem p, SelectionAlgorithm sA, CrossoverAlgorithm cA, MutateAlgorithm mA, ObservableList<Generation> i, int e) {
		maxGens = mG;
		initPop = iP;
		problem = p;
		selection = sA;
		crossover = cA;
		mutation = mA;
		items = i;
		numElites = e;
	}

	@Override
	protected Void call() throws Exception {
		for (int g = 0; g < maxGens; g++){
			Generation newGen = new Generation(g);
			problem.populateData(initPop, newGen);
			items.add(newGen);
			
			if (newGen.getMax() == 1) {
				System.out.println(newGen.getMaxStr());
				break;
			}

			int[][] elite = null;
			if (numElites > 0) {
				elite = problem.getElite(initPop, numElites);
//				problem.printPop(elite);
			}

			int[][] picks = selection.select(initPop);
			
			crossover.crossover(picks);
			mutation.mutate(picks);
			for (int i = 0; i < picks.length; i++) { 
				initPop[i] = numElites > 0 && i < numElites ? elite[i] : picks[i];
//				initPop[i] = picks[i];
			}
			Thread.sleep(10);
		}
		return null;
	}
}
