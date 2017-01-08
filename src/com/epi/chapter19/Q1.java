package com.epi.chapter19;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Q1 {
	
	

	public static void main(String[] args) {
		
	}
	
	
	boolean isFeasible(MazeNode current, List<List<Color>> maze) {//x corresponds to row and 
		if (current.row >= 0 && current.row < maze.size() && current.column >= 0 && current.column < maze.get(current.row).size()
				&& maze.get(current.row).get(current.column) == Color.WHITE) {
			return true;
		}
		return false;		
	}
	
	/*
	 * search a maze.
	 * Given a graph of black and white maze. check if there is a path from start to end.
	 * Find path from entrance to exit if one exists.
	 * 
	 * Try dfs first, so i can exit as soon as a path is found.
	 * if i try bfs, it can take more time.
	 */
	public List<MazeNode> getPath(List<List<Color>> maze) {
		MazeNode start = new MazeNode(0, 0);
		MazeNode end = new MazeNode(maze.size() - 1, maze.get(0).size() - 1);
		
		List<MazeNode> path = new ArrayList<>();
		path.add(start);
		
		if (!searchMazeHelper(maze, start, end, path)) {
			path.remove(path.get(path.size() - 1));
		}
		return path;
	}
	
	boolean searchMazeHelper(List<List<Color>> maze, MazeNode cur, MazeNode end, List<MazeNode> path) {
		// base case.
		if (cur.equals(end)) {
			return true;
		}
		
		int [][] shift = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // right, up, down, left
		
		for (int [] direction: shift) {
			MazeNode next = new MazeNode(cur.row + direction[0], cur.column + direction[1]);
			if (isFeasible(next, maze)) {
				maze.get(next.row).set(next.column, Color.BLACK);
				path.add(next);
				if(searchMazeHelper(maze, next, end, path)) {
					return true;
				}
				path.remove(path.size() - 1);
			}
		}
		return false;
	}
}

/*
 * how to represent the maze. should i represent as 2D array? 
 * m * n maze. m rows and n columns.
 * 
 */
class MazeNode {
	public int row;
	public int column;
	
	public MazeNode(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof MazeNode)) {
			return false;
		}
		MazeNode o = (MazeNode)  other;
		return o.row == this.row && o.column == this.column;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}
	
}


enum Color {
	WHITE, BLACK;
}

