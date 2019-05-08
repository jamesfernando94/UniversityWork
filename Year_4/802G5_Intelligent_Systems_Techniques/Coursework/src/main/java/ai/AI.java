package ai;

import game.Colour;

public class AI {


	int difficulty;
	Colour colour;

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
}
