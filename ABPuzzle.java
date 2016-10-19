import java.util.ArrayList;

public class ABPuzzle {

    public static void main(String[] args) {
        
        InputGetter ig = new InputGetter();       
        
        int willBeFromConsole = Integer.valueOf(args[0]);
       	//int willBeFromConsole = ig.getFirstNumber();
        Board board = ig.getBoard(willBeFromConsole);
        State state = ig.getState(willBeFromConsole);
        
        state.setNeighbours();
        state.setFriends();
        SearchNode tmp = new SearchNode(state);
	Goal goal = new Goal();
        
       if (goal.checkState(tmp)) {
            System.out.println("The puzzle is solved.");
            System.exit(0);
        }
        
        if (board.allOnes() && !goal.checkState(tmp)) {
            System.out.println("There is no solution.");
            System.exit(0);
        }
        
        Search search = new Search(state);
        int limit = 6;
        
        while (!goal.checkState(tmp)) {
            try {
                search.Expand(tmp, board);
            } catch (OutOfMemoryError e) {
                System.out.println("Out of memory.");
                System.exit(1);
            }
            
            tmp = search.getBest(limit);
            if (tmp == null) { //starting limit
                search = new Search(state);
                tmp = new SearchNode(state);
                limit = limit + 1;          
            } 

        }
        if (tmp != null) {    
            System.out.println("Solution is:");
            System.out.println(state.toString());
            search.printTracePath(tmp);
        }
    }
}
