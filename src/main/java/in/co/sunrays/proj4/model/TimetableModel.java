package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.TimetableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

public class TimetableModel {
	
	 private static Logger log = Logger.getLogger(TimetableModel.class);

	    /**
	     * Find next PK of TImetable
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
	                    .prepareStatement("SELECT MAX(ID) FROM ST_TIMETABLE");
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
	    public long add(TimetableBean bean) throws ApplicationException,
	            DuplicateRecordException {
	        log.debug("Model add Started");
	        Connection conn = null;

	        // get College Name
	                int pk = 0;
            CourceModel model=new CourceModel();
            CourceBean cbean=model.findByPk(bean.getCource_Id());
            SubjectModel model1=new SubjectModel();
            SubjectBean sbean=model1.findByPk(bean.getSubject_Id());//subject name and cource name yaha se set hoge
	        
            bean.setCource_Name(cbean.getName());
            bean.setSubject_Name(sbean.getSubjectName());
            
            System.out.println("Daaaaaaaaa"+bean.getExam_Date());
            
            TimetableModel m=new TimetableModel();
            TimetableBean b=m.findBySCS(bean.getSubject_Name(),bean.getCource_Name(),bean.getSemester());
            TimetableBean b1=m.findBySCD(bean.getSubject_Name(),bean.getCource_Name(),new java.sql.Date(bean.getExam_Date().getTime())) ;
            
            if(b!=null || b1!=null){
            	throw new DuplicateRecordException("time table exists");
            	
            }
            
	        try {
	            conn = JDBCDataSource.getConnection();
	            pk = nextPK();
	            // Get auto-generated next primary key
	            System.out.println(pk + " in ModelJDBC");
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("INSERT INTO ST_TIMETABLE VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
	            pstmt.setLong(1,pk);
	            pstmt.setInt(2, bean.getCource_Id());
	            pstmt.setString(3, bean.getCource_Name());
	            pstmt.setInt(4, bean.getSubject_Id());
	            pstmt.setString(5, bean.getSubject_Name());
	            pstmt.setString(6,bean.getSemester());
	            pstmt.setString(7, bean.getExam_time());
	            pstmt.setDate(8,new java.sql.Date(bean.getExam_Date().getTime()));
	            pstmt.setString(9, bean.getCreatedBy());
	            pstmt.setString(10, bean.getModifiedBy());
	            pstmt.setTimestamp(11, bean.getCreatedDatetime());
	            pstmt.setTimestamp(12, bean.getModifiedDatetime());
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
	                    "Exception : Exception in add Timetable");
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
	    public void delete(TimetableBean bean) throws ApplicationException {
	        log.debug("Model delete Started");
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
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
	                    "Exception : Exception in delete Timetable");
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

	    public TimetableBean findByCourceName(String courceName) throws ApplicationException {
	        log.debug("Model findBy Email Started");
	        StringBuffer sql = new StringBuffer(
	                "SELECT * FROM ST_TIMETABLE WHERE COURCE_NAME=?");
	        TimetableBean bean = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setString(1, courceName);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new TimetableBean();
	                bean.setId(rs.getInt(1));
	                bean.setCource_Id(rs.getInt(2));
	                bean.setCource_Name(rs.getString(3));
	                bean.setSubject_Id(rs.getInt(4));
	                bean.setSubject_Name(rs.getString(5));
	                bean.setSemester(rs.getString(6));
	                bean.setExam_time(rs.getString(7));
	                bean.setExam_Date(rs.getDate(8));
	                bean.setCreatedBy(rs.getString(9));
	                bean.setModifiedBy(rs.getString(10));;
	                bean.setCreatedDatetime(rs.getTimestamp(11));
	                bean.setModifiedDatetime(rs.getTimestamp(12));

	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by Email");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findBy courceName End");
	        return bean;
	    }
	    
	    
	    public TimetableBean findBySCD(String courceName,String SEMESTER,Date date) throws ApplicationException {
	        log.debug("Model findBy Email Started");
	        StringBuffer sql = new StringBuffer(
	                "SELECT * FROM ST_TIMETABLE WHERE SUBJECT_NAME=? AND SEMESTER=? AND EXAM_DATE=?");
	        TimetableBean bean = null;
	        Connection conn = null;
	       // Date d = new Date(date.getTime());
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setString(1, courceName);
	            pstmt.setString(2, SEMESTER);
	            pstmt.setDate(3,(java.sql.Date)date);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new TimetableBean();
	                bean.setId(rs.getInt(1));
	                bean.setCource_Id(rs.getInt(2));
	                bean.setCource_Name(rs.getString(3));
	                bean.setSubject_Id(rs.getInt(4));
	                bean.setSubject_Name(rs.getString(5));
	                bean.setSemester(rs.getString(6));
	                bean.setExam_time(rs.getString(7));
	                bean.setExam_Date(rs.getDate(8));
	                bean.setCreatedBy(rs.getString(9));
	                bean.setModifiedBy(rs.getString(10));;
	                bean.setCreatedDatetime(rs.getTimestamp(11));
	                bean.setModifiedDatetime(rs.getTimestamp(12));

	            }
	            rs.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by subject name");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findBy subjectName End");
	        return bean;
	    }
	    
	    public TimetableBean findBySCS(String subjectName,String courceName,String sem) throws ApplicationException {
	        log.debug("Model findBy Email Started");
	        StringBuffer sql = new StringBuffer(
	                "SELECT * FROM ST_TIMETABLE WHERE SUBJECT_NAME=? AND COURCE_NAME=? AND SEMESTER=?");
	        TimetableBean bean = null;
	        Connection conn = null;
	      
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setString(1, subjectName);
	            pstmt.setString(2, courceName);
	            pstmt.setString(3,sem);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new TimetableBean();
	                bean.setId(rs.getInt(1));
	                bean.setCource_Id(rs.getInt(2));
	                bean.setCource_Name(rs.getString(3));
	                bean.setSubject_Id(rs.getInt(4));
	                bean.setSubject_Name(rs.getString(5));
	                bean.setSemester(rs.getString(6));
	                bean.setExam_time(rs.getString(7));
	                bean.setExam_Date(rs.getDate(8));
	                bean.setCreatedBy(rs.getString(9));
	                bean.setModifiedBy(rs.getString(10));;
	                bean.setCreatedDatetime(rs.getTimestamp(11));
	                bean.setModifiedDatetime(rs.getTimestamp(12));

	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by subject name");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findBy subjectName End");
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

	    public static TimetableBean findByPK(long pk) throws ApplicationException {
	        log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
	        TimetableBean bean = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, pk);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	            	 bean = new TimetableBean();
	            	 bean.setId(rs.getInt(1));
		                bean.setCource_Id(rs.getInt(2));
		                bean.setCource_Name(rs.getString(3));
		                bean.setSubject_Id(rs.getInt(4));
		                bean.setSubject_Name(rs.getString(5));
		                bean.setSemester(rs.getString(6));
		                bean.setExam_time(rs.getString(7));
		                bean.setExam_Date(rs.getDate(8));
		                bean.setCreatedBy(rs.getString(9));
		                bean.setModifiedBy(rs.getString(10));;
		                bean.setCreatedDatetime(rs.getTimestamp(11));
		                bean.setModifiedDatetime(rs.getTimestamp(12));

	               }
	            rs.close();
	        } catch (Exception e) {
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

	    public void update(TimetableBean bean) throws ApplicationException,
	            DuplicateRecordException {
	        log.debug("Model update Started");
	        
	        Connection conn = null;
	        CourceModel model=new CourceModel();
            CourceBean cbean=model.findByPk(bean.getCource_Id());
            SubjectModel model1=new SubjectModel();
            SubjectBean sbean=model1.findByPk(bean.getSubject_Id());//subject name and cource name yaha se set hoge
	        
            bean.setCource_Name(cbean.getName());
            bean.setSubject_Name(sbean.getSubjectName());
	       
            TimetableModel m=new TimetableModel();
            TimetableBean b=m.findBySCS(bean.getSubject_Name(),bean.getCource_Name(),bean.getSemester());
            TimetableBean b1=m.findBySCD(bean.getSubject_Name(),bean.getCource_Name(),new java.sql.Date(bean.getExam_Date().getTime())) ;
            
            if(b!=null || b1!=null){
            	throw new DuplicateRecordException("time table exists");
            	
            }
           
            
	        
	        try {

	            conn = JDBCDataSource.getConnection();

	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("UPDATE ST_TIMETABLE SET COURCE_ID=?,COURCE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,SEMESTER=?,EXAM_TIME=?,"
	                + "EXAM_DATE=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
	            
	           
	            pstmt.setInt(1, bean.getCource_Id());
	            pstmt.setString(2, bean.getCource_Name());
	            pstmt.setInt(3, bean.getSubject_Id());
	            pstmt.setString(4, bean.getSubject_Name());
	            pstmt.setString(5,bean.getSemester());
	            pstmt.setString(6, bean.getExam_time());
	            pstmt.setDate(7,new java.sql.Date(bean.getExam_Date().getTime()));
	            pstmt.setString(8, bean.getCreatedBy());
	            pstmt.setString(9, bean.getModifiedBy());
	            pstmt.setTimestamp(10, bean.getCreatedDatetime());
	            pstmt.setTimestamp(11, bean.getModifiedDatetime());
	            pstmt.setLong(12,bean.getId());
	            pstmt.executeUpdate();
	         
	            
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

	    public List search(TimetableBean bean) throws ApplicationException {
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

 public List search(TimetableBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");
	System.out.println("search start");
	        if (bean != null) {
	            /*if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }*/
	            if (bean.getCource_Id()>0) {
	                sql.append(" AND COURCE_ID = " + bean.getCource_Id());
	                }  
	            
/*	            if (bean.getCource_Name() != null && bean.getCource_Name().length() > 0) {
	                sql.append(" AND COURCE_NAME like '" + bean.getCource_Name() + "'");
	            }*/
	            
