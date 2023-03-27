package BusinessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.log4j.Level;

import enums.Category;
import enums.Gender;
import users.Student;
import users.Teacher;

public class getTheDetails {
	
  static SimpleDateFormat pdf=new SimpleDateFormat("yyyy-mm-dd");
  public static List<Teacher> Teacherlist=new ArrayList<>();
  public static List<Student>student=new ArrayList<>();
  public static List<Result>result=new ArrayList<>();
  public static Connection Dbconnection;
  public static void runner() {
	  mainClass.log.log(Level.DEBUG,mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.runner method run");
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.getDbconnection method run");
	  getDBconnection();
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.getDbconnection method ended");
	 
	  if(Dbconnection == null) {
			System.out.println("FATAL: Please check DBConnection ISsue");
			System.exit(0);
	  }
	 mainClass.log.log(Level.DEBUG,mainClass.userID+" "+LocalDateTime.now()+" "+ "getTheDetatils.runner method ended");
  }
  public static void getDBconnection() {
	  try {
		  if(Dbconnection == null) {
			  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.Dbconnection variable initialize");
			    Class.forName("com.mysql.cj.jdbc.Driver");
			Dbconnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/FindATeacher", "Saran", "Saran1234");
		  }
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+"Dbconnection  issue");
	  }
  }
  
