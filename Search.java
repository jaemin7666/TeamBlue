import java.util.ArrayList;
import java.util.Collections;

public class Search {
    
    private NodeList fringe ;
    private NodeList closed;
    private NodeList traced;
    private SearchNode rootNode;
    
    
    /*startSearch: Search.startSeach(initialstate);
    Description: creates the fringes, closed list and starts the search
    Input: The initialstate
    Outputs: None
    */
    public Search(State initialState) {
        fringe = new NodeList();
        closed = new NodeList();
        traced = new NodeList();
        SearchNode rootNode = new SearchNode(initialState);
    }
    
    /* Moves checked Node from Fring to Closed */
    public void movingCheckedNode(SearchNode checkedNode){
        closed.addToNode(fringe.removeNode(checkedNode));
    }
    
    /* getBest:  Search.getBest(Nodelist)
    Description: Gets the best Node based on the Heuristics
    Inputs: fringe: The fringe List
    Outputs: the selected node based on the Heuristics
    */
    public SearchNode getBest(int limit) {
        int bestNodeIndex = 0;
        int counter;
        double bestValue;
        boolean flag = false;
        
        bestValue = fringe.getNode(0).getFevaluation();
        for (counter = 0; counter < (fringe.getSize()-1); counter++) {
            if ((fringe.getNode(counter).getFevaluation() <= bestValue) && (fringe.getNode(counter).getFevaluation() <= limit)) { //heuristic here
                bestNodeIndex = counter;
                bestValue=fringe.getNode(counter).getFevaluation();
                flag = true;
            }
        }
        if (flag == false) {
            return(null);
        } 
        return(fringe.removeNode(fringe.getNode(bestNodeIndex)));
    }
    
    public boolean isInClosed(SearchNode checkNode)
    {
        return closed.checkNode(checkNode);
    }
    
    //adds the tracepath once a goal has been made. 

    public void printTracePath(SearchNode goalNode) 
    {
        //Trace the path of the goal back to root so that the complete path can be printed
        // needs to be reversed
        // Tracing the path is looking at all the parent nodes.
        SearchNode nodeForInspection; 
        nodeForInspection = goalNode;
        while (nodeForInspection.getParent() != null)
        {
            traced.addToNode(nodeForInspection);
            nodeForInspection = nodeForInspection.getParent();
        }

        Collections.reverse(traced.getEverything());
        System.out.println(traced.toString());
    }
    
    /*
    Expand: method call: Search.Expand(Node, board)
    Inputs: Node-> The node to expand, Board -> The overall board
    Outputs: An arraylist of nodes expanded from the first node
    */
    public ArrayList<SearchNode> Expand(SearchNode nodeToExpand, Board board) {
        ArrayList<State> childrenStates = new ArrayList<>(); //node for input
        ArrayList<SearchNode> childrenNodes = new ArrayList<>(); //node to return
        State currentStateforNode; //extracting the node to expand (could have just called the method)
        currentStateforNode = nodeToExpand.getCurrent();

        childrenStates = currentStateforNode.produceChildren(board);
        for (State item : childrenStates) { //for each state expand to node and add to list
            SearchNode newChildNode = new SearchNode(item , nodeToExpand);
            childrenNodes.add(newChildNode);
        }
        for (SearchNode itemToAdd : childrenNodes) {
            if (closed.checkNode(itemToAdd) == false ) {
                fringe.addToNode(itemToAdd);
            }
        }
        closed.addToNode(nodeToExpand);
        return(childrenNodes); 
    }
    
    //test GetList 
    public NodeList testGetList(String choice) 
    {
        switch (choice) {
            case "fringe" : return(fringe);
            case "closed" : return(closed);
            case "traced" : return(traced);
            default: return (null);
        }
    }
}
