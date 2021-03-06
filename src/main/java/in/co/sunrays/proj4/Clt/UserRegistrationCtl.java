package in.co.sunrays.proj4.Clt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * User registration functionality Controller. Performs operation for User
 * Registration
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name="UserRegistrationCtl",urlPatterns={"/UserRegistrationCtl"})
public class UserRegistrationCtl extends BaseCtl{

    public static final String OP_SIGN_UP = "SignUp";

    private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("UserRegistrationCtl Method validate Started");

        boolean pass = true;
        
       // String login = request.getParameter("login");
       // String dob = request.getParameter("dob");
        //System.out.println(request.getParameter("gender"));
        //System.out.println("date format"+dob);

        if (DataValidator.isNull(request.getParameter("firstName"))) {
            request.setAttribute("firstName",
                    PropertyReader.getValue("error.require", "First Name"));
            pass = false;
        }
        
        else if (!DataValidator.isName(request.getParameter("firstName"))) {
            request.setAttribute("firstName",
                    PropertyReader.getValue("error.name", "Invalid "));
            pass = false;
        }
       
        
        if (DataValidator.isNull(request.getParameter("lastName"))) {
            request.setAttribute("lastName",
                    PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }
        
        else if (!DataValidator.isName(request.getParameter("lastName"))) {
            request.setAttribute("lastName",
                    PropertyReader.getValue("error.name", "Invalid "));
            pass = false;
        }
      
        
        if (DataValidator.isNull(request.getParameter("login"))) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.require", "Login Id"));
            pass = false;
        }
        

        else if (!DataValidator.isEmail(request.getParameter("login"))) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.email","Invalid "));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("password"))) {
            request.setAttribute("password",
                    PropertyReader.getValue("error.require", "Password"));
            pass = false;
        }
        

        else if (!DataValidator.isPassword(request.getParameter("password"))) {
            request.setAttribute("password",
                    PropertyReader.getValue("error.password", "Invalid "));
            pass = false;
        }
        
        
        if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
            request.setAttribute("confirmPassword", PropertyReader.getValue(
                    "error.require", "Confirm Password"));
            pass = false;
        }

        else if (!DataValidator.isPassword(request.getParameter("confirmPassword"))) {
            request.setAttribute("confirmPassword", PropertyReader.getValue(
                    "error.password", "Invalid "));
            pass = false;
        }
        else if (!request.getParameter("password").equals(
                request.getParameter("confirmPassword"))
                && !"".equals(request.getParameter("confirmPassword"))) {
        	request.setAttribute(
                    "confirmPassword","Password & Confirm Password is not matching.");

            pass = false;
        }

        
        
        if (DataValidator.isNull(request.getParameter("gender"))) {
            request.setAttribute("gender",
                    PropertyReader.getValue("error.require", "Gender"));
            pass = false;
        }
        
         if(DataValidator.isNull(request.getParameter("mobile"))){
        	request.setAttribute("mobile",PropertyReader.getValue("error.require","mobile"));
        	pass=false;
        	 }
         else if(!DataValidator.isMobileNum(request.getParameter("mobile"))){
        	 request.setAttribute("mobile",PropertyReader.getValue("error.mobile","Invalid mobile"));
        	 pass=false;
         }
        	 
         
       
        if (DataValidator.isNull( request.getParameter("dob"))) {
            request.setAttribute("dob",
                    PropertyReader.getValue("error.require", "Date Of Birth"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("address"))) {
            request.setAttribute("address",
                    PropertyReader.getValue("error.require", "Address"));
            pass = false;
        }
        
        else if (!DataValidator.isAddress(request.getParameter("address"))) {
            request.setAttribute("address",
                    PropertyReader.getValue("error.address", "Invalid address "));
            pass = false;
        }
       
        
               log.debug("UserRegistrationCtl Method validate Ended");
       System.out.println("boolean value"+pass);
        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        log.debug("UserRegistrationCtl Method populatebean Started");

        UserBean bean = new UserBean();

       // bean.setId(DataUtility.getLong(request.getParameter("id")));

        bean.setRoleId(RoleBean.STUDENT);

        bean.setFirstName(DataUtility.getString(request
                .getParameter("firstName")));

        bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

        bean.setLogin(DataUtility.getString(request.getParameter("login")));

        bean.setPassword(DataUtility.getString(request.getParameter("password")));

        bean.setConfirmPassword(DataUtility.getString(request
                .getParameter("confirmPassword")));
       

        bean.setGender(DataUtility.getString(request.getParameter("gender")));
        bean.setAddress(DataUtility.getString(request.getParameter("address")));
        
        bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
        bean.setDob(DataUtility.getDate(request.getParameter("dob")));

        populateDTO(bean, request);

        log.debug("UserRegistrationCtl Method populatebean Ended");

        return bean;
    }

    /**
     * Display concept of user registration
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	//System.out.println("get method user resistration");
        log.debug("UserRegistrationCtl Method doGet Started");
        ServletUtility.forward(getView(), request, response);

    }
    


    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("op"+request.getParameter("operation"));
        log.debug("UserRegistrationCtl Method doPost Started");
        
        String op = DataUtility.getString(request.getParameter("operation"));
        
        
        // get model
        UserModel model = new UserModel();
        //long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SIGN_UP.equalsIgnoreCase(op)){
            UserBean bean = (UserBean) populateBean(request);
            try {System.out.println("yaha tk aa gya");
                long pk = model.registerUser(bean);
                //System.out.println("dopost user r method");
               // bean.setId(pk);
                //request.getSession().setAttribute("UserBean", bean);
               ServletUtility.setBean(bean, request);
                
               ServletUtility.setSuccessMessage("you have been successfully register", request);
               ServletUtility.forward(getView(), request, response);
                
            } catch (ApplicationException e) {
            	e.printStackTrace();
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                log.error(e);
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Login id already exists",request);
                ServletUtility.forward(getView(), request, response);
            }
        }
        else if(OP_RESET.equalsIgnoreCase(op)){
        	 ServletUtility.forward(getView(), request, response);
        	
        }
        
        log.debug("UserRegistrationCtl Method doPost Ended");
	}

	@Override
    protected String getView() {
        return ORSView.USER_REGISTRATION_VIEW;
    }


		}
   
