import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class NoticeManagement {
	Map<String, Notice> noticeList = new HashMap<String, Notice>();
	Scanner sc = new Scanner(System.in);

	// 파일에서 값 불러오기 (추가 -> 클래스 다이어그램 수정)
	public Map<String, Notice> noticeFileLoad() {
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
		} else {
			return null;
		}
	}

	// 수정된 파일 저장하기 (추가 -> 클래스 다이어그램 수정)
	public void noticeFileSave(Map<String, Notice> notice) {
		File file = new File("noticeFile.txt");
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
	}

	// 지원자 조회
	public void applicantInquiry(String noticeNumber) {
		try {
			System.out.println("지원자를 조회합니다.");
			Notice notice = noticeFileLoad().get(noticeNumber);
			if (notice.getApplicant() == null ) {
				System.out.println("공고 지원자가 없습니다.");
			} else {
				System.out.print("해당 공고 지원자 : ");
				for (String applicant : notice.getApplicant()) { // 지원자 출력
					System.out.println(applicant + " ");
				}
			}	
		} catch (Exception e){
			System.out.println("존재하지 않는 공고번호 입니다.");
			//e.printStackTrace();
		}	
		System.out.println();
	}

	// 공고 상태 변경
	public void noticeStatusChange(String noticeNumber) {
		this.noticeList = noticeFileLoad();
		if(noticeList.get(noticeNumber)==null) {
			System.out.println("해당 공고가 존재하지 않습니다.");
			return;
		}
		Notice notice = noticeList.get(noticeNumber);
		notice.setNoticeStatus();
		noticeFileSave(noticeList);
	}

	// 공고 삭제
	public void noticeDeletion(String noticeNumber) {
		this.noticeList = noticeFileLoad();
		if(noticeList.get(noticeNumber)==null) {
			System.out.println("해당 공고가 존재하지 않습니다.");
			return;
		}
		noticeList.remove(noticeNumber);
		noticeFileSave(noticeList);
		System.out.println("공고를 삭제했습니다.");
	}

	// 공고 수정 -> 입력되지 않을 때 상황 처리해야함 함수에서 처리하면 좋을 듯..?
	public void noticeModification(String noticeNumber) {
		this.noticeList = noticeFileLoad();
		if(noticeList.size()==0) {
			System.out.println("등록된 공고가 없습니다.");
			return;
		}else {
			try {	
				Notice notice = noticeList.get(noticeNumber);
				if(noticeList.get(noticeNumber)==null) {
					System.out.println("해당 공고가 존재하지 않습니다.");
					return;
				}
				System.out.println("현재 공고 : " + noticeList.get(noticeNumber));
				int num = -1;
				while (num != 0) {
					System.out.println("\n수정할 항목을 선택해주세요");
					System.out.println("1:모집인원 2:성별 3:업체명 4:매장위치 5:시급 6:알바시간 7:공고상태 8:기간 9:실수령액 0:메뉴로 돌아가기");
					System.out.print("항목 번호 : ");
					num = Integer.parseInt(sc.nextLine());
					switch (num) {
					case 1:
						System.out.println("모집인원을 수정합니다. 모집인원을 다시 설정해주세요.");
						System.out.print("모집인원 : ");
						notice.setRecruitmentNumber(Integer.parseInt(sc.nextLine())); // 모집인원
						System.out.println();
						System.out.print("모집인원을 수정했습니다.");
						break;
					case 2:
						System.out.println("성별을 수정합니다. 성별을 다시 설정해주세요.");
						System.out.print("성별 : ");
						notice.setGender(sc.nextLine()); // 성별
						System.out.println();
						System.out.print("성별을 수정했습니다.");
						break;
					case 3:
						System.out.println("업체명을 수정합니다. 업체명을 다시 설정해주세요.");
						System.out.print("업체명 : ");
						notice.setCompanyName(sc.nextLine()); // 업체명
						System.out.println();
						System.out.print("업체명을 수정했습니다.");
						break;
					case 4:
						System.out.println("매장위치을 수정합니다. 매장위치을 다시 설정해주세요.");
						System.out.print("매장위치 : ");
						notice.setCompanyLocation(sc.nextLine()); // 매장위치
						System.out.println();
						System.out.print("매장위치을 수정했습니다.");
						break;
					case 5:
						System.out.println("시급을 수정합니다. 시급을 다시 설정해주세요.");
						System.out.print("시급 : ");
						notice.setHourlyWage(Integer.parseInt(sc.nextLine())); // 시급
						System.out.println();
						System.out.print("시급을 수정했습니다.");
						break;
					case 6:
						System.out.println("알바시간을 수정합니다. 알바시간을 다시 설정해주세요.");
						System.out.print("알바시간 : ");
						notice.setJobHours(Integer.parseInt(sc.nextLine())); // 알바시간
						System.out.println();
						System.out.print("알바시간을 수정했습니다.");
						break;
					case 7:
						System.out.println("공고상태을 수정합니다. 공고상태을 다시 설정해주세요.");
						System.out.print("공고상태 : ");
						notice.setNoticeStatus(); // 공고상태
						System.out.println();
						System.out.print("공고상태을 수정했습니다.");
						break;
					case 8:
						System.out.println("기간을 수정합니다. 기간을 다시 설정해주세요.");
						System.out.print("기간 : ");
						notice.setPeriod(Integer.parseInt(sc.nextLine())); // 기간
						System.out.println();
						System.out.print("기간을 수정했습니다.");
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
				System.out.println("수정된 공고: \n" + noticeList.get(noticeNumber));
				System.out.println();
			} catch (Exception e) {
				System.out.println("공고수정에러: 잘못된 입력입니다. 메뉴로 돌아갑니다.");
				//e.printStackTrace();
			}
		}
	}

	// 공고 등록
	public void noticeRegistration(String presidentID) {
		try {
			if ( noticeList.size() > 0) {
				this.noticeList = noticeFileLoad();
			}
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
			System.out.println("알바시간(단위: 시간) : "); 
			int jobHours = Integer.parseInt(sc.nextLine()); 
			System.out.println("기간(단위: 개월) : ");
			int period = Integer.parseInt(sc.nextLine());
			Notice notice = new Notice(presidentID, recruitmentNumber, gender, companyName, companyLocation, hourlyWage, jobHours, period);
			this.noticeList.put(notice.getNoticeNumber(), notice);
			noticeFileSave(noticeList);
			System.out.println("공고가 등록되었습니다.");
			System.out.println();
		} catch (Exception e) {
			System.out.println("공고등록입력에러: 잘못된 입력입니다.");
			//e.printStackTrace();
		}
	}

	// [사장] 등록한 공고 목록 확인
	public void noticeListCheck(String presidentID) {
		try {
			System.out.println("등록한 공고를 확인합니다.");
			this.noticeList = noticeFileLoad();
			if (noticeList.size() == 0) {
				System.out.println("등록된 공고가 없습니다.");
			} else {
				System.out.println("등록한 공고 정보 : ");
				for (Entry<String, Notice> noticeEntry : noticeList.entrySet()) {
					if (noticeEntry.getValue().getPresidentID().equals(presidentID)) {
						System.out.println(noticeEntry.getValue());
					}
				}
			}
		} catch (Exception e) {
			System.out.println();
		}
		System.out.println();
	}
	
	////////////////////////지원자 메소드 
	// 공고 지원
	public void noticeApplication(String applicantId) {
		try {
			System.out.print("지원할 공고 번호를 입력해주세요 : ");
			String number = sc.nextLine();
			this.noticeList = noticeFileLoad();
			System.out.println("inEntry : " + inEntry(applicantId, number));
			System.out.println("inRecru : " + isRecruitment(number));
			if(inEntry(applicantId, number) && isRecruitment(number)) {
				Notice notice = noticeList.get(number);
				notice.setApplicant(applicantId);
				noticeFileSave(noticeList);
				System.out.println();
				System.out.println(number + " 공고에 지원을 완료하였습니다.");
			}else {
				System.out.println("이미 신청한 공고 또는 모집이 마감된 공고 또는 없는 공고 번호 입니다.");
			}
		} catch (Exception e) {
			System.out.println("공고번호입력에러: 공고번호 입력이 잘못되었습니다.");
			//e.printStackTrace();
		}
	}
	
	// 해당 공고가 모집 중인가??
	public boolean isRecruitment(String number) {
		if(noticeList.get(number)==null) return false;
		if(noticeList.get(number).getNoticeStatus().equals("모집중")) return true;		
		else return false;
	}
	
	// 해당 공고 안에 이미 지원자가 있는가??
	public boolean inEntry(String applicantId, String number) {
		Notice notice = noticeList.get(number);
		if(notice.getApplicant()==null) { return true;}
		for(int i=0; i<notice.getApplicant().size(); i++) {
			System.out.println("list"+i+" : " + notice.getApplicant().get(i));
			if(notice.getApplicant().get(i).equals(applicantId)) {
				return false;
			}
		}
		return true;
	}
	
	// 전체 공고 목록 확인
	public void allNoticeListCheck() {
		System.out.println("전체 공고 목록을 확인합니다.");
		this.noticeList = noticeFileLoad();
		for (Entry<String, Notice> noticeEntry : noticeList.entrySet()) {
			System.out.println(noticeEntry.getValue());
		}
		System.out.println();
	}
	
	// [지원자] 각 공고 정보 확인 (하나씩)
	public void noticeInfoCheck(String noticeNumber) {
		System.out.println("해당 공고의 정보를 확인합니다.");
		this.noticeList = noticeFileLoad();
		System.out.println(noticeList.get(noticeNumber));
		System.out.println();
	}
	
	// 지원 공고 목록 확인 -> 회원 id 에서 리스트 값 가져와서 해야한다. 
	public void applicantNoticeListCheck(String applicantId) {
		System.out.println("지원한 공고의 목록을 확인합니다.");
		System.out.println();
		this.noticeList = noticeFileLoad();
		for(Map.Entry<String, Notice> noticeEntry : noticeList.entrySet()) {
			if(noticeEntry.getValue().getApplicant()==null) {
				continue;
			}
			for(int i=0; i<noticeEntry.getValue().getApplicant().size(); i++) { // 공고마다 지원자 수만큼
				if((noticeEntry.getValue().getApplicant().get(i).equals(applicantId))) {
					System.out.println(noticeEntry.getValue() + " ");
				}
			}
		}
		System.out.println();
	}
}