package ph.com.fss.model;

public class ResponseModel<T> {

	private boolean isSuccessful;
	private T data;
	private String msg;
	
	public boolean isSuccessful() {
		return isSuccessful;
	}
	public void setSuccessFlag(boolean pbSuccessFlag) {
		this.isSuccessful = pbSuccessFlag;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T pData) {
		this.data = pData;
	}
	
	public String getMessage() {
		return msg;
	}
	
	public void setMessage(String pstrErrorMessage) {
		this.msg = pstrErrorMessage;
	}
	
	
}
