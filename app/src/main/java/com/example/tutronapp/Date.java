public class Date{
	private int day, month, year, hour, minute;
	public Complain(){
			
	}
	public Complain(int d, int mo, int y, int h, int mi){
			this.day = d;
			this.month = mo;
			this.year = y;
			this.hour = h;
			this.minute = mi;
	}
	int getDay(){
		return day;
	}
	void setDay(int d){
		this.day = d;
	}
	int getMonth(){
		return month;
	}
	void setMonth(int m){
		this.month = m;
	}
	int getYear(){
		return y;
	}
	void setYear(int y){
		this.year = y;
	}
	int getHour(){
		return hour;
	}
	void setHour(int h){
		this.hour = h;
	}
	int getMinute(){
		return minute;
	}
	void setMinute(int m){
		this.minute = m;
	}
}