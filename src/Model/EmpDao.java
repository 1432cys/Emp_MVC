package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.Singletone;


public class EmpDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	/*
	 create table EMPQUIZ
	 id
	 pwd
	 name;
	 age; 
	 Gender;
	 EMAIL
	*/
	
	private static final String SQL_SELECT_ALL = "SELECT ID, PWD, NAME, AGE, GENDER, EMAIL, IP FROM KOREAMEMBER"; //전체조회
	private static final String SQL_SELECT_MEMBERS_BY_NAME = "SELECT ID, PWD, NAME, AGE, GENDER, EMAIL, IP FROM KOREAMEMBER WHERE NAME LIKE ?"; //Name 조회
	public static final String SQL_SELECT_MEMBER_BY_ID = "SELECT ID, PWD, NAME, AGE, GENDER, EMAIL, IP FROM KOREAMEMBER WHERE ID=?"; //Id 검색
	private static final String SQL_INSERT_MEMBER = "INSERT INTO KOREAMEMBER(ID, PWD, NAME, AGE, GENDER, EMAIL, IP) VALUES(?,?,?,?,?,?,?)"; //삽입
	private static final String SQL_UPDATE_MEMBER = "UPDATE KOREAMEMBER SET NAME=?, AGE=?, GENDER=?, EMAIL=? WHERE ID=?"; //수정
	private static final String SQL_DELETE_MEMBER = "DELETE FROM KOREAMEMBER WHERE ID=?"; //삭제
	
	public EmpDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 실패:" + e);
			return;
		}
	}
	
	//전체조회
		public List<EmpDto> getEmpList() {
			List<EmpDto> memberlist =  new ArrayList<EmpDto>();
			try {
				pstmt = conn.prepareStatement(SQL_SELECT_ALL);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EmpDto km = new EmpDto();
					km.setId(rs.getString("ID"));
					km.setPwd(rs.getString("PWD"));
					km.setName(rs.getString("NAME"));
					km.setAge(rs.getInt("AGE"));
					km.setGender(rs.getString("GENDER"));
					km.setEmail(rs.getString("EMAIL"));
					memberlist.add(km);
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} finally {
				Singletone.close(rs);
				Singletone.close(pstmt);
			}
			return memberlist;
		}
		
		// LIKE 이름으로 멤버리스트 조회
		public List<EmpDto> getEmpByName(String name) {
			System.out.println("getKoreaMemberListByName");
			List<EmpDto> memberlist =  new ArrayList<EmpDto>();
			try {
				pstmt = conn.prepareStatement(SQL_SELECT_MEMBERS_BY_NAME);
				pstmt.setString(1, "%" + name + "%");
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					EmpDto km = new EmpDto();
					km.setId(rs.getString("ID"));
					km.setPwd(rs.getString("PWD"));
					km.setName(rs.getString("NAME"));
					km.setAge(rs.getInt("AGE"));
					km.setGender(rs.getString("GENDER"));
					km.setEmail(rs.getString("EMAIL"));
					memberlist.add(km);
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} finally {
				Singletone.close(rs);
				Singletone.close(pstmt);
			}
			return memberlist;
		}
		
		// id로 멤버 조회
		public EmpDto getEmpByid(String _id) {
			EmpDto member = null;
	        try {
	        	pstmt = conn.prepareStatement(SQL_SELECT_MEMBER_BY_ID);
	        	pstmt.setString(1, _id);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                String id = rs.getString("ID");
	                String pwd = rs.getString("PWD");
	                String name = rs.getString("NAME");
	                int age = rs.getInt("AGE");
	                String gender = rs.getString("GENDER");
	                String email = rs.getString("EMAIL");
	                String ip = rs.getString("IP");
	                member = new EmpDto(id, pwd, name, age, gender, email);
	            }
	        } catch (SQLException e) {
	        	System.out.println(e.getMessage());
	        } finally {
				Singletone.close(rs);
				Singletone.close(pstmt);
			}
	        return member;
	    }
		
		
		// 멤버추가
		public int insertEmp(EmpDto km) {
			int resultRow = 0;
			try {
				pstmt = conn.prepareStatement(SQL_INSERT_MEMBER);
				pstmt.setString(1, km.getId());
				pstmt.setString(2, km.getPwd());
				pstmt.setString(3, km.getName());
				pstmt.setInt(4, km.getAge());
				pstmt.setString(5, km.getGender());
				pstmt.setString(6, km.getEmail());
				
				resultRow = pstmt.executeUpdate();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} finally {
				Singletone.close(pstmt);
			}
			
			return resultRow;
		}
		
		// 멤버수정
		public int updateEmp(EmpDto km) {
			int resultRow = 0;
			try {
				pstmt = conn.prepareStatement(SQL_UPDATE_MEMBER);
				pstmt.setString(1, km.getName());
				pstmt.setInt(2, km.getAge());
				pstmt.setString(3, km.getGender());
				pstmt.setString(4, km.getEmail());
				pstmt.setString(5, km.getId());
				System.out.println("in");
				resultRow = pstmt.executeUpdate();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} finally {
				Singletone.close(pstmt);
			}
			return resultRow;
		}
		
		// 멤버삭제
		public int deleteEmp(String id) {
			int resultRow = 0;
			try {
				pstmt = conn.prepareStatement(SQL_DELETE_MEMBER);
				pstmt.setString(1, id);
				
				resultRow = pstmt.executeUpdate();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} finally {
				Singletone.close(pstmt);
			}
			return resultRow;
		}
	
	
	
	
}
