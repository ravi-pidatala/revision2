package com.cci.chapter4;

import java.util.LinkedList;
import java.util.Queue;

public class Node {

	public boolean visited;
	public Node[] adj;
	
	public static void main (String [] args) {
		
	}
	
	void dfs(Node node) {
		if (node == null) {
			return;
		}
		
		node.visited = true;
		
		for (Node n: node.adj) {
			if (!n.visited) {
				dfs(n);
			}
		}
	}
	
	/*
	 * use queue, not recursion. 
	 * 
	 * Remember to use queue.
	 */
	void bfs(Node root) {
		Queue<Node> q = new LinkedList<>();
		if (root == null) return;
		q.add(root);
		
		while (!q.isEmpty()) {
			Node n = q.poll();
			n.visited = true;
			
			for (Node adj: n.adj) {
				if (!adj.visited) {
					q.add(adj);
				}
			}
		}
	}
}
