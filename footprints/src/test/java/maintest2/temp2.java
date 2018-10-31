package maintest2;


class number extends Thread{
	int num;
	public void run() {
		for (int i = 0; i < 1000; i++) {
			num++;
		}
		System.out.println(num);
	}
	
}
public class temp2 {
public static void main(String[] args) {
	number n = new number();
	Thread t = new Thread(new number());
	Thread t1 = new Thread(new number());
//	t.run();
	t.start();
	t1.start();
	
}
	
}
