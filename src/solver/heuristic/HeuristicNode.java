package solver.heuristic;

import model.Game;


public class HeuristicNode implements Comparable<HeuristicNode> {
	public final int g, h, f;
	public final Game state;
	public HeuristicNode (Game state, int g, int h) {
		this.state = state;
		this.g = g;
		this.h = h;
		f = g + h;
	}
	
	@Override
	public int compareTo(HeuristicNode that) {
		return this.f - that.f;
	}
	
	@Override
	public boolean equals(Object that) {
		if (that == null) return false;
		if (that instanceof HeuristicNode) {
			return ((HeuristicNode)that).state.equals(state);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return state.hashCode();
	}
}

