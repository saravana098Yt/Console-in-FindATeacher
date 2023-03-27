package BusinessLogic;

import users.Student;

public class Login {
  private static Login login=null;
  
  private Login() {}
  
  public static Login getInstance() {
	  if(login==null) {
		  login=new Login();
	  }
	  return login;
  }
  
  public Student login(int ID,String password) {
	  return ValidateLogin(ID,password);
  }
  public Student ValidateLogin(int ID,String password){
	  Student currStudent=null;
	  for(int i=0;i<mainClass.StudentandTeacherList.size();i++){
		  currStudent=mainClass.StudentandTeacherList.get(i);
		  if(currStudent.getUnicId() == ID && currStudent.getPassword().equals(password)){
			  return currStudent;
		  }
	  }
	  return null;
  }
}
