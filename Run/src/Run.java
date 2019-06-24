import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

class Game{
	JFrame frame = new JFrame();
	int runm = 0;
	boolean run = true;
	ImageIcon character = new ImageIcon();
	Image charRun[] = new Image[2];
	JLabel chara = new JLabel();
	
	ImageIcon backG;
	JButton closeB;
	JScrollPane scrollPane;
	Timer gochar;
	
	JPanel backGround;
		
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	void go() {
		
		backGround = new JPanel() { 
			ImageIcon backG = new ImageIcon("src/Image/backGround.jpg");
			public void paintComponent(Graphics g) {
				g.drawImage(backG.getImage(), 0, 0, null);
	            setOpaque(false);
	            super.paintComponent(g);
	        }
		};
		    
		{
	    	// 버튼 이미지 크기 줄이기
			ImageIcon close = new ImageIcon("src/Image/close.png");
		   	Image ci1 = close.getImage();
		   	Image ci2 = ci1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		   	close.setImage(ci2);
		   	closeB = new JButton(close);
	    }

		closeB.setBorderPainted(false); // 버튼 외곽선 없애기
		closeB.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		closeB.setFocusPainted(false); //버튼 클릭시 테두리 없애기
		closeB.setOpaque(false); // 버튼 투명하게
		
		closeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
				{	System.exit(0);		}
		});
			
		closeB.setBounds(1840, 20, 50, 50); // 절대위치 지정 (x, y, w, h)
			
		{
			try {
				charRun[0] = ImageIO.read(new File("src/Image/character_run1.png"));
				charRun[1] = ImageIO.read(new File("src/Image/character_run2.png"));
			} catch (Exception e) { e.printStackTrace(); }
				charRun[0] = charRun[0].getScaledInstance(165, 326, Image.SCALE_SMOOTH);
				charRun[1] = charRun[1].getScaledInstance(165, 332, Image.SCALE_SMOOTH);
		}
			
		goCharacter r = new goCharacter();
		r.start();
		
		chara.setBounds(100, 550, 190, 330);
		backGround.setLayout(null); // 절대위치 지정하기 위해 해줘야 함
		backGround.add(closeB);
		backGround.add(chara);
			
		//gochar = new Timer(30, new goCharacter());
			
		//setUndecorated(true); //메뉴 바 없애기 - 창 이동, 최소화, 최대화, 닫기 별도 구현
	}
	public void start() {
		frame.setResizable(false); // 창 크기 변경 X
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel sbackGround = new JPanel() { 
			ImageIcon backG = new ImageIcon("src/Image/start.jpg");
			public void paintComponent(Graphics g) {
				g.drawImage(backG.getImage(), 0, 0, null);
	            setOpaque(false);
	            super.paintComponent(g);
	        }
		};
		
		JButton start_btn;
		{
	    	// 버튼 이미지 크기 줄이기
			ImageIcon st = new ImageIcon("src/Image/start_btn.png");
		   	Image s1 = st.getImage();
		   	Image s2 = s1.getScaledInstance(270, 120, Image.SCALE_SMOOTH);
		   	st.setImage(s2);
		   	start_btn = new JButton(st);
	    }
		JButton Introduce_btn;
		{
	    	// 버튼 이미지 크기 줄이기
			ImageIcon in = new ImageIcon("src/Image/Introduce_btn.png");
		   	Image i1 = in.getImage();
		   	Image i2 = i1.getScaledInstance(270, 120, Image.SCALE_SMOOTH);
		   	in.setImage(i2);
		   	Introduce_btn = new JButton(in);
	    }
		
		start_btn.setBounds(1230, 600, 270, 120); // 절대위치 지정 (x, y, w, h)
		start_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		start_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		start_btn.setFocusPainted(false); //버튼 클릭시 테두리 없애기
		start_btn.setOpaque(false); // 버튼 투명하게
		
		Introduce_btn.setBounds(1230, 750, 270, 120); // 절대위치 지정 (x, y, w, h)
		Introduce_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		Introduce_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		Introduce_btn.setFocusPainted(false); //버튼 클릭시 테두리 없애기
		Introduce_btn.setOpaque(false); // 버튼 투명하게
		
		sbackGround.add(start_btn);
		sbackGround.add(Introduce_btn);
		
		JScrollPane SscrollPane = new JScrollPane(sbackGround);

		sbackGround.setLayout(null);
		frame.setPreferredSize(new Dimension(1920, 1080));
		frame.setContentPane(SscrollPane);
		frame.pack();
		frame.setLocationRelativeTo(null); // 창 화면 중앙에 위치
	}
	
	class goCharacter extends Thread {
		public void run() {
		while(true) {
			character.setImage(charRun[runm]);
			chara.setIcon(character);		
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			runm++;
			if(runm > 1) 
				runm = 0;
			
			frame.repaint();
			frame.setVisible(true);
		}
		}
	}
	
}