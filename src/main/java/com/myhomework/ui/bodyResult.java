/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhomework.ui;

import com.myhomework.database.StudentDB;
import com.myhomework.model.Student;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author taing
 */
public class bodyResult extends JPanel {
    static JTable StudentTable;
    public bodyResult() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //Search panel
        JLabel search = new JLabel("Input value to search");
        add(search);
        add(Box.createVerticalStrut(10));

        JLabel searchzone = new JLabel();
        searchzone.setLayout(new BoxLayout(searchzone, BoxLayout.X_AXIS));
        searchzone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        String[] optionsList = {"None", "Id", "Name"};
        JComboBox<String> options = new JComboBox<>(optionsList);
        searchzone.add(options);
        TextField searchInput = new TextField();
        searchInput.setPreferredSize(new Dimension(350, 25));
        searchInput.setFont(new Font("Segoe UI", Font.BOLD, 18));
        searchzone.add(searchInput);
        JButton searchButton = new JButton("SEARCH");
        searchButton.setPreferredSize(new Dimension(100, 25));
        searchzone.add(searchButton);
        add(searchzone);
        add(Box.createVerticalStrut(30));
        searchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                if(options.getSelectedIndex() == 1){
                    ArrayList<Student> data = StudentDB.FindStudentById(searchInput.getText());
                    DefaultTableModel tableModel = (DefaultTableModel) bodyResult.StudentTable.getModel();
                    tableModel.setRowCount(0);
                    for(Student s : data){
                        tableModel.addRow(new Object[] {s.getId(), s.getLastName(), s.getFirstName(), s.getBirthday(), s.getAddress()});
                    }
                }
                
                if(options.getSelectedIndex() == 2){
                    ArrayList<Student> data = StudentDB.FindStudentByName(searchInput.getText());
                    DefaultTableModel tableModel = (DefaultTableModel) bodyResult.StudentTable.getModel();
                    tableModel.setRowCount(0);
                    for(Student s : data){
                        tableModel.addRow(new Object[] {s.getId(), s.getLastName(), s.getFirstName(), s.getBirthday(), s.getAddress()});
                    }
                }
                
                if(options.getSelectedIndex() == 0){
                    searchInput.setText("");
                    ArrayList<Student> data = StudentDB.getAllStudent();
                    DefaultTableModel tableModel = (DefaultTableModel) bodyResult.StudentTable.getModel();
                    tableModel.setRowCount(0);
                    for(Student s : data){
                        tableModel.addRow(new Object[] {s.getId(), s.getLastName(), s.getFirstName(), s.getBirthday(), s.getAddress()});
                    }
                }
            }
        });

        //Table panel
        String[] columnNames = {"Id", "Last name", "First Name", "Date of birth", "Address"};
        ArrayList<Student> data = StudentDB.getAllStudent();
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
        public boolean isCellEditable(int row, int column) {
            return false; // Make all cells non-editable
            }
        };
        StudentTable = new JTable(tableModel);
            for(Student s : data){
            tableModel.addRow(new Object[] {s.getId(), s.getLastName(), s.getFirstName(), s.getBirthday(), s.getAddress()});
        }
            
        StudentTable.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                int row = StudentTable.getSelectedRow();
                String id = StudentTable.getValueAt(row,0).toString();
                String lname = StudentTable.getValueAt(row, 1).toString();
                String fname = StudentTable.getValueAt(row, 2).toString();
                Date bday = (Date)StudentTable.getValueAt(row, 3);
                String addr = StudentTable.getValueAt(row, 4).toString();
                
                bodyDetail.idInput.setText(id);
                bodyDetail.idInput.setEditable(false);
                bodyDetail.lnameInput.setText(lname);
                bodyDetail.fnameInput.setText(fname);
                bodyDetail.birthdayInput.setDate(bday);
                bodyDetail.addressInput.setText(addr);
            }
        });
        JScrollPane scrollPane = new JScrollPane(StudentTable);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        StudentTable.setFillsViewportHeight(true);
        add(scrollPane);
    }
    
}
