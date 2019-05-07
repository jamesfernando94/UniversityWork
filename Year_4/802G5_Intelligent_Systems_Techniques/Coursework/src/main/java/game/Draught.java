package game;

import moves.Move;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class Draught {
	int xPosition;

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public boolean isCrowned() {
		return crowned;
	}

	public void setCrowned(boolean crowned) {
		this.crowned = crowned;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	int yPosition;
	boolean crowned;
	Colour colour;
	boolean taken;


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
