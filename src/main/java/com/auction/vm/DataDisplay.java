package com.auction.vm;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.auction.pojo.User;

public class DataDisplay {

    public static void main(String[] args) {

        //Make your database calls here and fetch the data.
        DataDisplay obj = new DataDisplay();
        ArrayList<User> emp_arry = obj.getData();
         
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine ve = new VelocityEngine();
        ve.init(props);
        Template t = ve.getTemplate("report.vm");
        VelocityContext context = new VelocityContext();
        context.put("message", "Employee List Of Company xyz");
        context.put("emp_arry", emp_arry);

        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        System.out.println(writer.toString());


    }

    public ArrayList<User> getData() {

        ArrayList<User> emp_arry = new ArrayList();
        User obj1 = new User();
        obj1.setFirstName("ram");
        obj1.setPassword("pwd");
        obj1.setUserId(1);
        emp_arry.add(obj1);
        User obj2 = new User();
        obj2.setFirstName("ram1");
        obj2.setPassword("pwd1");
        obj2.setUserId(11);
        emp_arry.add(obj2);
        User obj3 = new User();
        obj3.setFirstName("ram2");
        obj3.setPassword("pwd2");
        obj3.setUserId(12);
        emp_arry.add(obj3);
        /*EmployeeVo obj2 = new EmployeeVo();
        obj2.setName("jack");
        obj2.setSalary(500);
        obj2.setDesignation("Developer");
        emp_arry.add(obj2);*/

       
        
        return emp_arry;
    }
}
