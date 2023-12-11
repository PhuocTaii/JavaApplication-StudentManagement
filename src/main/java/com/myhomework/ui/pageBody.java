/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhomework.ui;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author taing
 */
public class pageBody extends JPanel{

    public pageBody() {
        super();
        setLayout(new GridLayout(0, 2));
        JPanel detail = new bodyDetail();
        add(detail);
        JPanel result = new bodyResult();
        add(result);
    }
    
}
