package algorithms;

public class SimulatedForLoop {

		public static void main(String[] args) {
			forLoop(10, new Action() {
				
				int count = 1;
				
				@Override
				public void act() {
					// TODO Auto-generated method stub
					System.out.println(count + ". Hello World!");
					count++;
				}
			});
		}
		
		public static void forLoop(int n, Action a) {
			if(n > 0) {
				a.act();
				forLoop(n-1, a);
			}
		}
}
