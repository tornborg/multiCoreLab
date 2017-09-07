import java.util.LinkedList;

public class Monitor {
	LinkedList<Vertex> worklist;
	Vertex v;

	public Monitor(LinkedList<Vertex> worklist) {
		this.worklist = worklist;
	}

	public synchronized void removeWork() {
		while (worklist.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		v = worklist.remove();
		v.listed = false;
	}

	public synchronized void callCompute() {
		v.computeIn(worklist);
	}

}
