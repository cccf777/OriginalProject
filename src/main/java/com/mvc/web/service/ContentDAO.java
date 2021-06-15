package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.web.connection.ConnectionProvider;
import com.mvc.web.connection.jdbcUtil;
import com.mvc.web.entity.content.Etclist;
import com.mvc.web.entity.content.Notice;
import com.mvc.web.entity.content.Picture;
import com.mysql.cj.exceptions.RSAException;

public class ContentDAO {

	private static ContentDAO instance = new ContentDAO();
	//메모미를 한번만 쓰도록 하는것
	
	public static ContentDAO getInstance() {
		return instance;
	}
	

//	public List<Notice> getList(){
//		return getList(1,"");
//	}
//	
//	public List<Notice> getList(int page){
//		return getList(page,"");
//	}
	
	
	
	
	/* 글 목록 */
	public Etclist getList(int page,String field, String query,String rank) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		int start = (page-1)*10;

		
		String sql1="		   select row.* , cnt.count"
				+ "			   from(select * "
				+ "					from tbl_board "
				+ "                 where (levenshtein(writeID, ?) <= 2)"
				+ "					and useFlag ='Y' "
				+ "					and boardid in (select boardID "
				+ "									from user_auth "
				+ "									where rankcd= ?) "
				+ "					order by regdate desc  limit 10 offset ?)row,"
				+ "					(select count(id) as count"
				+ "					 from tbl_board "
				+ "					 where (levenshtein(writeID, ?) <= 2)"
				+ "					 and useFlag ='Y' "
				+ "					 and boardid in (select boardID "
				+ "									 from user_auth "
				+ "									 where rankcd= ?))cnt";
		
		String sql2 =  "	    select row.* , cnt.count as count"
				+ "				from(select * "
				+ "					 from tbl_board "
				+ "					 where title like ? "
				+ "					 and useFlag ='Y' "
				+ "					 and boardid in (select boardID "
				+ "									 from user_auth "
				+ "									 where rankcd=? ) "
				+ "					 order by regdate desc  limit 10 offset ?)row,"
				+ "					 (select count(id) as count "
				+ "					  from tbl_board "
				+ "					  where title like ? "
				+ "					  and useFlag ='Y' "
				+ "					  and boardid in (select boardID "
				+ "									  from user_auth "
				+ "									  where rankcd=? ))cnt "; // 조회 sql
		
		List<Notice> list = new ArrayList<>(); // list 배열 생성
		
		try {
			 con = ConnectionProvider.getConnection();
			 //검색조건이 title 일 경우
			if(field.equals("title")) {
			    psmt = con.prepareStatement(sql2);
				psmt.setString(1, "%"+query+"%");
				psmt.setString(2, rank);
				psmt.setString(4, "%"+query+"%");
				psmt.setString(5, rank);
				psmt.setInt(3, start);
			//검색조건이 writeid 일 경우
			}else if(field.equals("writeid")){
			    psmt = con.prepareStatement(sql1);
				psmt.setString(1, query);
				psmt.setString(2, rank);
				psmt.setString(4, query);
				psmt.setString(5, rank);
				psmt.setInt(3, start);
			}
			 // 필드 값으로 driver 명칭 선언
			
			
			System.out.println(psmt);
			rs = psmt.executeQuery();

			
			
			while (rs.next()) {
				int id1 = rs.getInt("id");
				String boardid = rs.getString("boardid");
				String title = rs.getString("title");
				String writeid = rs.getString("writeid");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				count = rs.getInt("count");
				
				
				
				//조회 된 값을 입력하여 초기화하는 생성자 생성
				Notice ns = new Notice(id1, title, writeid, content, regdate, hit);
				list.add(ns);
				//list 에 조회된 값이 저장된 notice 객체 추가
							
			}
			con.close();
			psmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		
		Etclist el = new Etclist(count,list);
		return el;
	}

