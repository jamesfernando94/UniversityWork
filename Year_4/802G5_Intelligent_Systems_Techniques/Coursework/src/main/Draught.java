package main;

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


}
