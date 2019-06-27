package org.demo.batch.tools;

public class BasicLogger {

	public final static BasicLogger getLogger( String name ) {
		return new BasicLogger(name);
	}
	
	public final static BasicLogger getLogger( Class<?> clazz ) {
		return new BasicLogger(clazz.getSimpleName());
	}
	
	private final String name ;
	
	public BasicLogger(String name) {
		super();
		this.name = name;
	}

	public void log(String msg) {
		println("[BASIC-LOG] (" + name + ") : " + msg);
	}

	private void println(String msg) {
		System.out.println(msg);
	}
}
