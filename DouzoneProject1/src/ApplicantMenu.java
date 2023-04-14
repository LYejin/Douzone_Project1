import java.util.Scanner;

public class ApplicantMenu {
	private String applicantId;
	private NoticeManagement noticeManagement;
	
	Scanner sc = new Scanner(System.in);	
	
	public ApplicantMenu(String applicantId) {
		super();
		this.applicantId = applicantId;
		this.noticeManagement = new NoticeManagement();
	}

	// 지원자 메뉴
	public void applicantMenu() {
		int temp = -1;
		while (temp != 6) {
			System.out.println("----------------------------------");
			System.out.println("-             지원자 메뉴           -");
			System.out.println("----------------------------------");
			System.out.println("원하시는 항목을 선택해주세요.");
			System.out.println("[1]공고지원 [2]공고정보확인 [3]전체공고목록확인 [4]지원공고목록확인 [5]개인정보수정 [6]로그아웃");
			temp = Integer.parseInt(sc.nextLine());
			switch (temp) {
			case 1: // 공고 지원
				noticeApplication(applicantId);
				break;
			case 2: // 공고 정보 확인
				noticeInfoCheck(inputNoticeNumber());
				break;
			case 3: // 전체 공고 목록 확인
				allNoticeListCheck();
				break;
			case 4: // 지원 공고 목록 확인
				applicantNoticeListCheck(this.applicantId);
				break;
			case 5: // 개인 정보 수정
				applicantInfomationModifocation(this.applicantId);
				break;
			case 6: // 로그아웃
				break;
			default:
				System.out.println("정확한 항목 번호를 입력해주세요.");
			}
		}
	}
	
	// 공고번호 입력 받는 메소드
	private String inputNoticeNumber() { 
		System.out.println("공고번호를 입력해주세요.");
		System.out.println("공고번호 : ");
		String noticeNumber = sc.nextLine();
		return noticeNumber;
	}
	
	
	public void noticeApplication(String applicantId) { // 공고지원 
		noticeManagement.noticeApplication(applicantId);
	}
	
	public void noticeInfoCheck(String noticeNumber) {  // 공고정보확인 
		noticeManagement.noticeInfoCheck(noticeNumber);
	}
	
	public void allNoticeListCheck(){ // 전체 공고 목록 확인 
		noticeManagement.allNoticeListCheck();
	}
	
	public void applicantNoticeListCheck(String applicantId) { // 지원 공고 목록 확인 
		noticeManagement.applicantNoticeListCheck(applicantId);
	}
	
	public void applicantInfomationModifocation(String applicantId) { // 개인정보 수정 
		MemberManagement memberManagement = new MemberManagement();
		memberManagement.applicantInfomationModifocation(applicantId);
	}

	public void logOut(String applicantId) {
		//indentifyingApplicants = false;
	}
}
