package gui;

import java.util.Formatter;
import java.util.TimerTask;
import javax.swing.JLabel;

class MyTimerTask extends TimerTask{
	 int seconds = 0;
	 int minutes = 0;
	 JLabel lTime;
	MyTimerTask(JLabel lTime){
		this.lTime = lTime;
	}
	 
    public void run()
    {
   	 Formatter formatter = new Formatter();
   	 formatter.format("%d  :  %02d",minutes, seconds);
   	 String formattedString = formatter.toString();
   	 
   	 lTime.setText(formattedString);
   	 seconds++;
   	 if(seconds==60) {
   		 seconds=0;
   		 minutes++;
   	 }
    }
}