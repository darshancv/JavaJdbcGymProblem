package com.mindtree.exception;

public class NoMemberFoundException extends GymAppDaoException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String message="No Female Member Found";
	public NoMemberFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoMemberFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public NoMemberFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NoMemberFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NoMemberFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	

}
