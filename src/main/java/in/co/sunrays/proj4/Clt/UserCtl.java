package in.co.sunrays.proj4.Clt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * * User functionality Controller. Performs operation for add, update and get
 * User
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl"})

public class UserCtl extends BaseCtl {
	
	  /** The Constant serialVersionUID. */
  	private static final long serialVersionUID = 1L;

	    /** The log. */
    private static Logger log = Logger.getLogger(UserCtl.class);
	    
	    
	    /* (non-Javadoc)
    	 * @see in.co.sunrays.proj4.Clt.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
    	 */                         
 protected void preload(HttpServletRequest request){
	    	
	   RoleModel model=new RoleModel();
	    	
	    	try {
				List l=model.list();
				 request.setAttribute("roleList", l);
			} catch (ApplicationException e) {
		         
				log.error(e);
				e.printStackTrace();
			}
	   
	    }
	    
	 /* (non-Javadoc)
 	 * @see in.co.sunrays.proj4.Clt.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 	 */
 
 	protected boolean validate(HttpServletRequest request){
	    	
	    	log.debug("UserCtl Method validate Started");
	    	  System.out.println("validate start");	
	    	boolean pass = true;
	    	
	    	
	    	 String login = request.getParameter("login");
	         String dob = request.getParameter("dob");
	         
	         
	         if(DataValidator.isNull(request.getParameter("firstName"))){
	        	 
	        	 request.setAttribute("firstName",PropertyReader.getValue("error.require","First Name"));
	        	 
	        	 pass=false;
	        	 
	         }
	         else if(!DataValidator.isName(request.getParameter("firstName"))){
	        	 
	        	 
	        	 request.setAttribute("firstName",PropertyReader.getValue("error.name","Invalid"));
	        	 
	        	 pass=false;
	         }
            
	         
 if(DataValidator.isNull(request.getParameter("lastName"))){
	        	 
	        	 request.setAttribute("lastName",PropertyReader.getValue("error.require","Last Name"));
	        	 
	        	 pass=false;
	         }
              else if(!DataValidator.isName(request.getParameter("lastName"))){
	        	 
	        	 
	        	 request.setAttribute("lastName",PropertyReader.getValue("error.name","Invalid"));
	        	 
	        	 pass=false;
	         }
	        

           if(DataValidator.isNull(login)){
        	   
        	   request.setAttribute("login",PropertyReader.getValue("error.require","Login Id"));
        	   
        	   pass=false;
           }
           else if(!DataValidator.isEmail(login)){
        	   
        	   request.setAttribute("login",PropertyReader.getValue("error.email", "Invalid"));
               
        	   pass = false;
           }
           
           if (DataValidator.isNull(request.getParameter("password"))) {
               
        	   request.setAttribute("password",PropertyReader.getValue("error.require", "Password"));
              
        	   pass = false;
           
           }
           else if(!DataValidator.isPassword(request.getParameter("password"))){
        	   
        	   request.setAttribute("password",PropertyReader.getValue("error.password","Invalid"));
        	   
           }
           
           if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
               request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
               
               pass = false;
           }
           else if(!DataValidator.isPassword(request.getParameter("confirmPassword"))){
        	   
        	   request.setAttribute("confirmPassword",PropertyReader.getValue("error.password","Invalid confirm"));
        	   
           }

           if (DataValidator.isNull(request.getParameter("gender"))) {
               request.setAttribute("gender",PropertyReader.getValue("error.require", "Gender"));
               pass = false;
           }
           
