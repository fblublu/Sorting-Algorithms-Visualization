package zFinal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BubbleSort extends MyPanel implements Runnable{
	//MyPanel panel;
	Thread b;
	int j=0;
	

	private static final long serialVersionUID = 1L; 
	Graphics g;
	
	BubbleSort(int v,JTextField sug,JTextField ant, int sorttype) {
	//BubbleSort(JPanel p){
		//runPressed=true;
		b = new Thread(this);
		b.start();
		System.out.println(Thread.currentThread());
    	System.out.println(EventQueue.isDispatchThread());
    	this.sugkriseis=sug;
		this.antimetatheseis=ant;
		this.listLen = v;
    	
		list= new int[listLen];
		if(sorttype==0){
			this.randlistGen();	
		}
		else if(sorttype==1){
			this.sortlistGen();
		}
		else{
			this.reverselistGen();
		}
		

		

	}
	

	 
	   public Thread getThread(){

			return this.b;
		}

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread());
    	System.out.println(EventQueue.isDispatchThread());
		startTime = System.currentTimeMillis();
		try {
			this.text.setText("Μετακίνησε το μεγαλύτερο στοιχείο στο τέλος.");
			boolean needNextPass = true;
			for (int k = 1; k < list.length && needNextPass; k++) {		
				
				needNextPass = false;
				//text.setText("Vres to megalutero stoixeio");
				for (int i = 0; i < list.length - k; i++) {
					//text.setText("To megalutero einai stin thesi"+i);
					redColumn = i;
					repaint();
					Thread.sleep(4 * sleepTime);
					this.text2.setText("Είναι το στοιχείο στη θέση "+(i+1)+" μεγαλύτερο από το στοιχείο στη θέση "+(i+2)+" ;");
					
					
					if (list[i] > list[i + 1]) {
						sug++;
						ant++;
						sugkriseis.setText(String.valueOf(sug));
						antimetatheseis.setText(String.valueOf(ant));
						this.text3.setText("Ναι, άλλαξε τα.");
						if(step1!=0 && step2==0){
							b.suspend();
						}
						redColumn = i + 1;
						int temp = list[i];
						list[i] = list[i + 1];
						list[i + 1] = temp;
						repaint();
						Thread.sleep(4 * sleepTime);
						needNextPass = true;
					}else{
						
						
						sug++;
						sugkriseis.setText(String.valueOf(sug));
						text3.setText("Όχι, επέλεξε το στοιχείο στη θέση "+(i+2));
						if(step1!=0 && step2==0){
							b.suspend();
						}
					}
				}
				greenColumn = listLen - k;
			}
			greenColumn = 0;
			redColumn = -1;
			cC=-1;
		}
		catch (InterruptedException e) {
		}
		this.text3.setText("");
		this.text2.setText("Τέλος ταξινόμησης.");
		repaint();
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		totalTime = totalTime/1000;
		if(timmed.isSelected()){
			JOptionPane.showMessageDialog(new Frame(),
			    String.valueOf(totalTime)+" seconds");
		}
		finished=true;
		sort.setEnabled(true);
		sortlen.setEnabled(true);
		sorttyp.setEnabled(true);
		run.setEnabled(true);
		timmed.setEnabled(true);
		check.setEnabled(true);
		runPressed=false;
	}
	
	

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / listLen;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / listLen;		
		for (int i = 0; i < (greenColumn == -1 ? list.length : greenColumn); i++) {
			g.setColor(Color.BLACK);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawString(String.valueOf(i+1), (2 * BORDER_WIDTH + columnWidth * i), getHeight()-5);
		}
		if(greenColumn != -1) {
			for (int i = greenColumn; i < list.length; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.WHITE);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawString(String.valueOf(i+1), (2 * BORDER_WIDTH + columnWidth * i), getHeight()-5);
			}
		}
		if(redColumn != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			//g.drawString(String.valueOf(i+1), (2 * BORDER_WIDTH + columnWidth * i)+10, getHeight() - list[i] * columnHeight - 2 );
		}

	}
	
	

	

}
