import game.Colour;
import game.Draught;
import game.Game;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import moves.Move;

import java.util.ArrayList;

public class Main extends Application {

	private static final int BOARDSIZE = 8;
	private static final Background WHITEBACKGROUND = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
	private static final Background BLACKBACKGROUND = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
	private Game game;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
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
		if (game.getSelectedDraught() != null) {
			board = displayPossibleMoves(board, game.getSelectedDraught());
		}
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

	public GridPane displayPossibleMoves(GridPane board, Draught draught){
		ArrayList<Move> moves = game.findPossibleMoves(draught);
		for (Move move : moves){
			Circle moveVisual = generateMoveVisual(move);
			board.add(moveVisual, move.getNewXPosition(), move.getNewYPosition());
			GridPane.setHalignment(moveVisual, HPos.CENTER);
			GridPane.setValignment(moveVisual, VPos.CENTER);
		}
		return board;
	}

	public Circle generateMoveVisual(Move move){
		Circle moveVisual = new Circle(20);
		moveVisual.setFill(Color.TRANSPARENT);
		moveVisual.setStrokeWidth(2);
		moveVisual.setStroke(Color.WHITE);
		moveVisual.setOnMouseClicked(event -> selectMove(move));
		return moveVisual;
	}

	public GridPane displayDraughtsOnBoard(GridPane board) {
		for (Draught draught : game.getDraughtArrayList()) {
			Node draughtVisual = generateDraughtVisual(draught);
			board.add(draughtVisual, draught.getxPosition(), draught.getyPosition());
			GridPane.setHalignment(draughtVisual, HPos.CENTER);
			GridPane.setValignment(draughtVisual, VPos.CENTER);
		}
		return board;
	}

	public StackPane generateDraughtVisual(Draught draught){
		Circle draughtVisual = new Circle(20);
		if (draught.getColour() == Colour.DARK) {
			draughtVisual.setFill(Color.RED);
		} else if (draught.getColour() == Colour.LIGHT) {
			draughtVisual.setFill(Color.WHITE);
		}

		if (game.draughtsWithPossibleMoves().contains(draught)) {

			// If draught has possible moves
			draughtVisual.setOnMouseClicked(event -> selectDraught(draught));
			if (game.getSelectedDraught() == null) {
				draughtVisual.setStroke(Color.YELLOW);
			}
		}
		if (draught.equals(game.getSelectedDraught())) {
			draughtVisual.setStroke(Color.CYAN);
		}
		draughtVisual.setStrokeWidth(2);
		StackPane rtnPane = new StackPane();
		rtnPane.getChildren().add(draughtVisual);
		if (draught.isCrowned()){
			Circle crownVisual = new Circle(10);
			crownVisual.setFill(Color.TRANSPARENT);
			crownVisual.setStroke(Color.BLACK);
			crownVisual.setStrokeWidth(2);
			rtnPane.getChildren().add(crownVisual);
		}

		return rtnPane;
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

	public void selectMove(Move move){
		game.selectMove(move);
		updateDisplay();
	}

	public void selectDraught(Draught draught){
		if (!game.isCurrentMultiStepMove()) {
			if (draught.equals(game.getSelectedDraught())) {

				game.setSelectedDraught(null);
			} else {
				game.setSelectedDraught(draught);
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Unable to complete move");
			alert.setHeaderText("Unable to complete move");
			alert.setContentText("Is it not possible to select any other draught therefore you must complete the move with the selected tile");
			alert.showAndWait();
		}
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
