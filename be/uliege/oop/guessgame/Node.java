/**
 * The Node class represents a node in a decision tree. It regroups 3 different types of constructors:
 * 
 * @constructor One for the first line of the file
 * @constructor Two for the Asnwers nodes which have as format |Type data|
 * @constructor Three for the Questions nodes which have as format |Type n1 n0 data| 
 */

package be.uliege.oop.guessgame;

public class Node{

    // Allows to specify the type of a node (First, A or Q).
    public enum Type{
        DIGIT, ANSWER, QUESTION
    }

    public int nb_node; // Is the nb of node (cfr. 1st line of .txt)
    public String data; // Represents the text of the node
    public Type type;   // Indicates whether it is a A or Q
    public int n1;      // Point to the "yes" child
    public int n0;      // Pointer to the "no" child

    public Node(Type type, int nb_node, String data){
        this.type = type;
        this.nb_node = nb_node;
        this.data = data;
    }

    public Node(Type type, String data){
        this.type = type;
        this.data = data; 
    }

    public Node(Type type, int n1, int n0, String data){
        this.type = type;
        this.n1 = n1;
        this.n0 = n0;
        this.data = data;
    }
}
