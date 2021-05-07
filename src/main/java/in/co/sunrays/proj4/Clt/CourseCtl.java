package in.co.sunrays.proj4.Clt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourceModel;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Course functionality Controller. Performs operation for add, update,
 * delete and get Course
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


@ WebServlet(name="CourceCtl",urlPatterns={"/ctl/CourceCtl"})

public class CourseCtl extends BaseCtl { 
	

private static Logger log = Logger.getLogger(CourseCtl.class);
	
protected boolean validate(HttpServletRequest request){
		
		log.debug("CourceCtl Method validate Started");
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("name"))){
			
			
			request.setAttribute("name",PropertyReader.getValue("error.require","Name"));
			
			pass=false;
		}
		else if(!DataValidator.isName1(request.getParameter("name"))){
			
			
			request.setAttribute("name",PropertyReader.getValue("error.course","Invalid "));
			
			pass=false;
		}
		
		
		
		if(DataValidator.isNull(request.getParameter("duration"))){
			
			request.setAttribute("duration",PropertyReader.getValue("error.require","Duration"));
			
			pass=false;
		}
		else if(!DataValidator.isInteger(request.getParameter("duration"))){
			
			request.setAttribute("duration",PropertyReader.getValue("error.integer","Duration "));
			
			pass=false;
		}
		
		 log.debug("CourceCtl Method validate Ended");
               System.out.println("validate");
	        return pass;
		}
	
	
protected BaseBean populateBean(HttpServletRequest request){
		
		 log.debug("RoleCtl Method populatebean Started");
		 
		 CourceBean bean=new CourceBean();
		 
		 bean.setId(DataUtility.getLong(request.getParameter("id")));
		 System.out.println("id  "+bean.getId());
		 
		 bean.setName(DataUtility.getString(request.getParameter("name")));
		  System.out.println("name  "+bean.getName());
		  
		 bean.setDuration(DataUtility.getInt(request.getParameter("duration")));
		 System.out.println("duration  "+bean.getDuration());
		 
		 
		  log.debug("CourceCtl Method populatebean Ended");
		  System.out.println("populate Baean");
		  populateDTO(bean,request);
	        return bean;
		 }

	
/**
 * Contains Display logics
 */

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
 log.debug("RoleCtl Method doGet Started");
	 System.out.println("get suru");
	 String op=DataUtility.getString(request.getParameter("operation"));
	 
	 CourceModel model=new CourceModel();
	 
	 long id=DataUtility.getLong(request.getParameter("id"));
	 
	 if(id>0 || op!=null){
		 
		 CourceBean bean;
		 
		 try {
			bean=model.findByPk(id);
			ServletUtility.setBean(bean, request);
			
		} catch (ApplicationException e) {
	       
			 log.error(e);
             ServletUtility.handleException(e, request, response);
             return;

		}
	 }
	 System.out.println("get puri");
	 ServletUtility.forward(getView(), request, response);
     log.debug("CourceCtl Method doGetEnded");
     System.out.println("do get");

	
}	
/**
 * Contains Submit logics
 */


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	log.debug("RoleCtl Method doGet Started");
	 
	 CourceModel model = new CourceModel();
	 
	 String op=DataUtility.getString(request.getParameter("operation"));
	 
	long id=DataUtility.getLong(request.getParameter("id"));
	
	 if (OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)) {
		 System.out.println("save Start");
           CourceBean bean = (CourceBean)populateBean(request);

           try {
				if(id>0){
				model.update(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Course updated successfully", request);
				//ServletUtility.forward(getView(), request, response);
			    }
				else{
					
					try{
						System.out.println("upr");
					long pk=model.add(bean);
					System.out.println("niche");
					ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage("Course is successfully saved",
	                        request);
					}catch(Exception e){
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Course already exists!!", request);
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
			
           
	 } else if (OP_RESET.equalsIgnoreCase(op)) {
     	System.out.println("cancle Start");
         ServletUtility.forward(getView(), request, response);
     	System.out.println("cancle end");
         return;
         
     }
	 else if (OP_CANCEL.equalsIgnoreCase(op)) {

         ServletUtility.redirect(ORSView.COURCE_LIST_CTL, request,
                 response);
         return;

     }

	 ServletUtility.forward(getView(), request, response);
	 
	 log.debug("CourceCtl Method doPOst Ended");
}

@Override
protected String getView() {
	
	return ORSView.COURCE_VIEW;
}

}
