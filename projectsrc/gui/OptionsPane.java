package projectsrc.projectsrc.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import projectsrc.projectsrc.MutateAlgorithm;
import projectsrc.projectsrc.Problem;
import projectsrc.projectsrc.ProjectMain;
import projectsrc.projectsrc.crossover.CrossoverAlgorithm;
import projectsrc.projectsrc.crossover.SinglePoint;
import projectsrc.projectsrc.crossover.TwoPoint;
import projectsrc.projectsrc.crossover.Uniform;
import projectsrc.projectsrc.selection.RandomSelection;
import projectsrc.projectsrc.selection.RouletteWheel;
import projectsrc.projectsrc.selection.SelectionAlgorithm;
import projectsrc.projectsrc.selection.Tournament;

public class OptionsPane extends GridPane {
	
	private ProjectMain parent;
	
	public TextField initTxt;
	public TextField genTxt;
	public TextField eliTxt;
	public TextField selTxt;
	public TextField cRTxt;
	public TextField mRTxt;
	
	public ToggleGroup selGrp;
	public ToggleGroup croGrp;
	
	public TextField tourTxt;
	
	RadioButton[] selBtns;
	RadioButton[] croBtns;
	
	public OptionsPane(ProjectMain p) {
		super();
		parent = p;

		Button startBtn = new Button("Start");
		selGrp = new ToggleGroup();
		croGrp = new ToggleGroup();
		
		int row=0;
		this.add(getGeneralPane(), 0, row++, 2, 1);
		this.add(getSelectionPane(), 0, row++, 2, 1);
		this.add(getCrossoverPane(), 0, row++, 2, 1);
		this.add(getMutationPane(), 0, row++, 2, 1);
		this.add(startBtn, 0, row++, 2, 1);

        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.solve();
            }
        });
	}
	
	private GridPane newGridPane() {
		GridPane grid = new GridPane();
		grid.setVgap(10);
		return grid;
	}
	
	private TitledPane newTitlePane(String title, Node content) {
		TitledPane pane = new TitledPane(title, content);
		pane.setCollapsible(false);
		return pane;
	}
	
	private TextField newTextField(String val) {
		TextField retval = new TextField(val);
		retval.setMaxWidth(70);
		retval.setAlignment(Pos.CENTER_RIGHT);
		return retval;
	}
	
	private void setButtonGroup(ToggleGroup grp, RadioButton[] btns) {
		btns[0].setSelected(true);
		for (int i = 0; i < btns.length; i++) btns[i].setToggleGroup(grp);
	}
	
	private void showButtonGroup(GridPane grid, int row, RadioButton[] btns) {
		for (int i = 0; i < btns.length; i++) grid.add(btns[i], 0, row+i+1, 2, 1);
	}
	
	private TitledPane getGeneralPane() {
		GridPane grid = newGridPane();

		Label lbl = new Label("Initial Population Size: ");
		grid.add(lbl, 0, 0);
		initTxt = newTextField("1000");
		grid.add(initTxt, 1, 0);

		lbl = new Label("Max Generations: ");
		grid.add(lbl, 0, 1);
		genTxt = newTextField("500");
		grid.add(genTxt, 1, 1);
		
		lbl = new Label("Number of Elites: ");
		grid.add(lbl,  0,  2);
		eliTxt = newTextField("0");
		grid.add(eliTxt, 1, 2);
		
		return newTitlePane("", grid);
	}
	
	private TitledPane getSelectionPane() {
		GridPane grid = newGridPane();
		
		Label lbl = new Label("Selection Size: ");
		grid.add(lbl, 0, 0);
		selTxt = newTextField("500");
		grid.add(selTxt, 1, 0);

		selBtns = new RadioButton[3];
		selBtns[0] = new RadioButton("Roulette-Wheel Selection");
		selBtns[1] = new RadioButton("Tournament Selection");
		selBtns[2] = new RadioButton("Random Selection");
		setButtonGroup(selGrp, selBtns);
		grid.add(selBtns[0], 0, 2, 2, 1);
		grid.add(selBtns[1], 0, 3, 2, 1);
		grid.add(selBtns[2], 0, 5, 2, 1);

		Label tourLbl = new Label("       Tournament Size: ");
		tourTxt = newTextField("2");
		grid.add(tourLbl, 0, 4);
		grid.add(tourTxt, 1, 4);
		
		grid.add(new Separator(), 0, 1, 2, 1);
		return newTitlePane("Selection", grid);
	}
	
	private TitledPane getCrossoverPane() {
		GridPane grid = newGridPane();
		
		Label lbl = new Label("Crossover Rate: ");
		grid.add(lbl, 0, 0);
		
		cRTxt = newTextField("0.8");
		grid.add(cRTxt, 1, 0);
		
		croBtns = new RadioButton[3];
		croBtns[0] = new RadioButton("Single-Point Crossover");
		croBtns[1] = new RadioButton("Two-Point Crossover");
		croBtns[2] = new RadioButton("Uniform Crossover");
		setButtonGroup(croGrp, croBtns);
		showButtonGroup(grid, 1, croBtns);
		
		grid.add(new Separator(), 0, 1, 2, 1);
		return newTitlePane("Crossover", grid);
	}

	private TitledPane getMutationPane() {
		GridPane grid = newGridPane();
		
		Label lbl = new Label("Mutation Rate: ");
		grid.add(lbl, 0, 0);
		
		mRTxt = newTextField("0.1");
		grid.add(mRTxt, 1, 0);

		return newTitlePane("Mutation", grid);
	}
	
	public SelectionAlgorithm getSelectionAlgo(Problem p) {
		int selSize = Integer.parseInt(selTxt.getText());
		Toggle btn = selGrp.getSelectedToggle();
		SelectionAlgorithm retval = null;
		if (btn == selBtns[0]) retval = new RouletteWheel(p, selSize);
		else if (btn == selBtns[1]) retval = new Tournament(p, selSize, Integer.parseInt(tourTxt.getText()));
		else if (btn == selBtns[2]) retval = new RandomSelection(p, selSize);
        return retval;
	}
	
	public CrossoverAlgorithm getCrossoverAlgo(Problem p) {
		double crossRate = Double.parseDouble(cRTxt.getText());
		Toggle btn = croGrp.getSelectedToggle();
		CrossoverAlgorithm retval = null;
		if (btn == croBtns[0]) retval = new SinglePoint(crossRate);
		else if (btn == croBtns[1]) retval = new TwoPoint(crossRate);
		else if (btn == croBtns[2]) retval = new Uniform(crossRate);
		return retval;
	}
	
	public MutateAlgorithm getMutateAlgo(Problem p) {
		double muteRate = Double.parseDouble(mRTxt.getText());
		return new MutateAlgorithm(p, muteRate);
	}
	
}
