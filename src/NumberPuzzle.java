import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.Random;
public class NumberPuzzle
{
	JFrame fr = new JFrame("Number Puzzle");
	JButton [] bt= new JButton[16];
	int[] num = new int[16];
	JPanel pa = new JPanel();
	JButton bb=null;
	int bi=0;
	
	public NumberPuzzle()
	{
		fr.setSize(500,530);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		generateNumbers();
		addButton();
	    fr.setVisible(true);
	}
	private void addButton()
	{
		fr.setLayout(null);
		pa.setBounds(50,50,400,400);
		fr.add(pa);
		pa.setLayout(new GridLayout(4,4));
		Font fo= new Font ("elephant",Font.PLAIN,25);
		Border b = BorderFactory.createLineBorder(Color.green,2);
		PuzzleListener listener=new PuzzleListener();
		for(int i=0;i<16;i++)
		{
			if(num[i]==0)
			{
				bt[i]=new JButton();
				bb=bt[i];
				bi=i;
			}
			else
				bt[i]= new JButton(String.valueOf(num[i]));
			bt[i].setBorder(b);
			bt[i].addActionListener(listener);
			bt[i].setForeground(Color.white);
			bt[i].setBackground(Color.red);
			bt[i].setFont(fo);
			pa.add(bt[i]);
		}	
	}
	private void generateNumbers()
	{
		boolean[] push=new boolean[16];
		Random ra=new Random();
		int i=-1;
		while(true)
		{
			int n=ra.nextInt(16);
			if(push[n])
				continue;
			num[++i]=n;
			push[n]=true;
			if(i==15)
			break;
		}
	}
	class PuzzleListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			JButton bc=(JButton)evt.getSource();
			int ci=getIndex(bc);
			System.out.println(ci);
			if(Math.abs(bi-ci)==1||Math.abs(bi-ci)==4) 
			{
				bb.setText(bc.getText());
				bc.setText("");
				bb=bc;
				bi=ci;
				
			}
		}
		private int getIndex(JButton b)
		{
			int i=0;
			for(;b!=bt[i];i++);
			return i;
		}
	}
	public static void main(String[] args) 
	{
		new NumberPuzzle();
	}

}
