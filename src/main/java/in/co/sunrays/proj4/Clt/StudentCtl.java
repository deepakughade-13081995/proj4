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
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.StudentModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@ WebServlet(name="StudentCtl",urlPatterns={"/ctl/StudentCtl"})
    public class StudentCtl extends BaseCtl {
	
	
	
    private static Logger log = Logger.getLogger(StudentCtl.class);
    
   protected void preload(HttpServletRequest request){
    	
    	CollegeModel model=new CollegeModel();
    	
    	try {
			List l=model.list();
			
			 request.setAttribute("collegeList",l);
			System.out.println("preload");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
    	
    }
	
	protected boolean validate(HttpServletRequest request){
		
		log.debug("StudentCtl Method validate Started");
		System.out.println("validate start");
		boolean pass=true;
		
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		if(DataValidator.isNull(request.getParameter("collegeId"))){
			
			request.setAttribute("college",PropertyReader.getValue("error.require", "College Name"));
			
			pass=false;
			
			
		}
		
		if(DataValidator.isNull(request.getParameter("fname"))){
			
			request.setAttribute("firstName1",PropertyReader.getValue("error.require","FirstName"));
			
			pass=false;
			
		}
		
		else if(!DataValidator.isName(request.getParameter("fname"))){
			
			request.setAttribute("firstName1",PropertyReader.getValue("error.name","Invalid"));
			
			pass=false;
			
		}
		
		
		if(DataValidator.isNull(request.getParameter("lastName"))){
			
			request.setAttribute("lastName",PropertyReader.getValue("error.require","LastName"));
			
			pass=false;
		}
		
		else if(!DataValidator.isName(request.getParameter("lastName"))){
			
			request.setAttribute("lastName",PropertyReader.getValue("error.name","Invalid "));
			
			pass=false;
		}
		
		
		if(DataValidator.isNull(request.getParameter("mobileNo"))){
			
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require","Mobile No"));
			
			pass=false;
			
		}
		

		else if(!DataValidator.isMobileNum(request.getParameter("mobileNo"))){
			
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile"," Invalid Mobile"));
			
			pass=false;
			
		}
		
		
		
		if(DataValidator.isNull(request.getParameter("email"))){
			
			request.setAttribute("email",PropertyReader.getValue("error.require","Email"));
			
			pass=false;
			
		}
		else if(!DataValidator.isEmail(request.getParameter("email"))){
			
			request.setAttribute("email",PropertyReader.getValue("error.email","Invalid "));
			
			pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("collegeId"))){
			
			request.setAttribute("collegeId",PropertyReader.getValue("error.require","College Name"));
			
			pass=false;	
			
		}
		
		if(DataValidator.isNull(request.getParameter("dob"))){
			
			request.setAttribute("dob",PropertyReader.getValue("error.require","Dath Of Birth"));
			
			pass=false;
			
		}
	
if(DataValidator.isNull(request.getParameter("address"))){
			
			request.setAttribute("address",PropertyReader.getValue("error.require","Address"));
			
			pass=false;
			
		}
		
		else if(!DataValidator.isAddress(request.getParameter("address"))){
			
			request.setAttribute("address",PropertyReader.getValue("error.address","Address"));
			
			pass=false;
			
		}
		
		        log.debug("StudentCtl Method validate Ended");

        System.out.println("validate end");
        return pass;
		
	}  
	
	
	protected BaseBean populateBean(HttpServletRequest request){
		
		log.debug("StudentCtl Method populatebean Started");
		System.out.println("popu start");
		StudentBean bean=new StudentBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setFirstName(DataUtility.getString(request.getParameter("fname")));
		
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		
	bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		
		bean.setCollageName(DataUtility.getString(request.getParameter("collegeId")));
		
		populateDTO(bean, request);
		
		 log.debug("StudentCtl Method populatebean Ended");
		 System.out.println("validate end");
	        return bean;
		
		}
	
	 /**
     * Contains Display logics
     */
   
	
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		System.out.println("get start");
   		log.debug("StudentCtl Method doGet Started");
   		
   		String op=DataUtility.getString(request.getParameter("operation"));
        
   		long id = DataUtility.getLong(request.getParameter("id"));
   		
   	 StudentModel model = new StudentModel();
   	 
   		if(id>0||op!=null){
   			System.out.println("id>0");
   			
   			StudentBean bean;
   			
   			try {
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
			}
   			
   			}
   		ServletUtility.forward(getView(), request, response);
   	    log.debug("StudentCtl Method doGett Ended");
   	 System.out.println("validate end");

		
	}
   	
    /**
     * Contains Submit logics
     */
   
   	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post start");
		 log.debug("StudentCtl Method doPost Started");
		 
		 String op=DataUtility.getString(request.getParameter("operation"));
		 
		 StudentModel model=new StudentModel();
		 
		 long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)){
			
			StudentBean bean=(StudentBean)populateBean(request);
			
			try {
				if(id>0){
				model.update(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Student is updated successfully", request);
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
					ServletUtility.setErrorMessage("student already exists!!", request);
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
				      
			}}
			

			
			
						
						/*else if(OP_DELETE.equalsIgnoreCase(op)){
			
			StudentBean bean=(StudentBean)populateBean(request);
			
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
			  e.printStackTrace();
			  log.error(e);
              ServletUtility.handleException(e, request, response);
              return;

			}}
*/		
		else if (OP_RESET.equalsIgnoreCase(op)) {
	     	System.out.println("cancle Start");
	         ServletUtility.forward(getView(), request, response);
	     	System.out.println("cancle end");
	         return;
	         
	     }


			else if(OP_CANCEL.equalsIgnoreCase(op)){
				
				 ServletUtility
                 .redirect(ORSView.STUDENT_LIST_CTL, request, response);
         return;

				
			}
			    ServletUtility.forward(getView(), request, response);

	        log.debug("StudentCtl Method doPost Ended");
	        System.out.println("post end");
}
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.STUDENT_VIEW;
	}

}
