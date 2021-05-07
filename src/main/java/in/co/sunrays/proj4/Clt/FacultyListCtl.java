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
import in.co.sunrays.proj4.bean.FacultyBean;

import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * College List functionality Controller. Performs operation for list, search
 * and delete operations of College
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


@ WebServlet(name="FacultyListCtl",urlPatterns={"/ctl/FacultyListCtl"})
public class FacultyListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	 private static Logger log = Logger.getLogger(UserListCtl.class);

	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {
	    	
	    	System.out.println("populate bean");
	        FacultyBean bean = new FacultyBean();

	        bean.setFirst_Name(DataUtility.getString(request.getParameter("firstName")));

	       

	        bean.setLogin_Id(DataUtility.getString(request.getParameter("login")));

	        return bean;
	    }

	    /**
		 * Contains display logic
		 */


   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		log.debug("FacultyListCtl doGet Start");
   		System.out.println("FacultyListCtl doGet Start");
   		
        List list = null;
        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
        FacultyBean bean = (FacultyBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        // get the selected checkbox ids array for delete list
        String[] ids = request.getParameterValues("ids");
        FacultyModel model = new FacultyModel();
        try {
            list = model.search(bean, pageNo, pageSize);
            
            System.out.println("list="+list.size());
            ServletUtility.setList(list, request);
            
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(ORSView.FACULTY_LIST_VIEW,request, response);
            
        } catch (ApplicationException e) {
        	e.printStackTrace();
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("FaculListCtl doPOst End");

        
   	}
   	
   	
   	/**
	 * Contains Submit logic
	 */


   	
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		
   		log.debug("UserListCtl doPost Start");
        List list = null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
        FacultyBean bean = (FacultyBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        
        // get the selected checkbox ids array for delete list
        String[] ids = request.getParameterValues("ids");
       
        FacultyModel model = new FacultyModel();
        try {

            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
                    || "Previous".equalsIgnoreCase(op)) {

                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }

            } else if (OP_NEW.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
                return;
            } else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                if (ids != null && ids.length > 0) {
                    FacultyBean deletebean = new FacultyBean();
                    for (String id : ids) {
                        deletebean.setId(DataUtility.getInt(id));
                        model.delete(deletebean);
                         ServletUtility.setSuccessMessage("Record deleted successfully ", request);
                    }
                } else {
                    ServletUtility.setErrorMessage(
                            "Select at least one record", request);
                }
            }
            else if(OP_RESET.equalsIgnoreCase(op)){
            	ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
            	return;
            	
            }
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
        log.debug("UserListCtl doGet End");
 

   		
			}

	@Override
	protected String getView() {
	
		return ORSView.FACULTY_LIST_VIEW;
	}

}
