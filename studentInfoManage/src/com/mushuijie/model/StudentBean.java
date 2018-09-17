package com.mushuijie.model;

import java.sql.Date;

/**
 * CREATE TABLE `studentinfotbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuNo` varchar(20) DEFAULT NULL,
  `stuName` varchar(10) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `stuDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gradeId` (`gradeId`),
  CONSTRAINT `gradeId` FOREIGN KEY (`gradeId`) REFERENCES `classtbl` (`id`)
)
 * @author py
 *
 */
public class StudentBean {
	private int stuId;
	private String stuNo;
	private String stuName;
	private String sex;
	private Date birthday;
	private int gradeId=-1;
	private String email;
	private String stuDesc;
	
	public StudentBean(){
		
	}
	
	public StudentBean(String stuNo, String stuName, String sex, Date birthday, int gradeId, String email,
			String stuDesc) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.sex = sex;
		this.birthday = birthday;
		this.gradeId = gradeId;
		this.email = email;
		this.stuDesc = stuDesc;
	}
	
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStuDesc() {
		return stuDesc;
	}
	public void setStuDesc(String stuDesc) {
		this.stuDesc = stuDesc;
	}
	
	
}
