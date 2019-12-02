package JDBC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;

class Outro extends JFrame {
	public Outro() {
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel backGround = new JPanel() {
			ImageIcon icon = new ImageIcon("src/Image/end.jpg");
			Image img = icon.getImage(); 
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, this);
			}
		};

		//DB연동
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			con = DriverManager.getConnection("JDBC:mysql://localhost:3306/turtlegame", "root", "mirim2");

		}catch(SQLException ex) {
			System.out.println("SQLException : " + ex);
		}catch(Exception ex) {
			System.out.println("Exception : " + ex);
		}

		JLabel wordEng[] = new JLabel[RunGame.indexCnt];
		JLabel wordKor[] = new JLabel[RunGame.indexCnt];
		for(int i = 0; i < RunGame.indexCnt; i++) {
			wordEng[i] = new JLabel();
			wordKor[i] = new JLabel();

			String sql = "select english, korean from words where num = " + RunGame.wordIndex[i] + ";";

			try {
				ps = con.prepareStatement(sql);
				ResultSet srs = ps.executeQuery();
				while(srs.next()) {
					wordEng[i].setText(srs.getString("english"));
					wordKor[i].setText(srs.getString("korean"));
				}
			}catch(Exception e) {
				System.out.println("Exception : " + e);
			}
			wordEng[i].setFont(new Font("배달의민족 도현", Font.BOLD, 50));
			wordKor[i].setFont(new Font("배달의민족 도현", Font.BOLD, 50));
			wordEng[i].setForeground(Color.WHITE);
			wordKor[i].setForeground(Color.WHITE);
			wordEng[i].setBounds(710, 400 + (100 * i), 400, 100);
			wordKor[i].setBounds(1110, 400 + (100 * i), 400, 100);
			backGround.add(wordEng[i]);
			backGround.add(wordKor[i]);
		}
		
		ImageIcon gC = new ImageIcon();
		Image gameC = null;
		JLabel gameclear = new JLabel();
		try {
			gameC = ImageIO.read(new File("src/Image/gameClear.png"));
		} catch (Exception e) { e.printStackTrace(); }
		gameC = gameC.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		gC.setImage(gameC);
		gameclear.setIcon(gC);
		gameclear.setBounds(770, 150, 400, 200);
		backGround.add(gameclear);
		
		JButton close_btn = new JButton();
		{
			// 버튼 이미지 크기 줄이기
			ImageIcon in = new ImageIcon("src/Image/clear_btn.png");
			Image i1 = in.getImage();
			Image i2 = i1.getScaledInstance(230, 100, Image.SCALE_SMOOTH);
			in.setImage(i2);
			close_btn = new JButton(in);
		}
		close_btn.setBounds(740, 850, 230, 100); // 절대위치 지정 (x, y, w, h)
		close_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		close_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		close_btn.setFocusPainted(false); // 버튼 클릭시 테두리 없애기
		close_btn.setOpaque(false); // 버튼 투명하게

		close_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		backGround.add(close_btn);
		
		JButton replay_btn = new JButton();
		{
			// 버튼 이미지 크기 줄이기
			ImageIcon in = new ImageIcon("src/Image/replay_btn.png");
			Image i1 = in.getImage();
			Image i2 = i1.getScaledInstance(230, 100, Image.SCALE_SMOOTH);
			in.setImage(i2);
			replay_btn = new JButton(in);
		}
		replay_btn.setBounds(990, 850, 230, 100); // 절대위치 지정 (x, y, w, h)
		replay_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		replay_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		replay_btn.setFocusPainted(false); // 버튼 클릭시 테두리 없애기
		replay_btn.setOpaque(false); // 버튼 투명하게

		replay_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Intro();
			}
		});
		backGround.add(replay_btn);
		
		
		JScrollPane SscrollPane = new JScrollPane(backGround);

		backGround.setLayout(null);
		setPreferredSize(new Dimension(1920, 1080));
		setContentPane(SscrollPane);
		pack();
		setLocationRelativeTo(null); // 창 화면 중앙에 위치
	}
}
