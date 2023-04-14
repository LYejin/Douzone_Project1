import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

//1. List에 값이 없을 경우 예외발생 - 값이 없을때는 읽지 않았으면 좋을 것 같다
//2. 로그인 이후 화면	

public class MemberManagement {
	private HashMap<String, President> presentsList = new HashMap<>();
	private HashMap<String, Applicant> applicantsList = new HashMap<>();
	private boolean indentifyingApplicants; // 사장 지원자 확인 - 아직까지 사용되고 있지 않음

	Scanner sc = new Scanner(System.in);
	// 지원자메뉴
	// 사장메뉴

	// 기존 사장 회원의 목록을 그대로 유지하기 위한 메소드
	public HashMap<String, President> presentsSetting() {
		File presidentFile = new File("PresidentData.txt");
		if (presidentFile.length() > 0) {
			try {
				FileInputStream fis = new FileInputStream(presidentFile);
				ObjectInputStream ois = new ObjectInputStream(fis);

				presentsList = (HashMap) ois.readObject();

				ois.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유저정보를 불러오는데 실패했습니다.");
			}
		}
		return presentsList;
	}

	// 사장 개인정보 변경
	public void presidentInfomationModifocation(String presidentID) {
		HashMap<String, President> presentsList = presentsSetting();
		President president = presentsList.get(presidentID);
		int num = -1;
		while (num != 0) { // ent(memberID, memberPW, userName));
			System.out.println("개인정보를 수정할 항목을 선택해주세요");
			System.out.println("1:비밀번호 2:이름");
			num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case 1:
				System.out.println("비밀번호는 영문자를 포함한 숫자의 조합으로 8~12자리로 입력해주세요");
				System.out.println("메뉴로 이동을 원하시면 '메뉴'를 입력해주세요");
				System.out.printf("비밀번호 : ");
				String memberPW = sc.nextLine();
				boolean check;
				if (memberPW.equals("메뉴")) {
					showMenu();
					break;
				} else {
					check = Pattern.matches("^[a-zA-Z0-9]{7,12}([a-zA-Z]+)", memberPW);
				}
				president.setmemberPW(memberPW);
				break;
			case 2:
				System.out.println("사용자의 이름을 정확히 입력해주세요");
				System.out.println("메뉴로 이동을 원하시면 '메뉴'를 입력해주세요");
				System.out.printf("사용자 이름 : ");
				String userName = sc.nextLine();
				if (userName.equals("메뉴")) {
					showMenu();
					break;
				} else {
					check = Pattern.matches("^[가-힣]{2,6}$", userName);
				}
				president.setuserName(userName);
				break;
			}
			presidentFileSave();
			System.out.println();
		}
	}
	
	// 사장 파일 저장
	public void presidentFileSave() {
		String presidentFile = "PresidentData.txt";
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream(presidentFile);
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(bos);

			out.writeObject(presentsList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				bos.close();
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// 지원자 개인정보 변경
	public void applicantInfomationModifocation(String applicantId) {
		HashMap<String, Applicant> applicantsList = applicantsSetting();
		Applicant applicant = applicantsList.get(applicantId);
		int num = -1;
		while (num != 0) {
			System.out.println("개인정보를 수정할 항목을 선택해주세요");
			System.out.println("1:비밀번호 2:이름");
			num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case 1:
				System.out.println("비밀번호는 영문자를 포함한 숫자의 조합으로 8~12자리로 입력해주세요");
				System.out.println("메뉴로 이동을 원하시면 '메뉴'를 입력해주세요");
				System.out.printf("비밀번호 : ");
				String memberPW = sc.nextLine();
				boolean check;
				if (memberPW.equals("메뉴")) {
					showMenu();
					break;
				} else {
					check = Pattern.matches("^[a-zA-Z0-9]{7,12}([a-zA-Z]+)", memberPW);
				}
				applicant.setmemberPW(memberPW);
				break;
			case 2:
				System.out.println("사용자의 이름을 정확히 입력해주세요");
				System.out.println("메뉴로 이동을 원하시면 '메뉴'를 입력해주세요");
				System.out.printf("사용자 이름 : ");
				String userName = sc.nextLine();
				if (userName.equals("메뉴")) {
					showMenu();
					break;
				} else {
					check = Pattern.matches("^[가-힣]{2,6}$", userName);
				}
				applicant.setuserName(userName);
				break;
			}
			applicantFileSave();
			System.out.println();
		}
	}
	
	// 지원자 파일 저장
	public void applicantFileSave() {
		String applicantFile = "ApplicantData.txt";
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream(applicantFile);
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(bos);

			out.writeObject(applicantsList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				bos.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 지원자 파일 불러오기
	public HashMap<String,Applicant> applicantsSetting() {
		File applicantFile = new File("ApplicantData.txt");// null 일 때 예외발생
		if (applicantFile.length() > 0) {
			try {
				FileInputStream fis = new FileInputStream(applicantFile);
				ObjectInputStream oos = new ObjectInputStream(fis);

				applicantsList = (HashMap) oos.readObject();

				oos.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유저정보를 불러오는데 실패했습니다.");
			}
			return applicantsList;
		} else {
			return null;
		}
	}

	// 회원가입과 로그인을 선택하는 메뉴화면 출력
	public void showMenu() {
		presentsSetting(); // 사장 목록 불러오기
		applicantsSetting();// 지원자 목록 불러오기

		while (true) {
			System.out.println("**********************************");
			System.out.println("*  반갑습니다. 원하시는 목록을 선택해주세요");
			System.out.println("**********************************");
			System.out.println("* 1. 로그인    2. 회원가입      0.종료");
			System.out.println("**********************************");
			System.out.printf("번호를 입력해주세요 : ");
			
			try {
				Scanner sc = new Scanner(System.in);

				int selectNum = Integer.parseInt(sc.nextLine());
				switch (selectNum) {
				case 1:
					Login();
					break;
				case 2:
					signUp();
					break;
				case 0:
					System.out.println("시스템을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("잘못된 입력입니다. 메뉴로 이동합니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("잘못된 입력입니다. 메뉴로 이동합니다.");
				showMenu();
			}
		}
	}

	// 회원가입
	public void signUp() {
		boolean check;
		String memberID;
		String memberPW;
		String userName;

		System.out.println("**회원가입 입니다.**");
		// memberID(아이디) 값 입력
		do {
			System.out.println("아이디는 영어대소문자와 숫자를 포함한 5~14자리로 입력해주세요");
			System.out.println("메뉴로 이동을 원하시면 '메뉴'를 입력해주세요");
			System.out.printf("아이디 : ");
			memberID = sc.nextLine();
			if (memberID.equals("메뉴")) { // 이미 생성된 아이디와 비교
				showMenu();
				break;
			} else if (presentsList.containsKey(memberID) || applicantsList.containsKey(memberID)) { // 아이디 중복검사
				System.out.printf("이미 존재하는 아이디 입니다. 다른 아이디를 입력해 주시기 바랍니다.\n\n");
				check = false;
			} else {
				check = Pattern.matches("^[a-zA-z0-9]{5,15}$", memberID);
			}
		} while (!check);

		// memberPW(비밀번호) 값 입력
		do {
			System.out.println("비밀번호는 영문자를 포함한 숫자의 조합으로 8~12자리로 입력해주세요");
			System.out.println("메뉴로 이동을 원하시면 '메뉴'를 입력해주세요");
			System.out.printf("비밀번호 : ");
			memberPW = sc.nextLine();
			if (memberPW.equals("메뉴")) {
				showMenu();
				break;
			} else {
				check = Pattern.matches("^[a-zA-Z0-9]{7,12}([a-zA-Z]+)", memberPW);
			}
		} while (!check);

		// name(사용자이름) 값 입력
		do {
			System.out.println("사용자의 이름을 정확히 입력해주세요");
			System.out.println("메뉴로 이동을 원하시면 '메뉴'를 입력해주세요");
			System.out.printf("사용자 이름 : ");
			userName = sc.nextLine();
			if (userName.equals("메뉴")) {
				showMenu();
				break;
			} else {
				check = Pattern.matches("^[가-힣]{2,6}$", userName);
			}
		} while (!check);

		// 사장 지원자 판단
		System.out.println("원하는 회원의 종류를 선택해주세요");
		System.out.println("1. 사장 회원   2. 지원자 회원   0.메인메뉴 이동");

		int selectNum = Integer.parseInt(sc.nextLine());

		switch (selectNum) {
		case 1:
			this.presentsList.put(memberID, new President(memberID, memberPW, userName));
			presidentFileSave();
			System.out.println("축하합니다 회원가입이 완료되었습니다.");
			showMenu();
			break;
		case 2:
			applicantsList.put(memberID, new Applicant(memberID, memberPW, userName));
			applicantFileSave();
			System.out.println("축하합니다 회원가입이 완료되었습니다.");
			showMenu();
			break;
		case 0:
			showMenu();
			break;
		}
	}

	public void Login() {
		System.out.print("아이디 입력 :");
		String inputId = sc.nextLine();
		System.out.print("비밀번호 입력 : ");
		String inputPwd = sc.nextLine();

		// 사장 로그인
		if (presentsList.containsKey(inputId)) {
			if (!presentsList.get(inputId).getmemberPW().equals(inputPwd)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			} else {
				System.out.println("로그인");
				President p = new President(inputId, inputPwd, presentsList.get(inputId).getuserName());
				p.setLoginStatus(true);
				indentifyingApplicants = false;
				PresidentMenu presidentmenu = new PresidentMenu(inputId);
				presidentmenu.presidentMenu();
			}
		} // 지원자 로그인
		else if (applicantsList.containsKey(inputId)) {
			if (!applicantsList.get(inputId).getmemberPW().equals(inputPwd)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			} else {
				System.out.println("로그인");
				indentifyingApplicants = true;
				President p = new President(inputId, inputPwd, applicantsList.get(inputId).getuserName());
				p.setLoginStatus(true);				
				ApplicantMenu applicantmenu = new ApplicantMenu(inputId);
				applicantmenu.applicantMenu();
			}
		} else {
			System.out.println("존재하지 않는 아이디 입니다.");
		}
	}
}
