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
import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.TimetableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourceModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Subject List functionality Controller. Performs operation for list, search
 * and delete operations of Subject
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


@WebServlet(name="SubjectListCtl",urlPatterns={"/ctl/SubjectListCtl"})
public class SubjectListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(RoleListCtl.class);
	
	
	protected void preload(HttpServletRequest request){
		
		
		SubjectModel model=new SubjectModel();
		CourceModel model1=new CourceModel();
		
		try {
			List l=model.list();
			List l1=model1.list();
			
			request.setAttribute("subjectname",l);
			request.setAttribute("courcename",l1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        SubjectBean bean = new SubjectBean();
        bean.setId(DataUtility.getLong(request.getParameter("subjectId")));
        bean.setCourceId(DataUtility.getLong(request.getParameter("courceId")));
        populateDTO(bean, request);
       return bean;
        
    
    }
    
    /**
	 * Contains display logic
	 */


       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    		log.debug("RoleListCtl doGet Start");
            List list = null;
            int pageNo = 1;
            int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
            SubjectBean bean = (SubjectBean) populateBean(request);
            String op = DataUtility.getString(request.getParameter("operation"));
            SubjectModel model = new SubjectModel();
            try {
                list = model.search(bean, pageNo, pageSize);
                ServletUtility.setList(list, request);
                if (list == null || list.size() == 0) {
                    ServletUtility.setErrorMessage("No record found ", request);
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
            log.debug("RoleListCtl doGet End");
       

    		
           		}
    	
    	 /**
    	 * Contains submit logic
    	 */


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			log.debug("CourceListCtl doPost Start");
	        List list = null;
	        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
	        pageNo = (pageNo == 0) ? 1 : pageNo;
	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
	                .getValue("page.size")) : pageSize;
	        SubjectBean bean = (SubjectBean) populateBean(request);
	        String op = DataUtility.getString(request.getParameter("operation"));

	        String[] ids = request.getParameterValues("ids");
	       SubjectModel model = new SubjectModel();

	        try {

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
	  	    			 
	  	    			 ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
	  	    			 
	  	    			 return;
	  	    		 }
	  	    		 
	  	    		 else if(OP_DELETE.equalsIgnoreCase(op)){
	  	    			 
	  	    			 pageNo=1;
	  	    			 
	  	    			 if(ids!=null&&ids.length>0){
	  	    				 
	  	    				 SubjectBean deletebean=new SubjectBean();
	  	    				 
	  	    				 for(String id:ids){
	  	    					 
	  	    					 deletebean.setId(DataUtility.getInt(id));
	  	    					 model.delete(deletebean);
	  	    					 ServletUtility.setSuccessMessage("Record deleted successfully", request);
	  	    				 }
	  	    				 }
	  	    				 else{
	  	    					 ServletUtility.setErrorMessage(
	  	                             "Select at least one record", request);
	  	    				  }
	  	    	
	            }	}
                    else if(OP_RESET.equalsIgnoreCase(op)){
	            	
	            	ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
	            	return;
	            }
	            
	            list = model.search(bean, pageNo, pageSize);
	            ServletUtility.setBean(bean, request);
	            
	            //   ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setBean(bean, request);
	            ServletUtility.setList(list, request);

	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);

	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("CourceListCtl doPost End");
	   

		
		}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_LIST_VIEW;
	}

}
