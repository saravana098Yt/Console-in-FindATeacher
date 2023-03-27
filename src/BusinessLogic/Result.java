package BusinessLogic;

import java.util.Date;

import enums.Category;
import users.Student;

public class Result{
    private Student student;
    private Date examDate;
    private Category examType;
    private int totalMarks;
    private String grade;
    
    public Result(Student student,Date examDate,Category examType,int totalMarks,String grade) {
    	this.student=student;
    	this.examDate=examDate;
    	this.examType=examType;
    	this.totalMarks=totalMarks;
    	this.grade=grade;
    }
    
    
	public Category getExamType() {
		return examType;
	}


	public void setExamType(Category type) {
		this.examType = type;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
    
//	public String toString() {
//	return "|--------------------|------------------|-----------------|----------------|-----------|\n|                       |                 |                  |                |           |\n|                       |                 |                  |                |           |\n|--------------------|------------------|-----------------|----------------|-----------|\n";
//
//	}
	public String toString() {
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
		return " Student name : "+this.student.getName()+"     Student id : "+this.student.getUnicId()+"      ExamDate : "+mainClass.pdf.format(this.getExamDate())+"        ExamType :  "+this.getExamType()+"       TotalMarks : "+this.getTotalMarks()+"      Grade : "+this.getGrade()+"|";
	}
}
