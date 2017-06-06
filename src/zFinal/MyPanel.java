package zFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;


public class MyPanel extends JPanel {
	public int[] list;
	protected int listLen;
	protected static int sleepTime = 30;
	protected int redColumn = -1;
	protected int blueColumn = -1;
	protected int cyanColumn = -1;
	protected int greenColumn = -1;
	protected int cC = -1;
	protected static long startTime= 0;
	protected static long endTime= 0 ;
	protected static long totalTime= 0;
	protected static final int BORDER_WIDTH = 10;
	private JTextField pick;
	public static JComboBox<String> sort;
	public static JComboBox sortlen;
	public static JButton run;
	protected static JTextField text;
	protected static  JTextField text2;
	protected static JTextField text3;
	protected JTextField sugkriseis;
	protected JTextField antimetatheseis;
	public static JComboBox sorttyp;
	private JTextField textanti;
	private JTextField textsug;
	protected int sug=0;
	protected int ant=0;
	private JTextField textLen;
	protected static JCheckBox check;
	private JTextField listType;
	protected JButton stop;
	public volatile  static boolean stopRequested = false;
	BubbleSort bubble;
	QuickSort quick;
	MergeSort merge;
	SelectionSort select;
	InsertionSort insert;
	protected static boolean finished = false;
	protected static JButton step;
	protected static int step1 = 0;
	protected static int step2 = 0;
	protected static JCheckBox timmed;
	public static boolean runPressed = false;
	//public static boolean stopPressed = false;
	private Font font = new Font("Verdana",Font.PLAIN,10);
	private JButton algo;
	MyPanel(){
		
	}
	
	
	public MyPanel(JPanel panel2) 
	  {
		panel2.removeAll();
		
		pick = new JTextField("������� ������");
		pick.setFont(font);
		pick.setBounds(10, 20, 155, 23);
		pick.setBackground(Color.LIGHT_GRAY);
		pick.setEditable(false);
		panel2.add(pick);
		
		String[] sortStrings = {"��������", "Γρήγορη Ταξιμόνηση", "MergeSort", "��������� �������", "��������� ��������"};
		sort = new JComboBox<String>(sortStrings);
		sort.setFont(font);
		sort.setBounds(10, 50, 155, 23);
		panel2.add(sort);
		
		textLen = new JTextField("������� ������� ���������");
		textLen.setFont(font);
		textLen.setBounds(10, 80, 155, 23);
		textLen.setBackground(Color.LIGHT_GRAY);
		textLen.setEditable(false);
		panel2.add(textLen);
		
		String[] sortlens = {"15",  "30", "50"};
		sortlen = new JComboBox(sortlens);
		sortlen.setFont(font);
		sortlen.setBounds(10, 110, 155, 23);
		panel2.add(sortlen);
		
		listType = new JTextField("������� ����� ���������");
		listType.setFont(font);
		listType.setBounds(10, 140, 155, 23);
		listType.setBackground(Color.LIGHT_GRAY);
		listType.setEditable(false);
		panel2.add(listType);
		
		String[] sortType = {"������ �����", "������������", "������� ������������"};
		sorttyp = new JComboBox(sortType);
		sorttyp.setFont(font);
		sorttyp.setBounds(10, 170, 155, 23);
		panel2.add(sorttyp);
		
		
		run = new JButton("��������");
		run.setFont(font);
		run.setBounds(10, 200, 155, 35);
		panel2.add(run);
		
		text = new JTextField("������� �������� ��� �� ��������� � ����������!");
		text.setFont(font);
		text.setBounds(180, 370, 625, 23);
		text.setBackground(Color.LIGHT_GRAY);
		text.setEditable(false);
		panel2.add(text);
		
		text2 = new JTextField("");
		text2.setFont(font);
		text2.setBounds(180, 400, 625, 23);
		text2.setBackground(Color.LIGHT_GRAY);
		text2.setEditable(false);
		panel2.add(text2);
		
		text3 = new JTextField("");
		text3.setFont(font);
		text3.setBounds(180, 430, 625, 23);
		text3.setBackground(Color.LIGHT_GRAY);
		text3.setEditable(false);
		panel2.add(text3);
				
		textsug = new JTextField("����������");
		textsug.setFont(font);
		textsug.setBounds(10, 240, 155, 23);
		textsug.setBackground(Color.LIGHT_GRAY);
		textsug.setEditable(false);
		panel2.add(textsug);
		
		sugkriseis = new JTextField("0");
		sugkriseis.setFont(font);
		sugkriseis.setBounds(10, 260, 155, 23);
		sugkriseis.setBackground(Color.LIGHT_GRAY);
		sugkriseis.setEditable(false);
		panel2.add(sugkriseis);
		
		textanti = new JTextField("��������������");
		textanti.setFont(font);
		textanti.setBounds(10, 300, 155, 23);
		textanti.setBackground(Color.LIGHT_GRAY);
		textanti.setEditable(false);
		panel2.add(textanti);
		
		antimetatheseis = new JTextField("0");
		antimetatheseis.setFont(font);
		antimetatheseis.setBounds(10, 320, 155, 23);
		antimetatheseis.setBackground(Color.LIGHT_GRAY);
		antimetatheseis.setEditable(false);
		panel2.add(antimetatheseis);
		
		
		check = new JCheckBox();
		check.setFont(font);
		check.setText("������� ��������");
		check.setBounds(15, 359, 155, 23);
		check.setBackground(Color.LIGHT_GRAY);
		check.setSelected(false);
		panel2.add(check);
		
		stop = new JButton("pause");
		stop.setFont(font);
		stop.setBounds(10, 390, 100 , 25);
		panel2.add(stop);
		
		step = new JButton("step");
		step.setFont(font);
		step.setBounds(10, 420, 100 , 25);
		panel2.add(step);
		
		timmed = new JCheckBox();
		timmed.setFont(font);
		timmed.setText("������������");
		timmed.setBounds(15, 450, 155, 30);
		timmed.setBackground(Color.LIGHT_GRAY);
		timmed.setSelected(false);
		panel2.add(timmed);
		
		algo = new JButton("����� ��� ���������");
		algo.setFont(font);
		algo.setBounds(10, 480, 155, 25);
		panel2.add(algo);
		
		
		
		ActionListener showalgo = new ActionListener() {

	        @Override
	          public void actionPerformed(ActionEvent e) {
	        	if(sort.getSelectedIndex()==0){
	        		JOptionPane.showMessageDialog(new Frame(),
	    			    ("boolean needNextPass = true;\n"+
	    			    		"for (int k = 1; k < list.length && needNextPass; k++) {\n"+
	    			    			"   needNextPass = false;\n"+
	    			    			"   for (int i = 0; i < list.length - k; i++) {\n"+
	    			    				"      if(list[i] > list[i + 1]) {\n"+
	    			    						"          int temp = list[i];\n"+
	    			    						"          list[i] = list[i + 1];\n"+
	    			    						"          list[i + 1] = temp;\n"+
	    			    						"          needNextPass = true;\n"+
	    			    				"      }\n"+
	    			    			"   }\n"+
	    			    		"}"));
	        	}
	        	else if(sort.getSelectedIndex()==1){
	        		JOptionPane.showMessageDialog(new Frame(),
		    			    ("int i = low;\n"+
		    				"int j = high;\n"+
		    				"int pivot = list[i];\n"+
		    				"while (i <= j) {\n"+
		    					"   while (list[i] < pivot) {\n"+
		    						"      i++;\n"+
		    					"}\n"+
		    					"   while (list[j] > pivot) {\n"+
		    						"      j--;\n"+
		    					"}\n"+
		    					"   if (i <= j) {\n"+
		    						"      int temp = list[i];\n"+
		    						"      list[i] = list[j];\n"+
		    						"      list[j] = temp;\n"+
		    						"      i++;\n"+
		    						"      j--;\n"+
		    					"}\n"+
		    				"}\n"+
		    				"if(low < j) {\n"+
		    					"quicksort(low, j);\n"+
		    				"}\n"+
		    				"if(i < high) {\n"+
		    					"quicksort(i, high);\n"+
		    				"}"));
	        	}
	        	else if(sort.getSelectedIndex()==2){
	        		JOptionPane.showMessageDialog(new Frame(),
		    			    ("mergeSort(int start, int fin){\n"+
		    			    		"   if ((fin - start) > 0) {\n"+
		    			    			"      mergeSort(start, start + (fin - start) / 2);\n"+
		    			    			"      mergeSort(start + (fin - start) / 2 + 1, fin);\n"+
		    			    			"      merge(start, start + (fin - start) / 2, start + (fin - start) / 2 + 1, fin);\n"+
		    			    		"   }\n"+
	        		"}\n"+
	        		"merge(int start1, int fin1, int start2, int fin2){\n"+
	        			"   int[] list1 = new int[fin1 - start1 + 1];\n"+
	        			"   int[] list2 = new int[fin2 - start2 + 1];\n"+
	        			"   int[] tmp = new int[list1.length + list2.length];\n"+
	        			"   System.arraycopy(list, start1, list1, 0, list1.length);\n"+
	        			"   System.arraycopy(list, start2, list2, 0, list2.length);\n"+
	    "   int current1 = 0;\n"+
	    "   int current2 = 0;\n"+
	    "   int current3 = 0;\n"+
		"   while(current1 < list1.length && current2 < list2.length) {\n"+
			"      if(list1[current1] < list2[current2]) {\n"+
				"         tmp[current3++] = list1[current1++];\n"+
				"      }\n"+
				"      else {\n"+
				"         tmp[current3++] = list2[current2++];\n"+
				"      }\n"+
			"   }\n"+
			"   while (current1 < list1.length) {\n"+
			"      tmp[current3++] = list1[current1++];\n"+
			"   }\n"+
			"   while (current2 < list2.length) {\n"+
				"      tmp[current3++] = list2[current2++];\n"+
				"   }\n"+
				"}"));
	        	}
	        	else if(sort.getSelectedIndex()==3){
	        		JOptionPane.showMessageDialog(new Frame(),
		    			    ("for(int i = 0; i < list.length - 1; i++){\n"+
		    			    		"   int currentMinIndex = i;\n"+
		    			    		"   for (int j = i + 1; j < list.length; j++){\n"+
		    			    			"      if (list[currentMinIndex] > list[j]){\n"+
		    			    				"         currentMinIndex = j;\n"+
		    			    			"      }\n"+
		    			    		"   }\n"+
		    			    		"   if(currentMinIndex != i){\n"+
		    			    			"      int tmp = list[currentMinIndex];\n"+
		    			    			"      list[currentMinIndex] = list[i];\n"+
		    			    			"      list[i] = tmp;\n"+
		    				"   }\n"+
		    				"}"));
	        	}
	        	else if(sort.getSelectedIndex()==4){
	        		JOptionPane.showMessageDialog(new Frame(),
		    			    ("for(int i = 1; i < list.length; i++){\n"+
		    			    		"   int k;\n"+
		    			    		"   for(k = i - 1; k >= 0 && list[k] > list[k + 1]; k--){\n"+
		    			    			"      int tmp = list[k + 1]; \n"+
		    			    			"      list[k + 1] = list[k];\n"+
		    			    			"      list[k] = tmp;\n"+
		    			    		"   }\n"+
		    				"}"));
	        	}
	        	}
			};
			algo.addActionListener(showalgo);
		
		ActionListener stopit = new ActionListener() {

	        @Override
	          public void actionPerformed(ActionEvent e) {
	        	sort.setEnabled(true);
	    		sortlen.setEnabled(true);
	    		sorttyp.setEnabled(true);
	    		run.setEnabled(true);
	    		runPressed=false;
	    		check.setEnabled(true);
	    		timmed.setEnabled(true);
	        	w();
	        	
	        	if(stop.getText()=="pause")
	        		stop.setText("resume");
	        	else
	        		stop.setText("pause");
	        	//stopPressed = true;
	        	
	        }
		};
		stop.addActionListener(stopit);
		
		
		
	
		  
		  
		ActionListener chec = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(check.isSelected()){
	        		sleepTime=5;
	        	}else{
	        		sleepTime=30;
	        	}
	        		
	        }
		};
		check.addActionListener(chec);
		
		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.validate();
		
		
		
		ActionListener freeze = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	stop.setEnabled(false);
	        		
	        }
		};
		sort.addActionListener(freeze);

		//MyPanel panel= new MyPanel();
		//panel.setBounds(150, 11, 550, 300);
		//p.add(panel);
		
		ActionListener start = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	stop.setEnabled(true);
	        	stop.setText("pause");
	        	finished=false;
	        	sort.setEnabled(false);
	        	sortlen.setEnabled(false);
	        	sorttyp.setEnabled(false);
	        	run.setEnabled(false);
	        	step1 = 0;
	        	step2 = 0;
	        	runPressed=true;
	        	if(sort.getSelectedIndex()==0){
	        		
	        		//Thread b = new Thread(buble);
	        		bubble = new BubbleSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		bubble.setBounds(180, 20, 800, 300);
	        		bubble.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(bubble);
	
					
	        	}
	        	else if(sort.getSelectedIndex()==1){
	        		quick = new QuickSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		quick.setBounds(180, 20, 800, 300);
	        		quick.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(quick);
	        		


	        	}
	        	else if(sort.getSelectedIndex()==2){
	        		merge = new MergeSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		merge.setBounds(180, 20, 800, 300);
	        		merge.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(merge);

	        	}
	        	else if(sort.getSelectedIndex()==3){
	        		select = new SelectionSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		select.setBounds(180, 20, 800, 300);
	        		select.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(select);

	        	}
	        	else if(sort.getSelectedIndex()==4){
	        		insert = new InsertionSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		insert.setBounds(180, 20, 800, 300);
	        		insert.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(insert);

	        	}

	        	
	        	
	        }
	    };
	    run.addActionListener(start);
	    
	    
	    
	    ActionListener stepLis = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	step1=1;
	        	step2=0;
	        	timmed.setEnabled(false);
	        	check.setEnabled(false);
	        	
	          if(sort.getSelectedIndex()==0){
	        		if(runPressed==true)	 
	        			bubble.getThread().resume();
	        		else{
	        			bubble = new BubbleSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        			bubble.setBounds(180, 20, 800, 300);
	        			bubble.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        			panel2.add(bubble);
	        			runPressed=true;
	  		   		}
	  		   }
	  		   else if(sort.getSelectedIndex()==1){
	  			 if(runPressed==true)	
	  			   		quick.getThread().resume();
	  			else{
	  				quick = new QuickSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		quick.setBounds(180, 20, 800, 300);
	        		quick.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(quick);
	        		runPressed=true;
	        	
	  				}
	  		   }
	  		   else if(sort.getSelectedIndex()==2){
	  			 if(runPressed==true)	
	  			   merge.getThread().resume();
	  			else{
	  				merge = new MergeSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		merge.setBounds(180, 20, 800, 300);
	        		merge.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(merge);
	        		runPressed=true;
	  			}
	  		   }
	  		   else if(sort.getSelectedIndex()==3){
	  			 if(runPressed==true)	
	  			   select.getThread().resume();
	  			else{
	  				select = new SelectionSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		select.setBounds(180, 20, 800, 300);
	        		select.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(select);
	        		runPressed=true;

	  			}
	  		   }
	  		   else if(sort.getSelectedIndex()==4){
	  			 if(runPressed==true)	
	  			   insert.getThread().resume();
	  			else{
	  				insert = new InsertionSort(Integer.valueOf((String) sortlen.getSelectedItem()),sugkriseis,antimetatheseis,sorttyp.getSelectedIndex());
	        		insert.setBounds(180, 20, 800, 300);
	        		insert.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	        		panel2.add(insert);
	        		runPressed=true;
	  			}
	  		   }
	          		
	        	 //runPressed=true;
	        }
		};
		step.addActionListener(stepLis);
	
		
	  }
	
	


	
	public MyPanel(int v) 
	  {
		  
	  }
	
	public void isFinished(){
		if(finished){
			sort.setEnabled(true);
        	sortlen.setEnabled(true);
        	sorttyp.setEnabled(true);
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	public void randlistGen(){
		for(int i=0; i<list.length;i++){
			list[i] = i+1;
		}
		
		for(int i=0; i<list.length;i++){
			System.out.print(list[i]+" ");
		}
		
		System.out.println(" ");
		
		for(int i=0; i<list.length;i++){
			int index = (int)(Math.random()*list.length);
			int temp = list[i];
			list[i] = list[index];
			list[index] = temp;
		}
		
		for(int i=0; i<list.length;i++){
			System.out.print(list[i]+" ");
		}
		
		System.out.println(" ");
		
	}
	
	public void reverselistGen(){
		int j = 0;
		for(int i=list.length; i>0;i--){
			list[j] = i;
			j++;
		}
		
		for(int i=0; i<list.length;i++){
			System.out.print(list[i]+" ");
		}
		
		System.out.println(" ");

	}
	
	public void sortlistGen(){
		for(int i=0; i<list.length;i++){
			list[i] = i+1;
		}
		for(int i=0; i<list.length;i++){
			System.out.print(list[i]+" ");
		}
		
		System.out.println(" ");
	}
	
	
	   public void  w(){
		   
		   step1 = 0;
       		step2 = 0;
		   if(stop.getText()=="pause"){
			   isPause(sort.getSelectedIndex());
			   
		   //Thread.currentThread().stop();
		   }
		   else{
			   isResume(sort.getSelectedIndex());
			   
		   }
		}
	   
	   private void isPause(int type){
		   step1=1;
		   if(type==0){
			   bubble.getThread().suspend();
		   }
		   else if(type==1){
			   quick.getThread().suspend();
		   }
		   else if(type==2){
			   merge.getThread().suspend();
		   }
		   else if(type==3){
			   select.getThread().suspend();
		   }
		   else if(type==4){
			   insert.getThread().suspend();
		   }
	   }
	   
	   private void isResume(int type){
		   if(type==0){
			   bubble.getThread().resume();
		   }
		   else if(type==1){
			   quick.getThread().resume();
		   }
		   else if(type==2){
			   merge.getThread().resume();
		   }
		   else if(type==3){
			   select.getThread().resume();
		   }
		   else if(type==4){
			   insert.getThread().resume();
		   }
		   
	   }
	   
	  
	
}
