package exception;

public class CannotAddMemberException extends Exception {

	private String message;
	
	public String getMessage(){
		return this.message;
	}
}
