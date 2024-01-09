/* COSC 4800 ASSIGNMENT 6
 * Demonstrate connection between database and application.
 * @AUTHOR CLARE FITZGERALD
 * DATE: 12/6/2023
 * INSTRUCTOR 4800 DR. MADIRAJU
 * These are all nearly identical to each other and the ones in the EmployeeService. They receive the output of the ParticipatorDAO and 
 * are also the ones called by the main method in ConferenceDb.java
 */
package com.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dao.ParticipatorDAO;
import com.domain.Author;
import com.domain.Paper;
import com.domain.Participator;
import com.util.ConnectionFactory;

public class ParticipatorService {
	
	public ParticipatorService() {
		
	}
	public List<Participator> getParticipatorList() {
		List<Participator> parList = new ArrayList<Participator>();
		ParticipatorDAO parDAO = new ParticipatorDAO();
		parList = parDAO.getParticipatorList();
		//System.out.print("Checkpoint (Service): " + parList);
		return parList;
	}
	public void insertParticipator(Participator p) {
		ParticipatorDAO parDAO = new ParticipatorDAO();
		parDAO.insertParticipator(p);
			
	}
	public void insertAuthor(Author a) {
		ParticipatorDAO parDAO = new ParticipatorDAO();
		parDAO.insertAuthor(a);
	}
	public List<Paper> getGoodPapers() {
		List<Paper> paperList = new ArrayList<Paper>();
		ParticipatorDAO parDAO = new ParticipatorDAO();
		paperList = parDAO.getGoodPapers();
		return paperList;
	}
	public List<Author> getAuthorList() {
		List<Author> authList = new ArrayList<Author>();
		ParticipatorDAO parDAO = new ParticipatorDAO();
		authList = parDAO.getAuthorList();
		//System.out.print("Checkpoint (Service): " + parList);
		return authList;
	}

	

}
