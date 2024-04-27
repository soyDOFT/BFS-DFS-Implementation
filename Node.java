import java.util.ArrayList;

/**
The Node class represents a Node in an unweighted graph
There should be no need to alter this code to complete your assignment
If you do wish to add extra methods for some reason, that is OK, but do not alter any existing code
*/
public class Node
{
   private String name;
   private ArrayList<Node> adjancencyList;//list of nodes that are adjacent to this node
   
   /**
   Constructor
   @param name The name of the node
   */
   public Node(String name)
   {
      this.name = name;
      adjancencyList = new ArrayList<Node>();//create an empty adjacency list
      //Adjacent nodes must be added to this list via addAdjacentNode(Node)
   }
   
   /**
   @return The name of the node
   */
   public String getName()
   {
      return name;
   }
   
   /**
   @return An Array of Node objects that are adjacent to this Node object
   */
   public ArrayList<Node> getAdjacencyList()
   {
      return adjancencyList;//shallow copy is fine
   }
   
   /**
   Adds a Node to the adjacency list of this Node
   @param n The Node to add to the adjacency list
   */
   public void addAdjacentNode(Node n)
   {
      adjancencyList.add(n);
   }
   
   /**
   Compares two Nodes for equality.
   Nodes are considered to be equal if they have the same name
   @return true if the Nodes have the same name, false otherwise
   */
   @Override
   public boolean equals(Object o)
   {
      if(!(o instanceof Node))//should handle differently, but this works for now
         return false;
      Node n = (Node) o;
      return this.name.equalsIgnoreCase(n.name);
   }
   
   /**
   @return A String representation of the Node
   */
   public String toString()
   {
      String str ="Node: " + name + " Adjacency List:";
      for(Node a : adjancencyList)
      {
         str += " " + a.getName();
      }       
      return str;
   }
}