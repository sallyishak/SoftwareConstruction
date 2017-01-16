/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareconstruction;

/**
 *
 * @author sallyishak
 */

import java.net.Socket;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MultiPartDownloader extends JFrame {

	
	public static void main(String[] args) {
             System.out.println(
        "Online: " +
        (testInet("google.com") || testInet("amazon.com"))
    );
        
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MultiPartDownloader main = new MultiPartDownloader();
				main.setVisible(true);
			}
		});
	}
	private static final String SEQ_SUFFIX = "-seq";
	static final String MANIFEST_SUFFIX = ".segments";
	private JTextArea textView;
	private JButton stepButton;
        private JLabel imgView, progressLabel;
	private JScrollPane scrollPane;
	private JTextField url;
	private Timer timer;
	private InputStream inptStream;
	private String fileType;
	
	
	public MultiPartDownloader() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Multi Part Data Downloader");
        
        url = new JTextField(400);
        JButton startButton = new JButton("Download");
        startButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    try {
                        startDownload();
                    } catch (Exception ex) {
                        Logger.getLogger(MultiPartDownloader.class.getName()).log(Level.SEVERE, null, ex);
                    }
        	}
        });
        stepButton = new JButton("Step");
        stepButton.setEnabled(false);
        stepButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
      		
        	}
        });
        
      
      
        imgView = new JLabel();
        textView = new JTextArea();
        textView.setEditable(false);
        
     
        
        scrollPane = new JScrollPane(textView);
        
        JPanel urlPanel = new JPanel(new BorderLayout());
        urlPanel.add(new JLabel("Insert URL Here:"),BorderLayout.NORTH);
        urlPanel.add(url,BorderLayout.CENTER);
        urlPanel.setBackground(Color.lightGray);
        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(stepButton);
      
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(urlPanel,BorderLayout.CENTER);
        controlPanel.add(panel,BorderLayout.EAST);
        add(controlPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        panel.setBackground(Color.lightGray);
   
        setSize(2000,900);
	}
public static boolean testInet(String site) {
    Socket sock = new Socket();
    InetSocketAddress addr = new InetSocketAddress(site,80);
    try {
        sock.connect(addr,3000);
             JOptionPane.showMessageDialog(null, "Connected, your MultiData will start Shortly", "InfoBox: " + "Message", JOptionPane.INFORMATION_MESSAGE);
        return true;
    } catch (IOException e) {
         
        return false;
        
    } finally {
        try {sock.close();}
        catch (IOException e) {}
    }
}
	
	private void startDownload() throws Exception {

    	String url = this.url.getText();
    	
		boolean isSequence = false;
		try {
			String path = new URL(url).getPath();

			if(path.endsWith(".cgi")) 
				path = path.substring(0, path.length()-".cgi".length());
			
			if(path.endsWith(MANIFEST_SUFFIX)) 
				path = path.substring(0, path.length()-MANIFEST_SUFFIX.length());
			
			if(path.endsWith(SEQ_SUFFIX)) { 
				path = path.substring(0, path.length()-SEQ_SUFFIX.length());
				isSequence = true;
			}

			
			fileType = path.substring(path.lastIndexOf('.')+1);
		} catch(MalformedURLException e) {
			fileType = "";
		}

                inptStream = MultiPartData.openStream(url);

		if(!isSequence)
			downloadSingleFile();
		else {
			
			stepButton.setEnabled(true);
			
			
	    	timer = new Timer(1000, new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {

	    		}
	    	});
	 
		}
	}
	

	
	
	private void downloadSingleFile() {
		try {
			ByteArrayOutputStream dest = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int read = 0;
			while(read!=-1) {
				dest.write(buf, 0, read); 
				read = inptStream.read(buf);
                                preview(dest.toByteArray());
			}
			
		} catch(IOException e) {
			e.printStackTrace();
			
		} 
	}
	private void preview(byte[] data) {
		
		if(fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("gif")
				||fileType.equalsIgnoreCase("png")) {
			imgView.setIcon(new ImageIcon(data));
			scrollPane.setViewportView(imgView);
		}
		else if(fileType.equalsIgnoreCase("txt")) {
			textView.setText(new String(data));
			scrollPane.setViewportView(textView);
		}
		else {
			textView.setText("[unknown file type]");
			scrollPane.setViewportView(textView);
		}
	

        }}

    
