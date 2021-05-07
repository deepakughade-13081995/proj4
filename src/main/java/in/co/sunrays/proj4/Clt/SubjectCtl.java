package in.co.sunrays.proj4.Clt;

/**
 * Subject functionality Controller. Performs operation for add, update,
 * delete and get subject
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourceModel;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.model.StudentModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;
@ WebServlet(name="SubjectCtl",urlPatterns={"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(CourseCtl.class);
	
	
	protected void preload(HttpServletRequest request){
		
		CourceModel model=new CourceModel();
		SubjectModel model1=new SubjectModel();
		
		try {
			List l=model.list();
			//List l1=model1.list();
			
			 request.setAttribute("courceName",l);
			 //request.setAttribute("subjectName",l1);
			System.out.println("preload");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}

	
	
	protected boolean validate(HttpServletRequest request){
			
			log.debug("SubjectCtl Method validate Started");
			
			boolean pass=true;
			
			if(DataValidator.isNull(request.getParameter("courceId"))){
				
				
				request.setAttribute("courceId",PropertyReader.getValue("error.require","Cource Name"));
				
				pass=false;
			}
			
            if(DataValidator.isNull(request.getParameter("sname"))){
				
				
				request.setAttribute("sname",PropertyReader.getValue("error.require","Subject Name"));
				
				pass=false;
			}
            
            else if(!DataValidator.isName1(request.getParameter("sname"))){
				
				
				request.setAttribute("sname",PropertyReader.getValue("error.name","Invalid Subject "));
				
				pass=false;
			}
		
		
		
		
			if(DataValidator.isNull(request.getParameter("description"))){
				
				request.setAttribute("description1",PropertyReader.getValue("error.require","Description"));
				
				pass=false;
				System.out.println("description value=  "+pass);
			}
			 else if(!DataValidator.isName1(request.getParameter("description"))){
					
					
					request.setAttribute("description1",PropertyReader.getValue("error.description","Invalid "));
					
					pass=false;
				}
			
			
			 log.debug("SubjectCtl Method validate Ended");
	               System.out.println("validate");
		        return pass;
			}
		
	protected BaseBean populateBean(HttpServletRequest request){
		
		 log.debug("RoleCtl Method populatebean Started");
		 
		 SubjectBean bean=new SubjectBean();
		 
		 bean.setId(DataUtility.getLong(request.getParameter("id")));
		 System.out.println("id  "+bean.getId());
		 
		 bean.setCourceName(DataUtility.getString(request.getParameter("courceId")));
		  System.out.println("name  "+bean.getCourceName());
		  
		  bean.setCourceId(DataUtility.getInt(request.getParameter("courceId")));
		  
		 bean.setSubjectName(DataUtility.getString(request.getParameter("sname")));
		 System.out.println("duration  "+bean.getSubjectName());
		 
		// bean.setsubjectId(DataUtility.getInt(request.getParameter("subjectId")));
		 
		 bean.setDescription(DataUtility.getString(request.getParameter("description")));
		 System.out.println("duration  "+bean.getDescription());
		 
	 populateDTO(bean,request);
		 
		  log.debug("CourceCtl Method populatebean Ended");
		  System.out.println("populate Baean");
	        return bean;
		 }

	/**
	 * Contains Display logics
	 */

       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		log.debug("SubjectCtl Method doGet Started");
    		 System.out.println("get suru");
    		 String op=DataUtility.getString(request.getParameter("operation"));
    		 
    		 SubjectModel model=new SubjectModel();
    		 
    		 long id=DataUtility.getLong(request.getParameter("id"));
    		 System.out.println("id------ "+id);
    		 
    		 if(id>0 || op!=null){
    			 
    			 SubjectBean bean;
    			 
    			 try {
    				bean=model.findByPk(id);
    				ServletUtility.setBean(bean, request);
    				System.out.println("course-----"+bean.getCourceId()+"    "+bean.getCourceName());
    				
    			} catch (ApplicationException e) {
    		       
    				 log.error(e);
    	             ServletUtility.handleException(e, request, response);
    	             return;

    			}
    		 }
    		 System.out.println("get puri");
    		 ServletUtility.forward(getView(), request, response);
    	     log.debug("SubjectCtl Method doGetEnded");
    	     System.out.println("do get");

    		
			}
    	
    	/**
    	 * Contains Submit logics
    	 */


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			log.debug("SubjectCtl Method doGet Started");
			 
			 SubjectModel model = new SubjectModel();
			 
			 String op=DataUtility.getString(request.getParameter("operation"));
			 
			long id=DataUtility.getLong(request.getParameter("id"));
			
			if(OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)){
				
				  SubjectBean bean = (SubjectBean)populateBean(request);	
							
						
							try {
								if(id>0){
								model.update(bean);
								ServletUtility.setBean(bean, request);
								ServletUtility.setSuccessMessage("subject updated successfully", request);
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
									ServletUtility.setErrorMessage("subject already exists!!", request);
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

		            ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request,
		                    response);
		            return;

		        }

			 ServletUtility.forward(getView(), request, response);
			 
			 log.debug("CourceCtl Method doPOst Ended");

			
			}

		@Override
		protected String getView() {
			// TODO Auto-generated method stub
			return ORSView.SUBJECT_VIEW;
		}

}
