package in.co.sunrays.proj4.bean;

import java.util.Date;
//import java.sql.Timestamp;

public class TimetableBean extends BaseBean{
	
	
	 private int subject_Id;
	 private String subject_Name;
	 private int cource_Id;
	 private String cource_Name;
	 private String exam_time;
	 private Date exam_Date;
	 private String semester;
	
	public int getSubject_Id() {
		return subject_Id;
	}
	public void setSubject_Id(int subject_Id) {
		this.subject_Id = subject_Id;
	}
	public String getSubject_Name() {
		return subject_Name;
	}
	public void setSubject_Name(String subject_Name) {
		this.subject_Name = subject_Name;
	}
	public int getCource_Id() {
		return cource_Id;
	}
	public void setCource_Id(int cource_Id) {
		this.cource_Id = cource_Id;
	}
	public String getCource_Name() {
		return cource_Name;
	}
	public void setCource_Name(String cource_Name) {
		this.cource_Name = cource_Name;
	}
	public String getExam_time() {
		return exam_time;
	}
	public void setExam_time(String exam_time) {
		this.exam_time = exam_time;
	}
	public Date getExam_Date() {
		return exam_Date;
	}
	public void setExam_Date(Date date) {
		this.exam_Date = date;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return cource_Name;
	}
	 
	 

}
