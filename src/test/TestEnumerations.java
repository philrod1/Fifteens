package test;

import java.util.Arrays;

import model.IntArrayGame;
import solver.BreadthFirstSolver;
import solver.heuristic.BFSHeuristic;
import solver.heuristic.Heuristic;
import solver.heuristic.ManhattanDistance;
import solver.heuristic.MySimpleHeuristic;
import solver.heuristic.PerfectHeuristic;

public class TestEnumerations {
	public static void main(String[] args) {
		Heuristic h1 = new ManhattanDistance(3,3);
		Heuristic h2 = new BFSHeuristic();
		Heuristic h3 = new MySimpleHeuristic(3, 3);
		Heuristic h4 = new PerfectHeuristic();
		int[] full = new int[]{0,1,2,3,4,5,6,7,8};
		for(int a : full) {
			for(int b : full) {
				for(int c : full) {
					for(int d: full) {
						for(int e : full) {
							for(int f : full) {
								for(int g : full) {
									for(int h : full) {
										for(int i : full) {
											if(allDifferent(a,b,c,d,e,f,g,h,i)) {
												if(isSolvable(a,b,c,d,e,f,g,h,i)) {
													IntArrayGame game = new IntArrayGame(3, 3, Arrays.asList(a,b,c,d,e,f,g,h,i), null, 0x87654321L);
//													if(!(h3.value(game) <= h4.value(game))) {
														int v1 = h1.value(game);
														int v3 = h3.value(game);
														int v4 = h4.value(game);
														System.out.print(v3 + " " + v4 + " " );
														System.out.println((v4<v3)?"SHIT":"OK");
//													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static boolean isSolvable(int... a) {
		return (parity(a) & 1) == 0;
	}

	private static int parity(int... puzzle) {
		int parity = 0;
		for (int tile = 0; tile < puzzle.length; tile++) {
			if(puzzle[tile] == 0) {
				int row = puzzle.length / 3;
				if((row & 1) == 0) {
					parity++;
				}
			}
			for (int tileBefore = 0; tileBefore < tile; tileBefore++) {
				if (puzzle[tileBefore] > puzzle[tile])
					parity++;
			}
//			for (int tileAfter = 0; tileAfter < puzzle.length; tileAfter++) {
//				if (puzzle[tileAfter] < puzzle[tile])
//					parity--;
//			}
		}
		
		return parity;
	}

	private static boolean allDifferent(int ... a) {
		int v = 0;
		for(int b : a) {
			v ^= 1 << b;
		}
//		System.out.println(Integer.toBinaryString(v));
		return v == 0b111111111;
	}
}
