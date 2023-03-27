package BusinessLogic;

import enums.Category;

@SuppressWarnings("unused")


public class Quizz {
private String question;
private String []option;
private String Answer;
private Category type;

 public Quizz(String question,String []option,String ans,Category type) {
	   this.question=question;
	   this.option=option;
	   this.Answer=ans;
	   this.type=type;
   }
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getAnswer() {
	return Answer;
}
public void setAnswer(String answer) {
	Answer = answer;
}
public String[] getOption() {
	return option;
}
public void setOption(String []option) {
	this.option = option;
}
public Category getType() {
	return type;
}
public void setType(Category type) {
	this.type = type;
}
public String toString() {
	//return "\t\t"+this.question+"\n\n\n\t\t"+this.option+"\n";
	String s="";
	s+="\t\t"+this.question+"\n\n\n\t\t";
	for(int i=0;i<this.option.length;i++) {
		s+=option[i]+"      ";
	}
	return s;
}
}
