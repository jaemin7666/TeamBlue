import java.util.ArrayList;

public class SearchNode {
    
    private State currentState; //state to which node corresponds to
    private SearchNode parentState; //parent State of the node
    private double HvalueForManhattanDistance; //Heuristic for Man Distance
    private double Gvalue; //Cost to reach the state
    private double FevaluationOfState; //Hvalue Man Distace+G value Parent
    
    public SearchNode(State state){ //constructor method
        currentState = state;
        parentState=null;
        HvalueForManhattanDistance = (state.getFinalTotal() - state.getFriends());
        Gvalue = 0;
        FevaluationOfState= HvalueForManhattanDistance+Gvalue;
    }

    public SearchNode(State child, SearchNode parent){ //override
        currentState = child;
        parentState = parent;
        HvalueForManhattanDistance = (child.getFinalTotal() - child.getFriends());
        Gvalue = parent.getGvalue() + 1; // parent + cost to get to child
        FevaluationOfState= HvalueForManhattanDistance+Gvalue;
    }
    //get Values Methods
    public double getHvalueMD () {
        return(HvalueForManhattanDistance);
    }
    public double getGvalue () {
        return(Gvalue);
    }
    public double getFevaluation() {
        return(FevaluationOfState);
    }
    public SearchNode getParent() {
        return(parentState);
    }
    public State getCurrent() {
        return(currentState);    
    }
    //set values
    public void setParent(SearchNode newParent) {
        parentState=newParent;
    }
    public void setCurrent(State newCurrent) {
        currentState = newCurrent;
    }
    public void setHvalueMD(double newHvalue) {
        HvalueForManhattanDistance=newHvalue;
    }
    public void setGvalue(double newGvalue) {
        Gvalue=newGvalue;
    }
    public void setFvalue () {
        FevaluationOfState=Gvalue+HvalueForManhattanDistance;
    }
    
    @Override
    public String toString()
    {
        //return state.toString();
        return (currentState.toString());
    }
    @Override
    public boolean equals(Object o)
    {
	SearchNode sn = (SearchNode) o;

	return sn.getCurrent() == currentState;

    }

}
