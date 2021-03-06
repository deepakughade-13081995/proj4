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
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Role List functionality Controller. Performs operation for list, search
 * operations of Role
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@ WebServlet(name="RoleListCtl",urlPatterns={"/ctl/RoleListCtl"})


public class RoleListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(RoleListCtl.class);

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        RoleBean bean = new RoleBean();
        bean.setName(DataUtility.getString(request.getParameter("name")));

        return bean;
    }

    /**
     * Contains Display logics
     */
	
       
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    		  log.debug("RoleListCtl doGet Start");
    	        List list = null;
    	        int pageNo = 1;
    	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
    	        RoleBean bean = (RoleBean) populateBean(request);
    	        String op = DataUtility.getString(request.getParameter("operation"));
    	        RoleModel model = new RoleModel();
    	        try {
    	            list = model.search(bean, pageNo, pageSize);
    	            ServletUtility.setList(list, request);
    	            if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
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
    	     * Contains Submit logics
    	     */
    		
    	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		log.debug("RoleListCtl doPost Start");
        List list = null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
        RoleBean bean = (RoleBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        String[] ids = request.getParameterValues("ids");
        System.out.println();
        RoleModel model = new RoleModel();

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
	  	    			 
	  	    			 ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
	  	    			 
	  	    			 return;
	  	    		 }
	  	    		 
	  	    		 else if(OP_DELETE.equalsIgnoreCase(op)){
	  	    			 
	  	    			 pageNo=1;
	  	    			 
	  	    			 if(ids!=null&&ids.length>0){
	  	    				 
	  	    				RoleBean deletebean=new RoleBean();
	  	    				 
	  	    				 for(String id:ids){
	  	    					 System.out.println(id);
	  	    					 deletebean.setId(DataUtility.getInt(id));
	  	    					
	  	    					 model.delete(deletebean);
	  	    					 ServletUtility.setSuccessMessage("Role is deleted successfully", request);
	  	    				 }
	  	    				 }
	  	    				 else{
	  	    					 ServletUtility.setErrorMessage(
	  	                             "Select at least one record", request);
	  	    				  }
            }}
        	 else if(OP_RESET.equalsIgnoreCase(op)){
        		 
        		 ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
        		 return;
        	 }
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
        log.debug("RoleListCtl doPost End");
   
	
	}
	@Override
	protected String getView() {
		return ORSView.ROLE_LIST_VIEW;}

}
