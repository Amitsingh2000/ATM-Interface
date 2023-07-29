import java.util.List;

public class User 
{
	private String userId;
	private String password;
	private long accountNo;
	private double accountBalance;
	private List<String> transactions;
	
	public User(){}
	
	public User(String userId, String password, long accountNo, double accountBalance, List<String> transactions) 
	{
		this.userId = userId;
		this.password = password;
		this.accountNo = accountNo;
		this.accountBalance = accountBalance;
		this.transactions = transactions;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public List<String> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", accountNo=" + accountNo + ", accountBalance="
				+ accountBalance + ", transactions=" + transactions + "]";
	}

	
}
