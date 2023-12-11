/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhomework.ui;

import com.myhomework.database.StudentDB;
import com.myhomework.model.Student;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author taing
 */
public class bodyDetail extends JPanel {
    static TextField idInput, fnameInput, lnameInput,addressInput;
    static JDateChooser birthdayInput;
    public bodyDetail(){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel idzone = new JLabel();
        idzone.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        idzone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        JLabel id = new JLabel("ID:");
        id.setPreferredSize(new Dimension(100, 20));
        idInput = new TextField();
        idInput.setPreferredSize(new Dimension(300, 20));
        idzone.add(id);
        idzone.add(idInput);
        add(idzone);
        add(Box.createVerticalStrut(30));
        
        JLabel lastnamezone = new JLabel();
        lastnamezone.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        lastnamezone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        JLabel lastname = new JLabel("Last name:");
        lastname.setPreferredSize(new Dimension(100, 20));
        lnameInput = new TextField();
        lnameInput.setPreferredSize(new Dimension(300, 20));
        lastnamezone.add(lastname);
        lastnamezone.add(lnameInput);
        add(lastnamezone);
        add(Box.createVerticalStrut(30));
        
        JLabel firstnamezone = new JLabel();
        firstnamezone.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        firstnamezone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        JLabel firstname = new JLabel("First name:");
        firstname.setPreferredSize(new Dimension(100, 20));
        fnameInput = new TextField();
        fnameInput.setPreferredSize(new Dimension(300, 20));
        firstnamezone.add(firstname);
        firstnamezone.add(fnameInput);
        add(firstnamezone);
        add(Box.createVerticalStrut(30));

        JLabel birthdayzone = new JLabel();
        birthdayzone.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        birthdayzone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        JLabel birthday = new JLabel("Birthday:");
        birthday.setPreferredSize(new Dimension(100, 20));
        birthdayInput = new JDateChooser();
        birthdayInput.setPreferredSize(new Dimension(300, 20));
        birthdayInput.setFont(new Font("Segoe UI", Font.BOLD, 12));
        birthdayzone.add(birthday);
        birthdayzone.add(birthdayInput);
        add(birthdayzone);
        add(Box.createVerticalStrut(30));

        JLabel addresszone = new JLabel();
        addresszone.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        addresszone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        JLabel address = new JLabel("Address:");
        address.setPreferredSize(new Dimension(100, 20));
        addressInput = new TextField();
        addressInput.setPreferredSize(new Dimension(300, 20));
        addresszone.add(address);
        addresszone.add(addressInput);
        add(addresszone);
        add(Box.createVerticalStrut(30));        
        
        JLabel choices = new JLabel();
        choices.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        choices.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JButton add = new JButton("Add");
        JButton modify = new JButton("Upgrade");
        JButton delete = new JButton("Delete");
        JButton clear = new JButton("Clear");
        
        choices.add(add);
        choices.add(modify);
        choices.add(delete);
        choices.add(clear);

        add(choices);       
        
        JLabel announce = new JLabel("");  
        announce.setPreferredSize(new Dimension(540, 20));
        announce.setMaximumSize(new Dimension(540, 20));
        announce.setMinimumSize(new Dimension(540, 20));

        add(announce);
        add.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                int id = Integer.parseInt(idInput.getText());
                String lname = lnameInput.getText();
                String fname = fnameInput.getText();
                java.sql.Date sqlDate = new java.sql.Date(birthdayInput.getDate().getTime());
                Date bday = sqlDate;
                String address = addressInput.getText();
                Boolean addSuccess = StudentDB.AddStudent(id, lname, fname, bday, address);
                if(addSuccess == true){
                    announce.setText("Add student success!");
                    ArrayList<Student> data = StudentDB.getAllStudent();
                    DefaultTableModel tableModel = (DefaultTableModel) bodyResult.StudentTable.getModel();
                    tableModel.setRowCount(0);
                    for(Student s : data){
                        tableModel.addRow(new Object[] {s.getId(), s.getLastName(), s.getFirstName(), s.getBirthday(), s.getAddress()});
                    }
                    idInput.setText("");
                    lnameInput.setText("");
                    fnameInput.setText("");
                    birthdayInput.setCalendar(null);
                    addressInput.setText("");
                    idInput.setEditable(true);
                }
                else{
                    announce.setText("Add student failed!");
                }
            }
        });
        
        modify.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                int id = Integer.parseInt(idInput.getText());
                String lname = lnameInput.getText();
                String fname = fnameInput.getText();
                java.sql.Date sqlDate = new java.sql.Date(birthdayInput.getDate().getTime());
                Date bday = sqlDate;
                String address = addressInput.getText();
                Boolean updateSuccess = StudentDB.UpdateStudent(id, lname, fname, bday, address);
                if(updateSuccess == true){
                    announce.setText("Update student success!");
                    ArrayList<Student> data = StudentDB.getAllStudent();
                    DefaultTableModel tableModel = (DefaultTableModel) bodyResult.StudentTable.getModel();
                    tableModel.setRowCount(0);
                    for(Student s : data){
                        tableModel.addRow(new Object[] {s.getId(), s.getLastName(), s.getFirstName(), s.getBirthday(), s.getAddress()});
                    }
                    idInput.setText("");
                    lnameInput.setText("");
                    fnameInput.setText("");
                    birthdayInput.setCalendar(null);
                    addressInput.setText("");
                    idInput.setEditable(true);
                }
                else{
                    announce.setText("Update student failed!");
                }
            }
        });
        
        delete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                int id = Integer.parseInt(idInput.getText());
                Boolean deleteSuccess = StudentDB.DeleteStudent(id);
                if(deleteSuccess == true){
                    announce.setText("Delete student success!");
                    ArrayList<Student> data = StudentDB.getAllStudent();
                    DefaultTableModel tableModel = (DefaultTableModel) bodyResult.StudentTable.getModel();
                    tableModel.setRowCount(0);
                    for(Student s : data){
                        tableModel.addRow(new Object[] {s.getId(), s.getLastName(), s.getFirstName(), s.getBirthday(), s.getAddress()});
                    }
                    idInput.setText("");
                    lnameInput.setText("");
                    fnameInput.setText("");
                    birthdayInput.setCalendar(null);
                    addressInput.setText("");
                    idInput.setEditable(true);
                }
                else{
                    announce.setText("Delete student failed!");
                }
            }
        });
        
        clear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                idInput.setText("");
                lnameInput.setText("");
                fnameInput.setText("");
                birthdayInput.setCalendar(null);
                addressInput.setText("");
                idInput.setEditable(true);
                announce.setText("Clear success!");
            }
        });
    }
}
