package information;

public class LoginAccount {
		
		private int accountNumber;

		public LoginAccount(int accountNumber) {
			super();
			this.accountNumber = accountNumber;
		}

		public int getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(int accountNumber) {
			this.accountNumber = accountNumber;
		}

		public LoginAccount() {
			super();
		}

		@Override
		public String toString() {
			return "LoginAccount [accountNumber=" + accountNumber + "]";
		}
		
}