	/* 자세히 보기 */
	public Notice getDetail(int no) {
		Connection con=null;
		Notice ns=null;
		String sql = " SELECT tb.id, tb.title, tb.writeid, tb.content, tb.regdate, tb.hit "
				+ "      FROM tbl_board tb "
				+ "     WHERE tb.useFlag = 'Y' "
				//+ "		  AND ua.boardID = tb.boardid "
				+ "       AND tb.id = ?  ";
		
		/*
		 * " SELECT tb.id, bm.board_name, tb.title, tb.writeid, tb.content, tb.regdate, tb.hit "
		 * + "      FROM tbl_board tb, " + "	          board_master bm " +
		 * "     WHERE bm.board_id = tb.boardid " + "       AND tb.useFlag = 'Y' " +
		 * "       AND tb.id = ?  ";
		 보드네임의 용도를 몰라서 뺍니다
		 */
		
		
		
		try {
			
			con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
			ResultSet rs = psmt.executeQuery();

			if(rs.next()) {
				//String board= rs.getString("board_name");
				int id1 = rs.getInt("id");
				String title = rs.getString("title");
				String writeid = rs.getString("writeid");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				
				//조회 된 값을 입력하여 초기화하는 생성자 생성
				ns = new Notice(id1, title, writeid, content, regdate, hit);
						
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ns;
	}
	//조회된 글 카운트
	public int getCount(String field, String query,String rank) {
		Connection con=null;
		int count = 0;
		
		String sql = "select count(*) as count "
				+ " from tbl_board "
				+ " where useFlag='Y' "
				+ " and "+field+" like ? ";

		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1,"%"+query+"%");
			
			
			System.out.println(psmt);
			ResultSet rs = psmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt("count");
				System.out.println("Service :"+count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	//글쓰기
	public int regeditNotice(String writeid,String title,String content,int boardid) {
		Connection con = null;
		int count = 0;
		
		String sql = "insert into tbl_board(title,writeid,content,boardid) "
				+ " values(?,?,?,?) ";
		int result =0;

		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1,title);
			psmt.setString(2,writeid);
			psmt.setString(3,content);
			psmt.setInt(4, boardid);
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//글수정
	public int updateContent(int pid,String userID, String title, String content) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement psmt = null;
		
		
		String sql = "update tbl_board "
				+"		set title =? , "
				+"		content=? , "
				+"		writeid=? "
				+"		where id=?"; // 수정 sql
		
		
		
		try {
			
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, userID);
			psmt.setInt(4, pid);
			
			System.out.println(psmt);
			result = psmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			
		}
		
		return result;
	}

	public void uphit(int pid) {
		Connection con = null;
		PreparedStatement psmt = null;
		
		String sql = "update tbl_board set hit=hit+1 where id=?"; // 수정 sql
		
		try {
			
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, pid);
			
			System.out.println(psmt);
			psmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//사진리스트 가져오기
	public List<Picture> getPictureList() {
		//Connection con= null;
		String sql = " SELECT * FROM tbl_picture";
		List<Picture> list = new ArrayList<>();
		
		try {
			
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				int id1 = rs.getInt("id");
				String title = rs.getString("ptitle");
				String writeid = rs.getString("writeid");
				String path = rs.getString("path");
				Date regdate = rs.getTimestamp("regdate");
				
				//조회 된 값을 입력하여 초기화하는 생성자 생성
				Picture pt = new Picture(id1, title, writeid,path,regdate);
				list.add(pt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public void usergrade(String userID, String grade_) {
		int result = 0;	
		Connection con = null;
		PreparedStatement psmt = null;
		
		String sql = "update user "
				+"		set userRank=? "
				+"		where userID=?"; // 수정 sql
		
		try {
			
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, grade_);
			psmt.setString(2, userID);
			
			System.out.println(psmt);
			result = psmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			
		}
		
	}


	public List<Notice> getContentAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public Etclist getAllContent(int page, String query, String rank) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		int start = (page-1)*10;

		
		String sql="select row.*, cnt.count"
				+ "	from(select *"
				+ "		   from tbl_board"
				+ "	      where useFlag ='Y' "
				+ "            and match(title, writeid, content)  against(? in boolean mode)"
				+ "		    and boardid in (select boardID "
				+ "							 from user_auth "
				+ "							where rankcd= ?) "
				+ "		 order by regdate desc  limit 10 offset ?)row,"
				+ "         (select count(id) as count"
				+ "		    from tbl_board"
				+ "	       where useFlag ='Y' "
				+ "             and match(title, writeid, content)  against(? in boolean mode)"
				+ "		     and boardid in (select boardID "
				+ "                            from user_auth "
				+ "						      where rankcd= ?))cnt"; // 조회 sql
		
		List<Notice> list = new ArrayList<>(); // list 배열 생성
		
		try {
			 con = ConnectionProvider.getConnection();			
			 psmt = con.prepareStatement(sql);
			 psmt.setString(1, query);
			 psmt.setString(2, rank);
			 psmt.setString(4, query);
			 psmt.setString(5, rank);
			 psmt.setInt(3, start);
			
			
			 // 필드 값으로 driver 명칭 선언
			
			
			System.out.println(psmt);
			rs = psmt.executeQuery();

			
			
			while (rs.next()) {
				int id1 = rs.getInt("id");
				String boardid = rs.getString("boardid");
				String title = rs.getString("title");
				String writeid = rs.getString("writeid");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				count = rs.getInt("count");
				
				
				
				//조회 된 값을 입력하여 초기화하는 생성자 생성
				Notice ns = new Notice(id1, title, writeid, content, regdate, hit);
				list.add(ns);
				//list 에 조회된 값이 저장된 notice 객체 추가
							
			}
			con.close();
			psmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		
		Etclist el = new Etclist(count,list);
		return el;
	}	
}
