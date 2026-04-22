package view;

import dto.TelDto;
import exceapion.InputValidation;
import exceapion.MyException;
import service.TelBookService;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final Scanner scanner;
    private final TelBookService service;
    
    public UserView(Scanner scanner, TelBookService service){
        this.scanner = scanner;
        this.service = service;
    }

    public void insert() throws MyException{
        // 검증 클래스 생성
        InputValidation validation = new InputValidation();
        //입력자료 저장을 위한 변수 선언
        String name = "";
        int age = 0;
        String phone = "";
        String address = "";


        //이름, 나이, 전화번호, 주소
        System.out.println("==전화번호 등록==");
        //이름 옳바른 값이 들어올 때 까지 반복
        boolean nameOK = false;
        do {
            try{
                //이름 : 무조건 한글만 중간공백 없이
                System.out.println("이름 :");
                name = scanner.next();
                validation.nameCheck(name);
                nameOK = true;
            }catch (MyException e){
                System.out.println(e.getMessage());
            }
        }while(! nameOK);


        //나이 :0세~120세 사이값
        boolean ageOK = false;
        do{
            try{
                System.out.println("나이 :");
                age = scanner.nextInt();
                validation.ageCheck(age);
                ageOK = true;
            }catch (MyException e){
                System.out.println(e.getMessage());
            }
        }while (!ageOK);

        //주소
        System.out.println("주소 :");
        address = scanner.next();
        //전화번호(010-XXXX-XXXX
        boolean phoneOK = false;
        do{
            try {
                System.out.println("전화번호 :");
                phone = scanner.next();
                validation.phoneCheck(phone);
                phoneOK = true;
            }catch (MyException e){
                System.out.println(e.getMessage());
            }
        }while (!phoneOK);

        service.insert(name, age, address, phone);
    }

    public void update() {
    }

    public void delete() {
    }

    public void searchAll() {
        List<TelDto> list = new ArrayList<>();
        list = service.getListAll();
        //list가 비어있는지 확인
        if (list.isEmpty()){
            System.out.println("주소록이 비어있습니다.");
            return;
        }
        //리스트 출력
        for (TelDto dto : list){
            System.out.println(dto);
        }
        list.forEach(x -> System.out.println(x));
    }

    public void searchOne() {
    }
}
