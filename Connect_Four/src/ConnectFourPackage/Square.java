package ConnectFourPackage;

/**
 * Created by Brandon on 5/1/2016.
 */
public class Square {
    // square's privates
    private boolean empty;
    private char mark;

    // Square's publics
    public Square (){
        empty = true;
        mark = ' ';
    }

    public boolean isEmpty(){
        return empty;
    }

    public char getMark(){
        return mark;
    }

    public void setMark(char rob){
        mark = rob;
        empty = false;
    }
}
