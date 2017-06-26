import java.lang.Thread;

public class Threads extends Thread{
	private int tid;
	public Threads(int id) {
		this.tid = id;
	}
	
	public void run() {
		int[] functions = function();
		main.testResult(functions, tid);
	}
	
	private int[] function() {
		int temp = 27 / main.totalThreads;
		if (27 % main.totalThreads != 0) temp++;
		int[] functions = new int[temp];
		int index = 0;

		for (int i = tid; i <= 27; i+=main.totalThreads) {
			functions[index++] = i;
		}
		return functions;
	}

}
