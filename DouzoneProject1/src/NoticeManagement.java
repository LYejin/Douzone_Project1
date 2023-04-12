import java.util.HashMap;
import java.util.Map;

public class NoticeManagement {
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
	private void noticeModification() {
		
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
