package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

public class FacultyModel {
	
    private static Logger log = Logger.getLogger(StudentModel.class);

    /**
     * Find next PK of Faculty
     * 
     * @throws DatabaseException
     */
    public Integer nextPK() throws DatabaseException {
        log.debug("Model nextPK Started");
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM ST_FACULTY");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model nextPK End");
        return pk + 1;
    }

    /**
     * Add a Faculty
     * 
     * @param bean
     * @throws DatabaseException
     * 
     */
    public long add(FacultyBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;

        // get College Name
                int pk = 0;
               // System.out.println("Cou Id  "+bean.getCource_Id());
                CourceModel model=new CourceModel();
                CourceBean cbean=model.findByPk(bean.getCource_Id());
                bean.setCource_Name(cbean.getName());
                //System.out.println("Cou Name  "+bean.getCollege_Name());
                CollegeModel model1=new CollegeModel();
                CollegeBean cobean=model1.findByPK(bean.getCollege_Id());
                bean.setCollege_Name(cobean.getName());

                SubjectModel model2=new SubjectModel();
               SubjectBean sbean=model2.findByPk(bean.getSubject_Id());
                bean.setSubject_Name(sbean.getSubjectName());        
                 
        FacultyBean duplicateLogin=findByLoginId(bean.getLogin_Id());
        System.out.println( );
        if(duplicateLogin!=null){
        	throw new DuplicateRecordException("logine id allready exists");
        	
        }
                
                
        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO ST_FACULTY VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setLong(1,pk);
            pstmt.setString(2, bean.getFirst_Name());
            pstmt.setString(3, bean.getLast_Name());
            pstmt.setString(4, bean.getGrnder());
            pstmt.setString(5, bean.getLogin_Id());
            pstmt.setDate(6,new java.sql.Date(bean.getDate_Of_joining().getTime()));
            pstmt.setString(7, bean.getMobile_No());
            pstmt.setString(8,bean.getAddress());
            pstmt.setString(9,bean.getQualification());
            pstmt.setInt(10, bean.getCource_Id());
            pstmt.setString(11, bean.getCource_Name());
            pstmt.setInt(12, bean.getCollege_Id());
            pstmt.setString(13, bean.getCollege_Name());
            pstmt.setInt(14, bean.getSubject_Id());
            pstmt.setString(15, bean.getSubject_Name());
            pstmt.setString(16, bean.getCreatedBy());
            pstmt.setString(17, bean.getModifiedBy());
            pstmt.setTimestamp(18, bean.getCreatedDatetime());
            pstmt.setTimestamp(19, bean.getModifiedDatetime());
           System.out.println("add k bahar");
            
            
          
             pstmt.executeUpdate();
            
            conn.commit(); // End transaction
            pstmt.close();
           
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            e.printStackTrace();
            throw new ApplicationException(
                    "Exception : Exception in add faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }

    /**
     * Delete a Student
     * 
     * @param bean
     * @throws DatabaseException
     */
    public void delete(FacultyBean bean) throws ApplicationException {
        log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM ST_FACULTY WHERE ID=?");
            pstmt.setLong(1, bean.getId());
           
            pstmt.executeUpdate();
           
            conn.commit(); // End transaction
           
            pstmt.close();
           

        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete Faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
    }

    /**
     * Find User by Student
     * 
     * @param Email
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */

    public FacultyBean findByLoginId(String Email) throws ApplicationException {
        log.debug("Model findBy Email Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_FACULTY WHERE LOGIN_ID=?");
        FacultyBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, Email);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new FacultyBean();
                bean.setId(rs.getLong(1));
                bean.setFirst_Name(rs.getString(2));
                bean.setLast_Name(rs.getString(3));
                bean.setGrnder(rs.getString(4));
                bean.setLogin_Id(rs.getString(5));
                bean.setDate_Of_joining(rs.getDate(6));
                bean.setMobile_No(rs.getString(7));
                bean.setAddress(rs.getString(8));
                bean.setCource_Id(rs.getInt(9));
                bean.setCollege_Name(rs.getString(10));
               
                bean.setSubject_Name(rs.getString(11));
                bean.setCreatedBy(rs.getString(12));
                bean.setModifiedBy(rs.getString(13));;
                bean.setCreatedDatetime(rs.getTimestamp(14));
                bean.setModifiedDatetime(rs.getTimestamp(15));
                bean.setCollege_Id(rs.getInt(16));
                bean.setSubject_Id(rs.getInt(17));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by Email");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findBy login_id End");
        return bean;
    }

    /**
     * Find Student by PK
     * 
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */

    public FacultyBean findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE ID=?");
        FacultyBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	 bean = new FacultyBean();
                 bean.setId(rs.getLong(1));
                 bean.setFirst_Name(rs.getString(2));
                 bean.setLast_Name(rs.getString(3));
                 bean.setGrnder(rs.getString(4));
                 bean.setLogin_Id(rs.getString(5));
                 bean.setDate_Of_joining(rs.getDate(6));
                 bean.setMobile_No(rs.getString(7));
                 bean.setAddress(rs.getString(8)); 
                 bean.setQualification(rs.getString(9));
                 bean.setCource_Id(rs.getInt(10));
                 bean.setCource_Name(rs.getString(11));
                 bean.setCollege_Id(rs.getInt(12));
                 bean.setCollege_Name(rs.getString(13));
                 bean.setSubject_Id(rs.getInt(14));
                 bean.setSubject_Name(rs.getString(15));
                 bean.setCreatedBy(rs.getString(16));
                 bean.setModifiedBy(rs.getString(17));;
                 bean.setCreatedDatetime(rs.getTimestamp(18));
                 bean.setModifiedDatetime(rs.getTimestamp(19));
                
            	
               }
            rs.close();
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Faculty by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByPK End");
        return bean;
    }

    /**
     * Update a Student
     * 
     * @param bean
     * @throws DatabaseException
     */

    public void update(FacultyBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;
        
        CourceModel model=new CourceModel();
        CourceBean cbean=model.findByPk(bean.getCource_Id());
        bean.setCource_Name(cbean.getName());
        //System.out.println("Cou Name  "+bean.getCollege_Name());
        CollegeModel model1=new CollegeModel();
        CollegeBean cobean=model1.findByPK(bean.getCollege_Id());
        bean.setCollege_Name(cobean.getName());

        SubjectModel model2=new SubjectModel();
       SubjectBean sbean=model2.findByPk(bean.getSubject_Id());
        bean.setSubject_Name(sbean.getSubjectName());
      
        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE ST_FACULTY SET FIRST_NAME=?,LAST_NAME=?,GENDER=?,LOGIN_ID=?,DATE_OF_JOINING=?,MOBILE_NO=?,ADDRESS=?"
                + "QUALIFICATION=?,COURCE_ID=?,COURCE_NAME=?,COLLEGE_ID=?,COLLEGE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            
            pstmt.setString(1, bean.getFirst_Name());
            pstmt.setString(2, bean.getLast_Name());
            pstmt.setString(3, bean.getGrnder());
            pstmt.setString(4, bean.getLogin_Id());
            pstmt.setDate(5,  new java.sql.Date(bean.getDate_Of_joining().getTime()));
            pstmt.setString(6, bean.getMobile_No());
            pstmt.setString(7, bean.getAddress());
            pstmt.setString(8, bean.getQualification());
            pstmt.setInt(9, bean.getCource_Id());
            pstmt.setString(10, bean.getCource_Name());
            pstmt.setInt(11, bean.getCollege_Id());
            pstmt.setString(12, bean.getCollege_Name());
            pstmt.setInt(13, bean.getSubject_Id());
            pstmt.setString(14, bean.getSubject_Name());
            pstmt.setString(15, bean.getCreatedBy());
            pstmt.setString(16, bean.getModifiedBy());
            pstmt.setTimestamp(17, bean.getCreatedDatetime());
            pstmt.setTimestamp(18, bean.getModifiedDatetime());
            pstmt.setLong(19,bean.getId());
          
            
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("Database Exception..", e);
           /* try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Student ");
        */} finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model update End");
    }

    /**
     * Search Student
     * 
     * @param bean
     *            : Search Parameters
     * @throws DatabaseException
     */

    public List search(FacultyBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

    /**
     * Search Student with pagination
     * 
     * @return list : List of Students
     * @param bean
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * 
     * @throws DatabaseException
     */

    public List search(FacultyBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE 1=1");
System.out.println("search start");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getFirst_Name() != null && bean.getFirst_Name().length() > 0) {
                sql.append(" AND FIRST_NAME like '" + bean.getFirst_Name()
                        + "%'");
                }  
            
            if (bean.getLast_Name() != null && bean.getLast_Name().length() > 0) {
                sql.append(" AND LAST_NAME like '" + bean.getLast_Name() + "%'");
            }
            
            if (bean.getGrnder() != null && bean.getGrnder().length() > 0) {
                sql.append(" AND GENDER like '" + bean.getGrnder()
                        + "%'");
            }
          
            if (bean.getLogin_Id() != null && bean.getLogin_Id().length() > 0) {
                sql.append(" AND LOGIN_ID like '" + bean.getLogin_Id()
                        + "%'");
            }
            
            if (bean.getDate_Of_joining() != null) {
                sql.append(" AND DATE_OF_JOINING like '" + bean.getDate_Of_joining()
                        + "%'");
            }
          
            if (bean.getMobile_No() != null && bean.getMobile_No().length() > 0) {
                sql.append(" AND MOBILE_NO like '" + bean.getMobile_No()
                        + "%'");
            }
          
            if (bean.getCource_Id()>0) {
                sql.append(" AND COURCE_ID = " + bean.getCource_Id());
            }
            
            if (bean.getCource_Name() != null && bean.getCource_Name().length() > 0) {
                sql.append(" AND COURCE_NAME like '" + bean.getCource_Name()
                        + "%'");
            }
           
            
            if (bean.getCollege_Id()>0) {
                sql.append(" AND COLLEGE_ID = " + bean.getCollege_Id());
            }
           
            if (bean.getCollege_Name() != null && bean.getCollege_Name().length() > 0) {
                sql.append(" AND COLLEGE_NAME like '" + bean.getCollege_Name() + "%'");
            }
            
            if (bean.getSubject_Id()>0) {
                sql.append(" AND SUBJECT_ID = " + bean.getSubject_Id());
            }
           
            if (bean.getSubject_Name() != null && bean.getSubject_Name().length() > 0) {
                sql.append(" AND SUBJECT_NAME like '" + bean.getSubject_Name()
                        + "%'");
            }
            System.out.println("search end");
                   }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        ArrayList list = new ArrayList();
        Connection conn = null;
        System.out.println("connection par");
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            System.out.println("preap par");
            ResultSet rs = pstmt.executeQuery();
            System.out.println("resultset par");
           
            while (rs.next()) {
                bean = new FacultyBean();
                System.out.println("rs.next");
                bean.setId(rs.getLong(1));
                bean.setFirst_Name(rs.getString(2));
                bean.setLast_Name(rs.getString(3));
                bean.setGrnder(rs.getString(4));
                bean.setLogin_Id(rs.getString(5));
                bean.setDate_Of_joining(rs.getDate(6));
                bean.setMobile_No(rs.getString(7));
                bean.setAddress(rs.getString(8));
                bean.setQualification(rs.getString(9));
                bean.setCource_Id(rs.getInt(10));
                bean.setCource_Name(rs.getString(11));
                bean.setCollege_Id(rs.getInt(12));
                bean.setCollege_Name(rs.getString(13));
                bean.setSubject_Id(rs.getInt(14));
                bean.setSubject_Name(rs.getString(15));
                 bean.setCreatedBy(rs.getString(16));
                bean.setModifiedBy(rs.getString(17));;
                bean.setCreatedDatetime(rs.getTimestamp(18));
                bean.setModifiedDatetime(rs.getTimestamp(19));
           	
              list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Get List of faculty
     * 
     * @return list : List of Student
     * @throws DatabaseException
     */

    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Student with pagination
     * 
     * @return list : List of Student
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */

    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from ST_FACULTY");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FacultyBean bean=new FacultyBean();
                
                bean.setId(rs.getLong(1));
                bean.setFirst_Name(rs.getString(2));
                bean.setLast_Name(rs.getString(3));
                bean.setGrnder(rs.getString(4));
                bean.setLogin_Id(rs.getString(5));
                bean.setDate_Of_joining(rs.getDate(6));
                bean.setMobile_No(rs.getString(7));
                bean.setAddress(rs.getString(8));
                bean.setQualification(rs.getString(9));
                bean.setCource_Id(rs.getInt(10));
                bean.setCource_Name(rs.getString(11));
                bean.setCollege_Id(rs.getInt(12));
                bean.setCollege_Name(rs.getString(13));
                bean.setSubject_Id(rs.getInt(14));
                bean.setSubject_Name(rs.getString(15));
                 bean.setCreatedBy(rs.getString(16));
                bean.setModifiedBy(rs.getString(17));
                
                bean.setCreatedDatetime(rs.getTimestamp(18));
                bean.setModifiedDatetime(rs.getTimestamp(19));
           	
                               list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }

}
