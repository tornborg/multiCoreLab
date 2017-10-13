package multi;

import java.util.LinkedList;

public class Monitor {
	LinkedList<Vertex> worklist;
	LinkedList<Vertex> bufferlist = new LinkedList<Vertex>();
	Vertex v;
	Vertex u;
	int remCount = 0;
	int compCount = 0;
	long begin;
	boolean alive = true;

	public Monitor(LinkedList<Vertex> worklist, long begin) {
		this.worklist = worklist;
		this.begin = begin;
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
			bufferlist.addLast(v);
			remCount++;
			notifyAll();
	}

	public synchronized void callCompute() {
		if(bufferlist.isEmpty() && worklist.isEmpty()){
			alive = false;
			finish();
		}
		 if(bufferlist.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}else{
		u = bufferlist.remove();
		compCount++;
		u.computeIn(worklist);
		notifyAll();
		}
	}
	
	public void finish(){
		long end = System.nanoTime();
		System.out.println("T = " + (end - begin) / 1e9 + " s");
		System.out.println("CompThread: " + compCount);
		System.out.println("RemThread: " + remCount);
	}
	
	public String printCount(){
		return "CompThread: " + compCount + "\nRemoveThread: " + remCount +"\n";
	}

}
