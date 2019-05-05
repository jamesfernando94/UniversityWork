public class Move {
	Draught draught;
	int newXPosition;
	int newYPosition;

	public Move(Draught draught, int newXPosition, int newYPosition){
		this.draught = draught;
		this.newXPosition = newXPosition;
		this.newYPosition = newYPosition;
	}

	public boolean moveInBounds() {
		return 0 <= newXPosition && newXPosition < 8 && 0 <= newYPosition && newYPosition < 8;
	}

	public int xPositionMovement(){return newXPosition - draught.xPosition;}
	public int yPositionMovement(){return newYPosition - draught.yPosition;}
}
