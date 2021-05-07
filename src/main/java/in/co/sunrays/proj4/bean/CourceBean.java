package in.co.sunrays.proj4.bean;

public class CourceBean extends BaseBean {


	private String name;
	private long cource_id;
	private int duration;
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getCourceId() {
		return cource_id;
	}
	public void setCourceId(long cource_id) {
		this.cource_id = cource_id;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
	
	
}
