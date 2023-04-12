import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NoticeManagement {
	Scanner sc = new Scanner(System.in);
	Map<Integer, Notice> noticeList = new HashMap<Integer, Notice>();
	
	// 공고 상태 변경
	private void noticeStatusChange(int noticeNumber) {
		Notice notice = noticeList.get(noticeList);
		notice.setNoticeStatus();
	}
	
	// 공고 삭제
	private void noticeDeletion(int noticeNumber) {
		noticeList.remove(noticeList);
	}
	
	// 공고 수정
	private void noticeModification(int noticeNumber) {
		Notice notice = noticeList.get(noticeNumber);		
		// -> 어떤 정보를 수정할지 생각해볼 필요가 있을듯!		
		notice.setRecruitmentNumber(Integer.parseInt(sc.nextLine())); // 모집인원		
		notice.setGender(sc.nextLine()); // 성별		
		notice.setCompanyName(sc.nextLine()); // 업체명		
		notice.setCompanyLocation(sc.nextLine()); // 매장위치		
		notice.setHourlyWage(Integer.parseInt(sc.nextLine())); // 시급		
		notice.setJobHours(Integer.parseInt(sc.nextLine())); // 알바시간		
		notice.setNoticeStatus(); // 공고상태		
		notice.setPeriod(Integer.parseInt(sc.nextLine())); // 기간		
		notice.setActualAmountReceived(notice.getHourlyWage()*(notice.getJobHours()*4) - (int)(notice.getHourlyWage()*(notice.getJobHours()*4)*0.033)); // 실수령액 -> 시급, 알바시간 변화에 맞춰 리로드
	}
	
	// 공고 등록
	private void noticeRegistration() {
		Notice notice = new Notice();
		this.noticeList.put(notice.getNoticeNumber(), notice);
	}
	
	// 공고 목록 확인
	private void noticeListCheck() {
		
	}
	
	// 공고 지원
	private void noticeApplication() {
		
	}
}
