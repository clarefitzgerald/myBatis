/* COSC 4800 ASSIGNMENT 6
 * Demonstrate connection between database and application.
 * @AUTHOR CLARE FITZGERALD
 * DATE: 12/6/2023
 * INSTRUCTOR 4800 DR. MADIRAJU
 * NO CHANGES MADE TO THIS FILE
 */
package com.util;


import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectionFactory {
	private static SqlSessionFactory factory;

	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("database-config.xml");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}