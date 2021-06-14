package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.web.connection.ConnectionProvider;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.entity.user.Login;
import com.mvc.web.entity.user.Register;
import com.mvc.web.entity.user.User;

public class UserDAO {

	private static UserDAO instance = new UserDAO();
	// 메모미를 한번만 쓰도록 하는것

	public static UserDAO getInstance() {
		return instance;
	}

	public User LoginCheck(Login lg) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		User ur = new User();

//		System.out.println("pid : " + lg.getId());
//		System.out.println("ppass : " + lg.getPassword());
		

		String sql = "select userID,userPass,userName,userEmail,userRank " 
					+ "	from user " 
					+ " where flag='Y' "
				    + " and userID = ? " 
					+ " and userPass = SHA2(?,256) ";
		try {

			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, lg.getId());// user객체 내 id값을 입력
			psmt.setString(2, lg.getPassword());
			System.out.println(psmt);
			rs = psmt.executeQuery();

			if (rs.next()) {// 조회된 값이 있다면
					ur.setId(lg.getId());
					ur.setName(rs.getString("userName"));
					ur.setRank(rs.getString("userRank"));
					ur.setEmail(rs.getString("userEmail"));
					ur.setNumber(1);

				} else {// 조회된 값이 없을때 id 혹은 pass 불일치 혹은 없음
					ur.setNumber(0);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("넘버값은?"+ur.getNumber());
		return ur;
	}

	public int idCheck(String pid) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int result = 0;

		String sql = "select userID from user where userID=?";
		try {

			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pid);
			rs = psmt.executeQuery();

			if (rs.next()) {

				result = 1;
			} else {

				result = 0;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;

	}

	// 사용자 추가
	public int signUp(Register rt) {
		Connection con = null;
		PreparedStatement psmt = null;

		int result = 0;
		String sql = "insert into user(userID,userPass,userName,userEmail) " + " values(?,SHA2(?,256),?,?)";

		try {

			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, rt.getId());
			psmt.setString(2, rt.getPassword());
			psmt.setString(3, rt.getName());
			psmt.setString(4, rt.getEmail());
			System.out.println("psmt aaaaaa:" + psmt);
			result = psmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}
}
