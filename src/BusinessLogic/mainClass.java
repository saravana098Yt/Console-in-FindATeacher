package BusinessLogic;

import java.util.ArrayList;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import enums.Category;
import users.Student;
import users.Teacher;

//@SuppressWarnings("unused")
public class mainClass {
	public static ArrayList<String> Categories=new ArrayList<String>();
	public static Logger log = Logger.getLogger(mainClass.class.getName()); 
	public static String userID ="";
	public static List<Student>StudentandTeacherList=new ArrayList<>();
	public static List<Teacher> teacherList=new ArrayList<Teacher>();
    public static List<Student> StudentList=new ArrayList<Student>();
    public static Student currentLoggedStudent;
    public static Teacher currentTeacher;
    public static Scanner sc=new Scanner(System.in);
    public static List <Quizz> Questions=new ArrayList<>();
    public static SimpleDateFormat pdf=new SimpleDateFormat("dd/MM/yyyy");
    public static List<Result> result=new ArrayList<>();
    
	@SuppressWarnings({ "unused" })
	
	
	public static void main(String[] args) {
		Categories.add("Maths");
		Categories.add("Knowledge");
		Categories.add("English");
		Categories.add("Tamil");
		Categories.add("cricket");
		Categories.add("Politician");
		Categories.add("Film");
		Categories.add("Animals");
		Categories.add("Books");
		Categories.add("Geography");
		Categories.add("Countries");
		log.log(Level.DEBUG,userID+" "+LocalDateTime.now()+" "+"Entering main Method");
////		file read
//		try {
//		File file=new File("/home/saravan-zstk298/eclipse-workspace/FindATeacher1/src/StudentAndTeacher.csv");
//		FileReader reader=new FileReader(file);
//		BufferedReader buf=new BufferedReader(reader);
//		String str="";
//		while((str=buf.readLine()) != null) {
//			if(str.startsWith("Teacher")) {
//				String[]copy=str.split(",");
//				Gender gender=null;
//				List<String>languages=new ArrayList<String>();
//				if(copy[4].equals("MALE")){
//					gender=Gender.MALE;
//				}
//				else if(copy[4].equals("FEMALE")) {
//					gender=Gender.FEMALE;
//				}
//				else if(copy[4].equals("OTHER")) {
//					gender=Gender.OTHER;
//				}
//				String value=copy[7];
//				value=value.replace("[", "");
//				value=value.replace("]", "");
//				String []lang=value.split("-");
//				for(int i=0;i<lang.length;i++) {
//					languages.add(lang[i]);
//				}
//				Teacher t1=new Teacher(copy[1],Integer.parseInt(copy[2]),Integer.parseInt(copy[3]),gender,new Date(copy[5]),Integer.parseInt(copy[6]),languages);
//			      teacherList.add(t1);
//			      StudentandTeacherList.add(t1);
//			}
//			else if(str.startsWith("Student")){
//				String[]copy=str.split(",");
//				Gender gender=null;
//				if(copy[4].equals("MALE")){
//					gender=Gender.MALE;
//				}
//				else if(copy[4].equals("FEMALE")){
//					gender=Gender.FEMALE;
//				}
//				else if(copy[4].equals("OTHER")) {
//					gender=Gender.OTHER;
//				}
//				
//				Student s1=new Student(copy[1],Integer.parseInt(copy[2]),Integer.parseInt(copy[3]),gender,new Date(copy[5]));
//				StudentList.add(s1);
//				StudentandTeacherList.add(s1);
//			}
//		}
//		reader.close();
//	}
//	catch(Exception ex) {
//		
//		
//	}
//		
//		
//		try {
//			File fie=new File("/home/saravan-zstk298/eclipse-workspace/FindATeacher1/src/BusinessLogic/Quizz.csv");
//			FileReader fi=new FileReader(fie);
//			BufferedReader f1=new BufferedReader(fi);
//			String que="";
//			while((que=f1.readLine()) != null) {
//				String[]copy=que.split(", ");
//				if(que.startsWith("Know")) {
//					copy[2]=copy[2].replace("[", "");
//					copy[2]=copy[2].replace("]", "");
//					String[]arr=copy[2].split("-");
//					for(int i=0;i<arr.length;i++) {
//						arr[i]=arr[i].replace("_", "-");
//					}
//					
//					Questions.add(new Quizz(copy[1].replace(",", ""),arr,copy[3].replace(",", ""),"Knowledge"));
//				}
//				else if(que.startsWith("Math")) {
//					copy[2]=copy[2].replace("[", "").replace("]", "");
//					String[]arr=copy[2].split("-");
//					for(int i=0;i<arr.length;i++) {
//						arr[i]=arr[i].replace("_", "-");
//					}
//					
//					Questions.add(new Quizz(copy[1].replace(",", ""),arr,copy[3].replace(",", ""),"Maths"));
//					
//				}
//				else if(que.startsWith("Eng")) {
//					copy[2]=copy[2].replace("[", "").replace("]", "");
//					String[]arr=copy[2].split("-");
//					for(int i=0;i<arr.length;i++) {
//						arr[i]=arr[i].replace("_", "-");
//					}
//					
//					Questions.add(new Quizz(copy[1].replace(",", ""),arr,copy[3].replace(",", ""),"English"));
//				}
//			}
//			fi.close();
//		}
//		catch(Exception ex) {
//			log.log(Level.ERROR, userID+" "+LocalDateTime.now()+" "+ex);
//		}
//		
//		
//		log.log(Level.DEBUG,userID+" "+LocalDateTime.now()+" "+"DataBase is running");
		int re=0;
		getTheDetails.runner();
//		for(int i=0;i<Questions.size();i++) {
//			
//			re=getTheDetails.insertData(Questions.get(i));
//		
//			if(re == -1) {
//				break;
//			}
//		}
		teacherList.addAll(getTheDetails.getAllrecordsTeachers());
		StudentList.addAll(getTheDetails.getAllrecordsStudents());
		StudentandTeacherList.addAll(StudentList);
		StudentandTeacherList.addAll(teacherList);
		result.addAll(getTheDetails.getAllrecordsResult());
		Questions.addAll(getTheDetails.getAllQuizz());
		
		
//		try {
//			File fil=new File("/home/saravan-zstk298/eclipse-workspace/FindATeacher1/src/BusinessLogic/result.csv");
//		    FileReader red=new FileReader(fil);
//		    BufferedReader buf=new BufferedReader(red);
//		    String str="";
//		    while((str=buf.readLine()) != null) {
//		    	String[] st=str.split(",");
//		    	Student student=null;
//		    	for(int i=0;i<StudentList.size();i++) {
//		    		student=StudentList.get(i);
//		    		if(student.getName().equals(st[0]) && student.getUnicId() == Integer.parseInt(st[1])) {
//		    			break;
//		    		}
//		    	}
//		    	
//		    	Category type=null;
//		    	if(st[3].equals("Maths")) {
//		    		type=Category.Maths;
//		    	}
//		    	else if(st[3].equals("English")) {
//		    		type=Category.English;
//		    	}
//		    	else if(st[3].equals("Knowledge")) {
//		    		type=Category.Knowledge;
//		    	}
//		    	int marks=Integer.parseInt(st[4]);
//		    	String grade=st[5];
//		    	Result re=new Result(student,pdf.parse(st[2]),type,marks,grade);
//		    	result.add(re);
//		    	getTheDetails.insertData(re);
//		    	
//		    }
//		}
//		catch(Exception ex) {
//			ex.printStackTrace();
//			log.log(Level.ERROR,userID+" "+LocalDateTime.now()+" "+ex);
//		}
//	
//		for(int i=0;i<result.size();i++) {
//			int re=getTheDetails.insertData(result.get(i));
//			if(re==-1) {
//				System.out.println("print Error");
//				break;
//			}
//	  }
	
       System.out.println("------------------------>   FIND A TEACHER MANAGEMENT SYSTEM   --------------------");   
	System.out.println();
	System.out.println();
       while(true) {
    	   System.out.println("------>   1. Login   ------>\n------>   2. SignUp   ----->\n------>   3. Exit   ----\nchoose one");
	       String choice=sc.nextLine();
	       
		if(choice.equals("1")){
			
			System.out.println("------------    Login    -------------");
			
		    while(mainClass.currentLoggedStudent == null) {
		    	
		    	System.out.println("Enter your uniqueId >>");
		    
		    	String uniqueId=sc.nextLine();
		    	System.out.println("Enter your Password >>");
		    	String password=sc.nextLine();
		    	
		    	mainClass.currentLoggedStudent=Login.getInstance().login(Integer.parseInt(uniqueId),password);
		    	if(currentLoggedStudent == null) {
		    		System.out.println("-----------------------------------------------");
		    		System.out.println( "-------------    Login Failed     ------------");
		    		System.out.println( "-----------    Please try again     ----------");
		    		System.out.println("-----------------------------------------------");
		    	}
		    	else if(currentLoggedStudent != null){
		    		
		    		System.out.println( "Successfully logged in");
		    		userID=Integer.toString(currentLoggedStudent.getUnicId());
		    		System.out.println();
				    System.out.println();
		    	}
		    }
		    

	   }
		else if(choice .equals("2")) {
			System.out.println("-----------------    SignUp    ------------------");
			System.out.println("------>   1. Teacher   -----\n------>   2. Student   -----\n-----   choose one");
			String choice1 =sc.nextLine();
			if(choice1.equals("1")) {
				try {
				currentLoggedStudent=SignUp.getInstance().addNewTeacher();
				System.out.println(currentLoggedStudent);
				userID=Integer.toString(currentLoggedStudent.getUnicId());
				}
				catch(Exception ex) {
					log.log(Level.ERROR,userID+" "+LocalDateTime.now()+" "+ex);
				}
				
			}
			else if(choice1.equals("2")) {
				try {
				currentLoggedStudent=SignUp.getInstance().addNewStudent();
				userID=Integer.toString(currentLoggedStudent.getUnicId());
				}
				catch(Exception ex) {
					System.out.println(ex);
					log.log(Level.ERROR, userID+" "+LocalDateTime.now()+" "+ex);
				}
				
			}
			System.out.println();
			System.out.println();
		}
		else {
			System.out.println("Thank You For Find A Teacher System Service !!!");
			break;
		}
		
		 
		if(currentLoggedStudent != null) {
		System.out.println("------------------------------------------------------");
		System.out.println("-------           --  Welcome "+currentLoggedStudent.getName()+"    -------------------");
		System.out.println("--------------------------------------------------------");
		     
		        if(currentLoggedStudent instanceof Teacher) {
		        currentTeacher=(Teacher)currentLoggedStudent;
		     	   while(true) {
		     		   System.out.println("                        Teacher Process                 ");
		     		   System.out.println();
		     		   System.out.println("--------->       1. my Studentlist       --------\n--------->       2. Create A Quizz      --------\n--------->       3. Update my details         --------\n--------->       4. All Result records     --------\n--------->       5. view My Details               --------\n--------->        6. Exit      ------------\n--------->  choose one option");
		     	       String choice2=sc.nextLine();
		     		   if(choice2.equals("1")) {
		     			   if(currentTeacher.getStudents().size() == 0) {
		     				   System.out.println("No students available, Empty set");
		     			   }
		     			   else {
		     			   System.out.println("--------------- my Students List --------------");
	                       for(int i=0;i<currentTeacher.students.size();i++) {
	                    	   System.out.println(currentTeacher.students.get(i));
	                       }
		     			   }
		     		   }
		     		   else if(choice2.equals("2")) {
		     			  System.out.println("------create Quizz----------");
		     			  System.out.println("Enter the  Category ?");
		     			  System.out.println("1.Maths\n2.Knowledge\n3.English\n4.Tamil\n5.Cricket\n6.Politician\n7.Film\n8.Animals\n9.Books\n10.Geography\n11.Countries\n  choose One");
		     			  String category=sc.nextLine();
		     			  Category c=null;
		     			  if(category.equals("1")) {
		     				  c=Category.Maths;
		     			  }
		     			  else if(category.equals("2")) {
		     				  c=Category.Knowledge;
		     			  }
		     			  else if(category.equals("3")) {
		     				  c=Category.English;
		     			  }
		     			 else if(category.equals("4")) {
		     				  c=Category.Tamil;
		     			  }
		     			  else if(category.equals("5")) {
		     				  c=Category.Cricket;
		     			  }
		     			 else if(category.equals("6")) {
		     				  c=Category.Politician;
		     			  }
		     			  else if(category.equals("7")) {
		     				  c=Category.Film;
		     			  }
		     			 else if(category.equals("8")) {
		     				  c=Category.Animals;
		     			  }
		     			  else if(category.equals("9")) {
		     				  c=Category.Books;
		     			  }
		     			 else if(category.equals("10")) {
		     				  c=Category.Geography;
		     			  }
		     			  else if(category.equals("11")) {
		     				  c=Category.Countries;
		     			  }
		     			  
		     			  System.out.println(" How many questions in this category ?");
		     			  String value=sc.nextLine();
		     			 
		     			  int v=Integer.parseInt(value);
		     			  for(int i=0;i<v;i++) {
		     				  System.out.println("Enter your Question : ");
		     				  String Que=sc.nextLine();
		     				  String[]options=new String[4];
		     				  for(int j=0;j<4;j++) {
		     				  System.out.println("Enter your"+(j+1)+"st Option : ");
		     				  String option=sc.nextLine();
		     				  options[i]=(j+1)+")"+option+". ";
		     				  }
		     				  System.out.println("Enter your option in one (1-4)correct option : ");
		     				  String ans=sc.nextLine();
		     				 Quizz a1=new Quizz(Que,options,ans,c);
		     				 Questions.add(a1);
		     				 re=getTheDetails.insertData(a1);
		     				  System.out.println("Questions added");
		     			  }
		     		   }
		     		   else if(choice2.equals("3")) {
		     			   getTheDetails.UpdateTeacher(currentTeacher);
		     		   }
		     	       else if(choice2.equals("4")) {
		     			   System.out.println("------------- Result Records ------------");
		     			   if(currentTeacher.getStudents().size()==0) {
		     				   System.out.println("no result,no students found");
		     			   }
		     			   else {
		     			   for(int i=0;i<currentTeacher.getStudents().size();i++) {
		     				   for(int j=0;j<result.size();j++) {
		     					   if(currentTeacher.getStudents().get(i).getUnicId() == result.get(j).getStudent().getUnicId()) {
		     						   System.out.println(result.get(j));
		     					   }
		     				   }
		     			   }
		     			   }
		     		   }
		     	       else if(choice2.equals("5")){
		     	    	  System.out.println("--------view my details-----");
		     	    	  System.out.println(currentTeacher.getName()+", "+currentTeacher.getAge()+", "+currentTeacher.getUnicId()+", "+currentTeacher.getGender());
		     	       }
		     	       else if(choice2.equals("6")) {
		     	    	   currentLoggedStudent=null;
		     	    	   currentTeacher=null;
		     	    	   break;
		     	       }
		     	       else {
		     	    	   System.out.println("input failed");
		     	       }
		     	   }
		        }
		        else if(currentLoggedStudent instanceof Student) {
		        	
		     	   aa:while(true) {
		     		  System.out.println();
		     		   System.out.println("                      Student process                            ");
		     		  
		     		   System.out.println("---------->     1. Quizzes               --------\n---------->     2. result                --------\n---------->     3. View Details     --------\n---------->     4. Choose Teacher   --------\n---------->     5. Update Details   --------\n---------->     6. Exit                  --------\n       chooseOne");
		     		  
		     			String choice1 = sc.nextLine();
		     		   if(choice1.equals("1")) {
		     			   System.out.println("             Categories             ");
		     			   System.out.println();
		     			   System.out.println();
		     			   for(int i=0;i<Categories.size();i++) {
		     			   System.out.println("------------->  "+i+1+". "+Categories.get(i)+ "            -----------------\n");
		     			   }
		     			   System.out.println("choose one");
		     			   String choice2=sc.nextLine();
		     		       String add="";
		     		       FileWriter fw=null;
		     		      
		     		       int marks=0;
		     		       switch(choice2){
		     		       case "1":
		     		    	   String exam="Maths";
		     		    	   Date examDate=new Date();
		     		    	   
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Maths)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	   String grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Maths,marks,grade));
		     		    	  int rem=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Maths,marks,grade));
		     		    	   System.out.println("Maths Exam completed");
		     		    	   break;
		     		       case "2":
		     		    	   exam="Knowledge";
		     		    	    examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Knowledge)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Knowledge,marks,grade));
		     		    	  re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Knowledge,marks,grade));
								
		     		    	   System.out.println("Knowledge Exam completed");
		     		    	   break;
		     		       case "3":
		     		    	   exam="English";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.English)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.English,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.English,marks,grade));
		     		    	   System.out.println("English Exam completed");
		     		    	   break;
		     		       case "4":
		     		    	  exam="Tamil";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Tamil)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Tamil,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Tamil,marks,grade));
		     		    	   System.out.println("English Exam completed");
		     		    	   break;
		     		       case "5":
		     		    	  exam="Cricket";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Cricket)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Cricket,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Cricket,marks,grade));
		     		    	   System.out.println("English Exam completed");
		     		    	   break;
		     		       case "6":
		     		    	  exam="Politician";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Politician)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Politician,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Politician,marks,grade));
		     		    	   System.out.println("Politician Exam completed");
		     		    	   break;
		     		       case "7":
		     		    	  exam="Film";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Film)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Film,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Film,marks,grade));
		     		    	   System.out.println("Film Exam completed");
		     		    	   break;
		     		       case "8":
		     		    	  exam="Animals";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Animals)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Animals,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Animals,marks,grade));
		     		    	   System.out.println("Animals Exam completed");
		     		    	   break;
		     		       case "9":
		     		    	  exam="Books";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Books)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Books,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Books,marks,grade));
		     		    	   System.out.println("Books Exam completed");
		     		    	   break;
		     		       case "10":
		     		    	  exam="Geography";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Geography)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Geography,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Geography,marks,grade));
		     		    	   System.out.println("Geography Exam completed");
		     		    	   break;
		     		       case "11":
		     		    	  exam="Countries";
		     		    	  examDate=new Date();
		     		    	    marks=0;
		     		    	   for(int i=0;i<Questions.size();i++) {
		     		    		   if(Questions.get(i).getType().equals(Category.Countries)) {
		     		    		   System.out.println(Questions.get(i));
		     		    		   int ans=sc.nextInt();
		     		    		   if(Integer.parseInt(Questions.get(i).getAnswer()) == ans) {
		     		    			   marks++;
		     		    			   System.out.println("Correct !!");
		     		    		   }
		     		    		   else {
		     		    			   System.out.println("Wrong !!");
		     		    		   }
		     		    		   }
		     		    	   }
		     		    	     grade="";
		     		    	   if(marks==1) {
		     		    		   grade="E";
		     		    	   }
		     		    	   else if(marks == 2) {
		     		    		   grade="D";
		     		    	   }
		     		    	  else if(marks == 3) {
		     		    		   grade="C";
		     		    	   }
		     		    	 else if(marks == 4) {
		     		    		   grade="B";
		     		    	   }
		     		    	 else if(marks == 5) {
		     		    		   grade="A";
		     		    	   }
		     		    	   result.add(new Result(currentLoggedStudent,examDate,Category.Countries,marks,grade));
		     		    	 re=getTheDetails.insertData(new Result(currentLoggedStudent,examDate,Category.Countries,marks,grade));
		     		    	   System.out.println("Countries Exam completed");
		     		    	   break;
		     		       case "12":
		     		    	   break;
		     		       default :
		     		    	   System.out.println("input wrong !!!");
		     		    	   break;
		     		       }
		     		   }
		     		   else if(choice1.equals("2")) {
		     			   List<Result> remm=new ArrayList<Result>();
		     			   for(int i=0;i<result.size();i++) {
		     				   if(currentLoggedStudent.getUnicId() == result.get(i).getStudent().getUnicId()) {
		     					   remm.add(result.get(i));
		     				   }
		     			   }
		     			   
		     			   if(remm.size()==0) {
		     				   System.out.println("no result records");
		     			   }
		     			   else {
		     				   for(int i=0;i<remm.size();i++) {
		     					   System.out.println(remm.get(i));
		     				   }
		     			   }
		     			   
		     		   }
		     		   else if(choice1.equals("3")) {
		     			   System.out.println(currentLoggedStudent);
		     		   }
		     		   else if(choice1.equals("4")) {
		     			   System.out.println("-----------Choose Your Teacher----------");
		     		      System.out.println("1. add teacher\n2. Delete teacher\n choose One");
		     		      String choice4=sc.nextLine();
		     		      switch(choice4){
		     		      case "1":
		     		    	  getTheDetails.chooseTeacher(currentLoggedStudent);
		     		    	  break;
		     		      case "2":
		     		    	  getTheDetails.DeleteTeacher(currentLoggedStudent);
		     		    	  break;
		     		      default :
		     		    	  break;
		     		      }
		     			   }
		     		   else if(choice1.equals("5")) {
		     			  System.out.println("----------Update your Details---------");
		     			    getTheDetails.UpdateStudent(currentLoggedStudent);
		     		   }
		     		   else if(choice1.equals("6")) {
		     			   currentLoggedStudent=null;
		     			   break aa;
		     		   }
		     		   else {
		     			   System.out.println("Invalid option, please try !!!");
		     		   }
		     	   }
		        }
		}
		
		
	}
       
	
	}

	


}
