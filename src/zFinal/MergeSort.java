package zFinal;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MergeSort extends MyPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	private int greenColumnStart = -1;
	private int greenColumnFinish = -1;
	Thread b;
	
	public MergeSort(int v,JTextField sug,JTextField ant, int sorttyp) {
		b = new Thread(this);
		b.start();
		this.sugkriseis=sug;
		this.antimetatheseis=ant;
		this.listLen = v;
		list= new int[listLen];
		if(sorttyp==0){
			this.randlistGen();	
		}
		else if(sorttyp==1){
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
		startTime = System.currentTimeMillis();
		try {
			mergeSort(0, list.length - 1);
			greenColumnStart = 0;
			greenColumnFinish = listLen - 1;
		} catch (InterruptedException e) {
		}
		repaint();
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		totalTime = totalTime/1000;
		if(timmed.isSelected()){
			JOptionPane.showMessageDialog(new Frame(),
			    String.valueOf(totalTime)+" seconds");
		}
		sort.setEnabled(true);
		sortlen.setEnabled(true);
		sorttyp.setEnabled(true);
		run.setEnabled(true);
		timmed.setEnabled(true);
		check.setEnabled(true);
		runPressed=false;
	}

	public void mergeSort(int start, int fin) throws InterruptedException {
			
		if ((fin - start) > 0) {
			mergeSort(start, start + (fin - start) / 2);
			mergeSort(start + (fin - start) / 2 + 1, fin);
			merge(start, start + (fin - start) / 2, start + (fin - start) / 2 + 1, fin);
		}
		
	}


public void merge(int start1, int fin1, int start2, int fin2) throws InterruptedException {		
		int[] list1 = new int[fin1 - start1 + 1];
		int[] list2 = new int[fin2 - start2 + 1];
		repaint();
		int[] tmp = new int[list1.length + list2.length];
		text.setText("Χώρισε τα στοιχεία σε υποπίνακες "+(tmp.length)+" θέσεων με ταξινομημένη σειρά.");
		if(step1!=0 && step2==0){
			b.suspend();
		}
		System.arraycopy(list, start1, list1, 0, list1.length);
		System.arraycopy(list, start2, list2, 0, list2.length);
		Thread.sleep(2 * sleepTime);
		repaint();
	    int current1 = 0;
	    redColumn = start1 + current1;
	    int current2 = 0;
	    blueColumn = start2 + current2;
	    int current3 = 0;
	    
	    //text2.setText("Όσο το "+current1+" είναι μικρότερο από "+list1.length+" και το "+current2+" είναι μικρότερο από "+list2.length);
	    //if(step1!=0 && step2==0){
			//b.suspend();
		//}
		while (current1 < list1.length && current2 < list2.length) {
			sug++;
			sugkriseis.setText(String.valueOf(sug));
			if (list1[current1] < list2[current2]) {
				repaint();
				text2.setText("Το στοιχείο "+(list1[current1])+" είναι μικρότερο από το στοιχείο "+(list2[current2]));
				text3.setText("’φησε τα όπως είναι.");
				if(step1!=0 && step2==0){
					b.suspend();
				}
				tmp[current3++] = list1[current1++];
				redColumn = start1 + current1;
				text2.setText("");
				text3.setText("");
			} else {
				repaint();
				text2.setText("Το στοιχείο "+(list1[current1])+" είναι μεγαλύτερο από το στοιχείο "+(list2[current2]));
				text3.setText("Αντάλλαξε τη θέση τους.");
				if(step1!=0 && step2==0){
					b.suspend();
				}
				tmp[current3++] = list2[current2++];
				blueColumn = start2 + current2 - 1;
				text2.setText("");
				text3.setText("");
				ant++;
				antimetatheseis.setText(String.valueOf(ant));
			}
			text2.setText("");
		}

		while (current1 < list1.length) {
			tmp[current3++] = list1[current1++];
			redColumn = start1 + current1;
			Thread.sleep(2 * sleepTime);
			repaint();
			//if(step1!=0 && step2==0){
				//b.suspend();
			//}
		}

		while (current2 < list2.length) {
			tmp[current3++] = list2[current2++];
			blueColumn = start2 + current2 - 1;
			Thread.sleep(2 * sleepTime);
			repaint();
			//if(step1!=0 && step2==0){
				//b.suspend();
			//}
		}

		redColumn = -1;
		blueColumn = -1;
		greenColumnStart = start1;
		for (int i = 0; i < tmp.length; i++) {
			greenColumnFinish = start1 + i;
			list[start1 + i] =  tmp[i];
			Thread.sleep(2 * sleepTime);
			repaint();
		}
		greenColumnStart = -1;
		greenColumnFinish = -1;

	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / listLen;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / listLen;
		for (int i = 0; i < list.length; i++) {
			g.setColor(Color.BLACK);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawString(String.valueOf(i+1), (2 * BORDER_WIDTH + columnWidth * i), getHeight()-5);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(list[i]), ((2 * BORDER_WIDTH + columnWidth * i)+2), ((getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH)+15));
		}
		if((greenColumnStart != -1)&&(greenColumnFinish != -1)) {
			for (int i = greenColumnStart; i <= greenColumnFinish; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.WHITE);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawString(String.valueOf(i+1), (2 * BORDER_WIDTH + columnWidth * i), getHeight()-5);
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(list[i]), ((2 * BORDER_WIDTH + columnWidth * i)+2), ((getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH)+15));
			}
		}
		if(redColumn != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(list[redColumn]), ((2 * BORDER_WIDTH + columnWidth * redColumn)+2), ((getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH)+15));
		}
		if(blueColumn != -1) {
			g.setColor(Color.BLUE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(list[blueColumn]), ((2 * BORDER_WIDTH + columnWidth * blueColumn)+2), ((getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH)+15));
		}

	}

}
