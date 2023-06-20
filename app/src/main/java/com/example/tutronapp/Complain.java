public class Complain{
	private Tutor tutor;
	private Student student;
	private String problem, topic;
	private Date date;
	private Complain next;
	
	public Complain(){
			
	}
	public Complain(Tutor t, Student s, String p, String t, Date d){
			this.tutor = t;
			this.student = s;
			this.problem = p;
			this.topic = t;
			this.date = d;
			this.next = null;
	}
	public Complain(Tutor t, Student s, String p, String t, Date d, Complain n){
			this.tutor = t;
			this.student = s;
			this.problem = p;
			this.topic = t;
			this.date = d;
			this.next = n;
	}
	Tutor getTutor(){
		return tutor;
	}
	void setTutor(Tutor t){
		this.tutor = t;
	}
	Student getStudent(){
		return student;
	}
	void setStudent(Student){
		this.student = s;
	}
	String getProblem(){
		return problem;
	}
	void setProblem(String p){
		this.problem = p;
	}
	String getTopic(){
		return topic;
	}
	void setTopic(String p){
		this.topic = t;
	}
	Date getDate(){
		return date;
	}
	void setDate(Date d){
		this.date = d;
	}
	Complain getNext(){
		return next;
	}
	void getNext(Complain n){
		this.next = n;
	}
}