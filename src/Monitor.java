import java.util.LinkedList;

public class Monitor {
	LinkedList<Vertex> worklist;
	Vertex v;
	int remCount = 0;
	int compCount = 0;

	public Monitor(LinkedList<Vertex> worklist) {
		this.worklist = worklist;
	}

	public synchronized void removeWork() {
		while (worklist.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}			
		}
			v = worklist.remove();
			v.listed = false;
			remCount++;
	}

	public synchronized void callCompute() {
		v.computeIn(worklist);
		compCount++;
	}
	
	public String printCount(){
		return "CompThread: " + compCount + "\nRemoveThread: " + remCount;
		
	}

}
