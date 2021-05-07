package in.co.sunrays.proj4.bean;

// TODO: Auto-generated Javadoc
/**
 * College JavaBean encapsulates College attributes.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class CollegeBean extends BaseBean {

    /** Name of College. */
    private String name;
    
    /** Address of College. */
    private String address;
    
    /** State of College. */
    private String state;
    
    /** City of College. */
    private String city;
    
    /** Phoneno of College. */
    private String phoneNo;

    /**
     * accessor.
     *
     * @return the name
     */
 
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phoneNo;
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phoneNo = phone;
	}
	
	/* (non-Javadoc)
	 * @see in.co.sunrays.proj4.bean.DropdownListBean#getKey()
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	
	/* (non-Javadoc)
	 * @see in.co.sunrays.proj4.bean.DropdownListBean#getValue()
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
}
