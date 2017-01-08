package com.epi.chapter19;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * Is there sequence  of teams starting with A and ending with B such that each team in A and ending with B such that each team in 
 * sequence has beaten next team in the sequence.
 * 
 * 
 * Directed graph , check if there is path from a to b. 
 * I can use DFS or bfs.
 * bfs is better.
 * 
 * How to model the graph ? 2D array or list of Nodes.
 */
public class BootCamp {
	
	public static boolean canTeamABeatTeamB(List<MatchResult> matches, String teamA, String teamB) {
		Set<String> visited = new HashSet<>();
		Map<String, Set<String>> graph = buildGraph(matches); // adjacency list graph
		
		return isReacheableDfs(graph, teamA, teamB, visited);
	}
	
	static boolean isReacheableDfs(Map<String, Set<String>> graph, String current, String teamB, Set<String> visited) {
		// base case.
		if (current.equals(teamB)) {
			return true;
		}
		if (visited.contains(current)) {
			return false;
		}
		visited.add(current);
		
		for (String team: graph.get(current)) {
			if (isReacheableDfs(graph, team, teamB, visited)) {
				return true;
			}
		}
		return false;
	}
	
	
	static boolean isReacheableBfs(Map<String, Set<String>> graph, String teamA, String teamB) {
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.add(teamA);
		
		
		while (!queue.isEmpty()) {
			String current = queue.poll();
			visited.add(current);
			
			if (current.equals(teamB)) {
				return true;
			}
			
			for (String team: graph.get(current)) {
				if (!visited.contains(team)) {
					queue.add(team);
				}
			}
		}
		return false;
		
	}
	
	static Map<String, Set<String>> buildGraph(List<MatchResult> matches) {
		Map<String, Set<String>> graph = new HashMap<>();
		// go though the matches, populate the graph
		
		for (MatchResult result: matches) {
			Set<String> loosingSet = graph.get(result.winningTeam);
			if (loosingSet == null) {
				loosingSet = new HashSet<>();
			}
			loosingSet.add(result.loosingTeam);
			graph.put(result.winningTeam, loosingSet);
		}
		return graph;
		
		}
}

class MatchResult {
	public String winningTeam;
	public String loosingTeam;
	
	MatchResult(String winningTeam, String loosingTeam) {
		this.winningTeam = winningTeam;
		this.loosingTeam = loosingTeam;
	}
}
