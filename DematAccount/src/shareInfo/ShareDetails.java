package shareInfo;

public class ShareDetails {
	private int userId;
	private String shareName;
	private int totalShares;
	private int noOfShares;
	private int valueOfShares;

	public ShareDetails(String shareName, int totalShares, int valueOfShares) {
		super();
		this.shareName = shareName;
		this.totalShares = totalShares;
		this.valueOfShares = valueOfShares;
	}

	public ShareDetails(int userId, String shareName, int noOfShares, int valueOfShares) {
		super();
		this.userId = userId;
		this.shareName = shareName;
		this.noOfShares = noOfShares;
		this.valueOfShares = valueOfShares;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public int getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(int noOfShares) {
		this.noOfShares = noOfShares;
	}

	public int getValurOfShares() {
		return valueOfShares;
	}

	public void setValurOfShares(int valueOfShares) {
		this.valueOfShares = valueOfShares;
	}

	@Override
	public String toString() {
		return "ShareDetails [userId=" + userId + ", shareName=" + shareName + ", noOfShares=" + noOfShares
				+ ", valurOfShares=" + valueOfShares + "]";
	}
}
