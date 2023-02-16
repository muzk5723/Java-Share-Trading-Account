package information;

public class AddMoney {
			private double money;
			private int accountNumber;
			public AddMoney(double money) {
				super();
				this.money = money;
			}
			public AddMoney(double money, int accountNumber) {
				super();
				this.money = money;
				this.accountNumber = accountNumber;
			}
			public AddMoney() {
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
				return "AddMoney [money=" + money + ", accountNumber=" + accountNumber + "]";
			}
			
			
}
