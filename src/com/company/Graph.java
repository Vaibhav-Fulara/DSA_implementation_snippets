package com.company;

// Graph Implementation
import java.util.*;
public class Graph {
    public static void main (String[]args){
        constructGraph();
    }
    public static class Edge{
        int src;
        int nbr;
        int wt;
        Edge(int src, int nbr, int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(u,v,w));
        graph[v].add(new Edge(v,u,w));
    }
    public static void constructGraph(){
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i=0; i<n; i++) graph[i] = new ArrayList<>();
        addEdge(graph, 0,1,10);
        addEdge(graph, 0,3,10);
        addEdge(graph, 1,2,10);
        addEdge(graph, 3,4,2);
        addEdge(graph, 4,5,2);
        addEdge(graph, 5,6,3);
        addEdge(graph, 4,6,8);
        display(graph, n);
    }
    public static void display(ArrayList<Edge>[]graph, int n){
        for(int i=0; i<n; i++){
            System.out.print(i + " -> " );
            for(Edge e : graph[i]){
                int u = e.src;
                int v = e.nbr;
                int w = e.wt;
                System.out.print(u + "-" + v + "@" + w + ", ");
            }
            System.out.println();
        }
    }
}
