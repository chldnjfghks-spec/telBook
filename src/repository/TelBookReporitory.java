package repository;

import db.DBConn;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try {
            String sql = "INSERT INTO telbook (name, age, address, phone) VALUES(?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getTelNumber());
            result = psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println("INSERT 오류 :" + e.getMessage());
        }
        return result;
    }

    public List<TelDto> findAll() {
        List<TelDto> dtoList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM telbook ORDER BY name";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                //읽어온 레코드를 담을 빈 DTO를 생성
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));
                //System.out.println(dto);
                //만들어진 dto를 리스트에 담는다
                dtoList.add(dto);

            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Find All Error :" + e.getMessage());
        }
        //psmt 닫아주는 작업

        return dtoList;
    }

    public List<TelDto> findById(int id) {
        List<TelDto> dtoList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM telbood WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("FindById Error:" + e.getMessage());
        }
        return dtoList;
    }

    public int deleteById(int id) {
        
            PreparedStatement psmt = null;
            int result = 0;

            try {
                String sql = "DELETE FROM telbook WHERE id = ?";
                psmt = conn.prepareStatement(sql);
                psmt.setInt(1, id);

                result = psmt.executeUpdate(); // ⭐ 이거 반드시 필요

            } catch (Exception e) {
                System.out.println("deleteById Error:" + e.getMessage());
            } finally {
                try { if(psmt != null) psmt.close(); } catch(Exception e){}
            }

            return result;
        }

    public void update(TelDto updateData) {
        PreparedStatement psmt = null;
        //2.쿼리 생성
        int result = 0;
        try {
            String sql = "update telbook ";
            sql =sql + "set name = ?, ";
            sql = sql + "age = ?, ";
            sql = sql + "address = ?, ";
            sql = sql + "phone =? ";
            sql = sql + "where id = ? ";

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, updateData.getName());
            psmt.setInt(2, updateData.getAge());
            psmt.setString(3, updateData.getAddress());
            psmt.setString(4, updateData.getTelNumber());
            psmt.setLong(5,updateData.getId());
            psmt.executeQuery();
            psmt.close();
        } catch (Exception e) {
            System.out.println("UPDATE 오류 :" + e.getMessage());
        }

    }

    public List<TelDto> search(int choice, String keyword) {
        List<TelDto> dtoList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(choice == 1){
                // 이름 검색
                sql = "SELECT * FROM telbook WHERE name LIKE ?";
            } else {
                // 주소 검색
                sql = "SELECT * FROM telbook WHERE address LIKE ?";
            }

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + keyword + "%");
            rs = psmt.executeQuery();
            // 리스트에 추가
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Search Error : " + e.getMessage());
        }
        return dtoList;
    }
}
