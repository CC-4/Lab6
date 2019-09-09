import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;


/** Inheritance Graph. */
public final class Graph {

  /** inheritance graph */
  private final HashMap<String, LinkedList<String>> graph;

  /** Creates an empty inheritance graph. */
  public Graph() {
    graph = new HashMap<>();
  }

  /**
   * Adds a new parent with his children.
   *
   * @param parent parent class
   * @param children parent children
   */
  public void add(String parent, LinkedList<String> children) {
    graph.put(parent, children);
  }

  /**
   * Checks if a class has cycles.
   *
   * @param name node name
   * @return true if the class has cycles, false if not
   */
  public boolean hasCycles(String name) {
    return hasCycles(name, new HashSet<>());
  }

  /**
   * Checks if a class has cycles.
   *
   * @param name class name
   * @param visted visited classes set
   * @return true if the class has cycles, false if not
   */
  private boolean hasCycles(String name, HashSet<String> visited) {
    // WRITE YOUR CODE HERE
    return false;
  }

}
