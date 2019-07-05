package org.demo.batch.fakejms;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * JMS Simulator based on a ConcurrentLinkedQueue
 * 
 * @author laguerin
 *
 */
public class JMS {

	private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

	public static void send(String msg) {
		queue.add(msg);
	}

	public static void send(String msg, long waitDuration ) {
		queue.add(msg);
		sleep(waitDuration);
	}


	public static String receive() {
		waitRandomly();
		return queue.poll();
	}

	/**
	 * Waits 0 to 5 sec randomly
	 */
	public static void waitRandomly() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		sleep(randomNum*1000);
//		try {
//			Thread.sleep(randomNum*1000);
//		} catch (InterruptedException e) {
//			return ;
//		}
	}

	public static void sleep(long n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			return ;
		}
	}
}
