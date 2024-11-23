package project;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
	private ImageIcon logo = new ImageIcon("src/project/resources/logo.png");
	private ImageIcon back = new ImageIcon("src/project/resources/back.jpeg");
	private String[] buttons = {"Plan&Book", "Help", "Login", "Sign up"};
	
	public HomePage() {
		
		setLayout(new BorderLayout());
//		setBackground(new ImageIcon("/Users/adnan/downloads/back.jpeg"));
		JPanel header = new JPanel();
		JPanel buttonsPanel = new JPanel();
		header.setBackground(new Color(37,43, 55));
		buttonsPanel.setBackground(new Color(37,43, 55));
		header.setLayout(new BorderLayout()); // Changed layout to FlowLayout
        header.setPreferredSize(new Dimension(100, 60)); // Setting preferred size for the header panel
        header.add(new JLabel(logo), BorderLayout.WEST);
        header.add(buttonsPanel, BorderLayout.EAST);
        for(int i =0; i < buttons.length; i++) {
        	JButton button = new JButton(buttons[i]);
        	button.setPreferredSize(new Dimension(120, 50));

        	button.setForeground(Color.WHITE);
        	button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	button.setBorderPainted(false); 
            button.setFocusPainted(false); 
        	buttonsPanel.add(button);
        	button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				JButton clickedButton = (JButton) e.getSource();
    	            String btnText = clickedButton.getText();
    	            switch (btnText) {
    	            case "Login":
    	            	SingInPage frame = new SingInPage();
    	    			frame.setTitle("Sign in");
    	    			frame.setSize(400,400);
    	    			frame.setLocationRelativeTo(null);
    	    			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	    			frame.setVisible(true);
    	    			break;
    	            case "Sign up":
    	            	SignUpPage frame1 = new SignUpPage();
    	        		frame1.setTitle("GUI Event Demo");
    	        		frame1.setSize(1200,900);
    	        		frame1.setLocationRelativeTo(null);
    	        		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	        		frame1.setVisible(true);
    	        		break;
    	            case "Help":
    	            	JOptionPane.showMessageDialog(null, "I haven't made the help page, did i ?.", "Alert", JOptionPane.WARNING_MESSAGE);
    	            	break;
    	            default:
                        
                        break;
    	        	
    	    			
    	            }
    			}
    		});
        }


        JPanel imagePanel = new JPanel();
        LayoutManager overlay = new OverlayLayout(imagePanel);
        imagePanel.setLayout(overlay);
        
        JLabel label1 = new JLabel("<html><div style='padding-left: 150px;'>HELLO<br>Where do you want to explore?</div></html>");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("SansSerif", Font.BOLD, 30));
        label1.setAlignmentX(0.5f);
        label1.setAlignmentY(0.5f);
        imagePanel.add(label1);

        JLabel label2 = new JLabel(back); 
        label2.setAlignmentX(0.5f);
        label2.setAlignmentY(0.5f);
        imagePanel.add(label2);

        add(imagePanel, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH); 
        add(new InputGroup(), BorderLayout.SOUTH);
        

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HomePage frame = new HomePage();
		frame.setTitle("Home Page");
		frame.setSize(1200,680);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		

	}

}
