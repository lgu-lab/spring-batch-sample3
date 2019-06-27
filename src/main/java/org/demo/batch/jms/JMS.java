package org.demo.batch.jms;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class JMS {

	private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

	public static void send(String msg) {
		queue.add(msg);
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
		try {
			Thread.sleep(randomNum*1000);
		} catch (InterruptedException e) {
			return ;
		}
	}
}
