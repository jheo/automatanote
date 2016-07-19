
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

/**
 * 필기인식 시스템의 GUI를 구성하는 클래스. Swing API를 이용하여 작성되었음. <BR />
 * 그래픽 데이터 파일은 PNG 포맷을 기본으로 함. <BR />
 * 
 * @author 인하대학교 정보공학계열 컴퓨터정보공학과 '03 허준
 */

public class MainGUI{
	// Application Constant
	private final String PROGRAM_NAME = "Automata Note - Project Final";
	private final Color DEFAULT_UI_COLOR = Color.white;
	
	// GUI Structures
	private JFrame frame;
	private Container cPane;
	
	// NORTH Components
	private JPanel toolPanel;
	
	// SOUTH Components
	private PenPanel note;
	private JLabel resultLabel;
	
	// CENTER Components
	private JEditorPane editor;
	private JScrollPane editorScroller;
	
	// EAST Components
	private JTextArea debugArea;
	
	// Other Components
	private JMenuBar menuBar;
	
	private PatternMap pHouse;
	
	private int debugCount;
	

	public MainGUI(){
		pHouse = PatternMap.getInstance();
		settingGUI();
	}

	private void settingGUI() {
	
		String operatingSystem;
		FlowLayout flowLayout;
		
		// Show Loading Title
		JWindow titleWindow = new JWindow();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		showTitle(titleWindow);
		
		// Setting Local Variable
		operatingSystem = System.getProperty("os.name");
		flowLayout = new FlowLayout();
		
		// Build Frame
		frame = new JFrame(PROGRAM_NAME+ " / " +operatingSystem);
		frame.setIconImage(new ImageIcon("./data/icon/note.idt").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,700);
		frame.setLocation((int)d.getWidth()/2 - (frame.getWidth()/2),(int) d.getHeight()/2 - (frame.getHeight()/2));
		
					
		// Build Menu
		
		/*
		 * File - Open, Save, Exit
		 * Pattern - Clear, Analysis, Study
		 * Help - Help, About
		 * 
		 */
		
		menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem newDocs = new JMenuItem("New Document");
		
		JMenuItem exitProgram = new JMenuItem("Exit Application");
		
		fileMenu.add(newDocs);
		
		fileMenu.addSeparator();
		fileMenu.add(exitProgram);
		newDocs.addActionListener(new NewListener());
		exitProgram.addActionListener(new ExitListener());
		newDocs.setMnemonic(KeyEvent.VK_O);
		
		exitProgram.setMnemonic(KeyEvent.VK_E);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenu patternMenu = new JMenu("Pattern");
		JMenuItem clearPattern = new JMenuItem ("Clear");
		JMenuItem showPattern = new JMenuItem ("Analysis");
		JMenuItem initPattern = new JMenuItem ("Init");
		
		clearPattern.addActionListener(new ClearListener());
		showPattern.addActionListener(new AnalysisListener());
		initPattern.addActionListener(new InitListener());
		
		patternMenu.add(clearPattern);
		patternMenu.add(showPattern);
		patternMenu.add(initPattern);
		
		clearPattern.setMnemonic(KeyEvent.VK_C);
		showPattern.setMnemonic(KeyEvent.VK_A);
		initPattern.setMnemonic(KeyEvent.VK_S);
		patternMenu.setMnemonic(KeyEvent.VK_P);
		
		JMenu helpMenu = new JMenu("About");
		
		JMenuItem about = new JMenuItem("About Application");
		
		about.addActionListener(new AboutListener());
		helpMenu.add(about);
		
		about.setMnemonic(KeyEvent.VK_A);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		menuBar.add(fileMenu);
		menuBar.add(patternMenu);
		menuBar.add(helpMenu);
		
		menuBar.setBackground(DEFAULT_UI_COLOR);
		
		frame.setJMenuBar(menuBar);
		
		// Build NORTH (toolbar)
		toolPanel = new JPanel();
		
		JButton newButton = new JButton (new ImageIcon("./data/icon/new.idt"));
		JButton saveButton = new JButton (new ImageIcon("./data/icon/save.idt"));
		JButton openButton = new JButton (new ImageIcon("./data/icon/open.idt"));
		
		JButton recogButton = new JButton (new ImageIcon("./data/icon/recog.idt"));
		JButton initButton = new JButton (new ImageIcon("./data/icon/study.idt"));
		JButton clearButton = new JButton (new ImageIcon("./data/icon/clear.idt"));
		
		JButton helpButton = new JButton (new ImageIcon("./data/icon/help.idt"));
		JButton infoButton = new JButton (new ImageIcon("./data/icon/info.idt"));
		JButton exitButton = new JButton (new ImageIcon("./data/icon/exit.idt"));
		
		newButton.setToolTipText("New File");
		saveButton.setToolTipText("Save File");
		openButton.setToolTipText("Open File");
		
		recogButton.setToolTipText("Recognization");
		initButton.setToolTipText("Initialize Pattern");
		clearButton.setToolTipText("Clear Note");
		
		helpButton.setToolTipText("Help");
		infoButton.setToolTipText("About Application");
		exitButton.setToolTipText("Exit Application");
		
		newButton.setBackground(DEFAULT_UI_COLOR);
		saveButton.setBackground(DEFAULT_UI_COLOR);
		openButton.setBackground(DEFAULT_UI_COLOR);
		
		recogButton.setBackground(DEFAULT_UI_COLOR);
		initButton.setBackground(DEFAULT_UI_COLOR);
		clearButton.setBackground(DEFAULT_UI_COLOR);
		
		infoButton.setBackground(DEFAULT_UI_COLOR);
		helpButton.setBackground(DEFAULT_UI_COLOR);
		exitButton.setBackground(DEFAULT_UI_COLOR);
		
		newButton.setBorderPainted(false);
		saveButton.setBorderPainted(false);
		openButton.setBorderPainted(false);
		
		recogButton.setBorderPainted(false);
		initButton.setBorderPainted(false);
		clearButton.setBorderPainted(false);
		
		infoButton.setBorderPainted(false);
		helpButton.setBorderPainted(false);
		exitButton.setBorderPainted(false);
		
		newButton.setMargin(new Insets(0,0,0,0));
		newButton.setFocusable(false);
		
		saveButton.setMargin(new Insets(0,0,0,0));
		saveButton.setFocusable(false);
		
		openButton.setMargin(new Insets(0,0,0,0));
		openButton.setFocusable(false);
		
		recogButton.setMargin(new Insets(0,0,0,0));
		recogButton.setFocusable(false);
		
		initButton.setMargin(new Insets(0,0,0,0));
		initButton.setFocusable(false);
		
		clearButton.setMargin(new Insets(0,0,0,0));
		clearButton.setFocusable(false);
		
		infoButton.setMargin(new Insets(0,0,0,0));
		infoButton.setFocusable(false);
		
		helpButton.setMargin(new Insets(0,0,0,0));
		helpButton.setFocusable(false);
		
		exitButton.setMargin(new Insets(0,0,0,0));
		exitButton.setFocusable(false);
		
		newButton.setRolloverIcon(new ImageIcon("./data/icon/new2.idt"));
		newButton.setRolloverEnabled(true);
		
		saveButton.setRolloverIcon(new ImageIcon("./data/icon/save2.idt"));
		saveButton.setRolloverEnabled(true);
		
		openButton.setRolloverIcon(new ImageIcon("./data/icon/open2.idt"));
		openButton.setRolloverEnabled(true);
		
		recogButton.setRolloverIcon(new ImageIcon("./data/icon/recog2.idt"));
		recogButton.setRolloverEnabled(true);
		
		initButton.setRolloverIcon(new ImageIcon("./data/icon/study2.idt"));
		initButton.setRolloverEnabled(true);
		
		clearButton.setRolloverIcon(new ImageIcon("./data/icon/clear2.idt"));
		clearButton.setRolloverEnabled(true);
		
		infoButton.setRolloverIcon(new ImageIcon("./data/icon/info2.idt"));
		infoButton.setRolloverEnabled(true);
		
		helpButton.setRolloverIcon(new ImageIcon("./data/icon/help2.idt"));
		helpButton.setRolloverEnabled(true);
		
		exitButton.setRolloverIcon(new ImageIcon("./data/icon/exit2.idt"));
		exitButton.setRolloverEnabled(true);
		
		newButton.addActionListener(new NewListener());
		recogButton.addActionListener(new AnalysisListener());
		initButton.addActionListener(new InitListener());
		clearButton.addActionListener(new ClearListener());
	
		exitButton.addActionListener(new ExitListener());
		infoButton.addActionListener(new AboutListener());
		
		// infoButton.addActionListener(new AboutListener());
		
		toolPanel.add(newButton);
		
		//toolPanel.add(saveButton);
		//toolPanel.add(openButton);
		
		toolPanel.add(initButton);
		toolPanel.add(recogButton);
		toolPanel.add(clearButton);
		
		toolPanel.add(infoButton);
		//toolPanel.add(helpButton);
		toolPanel.add(exitButton);
		
		
		toolPanel.setBackground(DEFAULT_UI_COLOR);
		flowLayout.setAlignment(FlowLayout.LEFT);
		toolPanel.setLayout(flowLayout);
		
		// Build EAST
		debugArea = new JTextArea(15,30);
		debugArea.setFont(new Font("Sans-Serif",Font.BOLD,15));
		debugArea.setBorder(new LineBorder(Color.black));
		debugArea.setLineWrap(true);
		
		// Build CENTER
		editor = new JEditorPane();
		
		editorScroller = new JScrollPane(editor);
		editorScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		editorScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		editorScroller.setBorder(new LineBorder(Color.red));
		
		// Build SOUTH
		JPanel notePanel = new JPanel();
		
		note = new PenPanel();

		note.setSize(700,300);

		
		note.setBackground(DEFAULT_UI_COLOR);
		
		note.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
		
		//activePanel = note[0];
	/*	PenPanel.activeNote = note[0];
		note[0].setAlterPanel(note[1]);
		note[1].setAlterPanel(note[0]); */
		
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("굴림체",Font.BOLD,200));
		resultLabel.setBackground(Color.white);
		resultLabel.setBorder(new LineBorder(Color.ORANGE));
		resultLabel.setPreferredSize(new Dimension(250,250));
		
