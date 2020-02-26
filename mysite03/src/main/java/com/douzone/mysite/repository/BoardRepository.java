package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BoardVo findContents(Long no) {
		sqlSession.update("board.update", no);
		
		return sqlSession.selectOne("board.findContents", no);
	}
	
	public List<BoardVo> findAll() {
		List<BoardVo> list = sqlSession.selectList("board.findAll");
		return list;
	}
	
	public List<BoardVo> findKwd(String kwd) {
		List<BoardVo> result = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = 
					"  select board.no, title, contents, hit, reg_date, g_no, o_no, depth, user_no, name" + 
					"    from board, user" + 
					"   where board.user_no = user.no" + 
					"     and title like ?" + 
					"order by g_no desc, o_no desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kwd + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Integer hit = rs.getInt(4);
				String regDate = rs.getString(5);
				Integer gNo = rs.getInt(6);
				Integer oNo = rs.getInt(7);
				Integer depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String name = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setName(name);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public void insertReply(BoardVo vo) {
		sqlSession.update("board.updateParent", vo);
		sqlSession.insert("board.insertReply", vo);
	}
	
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}
	
	public void modify(BoardVo vo) {
		sqlSession.update("board.modify", vo);
	}
	
	public void updateParent(BoardVo vo) {
		sqlSession.update("board.updateParent", vo);
	}
	
	public void delete(Long no) {
		sqlSession.delete("board.delete", no);
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.1.102:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}

	public BoardVo find(Long no) {
		BoardVo vo = sqlSession.selectOne("board.find", no);
		
		return vo;
	}
}