package in.co.sunrays.proj4.Clt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Role functionality Controller. Performs operation for add, update and get
 * Role
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@ WebServlet(name="RoleCtl",urlPatterns={"/ctl/RoleCtl"})
public class RoleCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(RoleCtl.class);
	
	protected boolean validate(HttpServletRequest request){
		
		log.debug("RoleCtl Method validate Started");
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("name"))){
			
			
			request.setAttribute("name",PropertyReader.getValue("error.require","Name"));
			
			pass=false;
		}
		
		else if(!DataValidator.isName1(request.getParameter("name"))){
			
			
			request.setAttribute("name",PropertyReader.getValue("error.role","Invalid "));
			
			pass=false;
		}
		
		
		
		
		if(DataValidator.isNull(request.getParameter("description"))){
			
			request.setAttribute("description",PropertyReader.getValue("error.require","Description"));
			
			pass=false;
		}
		else if(!DataValidator.isName1(request.getParameter("description"))){
			
			request.setAttribute("description",PropertyReader.getValue("error.description","Invalid"));
			
			pass=false;
		}
		
		
		 log.debug("RoleCtl Method validate Ended");
               System.out.println("validate");
	        return pass;
		}
	
	protected BaseBean populateBean(HttpServletRequest request){
		
		 log.debug("RoleCtl Method populatebean Started");
		 
		 RoleBean bean=new RoleBean();
		 
		 bean.setId(DataUtility.getLong(request.getParameter("id")));
		 System.out.println("id  "+bean.getId());
		 
		 bean.setName(DataUtility.getString(request.getParameter("name")));
		  System.out.println("name  "+bean.getName());
		  
		 bean.setDescription(DataUtility.getString(request.getParameter("description")));
		 System.out.println("desc  "+bean.getDescription());
		 
		 populateDTO(bean,request);
		 
		  log.debug("RoleCtl Method populatebean Ended");
		  System.out.println("populate Baean");
	        return bean;
		 }

	 /**
     * Contains Display logics
     */
   
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 log.debug("RoleCtl Method doGet Started");
	 
	 String op=DataUtility.getString(request.getParameter("operation"));
	 
	 RoleModel model=new RoleModel();
	 
	 long id=DataUtility.getLong(request.getParameter("id"));
	 
	 if(id>0 || op!=null){
		 
		 RoleBean bean;
		 
		 try {
			bean=model.findByPK(id);
			ServletUtility.setBean(bean, request);
			
		} catch (ApplicationException e) {
	       
			 log.error(e);
             ServletUtility.handleException(e, request, response);
             return;

		}
	 }
	 ServletUtility.forward(getView(), request, response);
     log.debug("RoleCtl Method doGetEnded");
     System.out.println("do get");
	 }

/**
 * Contains Submit logics
 */


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			 log.debug("RoleCtl Method doGet Started");
			 
			 RoleModel model = new RoleModel();
			 
			 String op=DataUtility.getString(request.getParameter("operation"));
			 
			long id=DataUtility.getLong(request.getParameter("id"));
			
			 if (OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)) {
				 System.out.println("save Start");
		            RoleBean bean = (RoleBean)populateBean(request);


					try {
						if(id>0){
						model.update(bean);
						ServletUtility.setBean(bean, request);
						ServletUtility.setSuccessMessage("Role updated successfully", request);
						//ServletUtility.forward(getView(), request, response);
					    }
						else{
							
							try{
							long pk=model.add(bean);
							ServletUtility.setBean(bean, request);
			                ServletUtility.setSuccessMessage("Data is successfully saved",
			                        request);
							}catch(Exception e){
							ServletUtility.setBean(bean, request);
							ServletUtility.setErrorMessage("Role already exists!!", request);
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
					
		            

		        } else if (OP_DELETE.equalsIgnoreCase(op)) {
		        	System.out.println("delete Start");
		            RoleBean bean = (RoleBean) populateBean(request);
		            try {
		                model.delete(bean);
		                ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,response);
		            	System.out.println("delete end");
		                return;
		                
		            } 
		            catch (ApplicationException e) {
		                log.error(e);
		                ServletUtility.handleException(e, request, response);
		                
		                return;
		                
		            }

		        } else if (OP_RESET.equalsIgnoreCase(op)) {
		        
		            ServletUtility.forward(getView(), request, response);
		      
		            return;
                    
		        }
		        else if (OP_CANCEL.equalsIgnoreCase(op)) {

		            ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
		                    response);
		            return;

		        }


		        ServletUtility.forward(getView(), request, response);

		        log.debug("RoleCtl Method doPOst Ended");
		    }
	
				
		@Override
		   protected String getView() {
			
			return ORSView.ROLE_VIEW;

		}

}
