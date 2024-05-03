import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Queue; //for BFS - https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
import java.util.LinkedList; //for BFS - https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html
import java.util.Stack; //for DFS - https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html

/**
This class will be a driver class that demonstrates graph traversal algorithms
*/
public class Graph
{
   public static void main(String[] args) throws IOException
   {
      //An ArrayList of Nodes, where each node contains an adjacency list of all nodes adjacent to it
      ArrayList<Node> graph = createGraph();
           
      //you can call displayGraph to have it print out the information in the graph
      //this is just to help you visualize each node in the graph, along with it's adjacent nodes
      displayGraph(graph);
      
      //call BFS and DFS here to have them print out their traversals
      breadthFirstSearch(graph);
      depthFirstSearch(graph);
      
   }
   
   /*
   Creates an ArrayList of Node objects that represent a graph
   
   Graph information is read from a GraphInfo.txt file contained in the same directory as this code
   Each line of the text file will contain information about a specific node and the nodes that are adjacent to it
   The nodes are delimited by a comma
   Example line of GraphInfo.txt:
   A,B,D
      A - the starting Node
      B D - nodes that are adjacent to A 
      This means that there is an edge from A to B and an edge from A to D
   This is an unweighted graph
   
   @return graph The graph 
   */
   public static ArrayList<Node> createGraph() throws IOException
   {
      ArrayList<Node> graph = new ArrayList<Node>();
      
      File file = new File("GraphInfo.txt");
      Scanner inputFile = new Scanner(file);
      
      //read in the information from each line of the text file
      while(inputFile.hasNext())
      {
         String line = inputFile.nextLine();
         String[] tokens = line.split(",");//split the line of input on the delimiter (a comma)
         
         //make sure there is at least one node worth of information
         if(tokens.length > 0)
         {
            Node n = new Node(tokens[0]); //create a new Node object
            int nodeIndex = graph.indexOf(n);
            if(nodeIndex != -1)//node is already in the graph, retrieve a reference to it
               n = graph.get(nodeIndex);
            else//add it to the graph as a new node
               graph.add(n);  
                     
            //if there are any adjacent nodes, create them (or find them in the graph) and add them as an adjacent Node
            for(int i = 1; i < tokens.length; i++)
            {
               Node adj = new Node(tokens[i]);
               int adjNodeIndex = graph.indexOf(adj);
               if(adjNodeIndex != -1)//adjacent node is already in the graph, retrieve a reference to it
                  adj = graph.get(adjNodeIndex);
               else//add it to the graph as a new node
                  graph.add(adj);   
               n.addAdjacentNode(adj);
            }
         }
      }
      return graph;
   }
   
   /**
   Displays all Node information for each Node in the graph
   @param graph The graph
   */
   public static void displayGraph(ArrayList<Node> graph)
   {
      for(Node n : graph)
      {
         System.out.println(n);
      }
   }
   
   /**
   This method will perform a Breadth First Search traversal starting with the first Node contained in the graph
   At the end of the method, display the contents of discoveredSet, which will be the traversal of the graph
   @param graph An ArrayList of Node objects representing a graph
   */
   public static void breadthFirstSearch(ArrayList<Node> graph)
   {
      //perform a breadth first traversal from the first Node in the graph
      Queue<Node> queue = new LinkedList<>();
      ArrayList<Node> discoveredSet = new ArrayList<>();
      Node startNode = graph.get(0);
      queue.add(startNode);
      discoveredSet.add(startNode);

      while(!queue.isEmpty()) {
        Node current = queue.remove();

        for (Node n : current.getAdjacencyList()) {
            if (!discoveredSet.contains(n)) {
                queue.add(n);
                discoveredSet.add(n);
            }
            
        }
      }
      //print out the contents of discoveredSet
      System.out.println("\nBFS:");
      for (Node n : discoveredSet) {
        System.out.print(n.getName() + " ");
      }
      System.out.println();
   }
   /**
   This method will perform a Depth First Search traversal starting with the first Node contained in the graph
   At the end of the method, displays the contents of visitedSet, which will be the traversal of the graph
   @param graph An ArrayList of Node objects representing a graph
   */
   public static void depthFirstSearch(ArrayList<Node> graph)
   {
   
      //perform a depth first traversal from the first Node in the graph
      Stack<Node> stack = new Stack<>();
      ArrayList<Node> visitedSet = new ArrayList<>();
      Node startV = graph.get(0);
      stack.push(startV);

      while(!stack.isEmpty()) {
        Node currentV = stack.pop();

        if (!visitedSet.contains(currentV)){
            visitedSet.add(currentV);
            

            for(Node adjV : currentV.getAdjacencyList()) {
                stack.push(adjV);
            }
        }

        
    }
     
      System.out.println("\nDFS:");
      //print out the contents of visitedSet - meaning the name of each node
      for (Node n : visitedSet) {
        System.out.print(n.getName() + " ");
      }
      System.out.println();
   }
}