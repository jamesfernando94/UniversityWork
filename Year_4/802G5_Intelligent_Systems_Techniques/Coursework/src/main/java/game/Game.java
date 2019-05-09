package game;

import moves.CapturingMove;
import moves.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
	ArrayList<Draught> draughtArrayList;
	Colour currentTurn;
	Draught selectedDraught;
	boolean isCurrentMultiStepMove;
	Colour gameWinner;
	boolean isGameComplete;

	public ArrayList<Draught> getDraughtArrayList() {
		return draughtArrayList;
	}

	public Colour getCurrentTurn() {
		return currentTurn;
	}

	public Draught getSelectedDraught() {
		return selectedDraught;
	}

	public void setSelectedDraught(Draught selectedDraught) {
		this.selectedDraught = selectedDraught;
	}

	public Colour getGameWinner() {
		return gameWinner;
	}

	public boolean isGameComplete() {
		return isGameComplete;
	}

	public Game() {
	}

	public Game(Game game) {
		draughtArrayList = game.draughtArrayList;
		currentTurn = game.currentTurn;
		selectedDraught = game.selectedDraught;
		isCurrentMultiStepMove = game.isCurrentMultiStepMove;
		isGameComplete = game.isGameComplete;
	}

	public void setUpNewGame() {
		draughtArrayList = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			if (i % 2 == 1) {
				draughtArrayList.add(new Draught(i, 0, Colour.DARK));
				draughtArrayList.add(new Draught(i, 2, Colour.DARK));
				draughtArrayList.add(new Draught(i, 6, Colour.LIGHT));
			} else {
				draughtArrayList.add(new Draught(i, 1, Colour.DARK));
				draughtArrayList.add(new Draught(i, 5, Colour.LIGHT));
				draughtArrayList.add(new Draught(i, 7, Colour.LIGHT));
			}
		}
		currentTurn = Colour.DARK;
		isCurrentMultiStepMove = false;
		isGameComplete = false;
	}

	public ArrayList<Move> findAllPossibleMoves() {
		if (isCurrentMultiStepMove) {

			return findPossibleMoves(selectedDraught)
				.stream()
				.filter(move -> move instanceof CapturingMove)
				.collect(Collectors.toCollection(ArrayList::new));
		}

		ArrayList<Move> moveList = draughtArrayList
			.stream()
			.filter(draught -> draught.colour == currentTurn)
			.map(this::findPossibleMoves)
			.flatMap(Collection::stream)
			.collect(Collectors.toCollection(ArrayList::new));

		return removeNonCapturingMovesIfCapturingMoveExists(moveList);

	}

	public ArrayList<Move> removeNonCapturingMovesIfCapturingMoveExists(ArrayList<Move> moveList) {
		ArrayList<Move> captureMoveList = moveList
			.stream()
			.filter(move -> move instanceof CapturingMove)
			.collect(Collectors.toCollection(ArrayList::new));

		return !captureMoveList.isEmpty() ? captureMoveList : moveList;
	}

	public ArrayList<Move> findPossibleMoves(Draught draught) {
		ArrayList<Move> possibleMoves = new ArrayList<>();

		for (Move move : draught.listPossibleMoves()) {
			if (isMoveBlocked(move)) {
				Optional<Draught> capturedDraught = draughtArrayList.
					stream()
					.filter(draught1 -> draught1.xPosition == move.getNewXPosition()
						&& draught1.yPosition == move.getNewYPosition()
						&& draught.colour != draught1.colour)
					.findFirst();
				if (capturedDraught.isPresent()) {
					Move captureMove = new CapturingMove(
						move.getDraught(),
						move.getNewXPosition() + move.xPositionMovement(),
						move.getNewYPosition() + move.yPositionMovement(),
						capturedDraught.get()
					);
					if (!isMoveBlocked(captureMove) && captureMove.moveInBounds()) {
						possibleMoves.add(captureMove);
					}
				}
			}else{possibleMoves.add(move);}
		}

		return removeNonCapturingMovesIfCapturingMoveExists(possibleMoves);
	}

	public boolean isMoveBlocked(Move move) {
		for (Draught draught : draughtArrayList) {
			if (draught.xPosition == move.getNewXPosition() && draught.yPosition == move.getNewYPosition()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Draught> draughtsWithPossibleMoves() {
		return findAllPossibleMoves()
			.stream()
			.map(Move::getDraught)
			.distinct()
			.collect(Collectors.toCollection(ArrayList::new));
	}

	public void selectMove(Move move){
		move.getDraught().setxPosition(move.getNewXPosition());
		move.getDraught().setyPosition(move.getNewYPosition());
		if (move instanceof CapturingMove) {
			draughtArrayList.remove(((CapturingMove) move).getCapturedDraught());
			ArrayList<Move> moves = findPossibleMoves(move.getDraught());
			if (moves.stream().anyMatch(move1 -> move1 instanceof CapturingMove)) {
				if (!move.getDraught().isDraughtOnKingsRow() || move.getDraught().isCrowned()) {
					selectedDraught = move.getDraught();
					isCurrentMultiStepMove = true;
					checkForWinner();
					return;
				}
			}

		}
		move.getDraught().crownDraughtIfPossible();

		if (currentTurn == Colour.DARK){
			currentTurn = Colour.LIGHT;
		}else{
			currentTurn = Colour.DARK;
		}
		selectedDraught = null;
		isCurrentMultiStepMove = false;
		checkForWinner();
	}

	public Game successorFunction(Move move) {
		Game rtnGame = new Game(this);

		rtnGame.selectMove(move);
		return rtnGame;
	}

	private boolean checkForWinner() {

		if (draughtArrayList.stream().noneMatch(draught -> draught.getColour() == Colour.LIGHT)) {
			isGameComplete = true;
			gameWinner = Colour.DARK;
			return true;
		}
		if (draughtArrayList.stream().noneMatch(draught -> draught.getColour() == Colour.DARK)) {
			isGameComplete = true;
			gameWinner = Colour.LIGHT;
			return true;
		}
		if (findAllPossibleMoves().isEmpty()) {
			isGameComplete = true;
			if (currentTurn == Colour.LIGHT) {
				gameWinner = Colour.DARK;
			} else if (currentTurn == Colour.DARK) {
				gameWinner = Colour.LIGHT;
			}
			return true;
		}
		return isGameComplete;
	}


	public boolean isCurrentMultiStepMove() {
		return isCurrentMultiStepMove;
	}

}
