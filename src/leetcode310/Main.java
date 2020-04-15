package leetcode310;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int n = 6;
		
		int[][] edges = {{0,3},{1,3}, {2,3}, {4,3}, {5,4}};
		
		System.out.println("Number of nodes: " + n);
		
		System.out.println("Edges: " + Arrays.deepToString(edges));
		
		FindMinimumHeightTreeFunction solution = new FindMinimumHeightTreeFunction();
		
		System.out.println("Solution: " + solution.findMinHeightTrees(n, edges));
	}
}
