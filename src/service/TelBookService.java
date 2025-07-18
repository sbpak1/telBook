package service;

import db.DBConn;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelBookService implements CrudInterface {
    // Db 연결하기
    Connection conn = DBConn.getConnection();
    PreparedStatement psmt = null;
    String sql;

    @Override
    public int InsertData(TelDto dto) {
        System.out.println("[TelbookService,InsertData]");

        try {
            sql = "INSERT INTO telbook(name, age, address, phone) ";
            sql = sql + "VALUES(?, ?, ?, ?)";

            psmt = conn.prepareStatement(sql);
            // ? 각 자리를 Mapping 해 준다
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());

            // 쿼리 실행하기
            int result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }


        return 0;
    }

    @Override
    public int UpdateData(TelDto dto) {
        System.out.println("[Telbookservice,UpdateData]");
        int result = 0;
        try {
            sql = "UPDATE telbook SET ";
            sql = sql + "name = ?, ";
            sql = sql + "age = ?, ";
            sql = sql + "address = ?, ";
            sql = sql + "phone = ? ";
            sql = sql + "WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());
            psmt.setInt(5, dto.getId());
            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deletedData(int id) {
        System.out.println("[Telbookservice,DeleteData]");
        int result = 0;
        try {
            sql = "DELETE FROM telbook WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<TelDto> getListAll() {
        System.out.println("[Telbookservice,getListAll]");
        // DB에서 select 한 결과를 담을 리스트 선언
        List<TelDto> dtoList = new ArrayList<>();
        ResultSet rs = null;

        try {
            sql = "SELECT * FROM telbook";
            psmt = conn.prepareStatement(sql);
            // SQL 구문 실행
            rs = psmt.executeQuery();

            // ResultSet 에 들어온 레코드들을 하나씩 뽑아서
            // DtoList에 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));

                // 리스트에 담기
                dtoList.add(dto);
            }
            // 잘 들어왔는지 확인
            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dtoList;
    }

    @Override
    public TelDto fidById(int id) {
        System.out.println("Telbookservice,fidById");
        // id를 받아서 해당 레코드 읽어오는 작업
        ResultSet rs = null;
        try {
            sql = "SELECT id, name, age, address, phone " +
                   "FROM telbook WHERE id = ?";
            //System.out.println(sql);
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            // 레코드 셋의 자료를 while 로 순회하면서 읽는다
            rs = psmt.executeQuery();
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                System.out.println(dto.toString());
                return dto;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<TelDto> serchList(String keyword) {
        System.out.println("[Telbookservice.serchList]");
        ResultSet rs = null;
        List<TelDto> dtoList = new ArrayList<>();
        try {
            sql = "SELECT id, name, age, address, phone ";
            sql = sql + " FROM telbook ";
            sql = sql + " WHERE name like ? ";
            sql = sql + " ORDER BY name DESC";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + keyword + "%");
            rs = psmt.executeQuery();
            // 돌면서 List<TelDto> 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return dtoList;
    }
}
