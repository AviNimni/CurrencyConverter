package il.ac.hit.java;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
* filename is Currency Converter
* was written in April 2015
* @author Bar Maltabashi, Dan Aharoni, Oz Ben Ami */

public class CurrencyProgram {
	
	private String[] curList = {"USD","ILS","POUND","EURO"};
	private JFrame frame;
	private JButton btConvert,btSwitch,btInfo;
	private JTextField tfSum, tfResult;;
	private JLabel labelMod, labelTo, labelFrom,labelInsert,labelCurFrom,labelCurTo,labelSpace,labelBigSpace;
	private JComboBox<Object> boxTo, boxFrom;
	private ActionListener listener;
	private JPanel panelTitle,panelA,panelB,panelC,panelCa,panelCb,panelCc,panelD,panelE,panelF;
	private Object[][] data = {
		    {"USD", 3.98},
		    {"ILS",1},
		    {"POUND",6.412},
		    {"EURO",4.28}
		};
	/** Initializes the frame and all the buttons, labels, panels etc. */
	public CurrencyProgram()
	{
		frame = new JFrame("Currency Converter");
		btConvert = new JButton("");
		btSwitch = new JButton("");
		btInfo = new JButton("");
		tfSum = new JTextField(8);
		tfResult = new JTextField(8);
		labelSpace = new JLabel("");
		labelBigSpace = new JLabel("              ");
		labelInsert = new JLabel("Enter Amount : ");
		labelMod = new JLabel("Last Update : 03/04/2015 12:33:05");
		labelTo = new JLabel("  To : ");
		labelFrom = new JLabel("From : ");
		labelCurFrom = new JLabel("");
		labelCurTo = new JLabel("");
		boxTo = new JComboBox<Object>(curList);
		boxFrom = new JComboBox<Object>(curList);
		listener = new ButtonsListener();
		panelTitle = new JPanel();
		panelA = new JPanel();
		panelB = new JPanel();
		panelC = new JPanel();
		panelCa = new JPanel();
		panelCb = new JPanel();
		panelCc = new JPanel();
		panelD = new JPanel();
		panelE = new JPanel();
		panelF = new JPanel();
	}
	/** Responsible for all the actions performed following the buttons clicks. */
	public class ButtonsListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Display the currency of the selected data underneath the boxes.
			if(e.getSource()==boxFrom || e.getSource()==boxTo)
			{
				double from = Double.parseDouble(data[boxFrom.getSelectedIndex()][1].toString());
				double to = Double.parseDouble(data[boxTo.getSelectedIndex()][1].toString());
				labelCurFrom.setText("1 "+data[boxFrom.getSelectedIndex()][0].toString()+" = "+Math.floor(from/to * 1000) / 1000+" "+data[boxTo.getSelectedIndex()][0].toString()+"     ");
				labelCurTo.setText("     "+"1 "+data[boxTo.getSelectedIndex()][0].toString()+" = "+Math.floor(to/from * 1000) / 1000+" "+data[boxFrom.getSelectedIndex()][0].toString());
				btConvert.doClick();
			}
			// Switch selected currencies.
			if(e.getSource()==btSwitch)
			{
				int to,from;
				from = boxFrom.getSelectedIndex();
				to = boxTo.getSelectedIndex();
				boxFrom.setSelectedIndex(to);
				boxTo.setSelectedIndex(from);
				btConvert.doClick();
			}
			// Display a popup window with a table of all currencies rates.
			if(e.getSource()==btInfo)
			{
				String[] columnNames = {"Currency","Rate"};
				
				Color bkgColor = new Color(229,228,184);
				JButton btOk = new JButton();
				btOk.setVisible(false);
				JTable table = new JTable(data,columnNames);
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(1,1));
				panel.add(table);
				table.setBackground(bkgColor);
				table.setRowHeight(23);
				table.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
				table.setShowGrid(false);
				table.setEnabled(false);
				UIManager.put("OptionPane.background", bkgColor);
				UIManager.put("Panel.background", bkgColor);
				JOptionPane.showOptionDialog(null,panel,"Currency Rates",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src//resources//ratesNewSmall.png"),new Object[]{btOk},btOk);
			}
			// Convert the selected currency to the desired currency.
			if(e.getSource()==btConvert)
			{
				double from = Double.parseDouble(data[boxFrom.getSelectedIndex()][1].toString());
				double to = Double.parseDouble(data[boxTo.getSelectedIndex()][1].toString());
				double mul = 0;
				try
				{
					mul = Double.parseDouble(tfSum.getText());
				}
				catch(Exception e1)
				{
					tfSum.setText("");
				}
				double result = from/to*mul;
				result = Math.floor(result * 1000) / 1000;
				tfResult.setText(String.valueOf(result));
			}
		}
	}
	/** GUI building function */
	public void start()
	{
		// Initiliaze color, attribute and fonts.
		Color bkgColor = new Color(229,228,184);
		Font bigFont = new Font("Harrington", Font.PLAIN, 34);
		Font normalFont = new Font("Comic sans MS", Font.PLAIN, 19);
		Font smallFont = new Font("Comic sans MS", Font.PLAIN, 14);
		Font hugeFont = new Font("Comic sans MS", Font.PLAIN, 44);
		@SuppressWarnings("unchecked")
		Map<TextAttribute, Integer> attributes = (Map<TextAttribute, Integer>) hugeFont.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		double from = Double.parseDouble(data[boxFrom.getSelectedIndex()][1].toString());
		double to = Double.parseDouble(data[boxTo.getSelectedIndex()][1].toString());
		// Setting labels.
		labelCurFrom.setFont(smallFont);
		labelCurFrom.setText("1 "+data[boxFrom.getSelectedIndex()][0].toString()+" = "+Math.floor(from/to * 1000) / 1000+" "+data[boxTo.getSelectedIndex()][0].toString()+"     ");
		labelCurTo.setText("     "+"1 "+data[boxTo.getSelectedIndex()][0].toString()+" = "+Math.floor(to/from * 1000) / 1000+" "+data[boxFrom.getSelectedIndex()][0].toString());
		labelCurTo.setFont(smallFont);
		labelInsert.setFont(bigFont);
		labelTo.setFont(bigFont);
		labelFrom.setFont(bigFont);
		labelMod.setFont(smallFont);
		// Setting textfields.
		tfSum.setFont(normalFont);
		tfSum.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			{
				if(e.getKeyChar() == ' ')
					e.consume();
				else
				{
					try
					{
						Double.parseDouble(tfSum.getText() + e.getKeyChar());
					}
					catch(Exception e1)
					{
							e.consume();
					} 
				}
		     }
			});
		tfSum.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
				try{
					btConvert.doClick();
				}
				catch(Exception e){		
				}	
			}
			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				try{
					btConvert.doClick();
				}
				catch(Exception e){	
				}
			}
			@Override
			public void removeUpdate(DocumentEvent arg0)
			{	
				try{
					btConvert.doClick();
				}
				catch(Exception e){		
				}	
			}
		});
		tfResult.setFont(normalFont);
		tfResult.setText("0.0");
		tfResult.setEditable(false);
		// Setting comboboxes.
		boxTo.addActionListener(listener);
		boxTo.setFont(normalFont);
		boxTo.setSelectedIndex(2);
		boxTo.setBackground(Color.white);
		boxFrom.addActionListener(listener);
		boxFrom.setFont(normalFont);
		boxFrom.setSelectedIndex(0);
		boxFrom.setBackground(Color.white);
		// Setting buttons.
		btConvert.addActionListener(listener);
		btConvert.setFont(bigFont);
		btConvert.setIcon(new ImageIcon("src//resources//convert.png"));
		btConvert.setBackground(bkgColor);
		btConvert.setBorder(null);
		btSwitch.addActionListener(listener);
		btSwitch.setToolTipText("Switch currencies");
		btSwitch.setIcon(new ImageIcon("src//resources//switch.png"));
		btSwitch.setPressedIcon(new ImageIcon("src//resources//switchPressed.png"));
		btSwitch.setCursor(new Cursor(12));
		btSwitch.setBackground(bkgColor);
		btSwitch.setBorder(null);
		btInfo.addActionListener(listener);
		btInfo.setToolTipText("Currency Rates");
		btInfo.setFont(smallFont);
		btInfo.setIcon(new ImageIcon("src//resources//ratesNewBig.png"));
		btInfo.setPressedIcon(new ImageIcon("src//resources//ratesNewBigPressed.png"));
		btInfo.setBackground(bkgColor);
		btInfo.setBorder(null);
		btInfo.setCursor(new Cursor(12));
		// Setting frame and panels.
		frame.setContentPane(new JLabel(new ImageIcon("src//resources//background.jpg")));
		frame.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{
				System.exit(0);
			}
		});	
		panelTitle.setOpaque(false);
		panelA.setOpaque(false);
		panelB.setOpaque(false);
		panelC.setOpaque(false);
		panelCa.setOpaque(false);
		panelCb.setOpaque(false);
		panelCc.setOpaque(false);
		panelD.setOpaque(false);
		panelE.setOpaque(false);
		panelF.setOpaque(false);
		frame.setLayout(new GridLayout(7,1));
		panelTitle.setLayout(new FlowLayout());
		panelTitle.add(labelSpace);
		frame.add(panelTitle);
		panelA.setLayout(new FlowLayout());
		panelA.add(labelInsert);
		panelA.add(tfSum);
		frame.add(panelA);
		panelB.setLayout(new FlowLayout());
		panelB.add(labelFrom);
		panelB.add(boxFrom);
		panelB.add(labelSpace);
		panelB.add(btSwitch);
		panelB.add(labelTo);
		panelB.add(boxTo);
		frame.add(panelB);
		panelC.setLayout(new BorderLayout());
		panelCa.setLayout(new FlowLayout());
		panelCa.add(btConvert);
		panelCb.setLayout(new FlowLayout());
		panelCb.add(labelCurFrom);
		panelCb.add(labelBigSpace);
		panelCb.add(labelCurTo);
		panelC.add(panelCb,BorderLayout.CENTER);
		panelC.add(panelCa,BorderLayout.SOUTH);
		frame.add(panelC);
		panelD.setLayout(new FlowLayout());
		panelD.add(tfResult);
		frame.add(panelD);
		panelF.add(btInfo);
		frame.add(panelF);
		panelE.setLayout(new FlowLayout());
		panelE.add(labelMod);
		frame.add(panelE);
		frame.setSize(1070,800);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocation(400, 100);
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				CurrencyProgram ui = new CurrencyProgram();
				ui.start();
			}
		});

	}
}