  public static int insertData(Student student) {
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.insertData method is running");
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"student data insert the database");
	  try {
	  PreparedStatement stmt=Dbconnection.prepareStatement("insert into Student values(?,?,?,?,?,?)");
	  String m="";
	  stmt.setString(1, student.getName());
	  stmt.setInt(2, student.getAge());
	  stmt.setInt(3, student.getUnicId());
	  if(student.getGender() == Gender.MALE) {
		  m="M";
	  }
	  else if(student.getGender() == Gender.FEMALE) {
		  m="F";
	  }
	  else {
		  m="O";
	  }
	  stmt.setString(4,m);
	  stmt.setDate(5,convertDate(student.getJoinDate()));
	  
	  if(student.getTeacher() == null) {
		  stmt.setInt(6,0);
	  }
	  else {
	      stmt.setInt(6, student.getTeacher().getUnicId());
	  }
	  
	  int result =stmt.executeUpdate();
	  return result;
	  }
	  catch(Exception ex) {
		System.out.println(ex.getMessage());
	  }
	  return -1;
  }
  
 
  
  public static java.sql.Date convertDate(java.util.Date date){
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"convertTime  is running(util.Date to sql.Date)");
	  java.sql.Date date1= new java.sql.Date(date.getTime());
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"convertTime method runned");
	  return date1;
  }
  public static int insertData(Teacher teacher) {
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.insertData method is running");
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"student data insert the database");
	  try {
		  String gender="";
		  PreparedStatement stmt=Dbconnection.prepareStatement("insert into Teacher values(?,?,?,?,?,?,?,?)");
	  stmt.setString(1, teacher.getName());
	  stmt.setInt(2, teacher.getAge());
	  stmt.setInt(3, teacher.getUnicId());
	  if(teacher.getGender() == Gender.MALE) {
		  gender="M";
	  }
	  else if(teacher.getGender() == Gender.FEMALE) {
		  gender="F";
	  }
	  else {
		  gender="O";
	  }
	  stmt.setString(4, gender);
	  stmt.setDate(5, convertDate(teacher.getJoinDate()));
	  stmt.setInt(6,teacher.getYearOfExp());
	  stmt.setString(7, String.join("-",teacher.getLanguages()));
	  ArrayList<String>StudentIds=new ArrayList<>();
	  for(int i=0;i<teacher.getStudents().size();i++) {
		  StudentIds.add(Integer.toString(teacher.getStudents().get(i).getUnicId()));
	  }
	  
	  
	  stmt.setString(8, String.join("-",StudentIds));
	  int result=stmt.executeUpdate();
	  return result;
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.insertData method runned.");
	  return -1;
  }
  
  public static List<Student> getAllrecordsStudents(){
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.getAllrecordsStudent method is running.");
	  List<Student> studentlist=new ArrayList<>();
	  
	  ResultSet result=null;
	  try {
		  Statement stmt= Dbconnection.createStatement();
		  result=stmt.executeQuery("select * from Student");
		  while(result.next()) {
			  Gender gender=null;
			  if(result.getString(4).equals("M")) {
				  gender=Gender.MALE;
			  }
			  else if(result.getString(4).equals("F")) {
				  gender=Gender.FEMALE;
			  }
			  else {
				  gender=Gender.OTHER;
			  }
			  Date a=new java.util.Date(result.getDate(5).getTime());
			  int uniqueId=result.getInt(6);
			  Teacher teacher=null;
			  for(int i=0;i<mainClass.teacherList.size();i++) {
				  if(mainClass.teacherList.get(i).getUnicId() == uniqueId) {
					  teacher=mainClass.teacherList.get(i);
					  break;
				  }
			  }
			 if(teacher ==null) {
				 Student student=new Student(result.getString(1),result.getInt(2),result.getInt(3),gender,a);
			   studentlist.add(student);
			 }
			 else {
			  Student student=new Student(result.getString(1),result.getInt(2),result.getInt(3),gender,a);
			  student.setTeacher(teacher);
			  studentlist.add(student);
			 }
			  
		  }
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.getAllrecordsStudents method ended");
	  return studentlist;
	  
  }
  @SuppressWarnings("unused")
public static List<Teacher> getAllrecordsTeachers(){
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.getAllrecordsTeachers method is running");
	  List<Teacher> teacherlist=new ArrayList<Teacher>();
	  ResultSet re=null;
	  try {
		  Statement stmt=Dbconnection.createStatement();
		  re=stmt.executeQuery("select * from Teacher");
		  while(re.next()) {
			  Gender gender=null;
			  if(re.getString(4).equals("M")) {
				  gender=Gender.MALE;
			  }
			  else if(re.getString(4).equals("F")) {
				  gender=Gender.FEMALE;
			  }
			  else {
				  gender=Gender.OTHER;
			  }
			  Date a=new Date(re.getDate(5).getTime());
			  String lan=re.getString(7).replace("[", "").replace("]", "");
			  String []lang=lan.split("-");
			  String stu=re.getString(8).replace("[", "").replace("]", "");
			  List<String>lang1=new ArrayList<String>(Arrays.asList(stu));
			  String[]ids=stu.split("-");
			  List<Student> students=new ArrayList<Student>();
			  for(int k=0;k<ids.length;k++) {
			  aa:for(int i=0;i<mainClass.StudentList.size();i++) {
				  if(Integer.parseInt(ids[k]) ==mainClass.StudentList.get(i).getUnicId()) {
					  students.add(mainClass.StudentList.get(i));
					  break aa;
				  }
	
			  }
			  }
			  if(student.size()==0) {
				  Teacher teacher=new Teacher(re.getString(1),re.getInt(2),re.getInt(3),gender,a,re.getInt(6),lang1);
				  teacherlist.add(teacher);
			  }
			  else {
			  Teacher teacher=new Teacher(re.getString(1),re.getInt(2),re.getInt(3),gender,a,re.getInt(6),lang1);
			  teacher.setStudents(students);
			  teacherlist.add(teacher);
			  }
		  }
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.getAllrecrdsTeachers method ended");
	  return teacherlist;
  }
 
public static void UpdateStudent(Student student){
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"Enter the updateStudent method is running");
	 try {
		 System.out.println("--------------Student Update---------------");
		 System.out.println("1.change name\n2.change age\n3.change Gender\n4.change Date\nchoose One ");
		 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"1.change name\n2.change age\n3.change Gender\n4.change Date\nchoose One");
		 String choice=mainClass.sc.nextLine();
		 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"choice answer -->"+choice);
		 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"switch case is starting");
		 switch(choice) {
		 case "1":
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeStudentName method is running");
			 changeStudentName(student);
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeStudentName method entered");
			 break;
		 case "2":
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeStudentage method  start");
			 changeStudentAge(student);
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getThe Details.changeStudentAge method  entered");
			 break;
		 case "3":
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeStudentGender method start");
			 changeStudentGender(student);
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeStudentGender method entered");
			 break;
		 case "4":
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"");
			 changeStudentDate(student);
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"");
			 break;
		 default:
			 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"input wrong ,input faild,please correct input");
			 System.out.println("input wrong");
			 break;
		 }
		 
	 }
	 catch(Exception ex) {
		 mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	 }
	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.UpdateStudent method entered");
  }
  
  public static void changeStudentName(Student student) {
	  try {
		  PreparedStatement st=Dbconnection.prepareStatement("update Student set StudentName=? where uniqueID =?");
			 System.out.println("Enter your new name ?");
			 String name=mainClass.sc.nextLine();
			 st.setString(1, name);
			 st.setInt(2, student.getUnicId());
			 int result = st.executeUpdate();
			 student.setStudentName(name);
			 System.out.println("name successfully changed");
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
	  
  }
  
  
  public static void changeStudentAge(Student student) {
	  
	  try {
		  PreparedStatement st=Dbconnection.prepareStatement("update Student set age=? where uniqueID =?");
			 System.out.println("Enter your new age ?");
			 String age=mainClass.sc.nextLine();
			 st.setInt(1, Integer.parseInt(age));
			 st.setInt(2, student.getUnicId());
			 int result = st.executeUpdate();
			 student.setAge(Integer.parseInt(age));
			 System.out.println("age successfully changed");
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
	  
  }
  
  
  @SuppressWarnings("unused")
public static void changeStudentGender(Student student) {
	  
	  try {
		  PreparedStatement st=Dbconnection.prepareStatement("update Student set gender=? where uniqueID =?");
			 System.out.println("Enter your Gender ?");
			 System.out.println("* Male(1)  * Female(2)  * Others(3)");
			 String gender=mainClass.sc.nextLine();
			 String gend="";
			 Gender gende=null;
			 if(gender.equals("1")) {
				gend="M"; 
				gende=Gender.MALE;
			 }
			 else if(gender.equals("2")) {
				gend="F";
				gende=Gender.FEMALE;
			 }
			 else if(gender.equals("3")){
				 gend="O";
				 gende=Gender.OTHER;
			 }
			 st.setString(1, gender);
			 st.setInt(2, student.getUnicId());
			 
			 
			 int result = st.executeUpdate();
			 student.setGender(gende);
			 System.out.println("Gender successfully changed");
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
  }
  
  
  @SuppressWarnings("deprecation")
public static void changeStudentDate(Student student) {
	  try {
		  PreparedStatement st=Dbconnection.prepareStatement("update Student set joinDate=? where uniqueID =?");
			 System.out.println("Enter your new Date(mm/dd/yyyy) ?");
			 String date=mainClass.sc.nextLine();
			 st.setDate(1, convertDate(new Date(date)));
			 st.setInt(2, student.getUnicId());
			 @SuppressWarnings("unused")
			int result = st.executeUpdate();
			 student.setJoinDate(new Date(date));
			 System.out.println("Date successfully changed");
	  }
	  catch(Exception ex) {
		  
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
  }
  
 
  
  public static void UpdateTeacher(Teacher teacher) {
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.UpdateTeacher method start");
	  System.out.println("-------------Update Teacher--------------");
	  
	  System.out.println("1.change name  \n2.change age\n3.change gender    \n4.change date     \n5.change experience   \n6.change Studentlist  \n7.languages\nchoose one");
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"1. change name     2.change age     3.change gender    4.change date     5.change experience   6. change Studentlist  7. languages  \nchoose one");
	  String choice=mainClass.sc.nextLine();
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"choice ans-->"+choice);
	  mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"switch case start");
	  switch(choice) {
     case "1":
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherName method start");
    	 changeTeacherName(teacher);
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherName method ended");
    	 break;
     case "2":
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherage method start");
    	 changeTeacherAge(teacher);
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherage method ended");
    	 break;
     case "3":
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherGender method start");
    	 changeTeacherGender(teacher);
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherGender method ended");
    	 break;
     case "4":
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherDate method start");
    	 changeTeacherDate(teacher);
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherDate method ended");
    	 break;
     case "5":
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherExperience method start");
    	 changeTeacherExperience(teacher);
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherExperience method ended");
    	 break;
     case "6":
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherStudentlist method start");
    	 changeTeacherStudentslist(teacher);
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherStudentlist method ended");
    	 break;
     case "7":
    	 
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherlanguages method start");
    	 changeTeacherLanguages(teacher);
    	 mainClass.log.log(Level.DEBUG, mainClass.userID+" "+LocalDateTime.now()+" "+"getTheDetails.changeTeacherLanguages method ended");
    	 break;
     default:
    	 System.out.println("Update Failed");
    	 mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+"Update failed");
    	 break;
     }
  }
  public static void changeTeacherName(Teacher teacher) {
	  try {
		 PreparedStatement stmt=Dbconnection.prepareStatement("update Teacher set TeacherName=? where uniqueID=?");
		 System.out.println("Enter your new Name : ");
		 String name=mainClass.sc.next();
		 stmt.setString(1,name);
		 stmt.setInt(2, teacher.getUnicId());
		 @SuppressWarnings("unused")
		int result=stmt.executeUpdate();
		 teacher.setStudentName(name);
		 System.out.println("name successfully changed");
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
  }
  @SuppressWarnings("unused")
public static void changeTeacherAge(Teacher teacher) {
	  try {
			 PreparedStatement stmt=Dbconnection.prepareStatement("update Teacher set age=? where uniqueID=?");
			 System.out.println("Enter your new age : ");
			 String age=mainClass.sc.nextLine();
			 stmt.setInt(1,Integer.parseInt(age));
			 stmt.setInt(2, teacher.getUnicId());
			 int result=stmt.executeUpdate();
			 teacher.setAge(Integer.parseInt(age));
			 System.out.println("age successfully changed");
		  }
		  catch(Exception ex) {
			  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
		  }
  }
  public static void changeTeacherGender(Teacher teacher) {
	  try {
			 PreparedStatement stmt=Dbconnection.prepareStatement("update Teacher set Gender=? where uniqueID=?");
			 System.out.println("Enter your new Gender : 1.Male,2. Female,3.Others");
			 String c=mainClass.sc.nextLine();
			 String gender=null;
			 Gender gen=null;
			 if(c.equals("1")) {
				 gender="M";
				 gen=Gender.MALE;
			 }
			 else if(c.equals("2")) {
				 gender="F";
				 gen=Gender.FEMALE;
			 }
			 else {
				 gender="O";
				 gen=Gender.OTHER;
			 }
			 
			 stmt.setString(1,gender);
			 stmt.setInt(2, teacher.getUnicId());
			 @SuppressWarnings("unused")
			int result=stmt.executeUpdate();
			 
			 teacher.setGender(gen);
			 System.out.println("gender successfully changed");
		  }
		  catch(Exception ex) {
			  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
		  }
  }
  @SuppressWarnings("deprecation")
public static void changeTeacherDate(Teacher teacher) {
	  try {
			 PreparedStatement stmt=Dbconnection.prepareStatement("update Teacher set joinDate=? where uniqueID=?");
			 System.out.println("Enter your new Date : (dd/mm/yyyy");
			 String d=mainClass.sc.nextLine();
			 stmt.setDate(1,convertDate(new Date(d)));
			 stmt.setInt(2, teacher.getUnicId());
			 @SuppressWarnings("unused")
			int result=stmt.executeUpdate();
			 System.out.println("date successfully changed");
			 teacher.setJoinDate(new Date(d));
		  }
		  catch(Exception ex) {
			  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
		  }
  }
  public static void changeTeacherExperience(Teacher teacher) {
	  try {
			 PreparedStatement stmt=Dbconnection.prepareStatement("update Teacher set yearsOfExp=? where uniqueID=?");
			 System.out.println("Enter your new experience : ");
			 int ex=mainClass.sc.nextInt();
			 stmt.setInt(1,ex);
			 stmt.setInt(2, teacher.getUnicId());
			 int result=stmt.executeUpdate();
			 teacher.setYearOfExp(ex);
			 System.out.println("experience successfully changed");
		  }
		  catch(Exception ex) {
			  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
		  }
  }
  public static void changeTeacherStudentslist(Teacher teacher) {
	  
	  System.out.println("1. Delete student 2.students total count \n chooseOne");
	  String c=mainClass.sc.nextLine();
	  if(c.equals("1")) {
		  System.out.println("Delete student id : ");
		  int studentid=mainClass.sc.nextInt();
		  updateStudentData(teacher,studentid);
		  
	  }
	  else if(c.equals("2")) {
		  System.out.println("------students total count-----");
		  System.out.println("students total count - "+teacher.getStudents().size());
	  }
  }
  public static void changeTeacherLanguages(Teacher teacher) {
	  System.out.println("1. addlanguage  2. delete language\n   chooseOne");
	  String c=mainClass.sc.nextLine();
	  if(c.equals("1")) {
		  System.out.println("enter your add language : ");
		  String lang=mainClass.sc.nextLine();
		  teacher.getLanguages().add(lang);
		  System.out.println("languages added");
		  try {
			  PreparedStatement st=Dbconnection.prepareStatement("update Teacher set languages=? where uniqueID=?");
			  String lan=String.join("-",teacher.getLanguages());
			  st.setString(1,lan+"-"+lang);
			  st.setInt(2, teacher.getUnicId());
			  st.executeUpdate();
			  
		  }
		  catch(Exception ex) {
			  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
		  }
	  }
	  else if(c.equals("2")) {
		  System.out.println("Enter your delete language : ");
		  String lang=mainClass.sc.nextLine();
		  List<String> language=teacher.getLanguages();
		  language.remove(lang);
		  teacher.setLanguages(language);
		  try {
			  PreparedStatement st=Dbconnection.prepareStatement("update Teacher set languages=? where uniqueID=?");
			  String lan=String.join("-",language);
			  st.setString(1, lan);
			  st.setInt(2, teacher.getUnicId());
		  }
		  catch(Exception ex) {
			  mainClass.log.log(Level.ERROR,mainClass.userID+" "+LocalDateTime.now()+" "+ ex);
		  }
		  System.out.println("language deleted");
	  }
  }
  
  public static void updateStudentData(Teacher teacher,int uniqueID) {
	  try {
		  PreparedStatement smt=Dbconnection.prepareStatement("update Teacher set studentlist=? where uniqueID=?");
		  
		  List<String>stu=new ArrayList<String>();
		  List<Student> students=teacher.getStudents();
		  
		  for(int i=0;i<teacher.getStudents().size();i++) {
			  stu.add(Integer.toString(teacher.getStudents().get(i).getUnicId()));
			  
		  }
		  
		  stu.remove(uniqueID);
		  smt.setString(1, String.join("-",stu));
		  smt.setInt(2, uniqueID);
		  smt.executeUpdate();
		  for(int i=0;i<students.size();i++) {
			  if(students.get(i).getUnicId() == uniqueID) {
				  students.remove(students.get(i));
				  break;
			  }
		  }
		  teacher.setStudents(students);
		  System.out.println("Students successfully deleted");
		  
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
  }
  public static void getDetails() {
	  result=mainClass.result;
  }
  
  public static int insertData(Result re) {
	  try {
		  PreparedStatement st=Dbconnection.prepareStatement("insert into Result values(?,?,?,?,?)");
		  st.setInt(1, re.getStudent().getUnicId());
		  st.setDate(2,convertDate(re.getExamDate()));
		  String type="";
		  if(re.getExamType().equals(Category.Maths)) {
			  type="Maths";
		  }
		  else if(re.getExamType().equals(Category.English)){
			  type="English";
		  }
		  else if(re.getExamType().equals(Category.Knowledge)){
			  type="Knowledge";
		  }
		  st.setString(3,type);
		  st.setInt(4, re.getTotalMarks());
		  st.setString(5, re.getGrade());
		 int result= st.executeUpdate();
		 return result;
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
		  mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	  }
	  return -1;
  }
  
  @SuppressWarnings("deprecation")
public static List<Result> getAllrecordsResult() {
	  List<Result> re=new ArrayList<Result>();
	  List<Student>students=getAllrecordsStudents();
	  ResultSet resul=null;
	  try {
		  Statement st=Dbconnection.createStatement();
		  resul=st.executeQuery("select * from Result");
		  
		  while(resul.next()) {
			  Student stu=null;
			  for(int i=0;i<students.size();i++) {
				  if(students.get(i).getUnicId()==resul.getInt(1)) {
					  stu=students.get(i);
					  break;
				  }
			  }
			  Category c=null;
			  String type="";
			  type=resul.getString(3);
			  if(type.equals("Maths")) {
				  c=Category.Maths;
			  }
			  else if(type.equals("Knowledge")) {
				  c=Category.Knowledge;
			  }
			  else if(type.equals("English")) {
				  c=Category.English;
			  }
			 java.util.Date d=(java.util.Date)convertDate(resul.getDate(2));
			 Result res=new Result(stu,d,c,resul.getInt(4),resul.getString(5));
		      re.add(res);
		  }
	  }
	  catch(Exception ex) {
		  mainClass.log.log(Level.ERROR,mainClass.userID+" "+LocalDateTime.now()+" "+ ex);
	  }
	  return re;
  }
 public static java.util.Date convertDate(java.sql.Date date1){
	 java.util.Date date=new java.util.Date(date1.getTime());
	 return date;
 }
 
 public static void chooseTeacher(Student stu) {
	 System.out.println("-----------Choose Your Teacher----------");
		
	   if(mainClass.currentLoggedStudent.getTeacher()!=null){
		   System.out.println("You are already teacher choosed !!");
	   }
	  else {
		   for(int i=0;i<mainClass.teacherList.size();i++) {
			   System.out.println(mainClass.teacherList.get(i));
			   System.out.println("Choose the techer Yes(1) or No(2) ?");
			   String choice3=mainClass.sc.nextLine();
			   if(choice3.equals("1")) {
				   
				   stu.setTeacher(mainClass.teacherList.get(i));				   
				   mainClass.teacherList.get(i).students.add(stu);			  
				   System.out.println("You choosed the Teacher. ");
				   changeStudentaddTeacher(stu);
				   //changeTeacherstu(mainClass.teacherList.get(i),stu);
				   System.out.println("Thank you !");
				   break;
	
			   }
			   else if(choice3.equals("2")) {
				   System.out.println("you didn't choosed the Teacher.");
				   System.out.println("You choose next teacher.");
			   }
			   else {
				   System.out.println("invalid option!!!");
				   i--;
			   }
		   }
	  }
 }
 public static void changeStudentaddTeacher(Student stu) {
	 try {
		 PreparedStatement stmt=Dbconnection.prepareStatement("update Student set assignTeacher=? where uniqueID=?");
		 stmt.setInt(1, stu.getTeacher().getUnicId());
		 stmt.setInt(2, stu.getUnicId());
		 stmt.executeUpdate();
		 changeTeacherstu(stu.getTeacher(),stu);
		 System.out.println("teacher add in the student profile");
	 }
	 catch(Exception ex) {
		 mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	 }
	 
 }
 public static void changeTeacherstu(Teacher teacher,Student stu) {
	 
	 try {
		 PreparedStatement stmt=Dbconnection.prepareStatement("update Teacher set studentlist=? where uniqueID=?");
		 TreeSet<String>stuId=new TreeSet<>();
		 for(int i=0;i<teacher.getStudents().size();i++) {
			 stuId.add(Integer.toString(teacher.getStudents().get(i).getUnicId()));
		 }
		 stuId.add(Integer.toString(stu.getUnicId()));
		
		 
		 stmt.setString(1, String.join("-", stuId));
		 System.out.println();
		 stmt.setInt(2,teacher.getUnicId());
		 System.out.println(teacher.getUnicId());
		 stmt.executeUpdate();
	 }
	 catch(Exception ex) {
		 
		 mainClass.log.log(Level.ERROR,mainClass.userID+" "+LocalDateTime.now()+" "+ ex);
	 }
	 }
 
 
 public static void DeleteTeacher(Student stu) {
	 if(stu.getTeacher()==null) {
		 System.out.println("Teacher already deleted");
	 }
	 else {
	 Teacher teacher=stu.getTeacher();
	 stu.setTeacher(null);
	List<Student>students= teacher.getStudents();
	students.remove(stu);
	teacher.setStudents(students);
	changeTeacherstu(teacher,stu);
	changeStudentDelTeacher(stu);
	System.out.println("teacher deleted");
	 }
 }
 public static void changeStudentDelTeacher(Student stu) {
	 try {
		 PreparedStatement stmt=Dbconnection.prepareStatement("update Student set assignTeacher=? where uniqueID=?");
		 stmt.setInt(1, 0);
		 stmt.setInt(2, stu.getUnicId());
		 stmt.executeUpdate();
		 //changeTeacherstu(stu.getTeacher(),stu);
		 System.out.println("teacher add in the student profile");
	 }
	 catch(Exception ex) {
		 mainClass.log.log(Level.ERROR, mainClass.userID+" "+LocalDateTime.now()+" "+ex);
	 }
	 
 }
 
 public static boolean nameCheck(String name) {
	 for(int i = 0; i < name.length(); ++i){
	        char ch = name.charAt(i);
	        
	        if(!Character.isLetter(ch)){
	            return false;
	        }   
	            
	        if(!Character.isWhitespace(ch)){
	            return false;
	        }
	        return true;
	    }
	    return true;
 }
 
 public static int insertData(Quizz quiz) {
	 try {
		 PreparedStatement stmt=Dbconnection.prepareStatement("insert into Quizz values(?,?,?,?)");
		 stmt.setString(1, quiz.getQuestion());
		 String[]arr=quiz.getOption();
		 for(int i=0;i<arr.length;i++) {
				arr[i]=arr[i].replace("-", "_");
			}
		 stmt.setString(2, String.join("-",arr));
		 stmt.setString(3, quiz.getAnswer());
		 String s="";
		 if(quiz.getType()==Category.Maths) {
			 s="Maths";
		 }
		 else if(quiz.getType()==Category.Knowledge) {
			 s="Knowledge";
		 }
		 else if(quiz.getType()==Category.English) {
			 s="English";
		 }
		 stmt.setString(4, s);
		 int result=stmt.executeUpdate();
		 return result;
	 }
	 catch(Exception ex) {
		 System.out.println(ex.getMessage());
	 }
	 return -1;
 }
 
 public static List<Quizz> getAllQuizz(){
	 List<Quizz> quizz=new ArrayList<Quizz>();
	 try {
		 PreparedStatement stmt=Dbconnection.prepareStatement("select * from Quizz");
	   ResultSet re=stmt.executeQuery();
	   while(re.next()) {
		   String[]option=re.getString(2).split("-");
		   Category c=null;
		   if(re.getString(4).equals("Maths")) {
			   c=Category.Maths;
		   }
		   else if(re.getString(4).equals("Knowledge")) {
			   c=Category.Knowledge;
		   }
		   else if(re.getString(4).equals("English")) {
			   c=Category.English;
		   }
		   Quizz q1=new Quizz(re.getString(1),option,re.getString(3),c);
		   quizz.add(q1);
	   }
	 }
	 catch(Exception ex) {
		 
	 }
	 return quizz;
 }
 public static void insertData(String value) {
	 try {
		 PreparedStatement stmt=Dbconnection.prepareStatement("insert into Categories values(?)");
		 stmt.setString(1, value);
		 stmt.executeUpdate();
	 }
	 catch(Exception ex) {
		 ex.printStackTrace();
	 }
 }
}
