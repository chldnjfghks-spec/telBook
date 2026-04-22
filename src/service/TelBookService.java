package service;

import dto.TelDto;
import repository.TelBookReporitory;

import java.util.List;

public class TelBookService {
    private final TelBookReporitory reporitory;

    public TelBookService(TelBookReporitory reporitory){
        this.reporitory = reporitory;
    }
    public void insert(String name, int age, String address, String phone) {
        //받은 자료로 TelDto를 생성
        TelDto dto = new TelDto(0L, name, age, address, phone);
        //repo 호출
        int result = reporitory.insertData(dto);
        if (result > 0){
            System.out.println("정상적으로 저장되었습니다.");
        }
    }

    public List<TelDto> getListAll() {
        return reporitory.findAll();
    }

    public List<TelDto> getListOne(int id) {
        return reporitory.findById(id);
    }

    public int delete(int id) {
        return reporitory.deleteById(id);
    }

    public void update(TelDto updateData) {
        System.out.println(updateData);
        reporitory.update(updateData);
    }
}
