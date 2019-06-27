package org.demo.batch.jms;

public class JMSTest {

	public static void main(String[] args) {
		
		System.out.println("Sending messages...");
		for ( int i = 0 ; i < 5 ; i++ ) {
			JMS.send("Msg" + i );
		}

		System.out.println("Receiving messages...");
		String msg = JMS.receive();
		while ( msg != null ) {
			System.out.println("Msg = " + msg );
			msg = JMS.receive();
		}
		System.out.println("End.");
	}

}