           if (DataValidator.isNull(request.getParameter("roleId"))) {
               request.setAttribute("roleId",PropertyReader.getValue("error.require", "Role"));
               pass = false;
           }
          
           
           if (DataValidator.isNull(dob)) {
               
        	   request.setAttribute("dob",PropertyReader.getValue("error.require", "Date Of Birth"));
              
        	  pass = false;
        	  
           } else if (!DataValidator.isDate(dob)) {
        	   
               request.setAttribute("dob",PropertyReader.getValue("error.date", "Date Of Birth"));
               
               pass = false;
           }
           if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
                   && !"".equals(request.getParameter("confirmPassword"))) {
        	   
               request.setAttribute("confirmPassword","Password & Confirm Password is not matching.");
               
               pass = false;
           }
           
           if(DataValidator.isNull(request.getParameter("mobileNo"))){
        	   request.setAttribute("mobileNo",PropertyReader.getValue("error.require","Mobile Number"));
        	  
        	   pass=false;
           }
           else if(!DataValidator.isMobileNum(request.getParameter("mobileNo"))){
        	   
        	   request.setAttribute("mobileNo",PropertyReader.getValue("error.mobile","Invalid mobile"));
           }
           
           if(DataValidator.isNull(request.getParameter("address"))){
	        	 
	        	 request.setAttribute("address",PropertyReader.getValue("error.require","Address"));
	        	 
	        	 pass=false;
	        	 
	         }
	         else if(!DataValidator.isAddress(request.getParameter("address"))){
	        	 
	        	 
	        	 request.setAttribute("address",PropertyReader.getValue("error.address","Address"));
	        	 
	        	 pass=false;
	         }
	       
           log.debug("UserCtl Method validate Ended");
           System.out.println("validate end");	
           return pass;
	    }
	    
	    	protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("UserCtl Method populatebean Started");
	        System.out.println("populate start");	
	        UserBean bean = new UserBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

	        bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

	        bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
	        
	        bean.setAddress(DataUtility.getString(request.getParameter("address")));

	        bean.setLogin(DataUtility.getString(request.getParameter("login")));

	        bean.setPassword(DataUtility.getString(request.getParameter("password")));

	        bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
	        
	        bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

	        bean.setGender(DataUtility.getString(request.getParameter("gender")));

	        bean.setDob(DataUtility.getDate(request.getParameter("dob")));

	        populateDTO(bean, request);

	        log.debug("UserCtl Method populatebean Ended");
	        System.out.println("populate end");	
	        return bean;
	    }
	    
	        /**
	          * Contains DIsplay logics
	         */
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			log.debug("UserCtl Method doGet Started");
			
	        String op = DataUtility.getString(request.getParameter("operation"));
	        System.out.println("operation in userctl doget"+op);	
	        // get model
	        UserModel model = new UserModel();
	        
	        long id = DataUtility.getLong(request.getParameter("id"));
	        
	        if (id > 0 || op != null) {

		    System.out.println("in id > 0  condition");
		        
		        UserBean bean;
		            try {
		                bean = model.findByPK(id);
		                System.out.println("get end");	
		                ServletUtility.setBean(bean, request);
		            } catch (ApplicationException e) {
		            	e.printStackTrace();
		                log.error(e);
		                ServletUtility.handleException(e, request, response);
		                return;
	}
	        }
	            

		        ServletUtility.forward(getView(), request, response);

	            }
		
		    /**
		     * Contains submit logics
		     */
		
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			 log.debug("UserCtl Method doPost Started");
			 
		        String op = DataUtility.getString(request.getParameter("operation"));
		        // get model
		       
		   
		        long id = DataUtility.getLong(request.getParameter("id"));
		        
		        if (OP_SAVE.equalsIgnoreCase(op)||(OP_UPDATE.equalsIgnoreCase(op))) {
		        	
		        	 UserModel model = new UserModel();
		            UserBean bean = (UserBean) populateBean(request);
		         	
		            try {
						if(id>0){
						model.update(bean);
						ServletUtility.setBean(bean, request);
						ServletUtility.setSuccessMessage("User is updated successfully", request);
						//ServletUtility.forward(getView(), request, response);
					    }
						else{
							
							try{
							long pk=model.add(bean);
							ServletUtility.setBean(bean, request);
			                ServletUtility.setSuccessMessage("User is successfully saved",
			                        request);
							}catch(Exception e){
							ServletUtility.setBean(bean, request);
							ServletUtility.setErrorMessage("User already exists!!", request);
							}
						}
					
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						log.error(e);
		                ServletUtility.handleException(e, request, response);
		                return;
		         
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						      
					}
   } /*else if (OP_DELETE.equalsIgnoreCase(op)) {
		        	  System.out.println("delete start");	
		            UserBean bean = (UserBean) populateBean(request);
		            try {
		                model.delete(bean);
		                System.out.println("delete end");	
		                ServletUtility.redirect(ORSView.USER_LIST_CTL, request,response);
		               
		                return;
		                
		            } catch (ApplicationException e) {
		                log.error(e);
		               
		                ServletUtility.handleException(e, request, response);
		               
		                return;
		                
		            }
*/
		         else if (OP_CANCEL.equalsIgnoreCase(op)) {
   
		        	  System.out.println("cancle start");	
		        	
		            ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
		            return;
		        }
		        ServletUtility.forward(getView(), request, response);

		        log.debug("UserCtl Method doPostEnded");

		}
		
				@Override
		protected String getView() {
		
			 return ORSView.USER_VIEW;		}
}
