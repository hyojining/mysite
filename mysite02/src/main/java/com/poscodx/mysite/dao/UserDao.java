package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poscodx.mysite.vo.UserVo;

public class UserDao {
	
	public void insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 객체 생성
			String sql = " insert" +
						 "   into user" +
						 " values(null, ?, ?, password(?), ?, current_date())";
			pstmt = conn.prepareStatement(sql);
			
			// 4. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			// 5. SQL 실행
			pstmt.executeUpdate();
			
			// 6. 결과 처리
	
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 7. 자원정리
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.176:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
					
		return conn;
	}
}
