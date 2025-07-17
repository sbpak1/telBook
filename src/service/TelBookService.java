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
        return 0;
    }

    @Override
    public int UpdateData(TelDto dto) {
        System.out.println("[Telbookservice,UpdateData]");
        return 0;
    }

    @Override
    public int deletedData(int id) {
        System.out.println("Telbookservice,DeleteData");
        return 0;
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
        return null;
    }

    @Override
    public List<TelDto> serchList(String keyword) {
        System.out.println("[Telbookservice.serchList]");
        return List.of();
    }
}
