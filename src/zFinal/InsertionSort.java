package zFinal;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InsertionSort extends MyPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	Thread b;
	
	InsertionSort(int v,JTextField sug,JTextField ant, int sorttyp) {
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
			this.text.setText("Βάλε στη σωστή θέση τα στοιχεία μετακινώντας τα, αν χρειαστεί, δεξιά κατά μία θέση, ξεκινώντας από το δεύτερο.");

			for (int i = 1; i < list.length; i++) {
				greenColumn = i;
				redColumn = greenColumn;
				int j;
				
				sug++;
				sugkriseis.setText(String.valueOf(sug));
				for (j = i - 1; j >= 0 && list[j] > list[j + 1]; j--) {
					this.text2.setText("Το στοιχείο στη θέση "+(j+2)+" είναι μικρότερο από το στοιχείο στη θέση "+(j+1)+".");
					this.text3.setText("Επομένως συνεχίζει.");
					Thread.sleep(3 * sleepTime);
					repaint();
				
					redColumn = j + 1;
					repaint();
					
					if(step1!=0 && step2==0){
						b.suspend();
					}
					Thread.sleep(4 * sleepTime);
					int tmp = list[j + 1]; 
					list[j + 1] = list[j];
					list[j] = tmp;
					sug++;
					sugkriseis.setText(String.valueOf(sug));
					ant++;
					this.antimetatheseis.setText(String.valueOf(ant));
				}

				redColumn = j + 1;
				repaint();
				this.text2.setText("Το στοιχείο στη θέση "+(j+2)+" είναι μεγαλύτερο από το στοιχείο στη θέση "+(j+1)+".");
				this.text3.setText("Επομένως συνεχίζει με το επόμενο.");
				if(step1!=0 && step2==0){
					b.suspend();
				}
			}
			redColumn = -1;
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
	}

}
