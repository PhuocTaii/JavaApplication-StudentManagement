/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhomework.ui;

/**
 *
 * @author taing
 */

import java.awt.BorderLayout;
import javax.swing.*;
//import com.myhomework.ui.pageHeader;
        
public class MainFrame extends JFrame{
    
    public MainFrame() {
        JFrame jfrm = new JFrame("Student management");
        jfrm.setSize(1080, 768);
        jfrm.setResizable(false);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //header
        JLabel pageTittle = new pageHeader();
        jfrm.add(pageTittle, BorderLayout.NORTH);
        //body (detail & table)
        JPanel pageBody = new pageBody();
        jfrm.add(pageBody);
        jfrm.setVisible(true);
    }
}
