package repository;

import db.DBConn;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelBookReporitory {
    private final Connection conn;

    public TelBookReporitory(Connection conn) {
        this.conn = conn;
    }

    public int insertData(TelDto dto) {
        //1.DB연걸

        PreparedStatement psmt = null;
        //2.쿼리 생성
        int result = 0;
        try{
            String sql = "INSERT INTO telbook (name, age, address, phone) VALUES(?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2,dto.getAge());
            psmt.setString(3,dto.getAddress());
            psmt.setString(4,dto.getTelNumber());
            result = psmt.executeUpdate();
            psmt.close();
        }catch (Exception e){
            System.out.println("INSERT 오류 :"+ e.getMessage());
        }
        return result;
    }
    public List<TelDto>findAll(){
        List<TelDto> dtoList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM telbook ORDER BY name";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while(rs.next()){
                //읽어온 레코드를 담을 빈 DTO를 생성
                TelDto dto =  new TelDto( );
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));
                //System.out.println(dto);
                //만들어진 dto를 리스트에 담는다
                dtoList.add(dto);
            }
        }catch (Exception e){
            System.out.println("Find All Error :"+e.getMessage());
        }
        return dtoList;
    }

}
