package ai;

import game.Colour;
import game.Draught;
import game.Game;
import javafx.util.Pair;
import moves.Move;

import java.util.ArrayList;
import java.util.Random;

public class AI {


	int difficulty;
	Colour colour;
	private Random random = new Random();

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public AI(int difficulty, Colour colour){
		this.difficulty = difficulty;
		this.colour = colour;
	}

	public Move selectMove(Game game) {
		return selectMove(game.findAllPossibleMoves());
	}

	private Move selectMove(ArrayList<Move> moves){return moves.get(random.nextInt(moves.size()));}

	public ArrayList<Pair<Integer, Move>> evaluateMoves(Game game, ArrayList<Move> moves){
		ArrayList<Pair<Integer, Move>> evaluatedMoves =  new ArrayList<>();

		for (Move move : moves)
		{
			Game futureGame = game.successorFunction(move);
			evaluatedMoves.add(new Pair<>(evaluateGameBoard(futureGame), move));
		}

		return evaluatedMoves;
	}


	private int evaluateGameBoard(Game gameBoard){
		ArrayList<Draught> draughts = gameBoard.getDraughtArrayList();
		int aiDraughts = Math.toIntExact(draughts.stream().filter(draught -> draught.getColour() == getColour()).count());
		int opponentDraughts = Math.toIntExact(draughts.stream().filter(draught -> draught.getColour() !=  getColour()).count());
		return aiDraughts-opponentDraughts;
	}
}
