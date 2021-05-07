package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;

/**
 * College Model Test classes
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class CollegeModelTest {

    /**
     * Model object to test
     */
    public static CollegeModel model = new CollegeModel();

    /**
     * Main method to call test methods.
     * 
     * @param args
     * @throws DuplicateRecordException
     * @throws DatabaseException 
     */
    public static void main(String[] args) throws DuplicateRecordException, DatabaseException {
         //testAdd();
        //testDelete();
        //testUpdate();
       // testFindByName();
        //testFindByPK();
         //testSearch();
        testList();

    }

    /**
     * Tests add a College
     * 
     * @throws DuplicateRecordException
     * @throws DatabaseException 
     */
    public static void testAdd() throws DuplicateRecordException, DatabaseException {

        try {
            CollegeBean bean = new CollegeBean();
           
            // bean.setId(2L);
          // bean.setId(1);
            bean.setName("manish");
            bean.setAddress("chouhan");
            bean.setState("mp");
            bean.setCity("indore");
            bean.setPhone("986654322");
            bean.setCreatedBy("deepak");
            bean.setModifiedBy("deepak");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(bean);
           
            System.out.println("Test add succ");
            CollegeBean addedBean = model.findByPK(pk);
           
           if (addedBean == null) {
                System.out.println("Test add fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests delete a College
     */
    public static void testDelete() {

        try {
            CollegeBean bean = new CollegeBean();
            long pk = 4L;
            bean.setId(pk);
            model.delete(bean);
            System.out.println("Test Delete succ");
            CollegeBean deletedBean = model.findByPK(pk);
            if (deletedBean != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests update a College
     */
    public static void testUpdate() {

        try {
            CollegeBean bean = model.findByPK(1L);
            bean.setName("TIT tt");
            bean.setAddress("anand nagar");
            model.update(bean);
            System.out.println("Test Update succ");
            CollegeBean updateBean = model.findByPK(1L);
            if (!"TIT tt".equals(updateBean.getName())) {
                System.out.println("Test Update fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests find a College by Name.
     */

    public static void testFindByName() {

        try {
            CollegeBean bean = model.findByName("LNCT");
            if (bean == null) {
                System.out.println("Test Find By Name fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getName());
            System.out.println(bean.getAddress());
            System.out.println(bean.getState());
            System.out.println(bean.getCity());
            System.out.println(bean.getPhone());
            System.out.println(bean.getCreatedBy());
            System.out.println(bean.getCreatedDatetime());
            System.out.println(bean.getModifiedBy());
            System.out.println(bean.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests find a College by PK.
     */
    public static void testFindByPK() {
        try {
            CollegeBean bean = new CollegeBean();
            long pk = 2L;
            bean = model.findByPK(pk);
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getName());
            System.out.println(bean.getAddress());
            System.out.println(bean.getState());
            System.out.println(bean.getCity());
            System.out.println(bean.getPhone());
            System.out.println(bean.getCreatedBy());
            System.out.println(bean.getCreatedDatetime());
            System.out.println(bean.getModifiedBy());
            System.out.println(bean.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests search a College by Name
     */

    public static void testSearch() {
        try {
            CollegeBean bean = new CollegeBean();
            List list = new ArrayList();
            bean.setName("LNCT");
            // bean.setAddress("borawan");
            list = model.search(bean, 1, 10);
            if (list.size() < 0) {
                System.out.println("Test Search fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (CollegeBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getName());
                System.out.println(bean.getAddress());
                System.out.println(bean.getState());
                System.out.println(bean.getCity());
                System.out.println(bean.getPhone());
                System.out.println(bean.getCreatedBy());
                System.out.println(bean.getCreatedDatetime());
                System.out.println(bean.getModifiedBy());
                System.out.println(bean.getModifiedDatetime());
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests get List a College.
     */
    public static void testList() {

        try {
            CollegeBean bean = new CollegeBean();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (CollegeBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getName());
                System.out.println(bean.getAddress());
                System.out.println(bean.getState());
                System.out.println(bean.getCity());
                System.out.println(bean.getPhone());
                System.out.println(bean.getCreatedBy());
                System.out.println(bean.getCreatedDatetime());
                System.out.println(bean.getModifiedBy());
                System.out.println(bean.getModifiedDatetime());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

}


