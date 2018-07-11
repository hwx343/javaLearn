package com.mushuijie.model;
/**
 * CREATE TABLE `classtbl` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 *`className` varchar(255) DEFAULT NULL,
 * `classDesc` varchar(255) DEFAULT NULL,
 *PRIMARY KEY (`id`)
 *)
 * @author py
 *
 */
public class ClassBean {
	private int id;
	private String className;
	private String classDesc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	
	
	
}
