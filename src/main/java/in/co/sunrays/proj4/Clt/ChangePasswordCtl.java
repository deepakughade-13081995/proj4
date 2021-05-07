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
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Change Password functionality Controller. Performs operation for Change
 * Password
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name="ChangePasswordCtl",urlPatterns={"/ctl/ChangePasswordCtl"})
public class ChangePasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_CHANGE_MY_PROFILE="My profile";
	
	
	//public static final String OP_CHANGE_MY_PROFILE="my profile";
	
	public static final String OP_SAVE="save";
	
	private static Logger log=Logger.getLogger(ChangePasswordCtl.class);
	
	
protected boolean validate(HttpServletRequest request){
	System.out.println("validate start");
	log.debug("ChangePasswordCtl Method Validate Started");
	
boolean pass=true;
	
	//String op=request.getParameter("operation");
	String op=DataUtility.getString(request.getParameter("operation"));
	
	/*if(OP_SAVE.equalsIgnoreCase(op)){
	
	return pass;
	}
	*/
	if(OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)){
		
		return pass;
	}
	
	if(DataValidator.isNull(request.getParameter("oldPassword"))){
		request.setAttribute("oldPassword1",PropertyReader.getValue("error.require","Old Password"));
		
		pass=false;
	
		}
	else if(!DataValidator.isPassword(request.getParameter("oldPassword"))){
		request.setAttribute("oldPassword1",PropertyReader.getValue("error.password"," Invalid Old "));
		
		pass=false;
	
		}
	
	
	
	if(DataValidator.isNull(request.getParameter("newPassword"))){
		request.setAttribute("newPassword1",PropertyReader.getValue("error.require","New Password"));
		
		pass=false;
		
	}
	
	else if(!DataValidator.isPassword(request.getParameter("newPassword"))){
		request.setAttribute("newPassword1",PropertyReader.getValue("error.password","Invalid "));
		
		pass=false;
		
	}
	

	
	if(DataValidator.isNull(request.getParameter("confirmPassword"))){
		
		request.setAttribute("confirmPassword1",PropertyReader.getValue("error.require","Confirm Password"));
		pass=false;
	}
	
	else if(!DataValidator.isPassword(request.getParameter("confirmPassword"))){
		
		request.setAttribute("confirmPassword1",PropertyReader.getValue("error.password","Invalid Confirm "));
		pass=false;
	}
	
		
	if(!request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))
    		 &&!"".equals(request.getParameter("confirmPassword"))){
    	 request.setAttribute("confirmPassword1","New and confirm password is not matching");
    	 pass=false;
    	 
    	 
     }
     
     log.debug("ChangePasswordCtl Method validate Ended");
     System.out.println("validate end");
     return pass;
	}
	
protected BaseBean populateBean(HttpServletRequest request){
	
	log.debug("ChangePasswordCtl Method populatebean Started");
	System.out.println("populate start");
	UserBean bean=new UserBean();
	
	bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));
	
	bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
	
	populateDTO(bean, request);
	
    log.debug("ChangePasswordCtl Method populatebean Ended");
    System.out.println("poulate end");
    return bean;

}

/**
 * Display Logics inside this method
 */

	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
	ServletUtility.forward(getView(), request, response);
	
	}

/**
 * Submit logic inside it
 */


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("doPost start");
	HttpSession session=request.getSession();
	
	log.debug("ChangePasswordCtl Method doGet Started");
	
	String op=DataUtility.getString(request.getParameter("operation"));
	
	UserModel model=new UserModel();
	
	UserBean bean=(UserBean)populateBean(request);
	
	UserBean UserBean = (UserBean) session.getAttribute("user");
	
	String newPassword =request.getParameter("newPassword");
	
	long id=UserBean.getId();
	
	      if(OP_SAVE.equalsIgnoreCase(op)){
		
		  try {
		  boolean flag=model.changePassword(id,bean.getPassword(),newPassword);
		  if(flag==true){
			    bean=model.findByLogin(UserBean.getLogin());
				session.setAttribute("user",bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("password have been changed successfuly",request);
	            	 
		  }
		  
		} catch (RecordNotFoundException e) {
			ServletUtility.setErrorMessage("Invalid Old Password !! ",
                    request);

		
		} catch (ApplicationException e) {
			 log.error(e);
             ServletUtility.handleException(e, request, response);
             
             return;
		}}
		  else if(OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)){
			 ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
	            return;
		  }
		   ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
	        log.debug("ChangePasswordCtl Method doGet Ended");
	        System.out.println("doPast end");
	}

@Override
protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CHANGE_PASSWORD_VIEW;

	}

}
