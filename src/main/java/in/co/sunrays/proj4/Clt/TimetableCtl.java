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
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.TimetableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CourceModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.TimetableModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Timetable functionality Controller. Performs operation for add, update, delete
 * and get Timetable
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


@ WebServlet(name="TimetableCtl",urlPatterns={"/ctl/TimetableCtl"})
public class TimetableCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	
private static Logger log = Logger.getLogger(TimetableCtl.class);


protected void preload(HttpServletRequest request){
	
	CourceModel model=new CourceModel();
	SubjectModel model1=new SubjectModel();
	
	try {
		List l=model.list();
		List l1=model1.list();
		
		 request.setAttribute("courceName",l);
		 request.setAttribute("subjectName",l1);
		System.out.println("preload");
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log.error(e);
	}
}

protected boolean validate(HttpServletRequest request){
			
			log.debug("TimetableCtl Method validate Started");
			
			boolean pass=true;
			
			if(DataValidator.isNull(request.getParameter("courceId"))){
				
				
				request.setAttribute("courceId",PropertyReader.getValue("error.require","Cource Name"));
		 		
				pass=false;
			}
			
            if(DataValidator.isNull(request.getParameter("subjectId"))){
				
				
				request.setAttribute("subjectId",PropertyReader.getValue("error.require","Subject Name"));
				
				pass=false;
			}
            
 if(DataValidator.isNull(request.getParameter("semester"))){
				
				
				request.setAttribute("semester",PropertyReader.getValue("error.require","Semester Name"));
				
				pass=false;
			}
            
            
 if(DataValidator.isNull(request.getParameter("examtime"))){
		
		
		request.setAttribute("examtime",PropertyReader.getValue("error.require","Exam Time"));
		
		pass=false;
	}
 

 if(DataValidator.isNull(request.getParameter("examdate"))){
		
		
		request.setAttribute("examdate",PropertyReader.getValue("error.require","Exam Date"));
		
		pass=false;
	}
 

 			 log.debug("TimetableCtl Method validate Ended");
	               System.out.println("validate  "+pass);
		        return pass;
			}
	
	
	protected BaseBean populateBean(HttpServletRequest request){
		
		 log.debug("RoleCtl Method populatebean Started");
	
		 System.out.println("populatebean Started");
		 TimetableBean bean=new TimetableBean();
		 
		 bean.setId(DataUtility.getLong(request.getParameter("id")));
		 //System.out.println("id  "+bean.getId());
		 
		 bean.setCource_Id(DataUtility.getInt(request.getParameter("courceId")));
		 //System.out.println("course id"+(request.getParameter("courceId")));
		
		 bean.setSubject_Id(DataUtility.getInt(request.getParameter("subjectId")));
		 
		 bean.setCource_Name(DataUtility.getString(request.getParameter("courceId")));
		  //System.out.println("name  "+bean.getCource_Name());
		  
		 bean.setSubject_Name(DataUtility.getString(request.getParameter("subjectId")));
		 //System.out.println("duration  "+bean.getSubject_Name());
		 
		 bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		 
		 bean.setExam_time(DataUtility.getString(request.getParameter("examtime")));
		 
		 bean.setExam_Date(DataUtility.getDate(request.getParameter("examdate")));
		 
		
		 
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
		 
		 TimetableModel model=new TimetableModel();
		 
		 long id=DataUtility.getLong(request.getParameter("id"));
		 
		 if(id>0 || op!=null){
			 System.out.println("id>0");	 
			 TimetableBean bean =new TimetableBean();
			 
			 try {
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
				
			} catch (ApplicationException e) {
		       
				 log.error(e);
	             ServletUtility.handleException(e, request, response);
	             return;

			}
		 }
		 System.out.println("get puri");
		 ServletUtility.forward(getView(), request, response);
	     log.debug("timetableCtl Method doGetEnded");
	     System.out.println("do get");


   		
		}
   	
   	/**
     * Contains submit logics
     */

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("dopost started");
			
			log.debug("SubjectCtl Method doGet Started");
			 
			 TimetableModel model = new TimetableModel();
			 
			 String op=DataUtility.getString(request.getParameter("operation"));
			 
			long id=DataUtility.getLong(request.getParameter("id"));
			
			
			 if (OP_SAVE.equalsIgnoreCase(op)) {
				 System.out.println("save Start");
		           TimetableBean bean = (TimetableBean)populateBean(request);
		           try {
		               
		                   //model.update(bean);
		                
		               	//System.out.println("if me gaya");
		                   long pk;
						try {
							pk = model.add(bean);
							// bean.setId(pk);
							 ServletUtility.setSuccessMessage("timetable is successfully added",request);
							 ServletUtility.setBean(bean, request);
				               System.out.println("Save end");
						
						} catch (DuplicateRecordException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
				
							ServletUtility.setErrorMessage("Timetable already exists", request);
							ServletUtility.setBean(bean, request);
						}
		                  
		              //ServletUtility.setBean(bean, request);
		               
		              
		           } catch (ApplicationException e) {
		           	e.printStackTrace();
		               log.error(e);
		               
		               ServletUtility.handleException(e, request, response);
		               
		               return;
		           }
		            }
		           
		           else if(OP_UPDATE.equalsIgnoreCase(op)){
		        	   TimetableBean bean=(TimetableBean)populateBean(request);
		        	   
		        	   try {
		        		   if(id>0){
						model.update(bean);
						ServletUtility.setBean(bean, request);
			               
			               ServletUtility.setSuccessMessage("timetable is successfully updated",request);
			               //ServletUtility.forward(getView(), request, response);
			               System.out.println("Save end");
		        		   }
						
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		        	   
		        	   catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
		        		   ServletUtility.setBean(bean, request);
		        		   ServletUtility.setErrorMessage("Timetable already exists", request);
		        		   
						e.printStackTrace();
					}
		        	   
		        	   
		        	   
		           }
		        	   
		           
			  else if (OP_RESET.equalsIgnoreCase(op)) {
		     	System.out.println("cancle Start");
		         ServletUtility.forward(getView(), request, response);
		     	System.out.println("cancle end");
		         return;
		         
		     }else if (OP_CANCEL.equalsIgnoreCase(op)) {

		            ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
		                    response);
		            return;

		        }

			 ServletUtility.forward(getView(), request, response);
			 
			 log.debug("CourceCtl Method doPOst Ended");

			

		
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_VIEW;
	}

}
