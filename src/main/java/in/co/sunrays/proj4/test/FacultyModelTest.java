package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.FacultyModel;

public class FacultyModelTest {

	public static void main(String[] args) throws ParseException {
		
		testAdd();
        //testDelete();
       //testUpdate();
        //testFindByPK();
        //testFindByLoginId();
       //testSearch();
       //testList();

		}
	

    /**
     * Tests add a Student
     * 
     * @throws ParseException
     */
    public static void testAdd() throws ParseException {

        try {
            FacultyBean bean=new FacultyBean();
            FacultyModel model=new FacultyModel();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

           // bean.setId(3L);
            bean.setFirst_Name("saroj");
            bean.setLast_Name("tiwari");
            bean.setGrnder("M");
            bean.setLogin_Id("sanas56@gmail.com");
            bean.setDate_Of_joining(sdf.parse("06/06/2013"));
            bean.setMobile_No("8665432345");
            bean.setCource_Id(457);
            bean.setCollege_Name("patel institute");
            bean.setQualification("phd");
            bean.setCollege_Name("b.tech");
            bean.setSubject_Name("computer science");
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
            FacultyBean bean = new FacultyBean();
            FacultyModel model=new FacultyModel();
            long pk = 4L;
            bean.setId(pk);
            model.delete(bean);
            FacultyBean deletedbean = model.findByPK(pk);
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
    	FacultyModel model=new FacultyModel();
        FacultyBean bean =new FacultyBean();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
        	bean.setId(4);
            bean.setFirst_Name("ashish");
            bean.setLast_Name("mahale");
            bean.setGrnder("M");
            bean.setLogin_Id("aaa76@gmail.com");
            bean.setDate_Of_joining(sdf.parse("25/08/2016"));
            bean.setMobile_No("896543456");
            bean.setCource_Id(458);
            bean.setCollege_Name("pakchiraj institute");
           
            bean.setSubject_Name("computer science");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
           
             model.update(bean);

            FacultyBean updatedbean = model.findByPK(1L);
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
        	FacultyModel model=new FacultyModel();
            FacultyBean bean =new FacultyBean();
            long pk = 1L;
            bean = model.findByPK(pk);
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getFirst_Name());
            System.out.println(bean.getLast_Name());
            System.out.println(bean.getDate_Of_joining());
            System.out.println(bean.getMobile_No());
            System.out.println(bean.getLogin_Id());
            System.out.println(bean.getCource_Id());
            
            System.out.println(bean.getSubject_Name());
            System.out.println(bean.getGrnder());
            
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    
    

    /**
     * Tests find a Faculty by Loginid.
     */
    public static void testFindByLoginId() {
        try {
        	FacultyModel model=new FacultyModel();
            FacultyBean bean =new FacultyBean();
           
            bean = model.findByLoginId("ramans98@gmail.com");
            if (bean == null) {
                System.out.println("Test Find By EmailId fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getFirst_Name());
            System.out.println(bean.getLast_Name());
            System.out.println(bean.getDate_Of_joining());
            System.out.println(bean.getMobile_No());
            System.out.println(bean.getLogin_Id());
            System.out.println(bean.getCource_Id());
            
            System.out.println(bean.getSubject_Name());
            System.out.println(bean.getGrnder());
            
       
            
             } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests get Search
     */
    public static void testSearch() {

        try {
        	FacultyModel model=new FacultyModel();
            FacultyBean bean =new FacultyBean();
           
           List list = new ArrayList();
            //bean.setFirst_Name("gautom");
            list = model.search(bean, 1, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            System.out.println("iterator par");
            while (it.hasNext()) {
                bean = (FacultyBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirst_Name());
                System.out.println(bean.getLast_Name());
                System.out.println(bean.getDate_Of_joining());
                System.out.println(bean.getMobile_No());
                System.out.println(bean.getLogin_Id());
                System.out.println(bean.getCource_Id());
                
                System.out.println(bean.getSubject_Name());
                System.out.println(bean.getGrnder());
               
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
        	FacultyModel model=new FacultyModel();
            FacultyBean bean =new FacultyBean();
          
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (FacultyBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirst_Name());
                System.out.println(bean.getLast_Name());
                System.out.println(bean.getDate_Of_joining());
                System.out.println(bean.getMobile_No());
                System.out.println(bean.getLogin_Id());
                System.out.println(bean.getCource_Id());
                
                System.out.println(bean.getSubject_Name());
                System.out.println(bean.getGrnder());
                   
            
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }


}
