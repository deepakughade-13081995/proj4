package in.co.sunrays.proj4.Clt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name="MyProfileCtl",urlPatterns={"/ctl/MyProfileCtl"})
public class MyProfileCtl extends BaseCtl {
	
	public static final String OP_CHANGE_MY_PASSWORD="changePassword";
	private static Logger log=Logger.getLogger(MyProfileCtl.class);
	
	protected boolean validate(HttpServletRequest request){
		
        log.debug("MyProfileCtl Method validate Started");
        
        boolean pass=true;
        
        String op=DataUtility.getString(request.getParameter("operation"));
       

		if(OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)|| OP_SAVE.equalsIgnoreCase(op)){
			
			return pass;
		}
		
	
      /*  if(OP_SAVE.equalsIgnoreCase(op)){
        	return pass;
        }
        
      */  if (DataValidator.isNull(request.getParameter("firstName"))){
		request.setAttribute("firstName",PropertyReader.getValue("error.require","First Name"));
		pass=false;
		}
      
      else if  (!DataValidator.isName(request.getParameter("firstName"))){
  		request.setAttribute("firstName",PropertyReader.getValue("error.name","Invalid "));
  		pass=false;
  		}
  		
		
		if(DataValidator.isNull(request.getParameter("lastName"))){
			request.setAttribute("lastName",PropertyReader.getValue("error.require","LastName"));
			pass=false;
			
			
		}
		else if(!DataValidator.isName(request.getParameter("lastName"))){
			request.setAttribute("lastName",PropertyReader.getValue("error.name","Invalid "));
			pass=false;
				}
		
		
		if(DataValidator.isNull(request.getParameter("dob")))
		{
		request.setAttribute("dob",PropertyReader.getValue("error.require","Date of Birth"));
		pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("mobileNo"))){
			
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require","mobile number "));
			pass=false;
		}
		
		else if(!DataValidator.isMobileNum(request.getParameter("mobileNo"))){
			
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile","Invalid mobile "));
			pass=false;
		}
		if(DataValidator.isNull(request.getParameter("gender"))){
			request.setAttribute("gender",PropertyReader.getValue("error.require","Gender"));
			pass=false;
			
			
		}
	
		
		 log.debug("MyProfileCtl Method validate Ended");
         System.out.println("validate method");
	        return pass;
}
	
	
	
	protected BaseBean populateBean(HttpServletRequest request){
		
		 log.debug("MyProfileCtl Method populatebean Started");
		 
		 UserBean bean=new UserBean();
		 
		 bean.setId(DataUtility.getLong(request.getParameter("id")));
		 bean.setLogin(DataUtility.getString(request.getParameter("login")));
		 bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		
		 bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		 bean.setGender(DataUtility.getString(request.getParameter("gender")));
	     bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		 bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		 System.err.println(bean.getDob());
		populateDTO(bean,request);
		System.out.println("populate bean");
		return bean;
	
	}

	 /**
     * Display Concept for viewing profile page view
     */
        
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		 log.debug("MyprofileCtl Method doGet Started");
		
	    UserBean UserBean=(UserBean)session.getAttribute("user");
	    
		long id=UserBean .getId();
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		UserModel model=new UserModel();
		
		if(id>0 || op!=null){
			
			UserBean bean;
			try {
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				log.error(e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(),request, response);
		System.out.println("doget yaha tk");
		log.debug("MyProfile Method doGet Ended");
		
	
	}
	
	/**
     * Submit Concept
     */
    

protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("doPost");
		HttpSession session =request.getSession(true);
		log.debug("MyprofileCtl Method doPost Started");
		
		UserBean userBean=(UserBean)session.getAttribute("user");
		long id=userBean.getId();
		String op=DataUtility.getString(request.getParameter("operation"));
		
		UserModel model=new UserModel();
	
		if(OP_SAVE.equalsIgnoreCase(op)){ 
			UserBean bean=(UserBean)populateBean(request);
	
			 try {
	                if (id > 0) {
	                    userBean.setFirstName(bean.getFirstName());
	                    userBean.setLastName(bean.getLastName());
	                    userBean.setGender(bean.getGender());
	                    userBean.setMobileNo(bean.getMobileNo());
	                    userBean.setDob(bean.getDob());
	                    model.update(userBean);
                    
	               }
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage("Profile has been updated Successfully ", request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Login id already exists",request);
	            }
	        } else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,response);
	      
	            return;

	        }

	        ServletUtility.forward(getView(), request, response);

	        log.debug("MyProfileCtl Method doPost Ended");
	    }
				
			
		
	

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
        return ORSView.MY_PROFILE_VIEW;

	}

}
