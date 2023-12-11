/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhomework.database;

import com.myhomework.model.Student;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author taing
 */
public class StudentDB {
    static Connection connection = null;
    public StudentDB() {
        String url = "jdbc:mysql://localhost:3306/?user=root";
        String username = System.getenv("TESTUSERNAME_DB");
        String password = System.getenv("TESTPASS_DB");
        try {
            //Register driver
            Statement stmt = null;
            Driver myDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            connection = DriverManager.getConnection(url, username, password);
            if(connection != null){
                System.out.println("Connected successfully");
            }
            stmt = connection.createStatement();
            String sql = "use students";            
            stmt.executeQuery(sql);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        }        
    }
    
    public static void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
             System.err.println(e);
            }
        }
    }
    public static ArrayList<Student> getAllStudent(){
        try{
            Statement stmt = null;

            ArrayList<Student>studentList = new ArrayList();
            
            stmt = connection.createStatement();
            String sql = "select * from student";            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                Date birthday = rs.getDate("birthday");
                String address = rs.getString("address");
                Student s = new Student(id, lastname, firstname, birthday, address);
                studentList.add(s);
            }
            stmt.close();
            return studentList;
        } catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
    public static Boolean AddStudent(int id, String lname, String fname, Date bday, String address){
        try{
            Statement stmt = null;
            stmt = connection.createStatement();
            connection.setAutoCommit(false);
            String sql = "insert into student values(" + id + ",'" + lname + "','" + fname +"','" + bday.toString() + "','" + address +"')";
            stmt.executeUpdate(sql);
            connection.commit();
            stmt.close();

            return true;
        } catch (SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public static Boolean UpdateStudent(int id, String lname, String fname, Date bday, String address){
        try{
            Statement stmt = null;
            stmt = connection.createStatement();
            connection.setAutoCommit(false);
            String sql = "update student set lastname='" + lname +"', firstname ='" + fname +"', birthday='" + bday.toString() + "', address='" + address + "' where id=" + id;
            stmt.executeUpdate(sql);
            connection.commit();
            stmt.close();

            return true;
        } catch (SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public static Boolean DeleteStudent(int id){
        try{
            Statement stmt = null;
            stmt = connection.createStatement();
            connection.setAutoCommit(false);
            String sql = "delete from student where id = " + id;
            stmt.executeUpdate(sql);
            connection.commit();
            stmt.close();

            return true;
        } catch (SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public static ArrayList<Student> FindStudentByName(String name){
        try{
            Statement stmt = null;
            ArrayList<Student>studentList = new ArrayList();
            stmt = connection.createStatement();
            String sql = "select * from student where lastname like '%" + name +"%' or firstname like '%" + name + "%'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                Date birthday = rs.getDate("birthday");
                String address = rs.getString("address");
                Student s = new Student(id, lastname, firstname, birthday, address);
                studentList.add(s);
            }
            stmt.close();
            return studentList;
        }catch (SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
        public static ArrayList<Student> FindStudentById(String ID){
        try{
            Statement stmt = null;
            ArrayList<Student>studentList = new ArrayList();
            stmt = connection.createStatement();
            String sql = "select * from student where id = " + Integer.valueOf(ID);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                Date birthday = rs.getDate("birthday");
                String address = rs.getString("address");
                Student s = new Student(id, lastname, firstname, birthday, address);
                studentList.add(s);
            }
            stmt.close();
            return studentList;
        }catch (SQLException | NumberFormatException e){
            System.err.println(e);
            return null;
        }
    }
}