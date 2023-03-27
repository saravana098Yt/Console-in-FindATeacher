package users;

import java.util.Date;

import BusinessLogic.mainClass;
import enums.Gender;

public class Student {
  private String Name;
  private int uniqueId;
  private int age;
  private Date dob;
  private Gender gender;
  private String password;
  private Teacher assignedToteacher=null;
  
  public Student(String Name,int age,int uniqueId,Gender gender,Date joinDate){
	  this.Name=Name;
	  this.uniqueId=uniqueId;
	  this.age=age;
	  this.gender=gender;
	  this.dob=joinDate;
	  this.password=Name.replaceAll(" ","")+"@"+uniqueId;
  }
  
public Date getJoinDate() {
	return dob;
}

public void setJoinDate(Date dob) {
	this.dob = dob;
}

public Gender getGender() {
		return gender;
	}
public void setGender(Gender gender) {
		this.gender = gender;
	}
public String getName() {
	return Name;
}
public void setStudentName(String Name) {
	this.Name = Name;
}
public int getUnicId() {
	return uniqueId;
}
public void setUnicId(int uniqueId) {
	this.uniqueId = uniqueId;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Teacher getTeacher() {
	return assignedToteacher;
}
public void setTeacher(Teacher teacher) {
	this.assignedToteacher = teacher;
}
public String toString() {
	System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
	if(this.assignedToteacher == null) {
		return "\nStudentName : "+this.getName()+"   Student age : "+this.getAge()+"  Student id : "+this.getUnicId()+"  Student Gender : "+
				this.getGender()+"  Student DOB : "+mainClass.pdf.format(this.getJoinDate())+"  Your Teacher : "+this.assignedToteacher+"\n";
	}
	else {
	return "\nStudentName : "+this.getName()+"   Student age : "+this.getAge()+"  Student id : "+this.getUnicId()+"  Student Gender : "+
this.getGender()+"  Student DOB : "+mainClass.pdf.format(this.getJoinDate())+"  Your Teacher : "+this.assignedToteacher.getUnicId()+"\n";
	}
}
}
