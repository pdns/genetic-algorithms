package projectsrc.projectsrc.gui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import projectsrc.projectsrc.EquationTerm;
import projectsrc.projectsrc.Problem;

public class EquationPane extends GridPane {
	private static final char[] alpha = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public ArrayList<TextField> constBoxes;
	public TextField eqBox;
	private Button addBtn;
	private Button delBtn;
	public TextField minTxt;
	public TextField maxTxt;
	
	public EquationPane() {
		super();
		constBoxes = new ArrayList<TextField>();
		eqBox = new TextField();
		eqBox.setPrefWidth(60);
		eqBox.setAlignment(Pos.CENTER);
		
		minTxt = new TextField();
		minTxt.setPrefWidth(60);
		minTxt.setAlignment(Pos.CENTER_RIGHT);
		minTxt.setText("1");
		maxTxt = new TextField();
		maxTxt.setPrefWidth(60);
		maxTxt.setText("180");
		maxTxt.setAlignment(Pos.CENTER_RIGHT);
		
		addBtn = new Button("+");
		addBtn.setPrefWidth(35);
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	addTerm();
            	update();
            }
        });
        
        delBtn = new Button("-");
		delBtn.setPrefWidth(35);
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	constBoxes.remove(constBoxes.size() - 1);
            	update();
            }
        });
        
        int term = 1;
        this.addTerm(term++);
        this.addTerm(term++);
        this.addTerm(term++);
        this.addTerm(term++);
        this.addTerm(term++);
        this.addTerm(term++);
        this.addTerm(term++);
        this.addTerm(term++);
        this.addTerm(term++);
        eqBox.setText("8000");
        
//		this.setAlignment(Pos.TOP_L);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));
	}
	
	public void addTerm(int val) {
		addTerm();
		constBoxes.get(constBoxes.size() - 1).setText(Integer.toString(val));
	}
	
	public void addTerm() {
		TextField txt = new TextField();
		txt.setPrefWidth(50);
		txt.setAlignment(Pos.CENTER_RIGHT);
		constBoxes.add(txt);
	}
	
	public void addControls() {
		this.add(new Label("Min Value:"), 0, 0);
		this.add(minTxt, 1, 0);
		this.add(new Label("Max Value:"), 0, 1);
		this.add(maxTxt, 1, 1);
		Separator s = new Separator();
		s.setOrientation(Orientation.VERTICAL);
		this.add(s, 2, 0, 1, 2);
		this.add(addBtn, 3, 0);
		this.add(delBtn, 3, 1);
	}
	
	public void update() {
		this.getChildren().clear();
		addControls();
		int ctr = 0;
		int startPos = 4;
		int pos = 0;
		for (TextField txt : constBoxes) {
			int col = startPos + (pos * 2);
			HBox box = new HBox();
			box.setAlignment(Pos.CENTER);
			Label varLbl = new Label(" " + Character.toString(alpha[ctr++]));
			box.getChildren().addAll(txt, varLbl);
			this.add(box, col, 0, 1, 2);
			if (ctr != constBoxes.size()) this.add(new Label("+"), col+1, 0, 1, 2); 
			pos++;
		}
		this.add(new Label("= "), startPos + (pos*2), 0, 1, 2);
		this.add(eqBox, startPos + (pos*2) + 1, 0, 1, 2);
	}
	
	public EquationTerm[] getEquationTerms() {
		EquationTerm[] retval = new EquationTerm[constBoxes.size()];
		int ctr = 0;
		for (TextField txt : constBoxes) {
			int c = Integer.parseInt(txt.getText());
			retval[ctr] = new EquationTerm(alpha[ctr], c);
			ctr++;
		}
		return retval;
	}
	
	public Problem getProblem() {
		int minVal = Integer.parseInt(minTxt.getText());
		int maxVal = Integer.parseInt(maxTxt.getText());
		int eq = Integer.parseInt(eqBox.getText());
		
		return new Problem(minVal, maxVal, getEquationTerms(), eq);
	}
}
