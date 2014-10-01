package teitelbaum.multithreading;

import java.util.concurrent.CountDownLatch;

public class Multithreading 
{
	public static void main(String[] args) throws InterruptedException
	{
		Thread[] threads = new Thread[5];
		CountDownLatch latch = new CountDownLatch(5);
		
		for (int i = 0; i < threads.length; i++)
		{
			final int current = i;
			threads[i] = new Thread()
			{
				public void run()
				{
					System.out.println(current);
					latch.countDown();
				}
			};
			threads[i].start();
		}
		
		latch.await(); //will wait forever until condition is met, can also specify max amt of time to wait
		
		System.out.println("finished");
	}
}
