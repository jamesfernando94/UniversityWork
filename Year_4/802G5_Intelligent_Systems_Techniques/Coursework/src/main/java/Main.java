import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int BOARDSIZE = 8;
	private static final Background WHITEBACKGROUND = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
	private static final Background BLACKBACKGROUND = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
	private Game game;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		game = new Game();
		game.setUpNewGame();
		this.primaryStage = primaryStage;
		updateDisplay();
	}


	public static void main(String[] args) {
		launch(args);
	}

	public GridPane generateDisplay(GridPane root){
		GridPane board = generateBoard();
		board = displayDraughtsOnBoard(board);

		root.add(board, 0, 0);

		return root;
	}

	public GridPane generateBoard() {
		GridPane board = new GridPane();
		for (int row = 0; row < BOARDSIZE; row++) {
			for (int column = 0; column < BOARDSIZE; column++) {
				StackPane square = new StackPane();
				square.setBackground((row + column) % 2 == 0 ? WHITEBACKGROUND : BLACKBACKGROUND);
				board.add(square, column, row);

			}
		}
		for (int i = 0; i < BOARDSIZE; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(50);
			board.getColumnConstraints().add(columnConstraints);
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(50);
			board.getRowConstraints().add(rowConstraints);
		}

		board.setPadding(new Insets(15, 15, 15, 15));
		return board;
	}

	public GridPane displayPossibleMoves(GridPane board,Draught draught){

		return board;
	}

	public GridPane displayDraughtsOnBoard(GridPane board) {
		for (Draught draught : game.draughtArrayList) {
			Node draughtVisual = generateDraughtVisual(draught);
			board.add(draughtVisual, draught.xPosition, draught.yPosition);
			GridPane.setHalignment(draughtVisual, HPos.CENTER);
			GridPane.setValignment(draughtVisual, VPos.CENTER);
		}
		return board;
	}

	public Circle generateDraughtVisual(Draught draught){
		Circle draughtVisual = new Circle(20);
		if (draught.colour == Colour.DARK) {
			draughtVisual.setFill(Color.RED);
		} else if (draught.colour == Colour.LIGHT) {
			draughtVisual.setFill(Color.WHITE);
		}
		if (game.selectedDraught == null && game.draughtsWithPossibleMoves().contains(draught)){
			// If draught has possible moves
			draughtVisual.setStroke(Color.YELLOW);
		}else if (draught.equals(game.selectedDraught)){
			draughtVisual.setStroke(Color.CYAN);
		}
		draughtVisual.setStrokeWidth(2);

		draughtVisual.setOnMouseClicked(event -> selectDraught(draught));

		return draughtVisual;
	}

	public GridPane setupRoot() {
		GridPane root = new GridPane();

		root.setHgap(8);
		root.setVgap(8);

		ColumnConstraints col0 = new ColumnConstraints();
		col0.setPrefWidth(500);
		ColumnConstraints col1 = new ColumnConstraints();

		root.getColumnConstraints().addAll(col0, col1);

		RowConstraints row0 = new RowConstraints();
		row0.setPrefHeight(500);
		root.getRowConstraints().add(row0);
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));



		return root;
	}

	public void selectDraught(Draught draught){

		game.selectedDraught = draught;
		updateDisplay();

	}

	private void updateDisplay() {
		primaryStage.setScene(new Scene(
			generateDisplay(setupRoot()),
			600,
			550)
		);
		primaryStage.show();
	}
}
