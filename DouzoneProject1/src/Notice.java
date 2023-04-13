import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notice implements Serializable {
	private static int noticeNumber = 1;
	private String presidentID;
	private int recruitmentNumber;
	private String gender;
	private String companyName;
	private String companyLocation;
	private int hourlyWage;
	private int jobHours;
	private String noticeStatus;
	private int period;
	private int actualAmountReceived;
	private List<String> applicant;
	
	public Notice() {} // 기본 생성자 -> 초기 공고 등록 에러 방지, 추후 삭제해도 무방
	public Notice(String presidentID, int recruitmentNumber, String gender, String companyName,
			String companyLocation, int hourlyWage, int jobHours, int period) {
		super();
		this.noticeNumber++;
		this.recruitmentNumber = recruitmentNumber;
		this.presidentID=presidentID;
		this.gender = gender;
		this.companyName = companyName;
		this.companyLocation = companyLocation;
		this.hourlyWage = hourlyWage;
		this.jobHours = jobHours;
		this.period = period;
		this.noticeStatus = "모집중";
		this.actualAmountReceived = hourlyWage*(jobHours*4) - (int)(hourlyWage*(jobHours*4)*0.033); // 실수령액 -> 시급*(알바시간(주)*4) - 공제(3.3%)
		this.applicant = new ArrayList<String>();
	}

	public int getNoticeNumber() {
		return noticeNumber;
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

	public String getNoticeStatus() {
		return this.noticeStatus;
	}

	public void setNoticeStatus() { 
		System.out.println("공고상태를 변경합니다.");
		if (getNoticeStatus() == "모집중") {
			System.out.println("현재 공고상태는 '모집중' 입니다.");
			System.out.println("현재 공고상태를 '모집완료'로 변경합니다.");
			this.noticeStatus = "모집완료";
		} else if (getNoticeStatus() == "모집완료") {
			System.out.println("현재 공고상태는 '모집완료' 입니다.");
			System.out.println("현재 공고상태를 '모집중'로 변경합니다.");
			this.noticeStatus = "모집중";
		}
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
	
	public List<String> getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicantId) {
		this.applicant.add(applicantId);
	}
	
	@Override
	public String toString() {
		return "-----------------------------------------" +
				"\n[공고번호]: " + noticeNumber + "\n[모집인원] : " + recruitmentNumber + "\n[성별]: " + gender + "\n[업체명]: " + companyName
				+ "\n[매장위치]: " + companyLocation + "\n[시급]: " + hourlyWage + "\n[알바시간]: " + jobHours
				+ "\n[공고상태]: " + noticeStatus + "\n[기간]: " + period + "\n[실수령액]: " + actualAmountReceived;
	}
	
	
}
