package main;

import controller.GameController;

public class Fifteens {
	public static void main (String[] args) {
		new GameController(4, 4).solve();
	}
}
