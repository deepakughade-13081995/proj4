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
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.StudentModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Student List functionality Controller. Performs operation for list, search
 * and delete operations of Student
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


@ WebServlet(name="StudentListCtl",urlPatterns={"/ctl/StudentListCtl"})
public class StudentListCtl extends BaseCtl {
	
    private static Logger log = Logger.getLogger(StudentListCtl.class);
    
    protected BaseBean populateBean(HttpServletRequest request){
    	
    	
    	StudentBean bean=new StudentBean();
    	
    	bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
    	
    	
    	bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
    	
    	bean.setEmail(DataUtility.getString(request.getParameter("email")));
    	
    	return bean;
    	 }
    
    /**
     * Contains Display logics
     */
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("StudentListCtl doGet Start");
		
		List list=null;
		
		int pageNo=1;
		
		
		int pageSize=DataUtility.getInt(PropertyReader.getValue("page.size"));
		
		StudentBean bean=(StudentBean)populateBean(request);
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		StudentModel model=new StudentModel();
		
		try {
			list=model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			
			if(list==null||list.size()==0){
				
		    ServletUtility.setErrorMessage("No record found", request);
				
			}
			ServletUtility.setList(list, request);
			
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			
			ServletUtility.forward(getView(), request, response);
			
			
		   } catch (ApplicationException e) {
			   log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	            }
		
		log.debug("StudentListCtl doGet End");
			
	}
	
	/**
     * Contains submit logics
     */
   

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			log.debug("StudentListCtl doPost Start");
			
			List list=null;
			
			int pageNo=DataUtility.getInt(request.getParameter("pageNo"));
			int pageSize=DataUtility.getInt(request.getParameter("pageSize"));
			pageNo=(pageNo==0)?1:pageNo;
			pageSize=(pageSize==0)?DataUtility.getInt(PropertyReader.getValue("page.Size")):pageSize;
			
			StudentBean bean=(StudentBean)populateBean(request);
			
			String op=DataUtility.getString(request.getParameter("operation"));
			
			StudentModel model=new StudentModel();
			String[] ids = request.getParameterValues("ids");
			try{
				 if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEW.equalsIgnoreCase(op)
		                    || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op) ) {
		            	
		            	if(OP_SEARCH.equalsIgnoreCase(op)){
		  	    			 
		  	    			 pageNo=1;
		  	    			 
		  	    		 }else if(OP_NEXT.equalsIgnoreCase(op)){
		  	    			 
		  	    			 pageNo++;
		  	    		 }
		  	    		 else if(OP_PREVIOUS.equalsIgnoreCase(op) && pageNo>1){
		  	    			 
		  	    			 pageNo--;
		  	    			 
		  	    		 }
		  	    		 
		  	    		 else if(OP_NEW.equalsIgnoreCase(op)){
		  	    			 
		  	    			 ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
		  	    			 
		  	    			 return;
		  	    		 }
		  	    		 
		  	    		 else if(OP_DELETE.equalsIgnoreCase(op)){
		  	    			 
		  	    			 pageNo=1;
		  	    			 
		  	    			 if(ids!=null&&ids.length>0){
		  	    				 
		  	    				 StudentBean deletebean=new StudentBean();
		  	    				 
		  	    				 for(String id:ids){
		  	    					 
		  	    					 deletebean.setId(DataUtility.getInt(id));
		  	    					 model.delete(deletebean);
		  	    					 ServletUtility.setSuccessMessage("Student is deleted successfully", request);
		  	    				 }
		  	    				 }
		  	    				 else{
		  	    					 ServletUtility.setErrorMessage(
		  	                             "Select at least one record", request);
		  	    				  }
		  	    	

				
				}
				 }
				 else if (OP_RESET.equalsIgnoreCase(op)) {
					 ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
					 return;
					
				}
			list=model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if(list==null || list.size()==0 && !OP_DELETE.equalsIgnoreCase(op)){
			
				ServletUtility.setErrorMessage("No record found", request);
				
			}
			
			 ServletUtility.setList(list, request);

	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);

			}
			catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("StudentListCtl doGet End");
	    }

	

		@Override
		protected String getView() {
		
			return ORSView.STUDENT_LIST_VIEW;
		}

}
