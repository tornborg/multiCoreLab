import java.util.LinkedList;

public class Monitor {
	LinkedList<Vertex> worklist;
	Dataflow dFlow;
	
	public Monitor(Dataflow dFlow, LinkedList<Vertex> worklist){
		this.worklist = worklist;
		this.dFlow = dFlow;
	}
	
	public synchronized void removeWork(){
		
	}
	
	public synchronized void callCompute(){
	}

}
