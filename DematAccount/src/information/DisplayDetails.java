package information;

public class DisplayDetails {
		
	private int accNo;
	private int accId;
	public DisplayDetails(int accNo, int accId) {
		super();
		this.accNo = accNo;
		this.accId = accId;
	}
	
	public DisplayDetails(int accId) {
		super();
		this.accId = accId;
	}
	public DisplayDetails() {
		super();
	}
	@Override
	public String toString() {
		return "DisplayDetails [accNo=" + accNo + ", accId=" + accId + "]";
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	
}
