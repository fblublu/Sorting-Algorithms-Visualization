package zFinal;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SelectionSort extends MyPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	Thread b;
	
	 SelectionSort(int v,JTextField sug,JTextField ant, int sorttyp) {
		 b = new Thread(this);
			b.start();
			//runPressed=true;
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
			
			for (int i = 0; i < list.length - 1; i++) {
				this.text.setText("Βρες το μικρότερο στοιχείο και αντάλλαξέ το με αυτό στην θέση "+(i+1));
				int currentMinIndex = i;
				redColumn = currentMinIndex;
				
				for (int j = i + 1; j < list.length; j++) {
					blueColumn = j;
					repaint();
					this.text2.setText("Είναι το "+(j+1)+" μικρότερο από το "+(currentMinIndex+1)+" ;");
					if(step1!=0 && step2==0){
						b.suspend();
					}
					Thread.sleep(4 * sleepTime);
					if (list[currentMinIndex] > list[j]) {
						text3.setText("Ναι. Το νέο ελάχιστο είναι στη θέση "+(j+1));
						
						if(step1!=0 && step2==0){
							b.suspend();
						}
						currentMinIndex = j;
						
						redColumn = currentMinIndex;
						repaint();
					}
					else{
						if(j==list.length-1){
							sug++;
							sugkriseis.setText(String.valueOf(sug));
							text3.setText("Όχι.");
						}
						else{
							sug++;
							sugkriseis.setText(String.valueOf(sug));
							text3.setText("Όχι, συνέχισε στο επόμενο.");
						}
						if(step1!=0 && step2==0){
							b.suspend();
						}
					}
					text3.setText("");
				}
				
				if (currentMinIndex != i) {
					ant++;
					antimetatheseis.setText(String.valueOf(ant));
					text2.setText("Άλλαξε το στοιχείο στην θέση "+(currentMinIndex+1)+" με αυτό στην θέση "+(i+1)+".");
					text3.setText("");
					if(step1!=0 && step2==0){
						b.suspend();
					}
					int tmp = list[currentMinIndex];
					list[currentMinIndex] = list[i];
					list[i] = tmp;
					repaint();
					Thread.sleep(4 * sleepTime);
				}
				greenColumn++;
				repaint();
			}
			greenColumn++;
			redColumn = -1;
			blueColumn = -1;
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
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / listLen;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / listLen;
		for (int i = (greenColumn == -1 ? 0 : greenColumn); i < list.length; i++) {
			g.setColor(Color.BLACK);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);	
			g.setColor(Color.BLACK);
			g.drawString(String.valueOf(i+1), (2 * BORDER_WIDTH + columnWidth * i), getHeight()-5);
		}
		for (int i = 0; i <= greenColumn; i++) {
			g.setColor(Color.GREEN);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawString(String.valueOf(i+1), (2 * BORDER_WIDTH + columnWidth * i), getHeight()-5);
		}
		if(redColumn != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
		}
		if(blueColumn != -1) {
			g.setColor(Color.BLUE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
			g.setColor(Color.WHITE);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
		}

	}

}