		note.setPreferredSize(new Dimension(700,250));
		
		
		note.setBorder(new LineBorder(Color.blue));
		
		notePanel.setLayout(flowLayout);
		notePanel.setBackground(DEFAULT_UI_COLOR);
		notePanel.add(note);
		// notePanel.add(note[1]);
		notePanel.add(resultLabel);
		
		
		// Setting Panels
				
		note.setMotherGUI(this);
		
		
		note.setFocus(true);
		// Setting Content Pane
		cPane = frame.getContentPane();
		
		cPane.setLayout(new BorderLayout());
		
		cPane.add(editorScroller, BorderLayout.CENTER);
		cPane.add(debugArea,BorderLayout.EAST);
		cPane.add(toolPanel, BorderLayout.NORTH);
		cPane.add(notePanel, BorderLayout.SOUTH);

		cPane.setBackground(DEFAULT_UI_COLOR);
		
		//while(!tPanel.getExitCondition()){	} // Show Title Window until mouse clicked
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		titleWindow.dispose();
		frame.setAlwaysOnTop(false);
		debugPrint("Application initialized.");
		
	}
	

	public String analysis(PenPanel panel) {
		String s = PatternAnalyzer.backtracking(panel.getStrokes());
		
		resultLabel.setText(s + "");
		
		return s;
	}
	
	public void textPrint(String text) {
		editor.setText(editor.getText() + text);
	}
	
	public void debugPrint(String text) {
		debugCount++;
		if (debugCount%15 == 0)
			clearText();
		
		debugArea.setText(debugArea.getText() + " " + text +"\n");
	}
	public void clearText() {
		debugArea.setText("");
	}
	

	public TitleWindow showTitle(JWindow titleWindow) {
		// Show Loading Title
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		TitleWindow tPanel = new TitleWindow("./data/title.gdt");
		titleWindow.getContentPane().add(tPanel);
		titleWindow.setSize(600,300);
		titleWindow.setLocation((int) d.getWidth()/2 - 300,(int) d.getHeight()/2 - 120);
		
		titleWindow.setAlwaysOnTop(true);
		titleWindow.setVisible(true);
		
		return tPanel;
	}
	
	class NewListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			debugPrint("Clear document.");
			editor.setText("");
			note.clearAll();
		}
	}
	
	class AnalysisListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = analysis(note);
			textPrint(s);
			debugPrint((s == "") ? "Pattern analyze failed." : "Pattern analyzed. result is "+s);
		}
	}
	
	class InitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			debugPrint("Pattern set initialized.");
			new CreatePattern(pHouse);
		}
	}
	
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			note.clearAll();
			resultLabel.setText("");
			debugArea.setText("");
		}
	}
	
	class AboutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearText();
			debugPrint("Automata & Intelligence Computing");
			debugPrint("Project report - Korean character recognition");
			debugPrint("Design by Inha University CSIT'03 Heo June");
			debugPrint("http://papercut.tistory.com");
			debugPrint("papercut@gmail.com");
		}
	}
	
	class RollOverListner extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			
		}
		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e)  {
			System.exit(0);
		}
	}
	
	
}
