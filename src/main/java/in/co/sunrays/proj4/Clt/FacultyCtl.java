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
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CourceModel;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 Faculty functionality Controller. Performs operation for add, update, delete
 * and get Faculty
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


@WebServlet(name="FacultyCtl",urlPatterns={"/ctl/FacultyCtl"})
public class FacultyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(CourseCtl.class);
	
	protected void preload(HttpServletRequest request){
		
		CourceModel model=new CourceModel();
		SubjectModel model1=new SubjectModel();
		CollegeModel model2=new CollegeModel();
		
		try {
			List l=model.list();
			List l1=model1.list();
			List l2=model2.list();
			
			 request.setAttribute("courceName",l);
			 request.setAttribute("subjectName",l1);
			 request.setAttribute("collegeName",l2);
			 
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
		
		if(DataValidator.isNull(request.getParameter("fname"))){
			
			
			request.setAttribute("fname",PropertyReader.getValue("error.require","First Name"));
			
			pass=false;
		}
		else if(!DataValidator.isName(request.getParameter("fname"))){
			
			
			request.setAttribute("fname",PropertyReader.getValue("error.name","Invalid First "));
			
			pass=false;
		}
		
		
        if(DataValidator.isNull(request.getParameter("lname"))){
			
			
			request.setAttribute("lname",PropertyReader.getValue("error.require","Last Name"));
			
			pass=false;
		}
        
        else if(!DataValidator.isName(request.getParameter("lname"))){
			
			
			request.setAttribute("lname",PropertyReader.getValue("error.name","Invalid Last "));
			
			pass=false;
		}
	
	

      if(DataValidator.isNull(request.getParameter("loginid"))){
			
			request.setAttribute("loginid",PropertyReader.getValue("error.require","Login id"));
			
			pass=false;
		}
      
      else if(!DataValidator.isEmail(request.getParameter("loginid"))){
			
			request.setAttribute("loginid",PropertyReader.getValue("error.email","Invalid "));
			
			pass=false;
		}
    
  
      
      
      
      
		
if(DataValidator.isNull(request.getParameter("doj"))){
			
			request.setAttribute("doj",PropertyReader.getValue("error.require","Date Of Joining"));
			
			pass=false;
		}
		

if(DataValidator.isNull(request.getParameter("qualification"))){
	
	request.setAttribute("qualification1",PropertyReader.getValue("error.require","Qualification"));
	
	pass=false;
}

if(DataValidator.isNull(request.getParameter("mobileno"))){
	
	request.setAttribute("mobileno",PropertyReader.getValue("error.require","Mobile No"));
	
	pass=false;
}
else if(!DataValidator.isMobileNum(request.getParameter("mobileno"))){
	
	request.setAttribute("mobileno",PropertyReader.getValue("error.mobile","Invalid Mobile "));
	
	pass=false;
}




if (DataValidator.isNull(request.getParameter("gender"))) {
    request.setAttribute("gender1",PropertyReader.getValue("error.require", "Gender"));
    pass = false;
}


if(DataValidator.isNull(request.getParameter("collegeId"))){
	request.setAttribute("collegeId1",PropertyReader.getValue("error.require","College Name"));
	
	pass=false;
}
System.out.println(request.getParameter("courceId")+"     selected course");
if(DataValidator.isNull(request.getParameter("courceId"))){
	
	request.setAttribute("courceId",PropertyReader.getValue("error.require","Cource Name"));
	
	pass=false;
}

if(DataValidator.isNull(request.getParameter("subjectId"))){
	
	request.setAttribute("subjectId1",PropertyReader.getValue("error.require","Subject Name"));
	
	pass=false;
}


if(DataValidator.isNull(request.getParameter("address"))){
	
	
	request.setAttribute("address",PropertyReader.getValue("error.require","Address"));
	
	pass=false;
}

else if(!DataValidator.isAddress(request.getParameter("address"))){
	
	
	request.setAttribute("address",PropertyReader.getValue("error.address","Address "));
	
	pass=false;
}



         log.debug("SubjectCtl Method validate Ended");
               System.out.println("validate");
               return pass;
		}

	
	protected BaseBean populateBean(HttpServletRequest request){
		
		 log.debug("RoleCtl Method populatebean Started");
		 
		 FacultyBean bean=new FacultyBean();
		 
		 bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		 
		 bean.setFirst_Name(DataUtility.getString(request.getParameter("fname")));
		  
		  
		 bean.setLast_Name(DataUtility.getString(request.getParameter("lname")));
		 
		 bean.setAddress(DataUtility.getString(request.getParameter("address")));
		
		 
		 bean.setLogin_Id(DataUtility.getString(request.getParameter("loginid")));
		 
		 bean.setDate_Of_joining(DataUtility.getDate(request.getParameter("doj")));
		 
		 bean.setQualification(DataUtility.getString(request.getParameter("qualification")));
		 
		 bean.setMobile_No(DataUtility.getString(request.getParameter("mobileno")));
		 
		// bean.setCollege_Name(DataUtility.getString(request.getParameter("collegeId")));
		 
		 bean.setCource_Id(DataUtility.getInt(request.getParameter("courceId")));
		 
		 bean.setCource_Name(DataUtility.getString(request.getParameter("courceId")));
		 
		 System.out.println(" course  id is "+(request.getParameter("courceId")));
		 
		 bean.setSubject_Id(DataUtility.getInt(request.getParameter("subjectId")));
		 
		 bean.setSubject_Name(DataUtility.getString(request.getParameter("subjectId")));
		
		 bean.setCollege_Id(DataUtility.getInt(request.getParameter("collegeId")));
		 
		 bean.setCollege_Name(DataUtility.getString(request.getParameter("collegeName")));
		 
		 bean.setGrnder(DataUtility.getString(request.getParameter("gender")));
		 
		
		  populateDTO(bean,request);
		 
		  log.debug("CourceCtl Method populatebean Ended");
		  System.out.println("populate Baean");
	        return bean;
		 }


    /**
     * Contains display logic
     */

       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		

    		log.debug("FacultyCtl Method doGet Started");
    		
    		 String op=DataUtility.getString(request.getParameter("operation"));
    		 
    		 
    		 FacultyModel model=new FacultyModel();
    		 
    		 long id=DataUtility.getLong(request.getParameter("id"));
    		
    		 FacultyBean bean;
			 System.out.println("id>0  "+id);
    		 
    		 if(id>0 || op!=null){
    			 
    			
    			 try {
    				 System.out.println("get suru");
    				bean=model.findByPK(id);
    				System.out.println("bean value --------------------------->"+bean.getFirst_Name());
    				ServletUtility.setBean(bean, request);
    				
    			} catch (ApplicationException e) {
    		       
    				 log.error(e);
    				 e.printStackTrace();
     	             /*ServletUtility.handleException(e, request, response);
    	             return;*/

    			}
    		 }
    		 System.out.println("get puri");
    		 ServletUtility.forward(getView(), request, response);
    	    /* log.debug("FacultyCtl Method doGetEnded");
    	     System.out.println("do get");*/

    		

    		
			}
    	
    	/**
    	 * Contains Submit logics
    	 */

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

			log.debug("FacultytCtl Method doGet Started");
			 
			 FacultyModel model = new FacultyModel();
			 
			String op=DataUtility.getString(request.getParameter("operation"));
			 
			long id=DataUtility.getLong(request.getParameter("id"));
			
			if(OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)){
				
	  FacultyBean bean = (FacultyBean)populateBean(request);	
				
			
				try {
					if(id>0){
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Faculty updated successfully", request);
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
						ServletUtility.setErrorMessage("loginId already exists", request);
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
				
			}
			
			else if (OP_RESET.equalsIgnoreCase(op)) {
		     	System.out.println("cancle Start");
		         ServletUtility.forward(getView(), request, response);
		     	System.out.println("cancle end");
		         return;
		         
		     }
			else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
	                    response);
	            return;

	        }

			 ServletUtility.forward(getView(), request, response);

			
			
			
				
			
			 
	log.debug("FacultyCtl Method doPOst Ended");


			}

		@Override
		protected String getView() {
			// TODO Auto-generated method stub
			return ORSView.FACULTY_VIEW;
		}

}
