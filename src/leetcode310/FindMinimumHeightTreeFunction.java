package leetcode310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FindMinimumHeightTreeFunction {
	// O(n) time and also O(n) space
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1){
            return Arrays.asList(0);
        }
        
        // All the adjacent nodes
        List<Set<Integer>> adjacent = new ArrayList<>(n);
        
        for(int i = 0; i < n; i++){
            adjacent.add(new HashSet<Integer>());
        }
        
        // Store each connection
        for(int[] edge : edges){
            adjacent.get(edge[0]).add(edge[1]);
            adjacent.get(edge[1]).add(edge[0]);
        }
        
        List<Integer> leaves = new ArrayList<>();
        
        // leaves list will have all the leaf nodes
        for(int i = 0; i < n; i++){
            if(adjacent.get(i).size() == 1){
                leaves.add(i);
            }
        }
        
        // Since there could only be one or two elements for the answer
        while(n > 2){
        	// Delete all the leaf node
            n = n - leaves.size();
            
            List<Integer> newLeaves = new ArrayList<>();
            
            for(int leaf : leaves){
            	// iterator().next() will an element in hashset
                int root = adjacent.get(leaf).iterator().next();
                
                // Remove the leaf node from the root node
                adjacent.get(root).remove(leaf);
                
                // If there is only one leaf node left
                if(adjacent.get(root).size() == 1){
                    newLeaves.add(root);
                }
            }
            
            leaves = newLeaves;
        }
        
        return leaves;
    }
	
	// Without using set, used array instead
	public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        
        if(n == 1){
            result.add(0);
            return result;
        }
        
        // Using an array instead
        List<Integer>[] adjacent = new ArrayList[n];
        
        for(int i = 0; i < n; i++){
            adjacent[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            adjacent[edge[0]].add(edge[1]);
            adjacent[edge[1]].add(edge[0]);
        }
        
        // This will keep track of each node connected size
        int[] sizeOfRoots = new int[n];
        
        // Since we will be adding and removing, queue is perfect
        Queue<Integer> leaves = new LinkedList<>();
        
        for(int i = 0; i < n; i++){
        	// Store the size at the same time
            sizeOfRoots[i] = adjacent[i].size();
            
            // While checking which one is leaf
            if(sizeOfRoots[i] == 1){
                leaves.offer(i);
            }
        }
        
        while(n > 2){
            int size = leaves.size();
            n = n - size;
            
            // using a variable size instead since the queue will keep adding
            for(int i = 0; i < size; i++){
                int leaf = leaves.poll();
                
                for(int w : adjacent[leaf]){
                    sizeOfRoots[w]--;
                    
                    if(sizeOfRoots[w] == 1){
                        leaves.add(w);
                    }
                }
            }
        }
        
        // addAll instead of add to add more than one elements
        result.addAll(leaves);
        
        return result;
    }
}
