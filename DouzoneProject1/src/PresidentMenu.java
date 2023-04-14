import java.util.Scanner;

public class PresidentMenu {
	private NoticeManagement noticeManagement; // 공고 관리
	private String presidentID; // 사장 ID
	Scanner sc = new Scanner(System.in);

	public PresidentMenu(String presidentID) {
		super();
		this.noticeManagement = new NoticeManagement();
		this.presidentID = presidentID;
	}

	// 지원자조회
	private void applicantInquiry(String noticeNumber) {
		noticeManagement.applicantInquiry(noticeNumber);
	}

	// 공고상태변경
	private void noticeStatusChange(String noticeNumber) {
		noticeManagement.noticeStatusChange(noticeNumber);
	}

	// 공고삭제
	private void noticeDeletion(String noticeNumber) {
		noticeManagement.noticeDeletion(noticeNumber);
	}

	// 공고수정
	private void noticeModification(String noticeNumber) {
		noticeManagement.noticeModification(noticeNumber);
	}

	// 공고등록 (수정)
	private void noticeRegistration() {
		noticeManagement.noticeRegistration(this.presidentID);
	}

	// 공고목록확인 (사장이 등록한 공고 목록) (수정)
	private void noticeListCheck() {
		noticeManagement.noticeListCheck(this.presidentID);
	}

	// 개인정보수정 -> text로 read해서 그 값을 받아와서 수정 후 다시 text 파일로 저장
	private void personalInfomationModifocation() throws Exception{
		MemberManagement memberManagement = new MemberManagement();
		memberManagement.presidentInfomationModifocation(presidentID);
	}

	// 로그아웃 -> text로 read해서 그 값을 받아와서 로그아웃 후 다시 text 파일로 저장
	private void logOut(int presidenID) {

	}

	// 공고번호 입력 받는 메소드
	private String inputNoticeNumber() {
		System.out.println("공고번호를 입력해주세요.");
		System.out.print("공고번호 : ");
		String noticeNumber = sc.nextLine();
		return noticeNumber;
	}
	
	// 사장 메뉴
	public void presidentMenu() {
		int temp = -1;
		while (temp != 8) {
			System.out.println("----------------------------------");
			System.out.println("-             사장 메뉴            -");
			System.out.println("----------------------------------");
			System.out.println("원하시는 항목을 선택해주세요.");
			System.out.println("[1]지원자조회 [2]공고목록확인 [3]공고삭제 [4]공고수정 [5]공고등록 [6]공고상태변경 [7]개인정보수정 [8]로그아웃");
			try {
				temp = Integer.parseInt(sc.nextLine());
				switch (temp) {
				case 1:
					applicantInquiry(inputNoticeNumber());
					break;
				case 2:
					noticeListCheck();
					break;
				case 3:
					noticeDeletion(inputNoticeNumber());
					break;
				case 4:
					noticeModification(inputNoticeNumber());
					break;
				case 5:
					noticeRegistration();
					break;
				case 6:
					noticeStatusChange(inputNoticeNumber());
					break;
				case 7:
					personalInfomationModifocation();
					break;
				case 8:
					break;
				default:
					System.out.println("정확한 항목 번호를 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다 메뉴의 숫자를 입력해주세요");
				e.printStackTrace();
			}
		}
	}

}
