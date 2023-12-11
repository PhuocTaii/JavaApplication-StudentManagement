/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myhomework.studentmanagement;

import com.myhomework.database.StudentDB;
import com.myhomework.ui.MainFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.SwingUtilities;


/**
 *
 * @author taing
 */
public class StudentManagement {
    public static void main(String[] args) {
        StudentDB database = new StudentDB();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                MainFrame mainF = new MainFrame();
                mainF.addWindowListener(new WindowAdapter(){
                    public void windowClosed(WindowEvent e){
                        StudentDB.closeConnection();
                    }
                });
            }
        });
    }
}
