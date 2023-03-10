package com.jn.javagame;

import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

public class Window extends Canvas {

	
	public Window(int width,int height,String title,Game game) {
		JFrame frame=new JFrame(title);
		Dimension dimension=new Dimension(width,height);
		frame.setPreferredSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setMinimumSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		game.start();
	}



}
