/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhomework.ui;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author taing
 */
public class pageHeader extends JLabel{

    public pageHeader() {
        super("STUDENT MANAGEMENT");
//        JLabel pageTittle = new JLabel("STUDENT MANAGEMENT");
        setPreferredSize(new Dimension(1080, 50));
        setHorizontalAlignment(JLabel.CENTER);
    }
    
}
