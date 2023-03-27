package users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import enums.Gender;

public class Teacher extends Student{
  private int yearOfExp;
  private List<String> languages;
  public List<Student> students=new ArrayList<Student>();
  public Teacher(String Name,int age,int uniqueID,Gender gender,Date dob,int yearOfExp,List<String>languages) {
	  super(Name,age,uniqueID,gender,dob);
	  this.languages=languages;
	  this.yearOfExp=yearOfExp;
  }


public int getYearOfExp() {
	return yearOfExp;
}

public void setYearOfExp(int yearOfExp) {
	this.yearOfExp = yearOfExp;
}

public List<String> getLanguages() {
	return languages;
}

public void setLanguages(List<String> languages) {
	this.languages = languages;
}

public List<Student> getStudents() {
	return students;
}

public void setStudents(List<Student> students) {
	this.students = students;
}
  
public String toString() {
	String lang="";
	for(int j=0;j<this.getLanguages().size();j++) {
		lang=lang+this.getLanguages().get(j)+",";
	}
	return "TeacherName : "+this.getName()+"\n"+"age          : "+this.getAge()+"\n"+"unic ID       : "+this.getUnicId()+"\n"+"Gender          : "+this.getGender()
	+"\n"+"joinDate       : "+this.getJoinDate()+"\n"+"experience per year  : "+this.getYearOfExp()+"\n"+"How many languages learned    : "+lang;
}
}
