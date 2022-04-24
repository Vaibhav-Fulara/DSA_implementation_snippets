package com.company;
// Prim's Shortest Path Algorithm

import java.util.*;
public class Prims_Algo{
    public static class Edge{
        int src;
        int nbr;
        int wt;
        Edge(int src, int wt){
            this.src = src;
            this.wt = wt;
        }
        Edge(int src, int nbr, int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    public static void addToGraph(ArrayList<Edge>[]graph, int src, int dest, int wt){
        graph[src].add(new Edge(src, dest, wt));
        graph[dest].add(new Edge(dest, src, wt));
    }
    public static void main(String[]args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int e = scn.nextInt();
        ArrayList<Edge>[]graph = new ArrayList[n];
        for(int i=0; i<n; i++) graph[i] = new ArrayList<>();
        for(int i=0; i<e; i++){
            int src = scn.nextInt();
            int dest = scn.nextInt();
            int wt = scn.nextInt();
            addToGraph(graph, src, dest, wt);
        }
        System.out.println(primsAlgo(graph));
    }
    public static int primsAlgo(ArrayList<Edge>[]graph){
        int minCost = 0;
        int[]arr = new int[graph.length];
        Arrays.fill(arr,-1);
        PriorityQueue<Edge>pq = new PriorityQueue<>((a,b)->{return a.wt-b.wt;});
        pq.add(new Edge(0,0));
        while(pq.size()!=0){
            Edge temp = pq.remove();
            if(arr[temp.src]==-1) minCost += temp.wt;
            arr[temp.src] = 0;
            for(Edge e:graph[temp.src]){
                int nbr = e.nbr;
                int cost = e.wt;
                if(arr[nbr]==-1) pq.add(new Edge(nbr, cost));
            }
        }
        return minCost;
    }
}