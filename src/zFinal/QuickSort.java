package zFinal;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class QuickSort extends MyPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	Thread b;
	
	public QuickSort(int v,JTextField sug,JTextField ant, int sorttyp) {
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
				quicksort(0, list.length - 1);			
			} catch (InterruptedException e) {
			}
			redColumn = -1;
			blueColumn = -1;
			cyanColumn = -1;
			greenColumn = listLen - 1;
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
		
		
		private void quicksort(int low, int high) throws InterruptedException {
			int i = low;
			int j = high;
			int pivot =list[i];
			redColumn = i;
			int pivpos=i;
			//low + (high - low) / 2;
			text.setText("efarmwse grigori taksinomisi apo "+(i+1)+" mexri "+(j+1)+".");
			repaint();
			if(step1!=0 && step2==0){
				b.suspend();
			}
			

			while (i <= j){					
				while (list[i] < pivot) {
					//blueColumn = i;
					Thread.sleep(4 * sleepTime);
					repaint();
					text2.setText("To stoixeio sti thesi "+(i+1)+" einai mikrotero apo to stoixeio sti thesi "+(pivpos+1)+".");
					text3.setText("To stoixeio sti thesi "+(i+1)+" anikei apo katw.");
					if(step1!=0 && step2==0){
						b.suspend();
					}
					text2.setText("");
					text3.setText("");
					sug++;
					sugkriseis.setText(String.valueOf(sug));
					i++;
					//blueColumn = i;
					Thread.sleep(4 * sleepTime);
					repaint();
				}
				/*if(list[j] > pivot){
					text2.setText("Epeidi aristera tou pivot vrethike megalutero stoixeio, psakse apo deksia");
					if(step1!=0 && step2==0){
						b.suspend();
					}
				}*/
				while (list[j] > pivot) {
					//cyanColumn = j;
					
					Thread.sleep(4 * sleepTime);
					repaint();
					text2.setText("To stoixeio sti thesi "+(j+1)+" einai megalutero apo to stoixeio sti thesi "+(pivpos+1)+".");
					text3.setText("To stoixeio sti thesi "+(j+1)+" anikei apo panw.");
					if(step1!=0 && step2==0){
						b.suspend();
					}
					text2.setText("");
					text3.setText("");
					sug++;
					sugkriseis.setText(String.valueOf(sug));
					j--;
					//cyanColumn = j;
					Thread.sleep(4 * sleepTime);
					repaint();	
				}								
				if (i <= j) {
					if(i!=j){
						ant++;
						antimetatheseis.setText(String.valueOf(ant));
					}
					text2.setText("To stoixeio sti thesi "+(j+1)+" einai mikrotero apo to stoixeio sti thesi "+(i+1)+".");
					text3.setText("Allakse to stoixeio sti thesi "+(i+1)+" me to stoixeio sti thesi "+(j+1)+".");
					if(step1!=0 && step2==0){
						b.suspend();
					}
					text2.setText("");
					text3.setText("");
					int temp = list[i];
					list[i] = list[j];
					list[j] = temp;
					//pivotpos=j;
					if(i == redColumn ) {
						redColumn = j;
					} else if (j == redColumn) {
						redColumn = i;
					}
					if(redColumn==cyanColumn)
						cyanColumn=-1;
					sug++;					
					sugkriseis.setText(String.valueOf(sug));					
					Thread.sleep(4 * sleepTime);
					repaint();
					i++;
					j--;
					if(step1!=0 && step2==0){
						b.suspend();
					}
				}
			}

			if (low < j) {	
				quicksort(low, j);
			}
			if (i < high) {
				quicksort(i, high);
			}
			if(low > greenColumn) {
				greenColumn = low;
				blueColumn = -1;
				cyanColumn = -1;
			}
			repaint();
		
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
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(list[i]), ((2 * BORDER_WIDTH + columnWidth * i)+2), ((getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH)+15));
			}
			for (int i = 0; i <= greenColumn; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.WHITE);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawString(String.valueOf(i+1), (2 * BORDER_WIDTH + columnWidth * i), getHeight()-5);
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(list[i]), ((2 * BORDER_WIDTH + columnWidth * i)+2), ((getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH)+15));
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
			if(cyanColumn != -1) {
				g.setColor(Color.CYAN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight);
				g.setColor(Color.WHITE);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight);
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(list[cyanColumn]), ((2 * BORDER_WIDTH + columnWidth * cyanColumn)+2), ((getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH)+15));
			}


		}

}
