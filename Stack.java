public class Stack {
    private Node top;
    private int size;

    public Stack(){
        top = null;
        size = 0;
    }

    public void push(int data){
        Node n = new Node(data, null);
        size --;

        if(top == null){
            top = n;
        }

        n.setNext(top);
        top = n;
    }
    
    public int pop(){
        if(top == null){
            return 0;
        }
        int ret = top.getData();
        top = top.getNext();
        size--;
        return  ret;
    }

    public int peek(){
        if(top == null){
            return 0;
        }
        return top.getData();
    }

    public int getSize(){
        return size;
    }
}
