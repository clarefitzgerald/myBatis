/* COSC 4800 ASSIGNMENT 6
 * Demonstrate connection between database and application.
 * @AUTHOR CLARE FITZGERALD
 * DATE: 12/6/2023
 * INSTRUCTOR 4800 DR. MADIRAJU
 * 
 */
package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.domain.Author;
import com.domain.Paper;
import com.domain.Participator;
import com.util.ConnectionFactory;

public class ParticipatorDAO {
	
	public ParticipatorDAO() {
		
	}
	//This method was not required but I used it to test out my ConferenceMapper. I ended up using it to test primary
	//key constraints in Case 2, even though the stack trace probably would've done that for me. Not sure if
	//there is any difference in efficiency.
	public List<Participator> getParticipatorList() {
		List<Participator> parList = new ArrayList<Participator>();
		SqlSession session = null;
		try {
			session = ConnectionFactory.getSqlSessionFactory().openSession();
			parList = session.selectList("com.mapper.ConferenceMapper.selectAll");
		} finally {
		  session.close();
		}
		//System.out.print("Checkpoint (DAO): " + parList);
		return parList;
	}
	//Connect to database and use specified ConferenceMapper SQL code
	public void insertParticipator(Participator p) {
		 
		SqlSession session = null;
		try {
			session = ConnectionFactory.getSqlSessionFactory().openSession();
			session.insert("com.mapper.ConferenceMapper.insertParticipator",p);
			session.commit();
		} finally {
		  session.close();
		}		
	}
	public void insertAuthor(Author a) {
	
		SqlSession session = null;
		try {
			session = ConnectionFactory.getSqlSessionFactory().openSession();
			session.insert("com.mapper.ConferenceMapper.insertAuthor",a);
			session.commit();
		} finally {
		  session.close();
		}		
	}
	public List<Paper> getGoodPapers() {
		List<Paper> paperList = new ArrayList<Paper>();
		SqlSession session = null;
		try {
			session = ConnectionFactory.getSqlSessionFactory().openSession();
			paperList = session.selectList("com.mapper.ConferenceMapper.selectGoodPapers");
		} finally {
		  session.close();
		}
		return paperList;
	}
	public List<Author> getAuthorList() {
		List<Author> authList = new ArrayList<Author>();
		SqlSession session = null;
		try {
			session = ConnectionFactory.getSqlSessionFactory().openSession();
			authList = session.selectList("com.mapper.ConferenceMapper.selectAllAuthors");
		} finally {
		  session.close();
		}
		//System.out.print("Checkpoint (DAO): " + parList);
		return authList;
	}

}
