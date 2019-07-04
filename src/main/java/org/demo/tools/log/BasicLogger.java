package org.demo.tools.log;

public class BasicLogger {

	private static boolean LOG = false ;
	
	public static final void setLogStatus(boolean b) {
		LOG = b ;
	}

	public static final BasicLogger getLogger( String name ) {
		return new BasicLogger(name);
	}
	
	public static final BasicLogger getLogger( Class<?> clazz ) {
		return new BasicLogger(clazz.getSimpleName());
	}
	
	//--------------------------------------------------------------------
	
	private final String name ;
	
	public BasicLogger(String name) {
		super();
		this.name = name;
	}

	public void log(String msg) {
		println("[BASIC-LOG] (" + name + ") : " + msg);
	}

	private void println(String msg) {
		if ( LOG ) {
			System.out.println(msg);
		}
	}
}
