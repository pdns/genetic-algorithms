package projectsrc.projectsrc;

import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projectsrc.projectsrc.crossover.CrossoverAlgorithm;
import projectsrc.projectsrc.crossover.SinglePoint;
import projectsrc.projectsrc.gui.AlgorithmTask;
import projectsrc.projectsrc.gui.EquationPane;
import projectsrc.projectsrc.gui.OptionsPane;
import projectsrc.projectsrc.selection.RouletteWheel;
import projectsrc.projectsrc.selection.SelectionAlgorithm;

public class ProjectMain extends Application {
	
	private TableView table;
	private ObservableList<Generation> items;
	private OptionsPane options;
	private EquationPane eqPane;
	
	@Override
    public void start(Stage primaryStage) {

		GridPane grid = new GridPane();
		
		eqPane = new EquationPane();
		grid.add(eqPane, 0, 0, 2, 1);
		eqPane.update();
		
		options = new OptionsPane(this);
		grid.add(options, 0, 1);
		
		items = FXCollections.observableArrayList();
		
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		grid.getColumnConstraints().addAll(col1, col2);
		
		RowConstraints row1 = new RowConstraints();
		RowConstraints row2 = new RowConstraints();
		row2.setVgrow(Priority.ALWAYS);
		grid.getRowConstraints().addAll(row1, row2);
		
		table = new TableView();
		TableColumn genCol = new TableColumn("Generation");
        TableColumn avgCol = new TableColumn("Average Fitness");
        TableColumn maxCol = new TableColumn("Highest Fitness");
        genCol.setCellValueFactory( new PropertyValueFactory<>("num") );
        avgCol.setCellValueFactory( new PropertyValueFactory<>("avg") );
        maxCol.setCellValueFactory( new PropertyValueFactory<>("max") );
        genCol.prefWidthProperty().bind(table.widthProperty().divide(3));
        avgCol.prefWidthProperty().bind(table.widthProperty().divide(3));
        maxCol.prefWidthProperty().bind(table.widthProperty().divide(3));
        table.setItems(items);
        table.getColumns().addAll(genCol, avgCol, maxCol);
        
        grid.add(table, 1, 1);
        
		Scene scene = new Scene(grid, 1600, 850);
		primaryStage.setScene(scene);

        primaryStage.setTitle("Genetic Algorithms - COMP 4106 Final Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public void solve() {
		items.clear();

		int popSize = Integer.parseInt(options.initTxt.getText());
		int maxGens = Integer.parseInt(options.genTxt.getText());
		int numElites = Integer.parseInt(options.eliTxt.getText());

		Problem p = eqPane.getProblem();
		int[][] init = p.getRandomPop(popSize);

		SelectionAlgorithm selection = options.getSelectionAlgo(p);
		CrossoverAlgorithm crossover = options.getCrossoverAlgo(p);
		MutateAlgorithm mutation = options.getMutateAlgo(p);
		
		AlgorithmTask task = new AlgorithmTask(maxGens, init, p, selection, crossover, mutation, items, numElites);

		Thread thread = new Thread(task);
	    thread.setDaemon(true);
	    thread.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
