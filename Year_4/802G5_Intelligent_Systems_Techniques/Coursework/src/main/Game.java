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
}
