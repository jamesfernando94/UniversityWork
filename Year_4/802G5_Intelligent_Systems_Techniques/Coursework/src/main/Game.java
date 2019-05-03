package main;

import java.util.ArrayList;

public class Game {
	ArrayList<Draught> draughtArrayList;

	public void setUpNewGame() {
		draughtArrayList = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			if (i % 2 == 1) {
				draughtArrayList.add(new Draught(i, 0, Draught.Colour.LIGHT));
				draughtArrayList.add(new Draught(i, 2, Draught.Colour.LIGHT));
				draughtArrayList.add(new Draught(i, 6, Draught.Colour.DARK));
			} else {
				draughtArrayList.add(new Draught(i, 1, Draught.Colour.LIGHT));
				draughtArrayList.add(new Draught(i, 5, Draught.Colour.DARK));
				draughtArrayList.add(new Draught(i, 7, Draught.Colour.DARK));
			}
		}
	}

	public ArrayList<Move> findAllPossibleMoves() {
		ArrayList<Move> possibleMoves = new ArrayList<>();
		for (Draught draught : draughtArrayList) {
			possibleMoves.addAll(findPossibleMoves(draught));
		}
		return possibleMoves;
	}

	public ArrayList<Move> findPossibleMoves(Draught draught) {
		ArrayList<Move> possibleMoves = new ArrayList<>();

		for (Move move : draught.listPossibleMoves()) {
			if (isMoveBlocked(move)) {
				Move captureMove = new Move(
					move.draught,
					move.newXPosition + move.xPositionMovement(),
					move.newYPosition + move.yPositionMovement()
				);
				if (!isMoveBlocked(captureMove)){
					possibleMoves.add(captureMove);
				}
			}else{possibleMoves.add(move);}
		}

		return possibleMoves;
	}

	public boolean isMoveBlocked(Move move) {
		for (Draught draught : draughtArrayList) {
			if (draught.xPosition == move.newXPosition && draught.yPosition == move.newYPosition) {
				return true;
			}
		}
		return false;
	}


}
