package in.co.sunrays.proj4.Clt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.TimetableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourceModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.TimetableModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Timetable List functionality Controller. Performs operation for list, search
 * and delete operations of timetable
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


@ WebServlet(name="TimetableListCtl",urlPatterns={"/ctl/TimetableListCtl"})
public class TimetableListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(TimetableListCtl.class);
	
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
	
	
	

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        TimetableBean bean = new TimetableBean();
        //bean.setCource_Name(DataUtility.getString(request.getParameter("courceId")));
        //bean.setSubject_Name(DataUtility.getString(request.getParameter("subjectName")));
        bean.setExam_Date(DataUtility.getDate(request.getParameter("examdate")));
        bean.setCource_Id(DataUtility.getInt(request.getParameter("courceId")));
        bean.setSubject_Id(DataUtility.getInt(request.getParameter("subjectId")));
        populateDTO(bean, request);
        return bean;
        
    }

	
	
    /**
     * Contains Display logics
     */
        
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		
  		
  		log.debug("TimeListListCtl doGet Start");
  		String [] ids=request.getParameterValues("chk_1");
        List list = null;
        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
        TimetableBean bean = (TimetableBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        TimetableModel model = new TimetableModel();
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
        log.debug("TimetableListCtl doGet End");
   

			}
	
  	/**
     * Contains submit logics
     */
    
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
  		 log.debug("TimetableListCtl doPost Start");
  		 List list=null;
  		 int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
  	     int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

  	     pageNo = (pageNo == 0) ? 1 : pageNo;

  	     pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
  	             .getValue("page.size")) : pageSize;

  	    TimetableBean bean = (TimetableBean) populateBean(request);

  	     String op = DataUtility.getString(request.getParameter("operation"));

  	     // get the selected checkbox ids array for delete list
  	     String[] ids = request.getParameterValues("ids");

  	     TimetableModel model = new TimetableModel();
  	     
  	     
  	    	 
  	    	 if(OP_SEARCH.equalsIgnoreCase(op)||OP_NEXT.equalsIgnoreCase(op)||OP_PREVIOUS.equalsIgnoreCase(op));{
  	    		 
  	    		 
  	    		 if(OP_SEARCH.equalsIgnoreCase(op)){
  	    			 
  	    			 pageNo=1;
  	    			 
  	    		 }else if(OP_NEXT.equalsIgnoreCase(op)){
  	    			 
  	    			 pageNo++;
  	    		 }
  	    		 else if(OP_PREVIOUS.equalsIgnoreCase(op) && pageNo>1){
  	    			 
  	    			 pageNo--;
  	    			 
  	    		 }
  	    		 
  	    		 else if(OP_NEW.equalsIgnoreCase(op)){
  	    			 
  	    			 ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
  	    			 
  	    			 return;
  	    		 }
  	    		 
  	    		 else if(OP_DELETE.equalsIgnoreCase(op)){
  	    			 
  	    			 pageNo=1;
  	    			 
  	    			 if(ids!=null&&ids.length>0){
  	    				 
  	    				 TimetableBean deletebean=new TimetableBean();
  	    				 
  	    				 for(String id:ids){
  	    					 
  	    					 deletebean.setId(DataUtility.getInt(id));
  	    					 try {
								model.delete(deletebean);
							} catch (ApplicationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
  	    					 ServletUtility.setSuccessMessage("Record deleted successfully", request);
  	    				 }
  	    				 }
  	    				 else{
  	    					 ServletUtility.setErrorMessage(
  	                             "Select at least one record", request);
  	    				  }
  	    				 
  	    			 }
  	    		 
  	    		 else if(OP_RESET.equalsIgnoreCase(op)){
  	    			 
  	    			 ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
  	    			 return;
  	    		 }
  	    		 
  	    		  try {
					list = model.search(bean, pageNo, pageSize);
					 ServletUtility.setBean(bean, request);
	  	             
  	    		  } catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  	    		  System.out.println("time table  "+list.size());
  	         //     ServletUtility.setList(list, request);
  	              if (list == null  || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
  	                  ServletUtility.setErrorMessage("No record found ", request);
  	               
  	    			 }
  	    		  ServletUtility.setBean(bean, request); 
  	              ServletUtility.setList(list, request);
  	              ServletUtility.setPageNo(pageNo, request);
  	              ServletUtility.setPageSize(pageSize, request);
  	              ServletUtility.forward(getView(), request, response);
  	    	 
  	    	 
  	    	 }     
  	     
  	    	 log.debug("TimetableListListCtl doPost End");
  	    	 
  	    	 	}

  	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
