package in.co.sunrays.proj4.Clt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mchange.v2.sql.filter.SynchronizedFilterCallableStatement;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name="LoginCtl",urlPatterns={"/LoginCtl"})
public class LoginCtl extends BaseCtl {

   // private static final long serialVersionUID = 1L; 
    public static final String OP_REGISTER = "Register";
    public static final String OP_SIGN_IN = "SignIn";
    public static final String OP_SIGN_UP = "SignUp";
    public static final String OP_LOG_OUT = "logout";

  private static Logger log = Logger.getLogger(LoginCtl.class);

  @Override//method of base controller
  protected boolean validate(HttpServletRequest request) {

        log.debug("LoginCtl Method validate Started");

        boolean pass = true;

        String op = request.getParameter("operation");
       // System.out.println(op);
        
        
        if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
            return pass;
        }

        String login = request.getParameter("login");

        if (DataValidator.isNull(login)) {
            request.setAttribute("login",PropertyReader.getValue("error.require", "Login Id"));
            pass = false;
        } else if (!DataValidator.isEmail(login)) {
            request.setAttribute("login",PropertyReader.getValue("error.email", "Invalid "));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("password"))) {
            request.setAttribute("password",PropertyReader.getValue("error.require", "Password"));
            pass = false;
        }
        else  if (!DataValidator.isPassword(request.getParameter("password"))) {
            request.setAttribute("password",PropertyReader.getValue("error.password", "Invalid"));
            pass = false;
        }


        log.debug("LoginCtl Method validate Ended");

        return pass;
    }

    @Override//method of base controller
    protected BaseBean populateBean(HttpServletRequest request) {

        log.debug("LoginCtl Method populatebean Started");

        UserBean bean = new UserBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setLogin(DataUtility.getString(request.getParameter("login")));
        bean.setPassword(DataUtility.getString(request.getParameter("password")));

        log.debug("LoginCtl Method populatebean Ended");

        return bean;//it is object of baseBean
    }

    /**
     * Display Login form
     * 
     */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       
        log.debug(" Method doGet Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        /*UserModel model = new UserModel();
        RoleModel role = new RoleModel();

        long id = DataUtility.getLong(request.getParameter("id"));
        if (id > 0) {
            UserBean userbean;
            try {
                userbean = model.findByPK(id);
                System.out.println(userbean.getFirstName());
                ServletUtility.setBean(userbean, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
*/        if (OP_LOG_OUT.equals(op)) {
	
	        HttpSession session = request.getSession(true);
            session = request.getSession();
            ServletUtility.setSuccessMessage("Logout successfully!!!!!", request);
            session.invalidate();
            
           

            ServletUtility.forward(getView(), request, response);
            
            return;
        }

        ServletUtility.forward(getView(), request, response);

        log.debug("UserCtl Method doPost Ended");

    }
 
    /**
     * Submitting or login action performing
     * 
     */
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
       

       // System.out.println("inside dopost");
        
        log.debug(" Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        // System.out.println("operation"+op);
        // get model
       
        //long id = DataUtility.getLong(request.getParameter("id"));

        //System.out.println("id="+id); 
        if (OP_SIGN_IN.equalsIgnoreCase(op)) {

        	
            UserBean bean = (UserBean) populateBean(request);
            UserModel model = new UserModel();
            
            try {

                bean = model.authenticate(bean.getLogin(), bean.getPassword());
                
                if(bean != null) {
                	 HttpSession session = request.getSession(true);
                      session.setAttribute("user", bean);
                    
                   
                    long rollId = bean.getRoleId();
                    RoleModel role = new RoleModel();
                    RoleBean rolebean = role.findByPK(rollId);
                    
                     if(rolebean != null) {
                     session.setAttribute("role", rolebean.getName());
                    }
                     
                     
                    String str=(String)session.getAttribute("URI");
                    
                    if(str==null || "null".equalsIgnoreCase(str)){
                    ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
                     return;
                     
                    }
                    
                    else{
                    	
                    	if(rollId==1 ||rollId==2 ||rollId==3 || rollId==4 || rollId==5){
                    		ServletUtility.redirect(str, request, response);
                    		
                    	}
                    	else{
                    		
                      ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
                    	}return;
                    }
                    
                } else {
                    bean =(UserBean) populateBean(request);
                    ServletUtility.setBean(bean, request);
                    ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
                }

            } catch (ApplicationException e) {
               e.printStackTrace();
            	log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

                } else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request,response);
            return;

        }

        ServletUtility.forward(getView(), request,response);

        log.debug("UserCtl Method doPost Ended");
    }

    @Override
    protected String getView() {
        return ORSView.LOGIN_VIEW;
    }

}
