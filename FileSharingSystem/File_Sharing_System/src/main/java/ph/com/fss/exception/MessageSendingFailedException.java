package ph.com.fss.exception;

public class MessageSendingFailedException extends FileSharingSystemException {

	public MessageSendingFailedException(String pstrMessage) {
		super(pstrMessage);
	}
}
