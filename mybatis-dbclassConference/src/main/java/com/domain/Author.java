/* COSC 4800 ASSIGNMENT 6
 * Autogenerated constructor and setters/getters
 * @AUTHOR CLARE FITZGERALD
 * DATE: 12/6/2023
 * INSTRUCTOR 4800 DR. MADIRAJU
 * 
 */
package com.domain;

public class Author {
	private String email;
	private String fname;
	private String lname;
	
	public Author(String email) {
		super();
		this.email = email;
	}
	//Polymorphic constructor to Case 3
	public Author(String email, String fname, String lname) {
		super();
		this.email = email;
		this.fname = fname;
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Override
	public String toString() {
		return "Author [email=" + email + "]";
	}
	
}
