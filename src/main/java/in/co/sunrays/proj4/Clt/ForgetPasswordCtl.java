package in.co.sunrays.proj4.Clt;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Forget Password functionality Controller. Performs operation for Forget
 * Password
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@ WebServlet(name="ForgetPasswordCtl",urlPatterns={"/ForgetPasswordCtl"})
public class ForgetPasswordCtl extends BaseCtl {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("ForgetPasswordCtl Method validate Started");

        boolean pass = true;

        String login = request.getParameter("login");

        if (DataValidator.isNull(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.require", "Email Id"));
            pass = false;
        } else if (!DataValidator.isEmail(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.email", "Invalid"));
            pass = false;
        }
        log.debug("ForgetPasswordCtl Method validate Ended");

        return pass;
    }

   @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        log.debug("ForgetPasswordCtl Method populatebean Started");

        UserBean bean = new UserBean();

        bean.setLogin(DataUtility.getString(request.getParameter("login")));

        log.debug("ForgetPasswordCtl Method populatebean Ended");

        return bean;
    }

    /**
     * DIsplay Concept are there
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("ForgetPasswordCtl Method doGet Started");

        ServletUtility.forward(getView(), request, response);

    }

    /**
     * Submit Concepts
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("ForgetPasswordCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        UserBean bean = (UserBean) populateBean(request);

        // get model
        UserModel model = new UserModel();

        if (OP_GO.equalsIgnoreCase(op)) {

            try {
                model.forgetPassword(bean.getLogin());
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage(
                        "Password has been sent to your email id.", request);
            } catch (RecordNotFoundException e) {
                ServletUtility.setErrorMessage(e.getMessage(), request);
                log.error(e);
            } catch (ApplicationException e) {
            	e.printStackTrace();
                log.error(e);
                //System.out.println("inside forget");
                ServletUtility.handleException(e, request, response);
                return;
            }
            ServletUtility.forward(getView(), request, response);
        }
        else if(OP_RESET.equalsIgnoreCase(op)){
        	
        	ServletUtility.redirect(ORSView.FORGET_PASSWORD_CTL, request, response);
        	
        }

        log.debug("ForgetPasswordCtl Method doPost Ended");
    }

    @Override
    protected String getView() {
        return ORSView.FORGET_PASSWORD_VIEW;
    }

}
