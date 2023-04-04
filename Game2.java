/*----------------------------------
* IMPORTATIONS OF NECESSARY LIBS 
------------------------------------*/



/*----------------------------------
* CREATION OF THE NODE CLASS 
------------------------------------*/

public class Node{

    //Allows to specify the type of a node (A or Q).
    enum Type{
        ANSWER, QUESTION
    }

    //The flags allow to identify the type from the .txt file
    public static final String QUESTION_FLAG ="?";
    public static final String ANSWER_FLAG = "=";

    public String data;     //Represents the text of the node
    public Type type;       //Indicates whether it is a A or Q
    public int n0;
    public int n1; 

    public Node(String data, Type type){
        this.data = data; 
        this.type = type;
    }

    public boolean isQuestion() {
        return Type.QUESTION.equals(this.type);
    }

    //Checks the type of the node
    public void setData(String data) {
        Type type;

        if (data.startsWith(ANSWER_FLAG)) {
            type = Type.ANSWER;
            this.data = data.substring(2); //Removes the flag (for latter use)
        }
        else {//We split the string into 4 --> [0] = type, [1]=n1, [2]=n0, [3]=question
            type = Type.QUESTION;
            String[] parts = data.split(" "); 
            n1 = Integer.parseInt(parts[1]);
            n0 = Integer.parseInt(parts[2]);
            this.data = parts[3];
        }
    }

}

/*----------------------------------
* CREATION OF THE GAME CLASS 
------------------------------------*/