	            if (bean.getSubject_Id() > 0) {
	                sql.append(" AND SUBJECT_ID = " + bean.getSubject_Id());
	            }
	          
	          /*  if (bean.getSubject_Name()!= null && bean.getSubject_Name().length() > 0) {
	                sql.append(" AND SUBJECT_NAME like '" + bean.getSubject_Name()
	                        + "%'");
	            }
	            
	            if (bean.getSemester() != null && bean.getSemester().length()>0) {
	                sql.append(" AND SEMESTER like '" + bean.getSemester()
	                        + "%'");
	            }
	          
	            if (bean.getExam_time() != null) {
	                sql.append(" AND EXAM_TIME like '" + bean.getExam_time()
	                        + "%'");
	            }
	            */
	            if (bean.getExam_Date() != null) {
	            	Date d=new Date(bean.getExam_Date().getTime());
	                sql.append(" AND EXAM_DATE like '" + d + "%'");   
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
	        System.out.println("final sql   "+sql);
	        List list = new ArrayList();
	        Connection conn = null;
	        System.out.println("connection par");
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            System.out.println("preap par");
	            ResultSet rs = pstmt.executeQuery();
	            System.out.println("resultset par");
	           
	            while (rs.next()) {
	                TimetableBean bean1 = new TimetableBean();
	                System.out.println("rs.next" );
	                bean1.setId(rs.getInt(1));
	                bean1.setCource_Id(rs.getInt(2));
	                bean1.setCource_Name(rs.getString(3));
	                bean1.setSubject_Id(rs.getInt(4));
	                bean1.setSubject_Name(rs.getString(5));
	                bean1.setSemester(rs.getString(6));
	                bean1.setExam_time(rs.getString(7));
	                bean1.setExam_Date(rs.getDate(8));
	                bean1.setCreatedBy(rs.getString(9));
	                bean1.setModifiedBy(rs.getString(10));;
	                bean1.setCreatedDatetime(rs.getTimestamp(11));
	                bean1.setModifiedDatetime(rs.getTimestamp(12));
	                System.out.println("add "+rs.getTimestamp(11) );
	              list.add(bean1);
	            }
	            rs.close();
	        } catch (Exception e) {
	        	//e.printStackTrace();
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search timetable");
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
	        List list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from ST_TIMETABLE");
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
	               TimetableBean bean=new TimetableBean();
	                
	               bean.setId(rs.getInt(1));
	                bean.setCource_Id(rs.getInt(2));
	                bean.setCource_Name(rs.getString(3));
	                bean.setSubject_Id(rs.getInt(4));
	                bean.setSubject_Name(rs.getString(5));
	                bean.setSemester(rs.getString(6));
	                bean.setExam_time(rs.getString(7));
	                bean.setExam_Date(rs.getDate(8));
	                bean.setCreatedBy(rs.getString(9));
	                bean.setModifiedBy(rs.getString(10));;
	                bean.setCreatedDatetime(rs.getTimestamp(11));
	                bean.setModifiedDatetime(rs.getTimestamp(12));

	                               list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of TIMETABLE");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	        log.debug("Model list End");
	        return list;

	    }


}
