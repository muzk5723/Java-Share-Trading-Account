package information;

public class AccountCreate {
			
	private int accountNumber;
	private int accountId;
	private String userName;
	private double money;
	public AccountCreate(int accountNumber, String userName) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
	}
	public AccountCreate(int accountNumber, int accountId, String userName) {
		super();
		this.accountNumber = accountNumber;
		this.accountId = accountId;
		this.userName = userName;
	}
	public AccountCreate(int accountNumber, int accountId, String userName, double money) {
		super();
		this.accountNumber = accountNumber;
		this.accountId = accountId;
		this.userName = userName;
		this.money = money;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public AccountCreate(int accountNumber, String userName, double money) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.money = money;
	}
	@Override
	public String toString() {
		return "AccountCreate [accountNumber=" + accountNumber + ", accountId=" + accountId + ", userName=" + userName
				+ ", money=" + money + "]";
	}
	
	
}
