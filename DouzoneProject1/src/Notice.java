
public class Notice {
	private int noticeNumber;
	private String presidentID;
	private int recruitmentNumber;
	private String gender;
	private String companyName;
	private String companyLocation;
	private int hourlyWage;
	private int jobHours;
	private boolean noticeStatus;
	private int period;
	private int actualAmountReceived;
//	private List<> : 지원자 리스트 올 예정
	
	public Notice() {} // 기본 생성자 -> 초기 공고 등록 에러 방지, 추후 삭제해도 무방
	public Notice(int noticeNumber, String presidentID, int recruitmentNumber, String gender, String companyName,
			String companyLocation, int hourlyWage, int jobHours, boolean noticeStatus, int period,
			int actualAmountReceived) {
		super();
		this.noticeNumber = noticeNumber;
		this.presidentID = presidentID;
		this.recruitmentNumber = recruitmentNumber;
		this.gender = gender;
		this.companyName = companyName;
		this.companyLocation = companyLocation;
		this.hourlyWage = hourlyWage;
		this.jobHours = jobHours;
		this.noticeStatus = noticeStatus;
		this.period = period;
		this.actualAmountReceived = hourlyWage*(jobHours*4) - (int)(hourlyWage*(jobHours*4)*0.033); // 실수령액 -> 시급*(알바시간(주)*4) - 공제(3.3%)
	}

	public int getNoticeNumber() {
		return noticeNumber;
	}

	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}

	public String getPresidentID() {
		return presidentID;
	}

	public void setPresidentID(String presidentID) {
		this.presidentID = presidentID;
	}

	public int getRecruitmentNumber() {
		return recruitmentNumber;
	}

	public void setRecruitmentNumber(int recruitmentNumber) {
		this.recruitmentNumber = recruitmentNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyLocation() {
		return companyLocation;
	}

	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}

	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public int getJobHours() {
		return jobHours;
	}

	public void setJobHours(int jobHours) {
		this.jobHours = jobHours;
	}

	public boolean isNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus() {
		this.noticeStatus = !noticeStatus;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getActualAmountReceived() {
		return actualAmountReceived;
	}

	public void setActualAmountReceived(int actualAmountReceived) {
		this.actualAmountReceived = actualAmountReceived;
	}
}
