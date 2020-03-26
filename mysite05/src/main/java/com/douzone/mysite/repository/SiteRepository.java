package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public SiteVo update(SiteVo siteVo) {
		System.out.println(siteVo);
		sqlSession.update("site.update", siteVo);
		
		return sqlSession.selectOne("site.find");
	}
	
	public SiteVo find() {
		return sqlSession.selectOne("site.find");
	}
}