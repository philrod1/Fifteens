package test;

import model.IntArrayGame;

public class Test {

	public static void main(String[] args) {
//		IntArrayGame game = new IntArrayGame(4, 2);
//		System.out.println(Long.toHexString(game.longId()));
//		IntArrayGame game2 = game.clone();
//		System.out.println(Long.toHexString(game2.longId()));
		long l = 0;
		l ^= 2;
		l ^= 1;
		l ^= 3;
		l ^= 4;
		l ^= 5;
		l ^= 6;
		l ^= 7;
		l ^= 10;
		l ^= 11;
		l ^= 12;
		l ^= 13;
		l ^= 14;
		l ^= 15;
		l ^= 0;
		
		System.out.println(l);
	}

}
