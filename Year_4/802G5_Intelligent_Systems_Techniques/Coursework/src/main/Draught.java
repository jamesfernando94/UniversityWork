package main;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class Draught {
	int xPosition;
	int yPosition;
	boolean crowned;
	Colour colour;
	boolean taken;
	enum Colour {LIGHT, DARK}

	public Draught(int xPosition, int yPosition, Colour colour)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		crowned = false;
		this.colour = colour;
		taken = false;
	}

	public ArrayList<Move> listPossibleMoves() {

		ArrayList<Move> possibleMoves = new ArrayList<>();
		int yDirection;

		if (colour == Colour.LIGHT) {
			yDirection = -1;
		} else if (colour == Colour.DARK) {
			yDirection = 1;
		} else throw new NotImplementedException();

		if (crowned) {
			possibleMoves.add(new Move(this, xPosition - 1, yPosition - yDirection));
			possibleMoves.add(new Move(this, xPosition + 1, yPosition - yDirection));
		}

		possibleMoves.add(new Move(this, xPosition - 1, yPosition + yDirection));
		possibleMoves.add(new Move(this, xPosition + 1, yPosition + yDirection));

		possibleMoves.removeIf(move -> !move.moveInBounds());

		return possibleMoves;
	}




}
