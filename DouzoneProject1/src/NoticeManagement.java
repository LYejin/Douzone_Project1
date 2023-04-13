import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class NoticeManagement {
	Scanner sc = new Scanner(System.in);
	Map<Integer, Notice> noticeList = new HashMap<Integer, Notice>();

	// 파일에서 값 불러오기 (추가 -> 클래스 다이어그램 수정)
	public Map<Integer, Notice> noticeFileLoad() {
		File file = new File("noticeFile.txt");
		if (file.length() > 0) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oos = new ObjectInputStream(fis);

				noticeList = (HashMap) oos.readObject();

				oos.close();
				fis.close();

			} catch (Exception e) {
				System.out.println("공고 파일을 불러오는데 실패하였습니다.");
				e.printStackTrace();
			}
			return noticeList;
		}
		return null;
	}

	// 수정된 파일 저장하기 (추가 -> 클래스 다이어그램 수정)
	public Map<Integer, Notice> noticeFileSave(Map<Integer, Notice> notice) {
		File file = new File("noticeFile.txt");
		if (file.length() > 0) {
			try {
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(noticeList);
				
				oos.close();
				fos.close();
			} catch (Exception e) {
				System.out.println("수정된 파일 저장 과정에서 에러발생");
				e.printStackTrace();
			}
			return notice;
		}
		return null;
	}

	// 지원자 조회
	public void applicantInquiry(int noticeNumber) {
		System.out.println("지원자를 조회합니다.");
		Notice notice = noticeFileLoad().get(noticeNumber);
		System.out.print("해당 공고 지원자 : ");
		for (String applicant : notice.getApplicant()) { // 지원자 출력
			System.out.println(applicant + " ");
		}
		System.out.println();
	}

	// 공고 상태 변경
	public void noticeStatusChange(int noticeNumber) {
		this.noticeList = noticeFileLoad();
		Notice notice = noticeList.get(noticeNumber);
		notice.setNoticeStatus();
		noticeFileSave(noticeList);
	}

	// 공고 삭제
	public void noticeDeletion(int noticeNumber) {
		this.noticeList = noticeFileLoad();
		noticeList.remove(noticeNumber);
		noticeFileSave(noticeList);
		System.out.println("공고를 삭제했습니다.");
	}

	// 공고 수정 -> 입력되지 않을 때 상황 처리해야함 함수에서 처리하면 좋을 듯..?
	public void noticeModification(int noticeNumber) {
		this.noticeList = noticeFileLoad();
		Notice notice = noticeList.get(noticeNumber);
		System.out.println("수정할 항목을 선택해주세요");
		System.out.println("1:모집인원 2:성별 3:업체명 4:매장위치 5:시급 6:알바시간 7:공고상태 8:기간 9:실수령액 0:메뉴로 돌아가기");
		int num = -1;
		while (num != 0) {
			num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case 1:
				System.out.println("모집인원을 수정합니다. 모집인원을 다시 설정해주세요.");
				System.out.print("모집인원 : ");
				notice.setRecruitmentNumber(Integer.parseInt(sc.nextLine())); // 모집인원
				System.out.println();
				break;
			case 2:
				System.out.println("성별을 수정합니다. 성별을 다시 설정해주세요.");
				System.out.print("성별 : ");
				notice.setGender(sc.nextLine()); // 성별
				System.out.println();
				break;
			case 3:
				System.out.println("업체명을 수정합니다. 업체명을 다시 설정해주세요.");
				System.out.print("업체명 : ");
				notice.setCompanyName(sc.nextLine()); // 업체명
				System.out.println();
				break;
			case 4:
				System.out.println("매장위치을 수정합니다. 매장위치을 다시 설정해주세요.");
				System.out.print("매장위치 : ");
				notice.setCompanyLocation(sc.nextLine()); // 매장위치
				System.out.println();
				break;
			case 5:
				System.out.println("시급을 수정합니다. 시급을 다시 설정해주세요.");
				System.out.print("시급 : ");
				notice.setHourlyWage(Integer.parseInt(sc.nextLine())); // 시급
				System.out.println();
				break;
			case 6:
				System.out.println("알바시간을 수정합니다. 알바시간을 다시 설정해주세요.");
				System.out.print("알바시간 : ");
				notice.setJobHours(Integer.parseInt(sc.nextLine())); // 알바시간
				System.out.println();
				break;
			case 7:
				System.out.println("공고상태을 수정합니다. 공고상태을 다시 설정해주세요.");
				System.out.print("공고상태 : ");
				notice.setNoticeStatus(); // 공고상태
				System.out.println();
				break;
			case 8:
				System.out.println("기간을 수정합니다. 기간을 다시 설정해주세요.");
				System.out.print("기간 : ");
				notice.setPeriod(Integer.parseInt(sc.nextLine())); // 기간
				System.out.println();
				break;
			case 9:
				System.out.println("시급, 알바시간 변화에 맞춰 실수령액을 변경합니다.");
				notice.setActualAmountReceived(notice.getHourlyWage() * (notice.getJobHours() * 4)
						- (int) (notice.getHourlyWage() * (notice.getJobHours() * 4) * 0.033)); // 실수령액 -> 시급, 알바시간 변화에 맞춰 리로드
				break;
			case 0:
				System.out.println("메뉴로 돌아갑니다.");
				break;
			default:
				System.out.println("정확한 항복을 기입해주세요.");
			}
		}
		noticeFileSave(noticeList);
		System.out.println();
	}

	// 공고 등록
	public void noticeRegistration(String presidentID) {
		System.out.println("아래의 정보를 입력해주세요.");
		System.out.println("모집인원 : ");
		int recruitmentNumber = Integer.parseInt(sc.nextLine());
		System.out.println("성별 : ");
		String gender = sc.nextLine();
		System.out.println("업체명 : ");
		String companyName = sc.nextLine();
		System.out.println("매장위치 : ");
		String companyLocation = sc.nextLine();
		System.out.println("시급 : ");
		int hourlyWage = Integer.parseInt(sc.nextLine());
		System.out.println("알바시간 : "); 
		int jobHours = Integer.parseInt(sc.nextLine()); 
		System.out.println("기간 : ");
		int period = Integer.parseInt(sc.nextLine());
		Notice notice = new Notice(presidentID, recruitmentNumber, gender, companyName, companyLocation, hourlyWage, jobHours, period);
		this.noticeList.put(notice.getNoticeNumber(), notice);
		noticeFileSave(noticeList);
		System.out.println("공고가 등록되었습니다.");
		System.out.println();
	}

	// [사장] 등록한 공고 목록 확인
	public void noticeListCheck(String presidentID) {
		System.out.println("등록한 공고를 확인합니다.");
		this.noticeList = noticeFileLoad();
		if (noticeList.size() == 0) {
			System.out.println("등록된 공고가 없습니다.");
		} else {
			System.out.print("등록한 공고 정보 : ");
			for (Entry<Integer, Notice> noticeEntry : noticeList.entrySet()) {
				if (noticeEntry.getValue().getPresidentID().equals(presidentID)) {
					System.out.println(noticeEntry.getValue());
				}
			}
		}
		System.out.println();
	}
	
	////////////////////////지원자 메소드 
	// 공고 지원
	public void noticeApplication(String applicantId, int noticeNumber) {
		System.out.print("지원할 공고 번호를 입력해주세요 : ");
		this.noticeList = noticeFileLoad();
		Notice notice = noticeList.get(noticeNumber);
		notice.setApplicant(applicantId);
		noticeFileSave(noticeList);
		System.out.println();
	}
	
	// 전체 공고 목록 확인
	public void allNoticeListCheck() {
		System.out.println("전체 공고 목록을 확인합니다.");
		this.noticeList = noticeFileLoad();
		for (Entry<Integer, Notice> noticeEntry : noticeList.entrySet()) {
			System.out.println(noticeEntry.getValue());
		}
		System.out.println();
	}
	
	// [지원자] 각 공고 정보 확인 (하나씩)
	public void noticeInfoCheck(int noticeNumber) {
		System.out.println("해당 공고의 정보를 확인합니다.");
		this.noticeList = noticeFileLoad();
		System.out.println(noticeList.get(noticeNumber));
		System.out.println();
	}
	
	// 지원 공고 목록 확인
	public void applicantNoticeListCheck(String applicantId) {
		System.out.println("지원한 공고의 목록을 확인합니다.");
		this.noticeList = noticeFileLoad();
//		for () { // 공고 번호 size 까지 돌아간다.
//			System.out.println(noticeList.get(applicantId));
//		}
		System.out.println();
	}
}
