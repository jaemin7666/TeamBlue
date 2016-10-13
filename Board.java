

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    
    private ArrayList<Integer> board = new ArrayList<>();
    
    Board(){}
    Board(List<Integer> b)
    {
        this.board = (ArrayList<Integer>) b;
    }
    
    public void addToBoard(int value) 
    {
        board.add(value);
    }
    
    public boolean allOnes() 
    {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i) > 1) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return board.toString();
        
       /* String result = "";
        for (int item : board) {
            result += item;
        }
        return result;*/
    }
    
    public int getPosition(int pos)
    {
        return board.get(pos);
    }
    
    public List<Integer> getBoard()
    {
        return board;
    }
    
    public void reverseBoard()
    {
        Collections.reverse(board);
    }
}