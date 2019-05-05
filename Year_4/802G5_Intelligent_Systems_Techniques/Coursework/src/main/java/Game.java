import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Game {
	ArrayList<Draught> draughtArrayList;
	Colour currentTurn;
	Draught selectedDraught;

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
	}

	public ArrayList<Move> findAllPossibleMoves() {
		return draughtArrayList
			.stream()
			.filter(draught -> draught.colour == currentTurn)
			.map(this::findPossibleMoves)
			.flatMap(Collection::stream)
			.collect(Collectors.toCollection(ArrayList::new));

	}

	public ArrayList<Move> findPossibleMoves(Draught draught) {
		ArrayList<Move> possibleMoves = new ArrayList<>();

		for (Move move : draught.listPossibleMoves()) {
			if (isMoveBlocked(move)) {
				if (draughtArrayList.
					stream()
					.anyMatch(draught1 -> draught1.xPosition == move.newXPosition
						&& draught1.yPosition == move.newYPosition
						&& draught.colour != draught1.colour)
				) {
					Move captureMove = new Move(
						move.draught,
						move.newXPosition + move.xPositionMovement(),
						move.newYPosition + move.yPositionMovement()
					);
					if (!isMoveBlocked(captureMove) && captureMove.moveInBounds()) {
						possibleMoves.add(captureMove);
					}
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

	public ArrayList<Draught> draughtsWithPossibleMoves() {
		return findAllPossibleMoves()
			.stream()
			.map(move -> move.draught)
			.distinct()
			.collect(Collectors.toCollection(ArrayList::new));
	}


}
