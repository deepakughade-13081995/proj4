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
import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.MarksheetModel;
import in.co.sunrays.proj4.model.StudentModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name="MarksheetCtl",urlPatterns={"/ctl/MarksheetCtl"})
public class MarksheetCtl extends BaseCtl {
 
	public static final String OP_SAVE = "Save";
	public static final String OP_DELETE = "Delete";
	public static final String OP_CANCEL ="Cancle";
	private static Logger log = Logger.getLogger(MarksheetCtl.class);
	 
	    protected void preload(HttpServletRequest request) {
	        StudentModel model = new StudentModel();
	        try {
	            List l = model.list();
	            System.out.println("list value size.....  "+l.size());
	            request.setAttribute("studentList",l);
	            
	            
	        } catch (ApplicationException e) {
	            log.error(e);
	        }

	    }

	    
	    protected boolean validate(HttpServletRequest request) {

	        log.debug("MarksheetCtl Method validate Started");

	        boolean pass = true;

	        if (DataValidator.isNull(request.getParameter("rollNo"))) {
	            request.setAttribute("rollNo",
	                    PropertyReader.getValue("error.require", "Roll number"));
	            pass = false;
	        }
	        
	        else if(!DataValidator.isRollNo(request.getParameter("rollNo"))){
	        	
	        	request.setAttribute("rollNo",PropertyReader.getValue("error.rollno","Invalid"));
	        	pass=false;
	        }
	        
	        
	        if (DataValidator.isNull(request.getParameter("physics"))) 
	                {
	            request.setAttribute("physics",
	                    PropertyReader.getValue("error.require", "Physics marks"));
	            pass = false;
	        }

	        else if(!DataValidator.isInteger(request.getParameter("physics"))){
	        	  request.setAttribute("physics",
		                    PropertyReader.getValue("error.integer", "Marks"));
		            pass = false;
		        
	        	
	        }
	        else if (DataUtility.getInt(request.getParameter("physics")) > 100) {
	            request.setAttribute("physics", "Marks can not be greater than 100");
	            pass = false;
	        }
	        else if (DataUtility.getInt(request.getParameter("physics")) <0 ) {
	            request.setAttribute("physics", "Marks can not be less than 0");
	            pass = false;
	        }


	        if (DataValidator.isNull(request.getParameter("chemistry")))
	                 {
	            request.setAttribute("chemistry",
	                    PropertyReader.getValue("error.require", " Chemistry marks"));
	            pass = false;
	        }
	        
	        else if(!DataValidator.isInteger(request.getParameter("chemistry"))){
	        	  request.setAttribute("chemistry",
		                    PropertyReader.getValue("error.integer", "Marks"));
		            pass = false;
		        
	        	
	        }
	      

	        else if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
	            request.setAttribute("chemistry",
	                    "Marks can not be greater than 100");
	            pass = false;
	        }
	        else if (DataUtility.getInt(request.getParameter("chemistry")) < 0) {
	            request.setAttribute("chemistry",
	                    "Marks can not be less than 0");
	            pass = false;
	        }

	        if (DataValidator.isNull(request.getParameter("maths"))) {
	            request.setAttribute("maths",
	                    PropertyReader.getValue("error.require", " Maths marks"));
	            pass = false;
	        }

	        else if(!DataValidator.isInteger(request.getParameter("maths"))){
	        	  request.setAttribute("maths",
		                    PropertyReader.getValue("error.integer", "Maths marks"));
		            pass = false;
		        
	        	
	        }
	       
	        else if (DataUtility.getInt(request.getParameter("maths")) > 100) {
	            request.setAttribute("maths", "Marks can not be greater than 100");
	            pass = false;
	        }

	        else if (DataUtility.getInt(request.getParameter("maths")) <0 ) {
	            request.setAttribute("maths", "Marks can not be less than 0");
	            pass = false;
	        }


	        if (DataValidator.isNull(request.getParameter("studentId"))) {
	            request.setAttribute("studentId",
	                    PropertyReader.getValue("error.require", "Student name"));
	            pass = false;
	        }

	        log.debug("MarksheetCtl Method validate Ended");

	        return pass;
	    }

	   
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("MarksheetCtl Method populatebean Started");

	        MarksheetBean bean = new MarksheetBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

	        bean.setName(DataUtility.getString(request.getParameter("studentId")));

	        bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));

	        bean.setChemestry(DataUtility.getInt(request.getParameter("chemistry")));

	        bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

	        bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));

	        populateDTO(bean, request);

	        System.out.println("Population done");

	        log.debug("MarksheetCtl Method populatebean Ended");

	        return bean;
	    }

	   
		/**
	     * Contains Display logics
	     */
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("MarksheetCtl Method doGet Started");

	        String op = DataUtility.getString(request.getParameter("operation"));
	        MarksheetModel model = new MarksheetModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        System.out.println("doGet...........");
	        if (id > 0) {
	        	System.out.println("id>0..........");
	            MarksheetBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }
	        ServletUtility.forward(getView(), request, response);
	        log.debug("MarksheetCtl Method doGet Ended");
	    }

	    /**
	     * Contains Submit logics
	     */
	protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	        log.debug("MarksheetCtl Method doPost Started");

	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get model
	        MarksheetModel model = new MarksheetModel();

	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op) ) {

	            MarksheetBean bean = (MarksheetBean) populateBean(request);
	            
	            try {
					if(id>0){
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Maeksheet is updated successfully", request);
					//ServletUtility.forward(getView(), request, response);
				    }
					else{
						
						try{
						long pk=model.add(bean);
						ServletUtility.setBean(bean, request);
		                ServletUtility.setSuccessMessage("Marksheet is successfully saved",
		                        request);
						}catch(Exception e){
						ServletUtility.setBean(bean, request);
						ServletUtility.setErrorMessage("Marksheet already exists!!", request);
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

	          } else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
	                    response);
	            return;

	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("MarksheetCtl Method doPost Ended");
	    }

	    
	    protected String getView() {
	        return ORSView.MARKSHEET_VIEW;
	    }

	


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarksheetCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
}
