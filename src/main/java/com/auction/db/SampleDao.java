package com.auction.db;

import org.springframework.jdbc.core.JdbcTemplate;  

public class SampleDao {  
private JdbcTemplate jdbcTemplate;  
  
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
    this.jdbcTemplate = jdbcTemplate;  
}  
  
public int saveA1(){  
    String query="insert into a1 values('Venni')";  
    return jdbcTemplate.update(query);  
} 
public int saveB1(){  
    String query="insert into employee values(7,'san',21)";  
    return jdbcTemplate.update(query);  
} 
/*public int updateEmployee(Employee e){  
    String query="update employee set   
    name='"+e.getName()+"',salary='"+e.getSalary()+"' where id='"+e.getId()+"' ";  
    return jdbcTemplate.update(query);  
}  
public int deleteEmployee(Employee e){  
    String query="delete from employee where id='"+e.getId()+"' ";  
    return jdbcTemplate.update(query);  
}  */

}

