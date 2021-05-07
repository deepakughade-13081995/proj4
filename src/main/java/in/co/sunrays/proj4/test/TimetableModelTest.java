package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.TimetableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.model.TimetableModel;

public class TimetableModelTest {

	public static void main(String[] args) throws ParseException, ApplicationException {
		// TODO Auto-generated method stub
		
		//testAdd();
        //testDelete();
       //testUpdate();
        //testFindByPK();
       // testFindByCourceName();
       testSearch();
       //testList();
       TimetableModel m=new TimetableModel();
       TimetableBean b=new TimetableBean();
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
      java.sql.Date d = new java.sql.Date(sdf.parse("1990-01-04 00:00:00").getTime());
     b=m.findBySCD("power system","B.E",d);
      
      // b=m.findBySCS("power system","B.E","2nd");
       
      System.out.println(b.getCource_Id());
      System.out.println(b.getCource_Name());
      System.out.println(b.getCreatedBy());
      System.out.println(b.getExam_time());
      System.out.println(b.getId());
      System.out.println(b.getModifiedBy());
      System.out.println(b.getSemester());
      
       
		


	}
	
	 public static void testAdd() throws ParseException {

	        try {
	            TimetableBean bean=new TimetableBean();
	            TimetableModel model=new TimetableModel();
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	           // bean.setId(3L);
	            bean.setCource_Id(1);
	            bean.setCource_Name("b.tech");
	            bean.setSubject_Id(3);
	            bean.setSubject_Name("elect");
	            bean.setSemester("3");
	            bean.setExam_time("10 am to 1 pm");
	            bean.setExam_Date(sdf.parse("08/06/2013"));
		        bean.setCreatedBy("Admin");
	            bean.setModifiedBy("Admin");
	            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	                       
	            model.add(bean);
	           // long pk = model.add(bean);
	            //StudentBean addedbean = model.findByPK(pk);
	           // if (addedbean == null) {
	             //   System.out.println("Test add fail");
	            //}
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }

	    }

	    /**
	     * Tests delete a Student
	     */
	    public static void testDelete() {

	        try {
	        	 TimetableBean bean=new TimetableBean();
		            TimetableModel model=new TimetableModel();
		            long pk = 4L;
	            bean.setId(pk);
	            model.delete(bean);
	            TimetableBean deletedbean = model.findByPK(pk);
	            if (deletedbean != null) {
	                System.out.println("Test Delete fail");
	                
	            }
	            else{
	            	System.out.println("record deleted successfully");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Tests update a Student
	     * @throws ParseException 
	     * @throws ApplicationException 
	     */
	    public static void testUpdate() throws ParseException {
	    	 TimetableBean bean=new TimetableBean();
	            TimetableModel model=new TimetableModel();
	           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        
	        try {
	        	bean.setId(3);
	        	 bean.setCource_Id(1);
		            bean.setCource_Name("b.tech");
		            bean.setSubject_Id(1);
		            bean.setSubject_Name("transformer");
		            bean.setSemester("3");
		            bean.setExam_time("10 am to 1 pm");
		            bean.setExam_Date(sdf.parse("06/06/2013"));
			        bean.setCreatedBy("Admin");
		            bean.setModifiedBy("Admin");
		            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		           
	             model.update(bean);

	            TimetableBean updatedbean = model.findByPK(1L);
	            /*if (!"rr".equals(updatedbean.getFirstName())) {
	                System.out.println("Test Update fail");
	            }*/
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Tests find a faculty by PK.
	     */
	    public static void testFindByPK() {
	        try {
	        	 TimetableBean bean=new TimetableBean();
		            TimetableModel model=new TimetableModel();
		           long pk = 1L;
	            bean = model.findByPK(pk);
	            if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getCource_Id());
	            System.out.println(bean.getCource_Name());
	            System.out.println(bean.getSubject_Id());
	            System.out.println(bean.getSubject_Name());
	            System.out.println(bean.getExam_Date());
	            System.out.println(bean.getExam_time());
	            
	             
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    

	    /**
	     * Tests find a Faculty by Loginid.
	     */
	    public static void testFindByCourceName() {
	        try {
	        	 TimetableBean bean=new TimetableBean();
		            TimetableModel model=new TimetableModel();
		           
	            bean = model.findByCourceName("b.tech");
	            if (bean == null) {
	                System.out.println("Test Find By CourceName fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getCource_Id());
	            System.out.println(bean.getCource_Name());
	            System.out.println(bean.getSubject_Id());
	            System.out.println(bean.getSubject_Name());
	            System.out.println(bean.getExam_Date());
	            System.out.println(bean.getExam_time());
	            
	            
	            
	             } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Tests get Search
	     */
	    public static void testSearch() {

	        try {
	        	 TimetableBean bean=new TimetableBean();
		            TimetableModel model=new TimetableModel();
		           
	           List list = new ArrayList();
	            //bean.setFirst_Name("gautom");
	            list = model.search(bean, 1, 0);
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            System.out.println("iterator par");
	            while (it.hasNext()) {
	                bean = (TimetableBean) it.next();
	                System.out.println(bean.getId());
		            System.out.println(bean.getCource_Id());
		            System.out.println(bean.getCource_Name());
		            System.out.println(bean.getSubject_Id());
		            System.out.println(bean.getSubject_Name());
		            System.out.println(bean.getExam_Date());
		            System.out.println(bean.getExam_time());
		            
		            
	                 }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }

	    /**
	     * Tests get List.
	     */
	    public static void testList() {

	        try {
	        	 TimetableBean bean=new TimetableBean();
		            TimetableModel model=new TimetableModel();
		           
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (TimetableBean) it.next();
	                System.out.println(bean.getId());
		            System.out.println(bean.getCource_Id());
		            System.out.println(bean.getCource_Name());
		            System.out.println(bean.getSubject_Id());
		            System.out.println(bean.getSubject_Name());
		            System.out.println(bean.getExam_Date());
		            System.out.println(bean.getExam_time());
		            
		                
	            
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }


}
