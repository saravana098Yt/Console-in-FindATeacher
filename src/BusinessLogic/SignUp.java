package BusinessLogic;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enums.Gender;
import users.Student;
import users.Teacher;

public class SignUp {
	
	
   
static SignUp signup=null;
	private SignUp() {}
	public static SignUp getInstance() {
		if(signup ==null) {
			signup=new SignUp();
		}
		return signup;
	}
	
	
     @SuppressWarnings("deprecation")
	public Student addNewStudent() throws FileNotFoundException,IOException{
    	 System.out.println("Students signUp");
			String StudentName="";
			boolean check=false;
			while(check== false) {
				System.out.println("Enter your Name >>");
				StudentName=mainClass.sc.nextLine();
				break;
//				check=getTheDetails.nameCheck(StudentName);
//				if(check==false) {
//					System.out.println("Name not found");
//				}
			}
			
			String age="";
			String unicID="";
			while(age.equals("")) {
			System.out.println("Enter your Age >>");
			 age =mainClass.sc.nextLine();
			}
			while(true) {
				System.out.println("Enter your uniqueID "+"Example : "+mainClass.StudentandTeacherList.get(mainClass.StudentandTeacherList.size()-1).getUnicId() +" under >>");
				
				 unicID =mainClass.sc.nextLine();
				aa:for(int i=0;i<mainClass.StudentandTeacherList.size();i++) {
					if(mainClass.StudentandTeacherList.get(i).getUnicId() == Integer.parseInt(unicID)) {
						
						//System.out.println(mainClass.StudentandTeacherList.get(i));
						System.out.println("unique ID already registered,please new unique number");	
						unicID="";
						break aa;
						
				    }
				}
				if(!(unicID.equals(""))) {
					break;
				}
				}
			Gender gender=null;
			while(gender == null) {
			System.out.println("Enter your Gender \n1.Male\n2.Female\n3.Others\n chooseOne");
			String ch=mainClass.sc.nextLine();
			
			if(ch.equals("1")) {
				gender=Gender.MALE;
			}
			else if(ch.equals("2")) {
				gender=Gender.FEMALE;
			}
			else if(ch.equals("3")) {
				gender=Gender.OTHER;
			}
			else {
				gender=null;
				System.out.println("Gender is wrong");
			}
			}
			Date dob=null;
			String date="";
			while(date == "") {
			try {
				System.out.println("Enter your DateOfBirth (dd/mm/yyyy) >>");
				date=mainClass.sc.nextLine();
				dob=new Date(date);
			}
			catch(Exception ex) {
				System.out.println("dateofbirth wrong");
				date="";
				mainClass.sc.nextLine();
			}
			}
			Student t1=new Student(StudentName,Integer.parseInt(age),Integer.parseInt(unicID),gender,dob);
			getTheDetails.insertData(t1);
			mainClass.StudentList.add(t1);
			//mainClass.currentLoggedStudent=t1;
			//mainClass.userID=Integer.toString(mainClass.currentLoggedStudent.getUnicId());
			System.out.println("Successfully Students Signed in");
			return t1;
     }
     
     
     
     
     @SuppressWarnings("deprecation")
	public Teacher addNewTeacher() throws FileNotFoundException,IOException{
    	 System.out.println("Teachers signUp");
			
			String StudentName="";
			boolean check=false;
			while(check== false) {
				System.out.println("Enter your Name >>");
				StudentName=mainClass.sc.nextLine();
				break;
//				check=getTheDetails.nameCheck(StudentName);
//				if(check==false) {
//					System.out.println("Name not found");
//				}
			}
			String age="";
			String unicID="";
			while(age.equals("")) {
			System.out.println("Enter your Age >>");
			
			 age =mainClass.sc.nextLine();
		     if(Integer.parseInt(age)>=50) {
		    	 age="";
		    	 System.out.println("age not found");
		     }
			}
			while(true) {
				System.out.println("Enter your uniqueID "+"Example :2400 >>");
				 unicID =mainClass.sc.nextLine();
				aa:for(int i=0;i<mainClass.StudentandTeacherList.size();i++) {
					if(mainClass.StudentandTeacherList.get(i).getUnicId() == Integer.parseInt(unicID)) {
						unicID="";
						System.out.println("unique ID already registered,please new unique number");	
				       break aa;
					}
				}
				if(!(unicID.equals(""))) {
					break;
				}
				}
			Gender gender=null;
			while(gender == null) {
			System.out.println("Enter your Gender \n1.Male\n2.Female\n3.Others\n chooseOne");
			String ch=mainClass.sc.nextLine();
			
			if(ch.equals("1")) {
				gender=Gender.MALE;
			}
			else if(ch.equals("2")) {
				gender=Gender.FEMALE;
			}
			else if(ch.equals("3")) {
				gender=Gender.OTHER;
			}
			else {
				gender=null;
				System.out.println("gender not found");
			}
			}
			Date dob=null;
			String date="";
			while(date == "") {
			try {
				System.out.println("Enter your DateofBirth (dd/mm/yyyy) >>");
				date=mainClass.sc.nextLine();
				dob=new Date(date);
			}
			catch(Exception ex) {
				System.out.println("DateOfBirth wrong");
				date="";
				mainClass.sc.nextLine();
			
			}
			}
			int yearsOfExp=0;
			while(yearsOfExp == 0) {
				try {
					System.out.println("Enter your Experience (int)>>");
					yearsOfExp=mainClass.sc.nextInt();
				}
				catch(Exception ex) {
					System.out.println("--------------   Not Experience Found   ----------");
					yearsOfExp=0;
					mainClass.sc.nextLine();
				}
			}
			List<String> languages=new ArrayList<>();
			System.out.println("How many languages learned (int) >>");
			int length=mainClass.sc.nextInt();
			for(int i=0;i<length;i++) {
				String language=mainClass.sc.nextLine();
				languages.add(language);
			}
			
			Teacher t1=new Teacher(StudentName,Integer.parseInt(age),Integer.parseInt(unicID),gender,dob,yearsOfExp,languages);
			//mainClass.currentLoggedStudent=t1;
			getTheDetails.insertData(t1);
			mainClass.teacherList.add(t1);
			//mainClass.userID=Integer.toString(mainClass.currentLoggedStudent.getUnicId());
			System.out.println("Successfully Teachers Signed in");
			return t1;
     }
}
