package in.co.sunrays.proj4.bean;

public class SubjectBean extends BaseBean {

	private long courceId;
	private String courceName;
	private String subjectName;
	private long subjectId;
	private String description;
	
	public long getCourceId() {
		return courceId;
	}
	public void setCourceId(long courceId) {
		this.courceId = courceId;
	}
	public String getCourceName() {
		return courceName;
	}
	public void setCourceName(String courceName) {
		this.courceName = courceName;
	}
	
	public long getsubjectId() {
		return subjectId;
	}
	public void setsubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return subjectName;
	}
	
	
	
}
