package information;

public class WithdrawMoney {
	private double money;
	private int accountNumber;
	
	public WithdrawMoney(double money) {
		super();
		this.money = money;
	}
	public WithdrawMoney(double money, int accountNumber) {
		super();
		this.money = money;
		this.accountNumber = accountNumber;
	}
	public WithdrawMoney() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "WithdrawMoney [money=" + money + ", accountNumber=" + accountNumber + "]";
	}
	
	
	


}
