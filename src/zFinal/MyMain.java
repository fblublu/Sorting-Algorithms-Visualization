package zFinal;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyMain {
	private static final long serialVersionUID = 1L;
	URL url1 = MyMain.class.getResource("/icon.jpg");
	URL url2 = MyMain.class.getResource("/logo_teiser.png");
	final public ImageIcon img = new ImageIcon(url1);
	final public ImageIcon img2 = new ImageIcon(url2);
	
	private MyMain(){
		System.out.println(Thread.currentThread());
    	System.out.println(EventQueue.isDispatchThread());
    	JFrame f = new JFrame();
    	
    	 f.setFont(new Font("Verdana",1,20));
    	f.setTitle("Οπτικοποίηση αλγορίθμων ταξινόμησης");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1000, 600);
		
		f.setResizable(false);
		
		
		
		f.setIconImage(img.getImage());
		
		
    	JPanel panel1 = new JPanel();
    	panel1.setBackground(Color.WHITE);
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel wIcon = new JLabel(img2);
		wIcon.setBounds(800, 400, 170, 135);
		panel1.add(wIcon);
		
		
		JLabel label1 = new JLabel();
	    label1.setText("������������ ���������� �����������");
	    label1.setFont(new Font("Verdana",1,20));
	    label1.setBounds(270, 50, 450, 50);
		panel1.add(label1);
		
		
		
		JButton b = new JButton("����");
		b.setFont(new Font("Verdana",1,20));
		b.setBounds(420, 300, 120, 35);
		panel1.add(b);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("�����");
		menuBar.add(menu);
		JMenuItem menuItem1 = new JMenuItem("������� �� ��� ��������");
		menu.add(menuItem1);
		menu.addSeparator();
		JMenuItem menuItem2 = new JMenuItem("������");
		menu.add(menuItem2);
		
		
		f.setJMenuBar(menuBar);
		f.setVisible(true);
		
		ActionListener exit = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	System.exit(0);
	        }		 
		};
		menuItem2.addActionListener(exit);
		
		ActionListener info = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	JOptionPane.showMessageDialog(null,"������������� ��� ��� ����� ���������\n\n������: v1.0\n\n������, ������� 2016");
	        }		 
		};
		menuItem1.addActionListener(info);
		
		
		ActionListener start = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	MyPanel panel2 = new MyPanel(panel1);
	        }		 
		};
		b.addActionListener(start);
		}
	
	
	
	public static void main(String[] args)throws Exception  {
		SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		    	  MyMain main = new MyMain();
		    	
		    	
		    
		}});


	
		
	}
	}




	
