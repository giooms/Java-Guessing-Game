public class Node{

    //Allows to specify the type of a node (A or Q).
    enum Type{
        ANSWER, QUESTION
    }

    //The flags allow to identify the type from the .txt file
    public static final String QUESTION_FLAG ="?";
    public static final String ANSWER_FLAG = "=";

    public String data; //Represents the text of the node
    public Type type; //Indicates whether it is a A or Q

    //Reference to the child nodes
    public Node yes;
    public Node no; 

    public Node(){ //By default it's empty
    }

    public Node(String data, Type type){
        this.data = data; 
        this.type = type;
    }

    public boolean isQuestion() {
        return Type.QUESTION.equals(type);
    }

    //Checks the type of the node
    public void setData(String data) {
        type = Type.QUESTION;

        if (data.startsWith(ANSWER_FLAG)) {
        type = Type.ANSWER;
        }

        this.data = data.substring(2); //Removes the flag (for latter use)
    }

    public void addYes(TreeNode yes) {
        this.yes = yes;
    }

    public void addNo(TreeNode no) {
        this.no = no;
    }
}