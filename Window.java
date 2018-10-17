import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.hyperic.sigar.SigarException;

@SuppressWarnings("serial")
public class Window extends JFrame{
	private final int WINDOW_WIDTH=800, WINDOW_HEIGHT=400;
	private ImageIcon windowIcon;
	private SystemInfoCollector info;
	private JPanel panel;
	private JPanel procPanel;
	private JPanel memPanel;
	private JLabel osName;
	private JLabel osVersion;
	private JLabel osArch;
	private JLabel userAndUptime;
	private JLabel memLabel;
	private JLabel procInfo;
	private JLabel numCores;
	private JLabel procVendor;
	private ImageIcon osIcon; 
	private JLabel osIconImageLabel;
	private JLabel iconLabel;
	
	public Window() throws SigarException{
		setTitle("JSystemInfo");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  //centers window
		getRootPane().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
		windowIcon = new ImageIcon("icon.png");
		setIconImage(windowIcon.getImage());
		info = new SystemInfoCollector();
		init();
		setVisible(true);
	}

	private void init() {
		/*
		 * Main panel that other sub panels will be added to
		 */
		panel = new JPanel();
		add(panel);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		/*
		 * Panel that holds all OS info
		 */
		JPanel osPanel = new JPanel();
		osPanel.setLayout(new GridBagLayout());
		osPanel.setPreferredSize(new Dimension(400, 200));
		osPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Operating System"));
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(osPanel, c);
		
		osName = new JLabel(String.format("Name: %39s", info.getOsName()));
		osName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		osName.setPreferredSize(new Dimension(200, 30));
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		osPanel.add(osName, c);
		
		osVersion = new JLabel(String.format("Version: %42s", info.getOsVersion()));
		osVersion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		osVersion.setPreferredSize(new Dimension(200, 200));
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		osPanel.add(osVersion, c);
		
		osArch = new JLabel(String.format("Architechture: %30s", info.getOsArch()));
		osArch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		osArch.setPreferredSize(new Dimension(200, 200));
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		osPanel.add(osArch, c);
		
		/*
		 * Panel that contains processor information
		 */
		procPanel = new JPanel();
		procPanel.setLayout(new GridBagLayout());
		procPanel.setPreferredSize(new Dimension(400, 200));
		procPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Processor"));
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(procPanel, c);
		
		procInfo = new JLabel(String.format("%-36s", info.getProcInfo()));
		procInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		procInfo.setPreferredSize(new Dimension(200, 200));
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		procPanel.add(procInfo, c);
		
		numCores = new JLabel(String.format("Cores: %46s", info.getNumCores()));
		numCores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		numCores.setPreferredSize(new Dimension(200, 200));
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		procPanel.add(numCores, c);
		
		procVendor = new JLabel(String.format("Vendor: %43s", info.getProcVendor()));
		procVendor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		procVendor.setPreferredSize(new Dimension(200, 200));
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		procPanel.add(procVendor, c);
		
		/*
		 * Panel that contains main memory information
		 */
		memPanel = new JPanel();
		memPanel.setLayout(new GridBagLayout());
		memPanel.setPreferredSize(new Dimension(400, 200));
		memPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Main Memory"));
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(memPanel, c);
		
		memLabel = new JLabel(String.format("[used]G/[total]G: %20s/%s", info.getMemUsed(), info.getMemTotal()));
		memLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		memLabel.setPreferredSize(new Dimension(200, 30));
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
	    memPanel.add(memLabel, c);
		
	    /*
	     * Label that contains user's name and the system uptime
	     * this label is added directly to the main panel
	     */
		userAndUptime = new JLabel(String.format("User: %-18s Uptime:%10s", info.getUserName(), info.getUptimeFormatted()));
		userAndUptime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userAndUptime.setPreferredSize(new Dimension(200, 200));
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(20 ,0, 0, 0);
		panel.add(userAndUptime, c);
		
		/*
		 * The image of the OS icon is stored as an ImageIcon and then
		 * added to the JLabel, isIcon, which is added directly to the main panel
		 */
		osIcon = new ImageIcon(getIconString(osName.toString()));
		osIconImageLabel = new JLabel(osIcon);
		osIconImageLabel.setPreferredSize(new Dimension(200, 200));
		osIconImageLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "OS Icon"));
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridheight = 8;
		c.insets = new Insets(0, 50, 0, 0);
		panel.add(osIconImageLabel, c);
		
		iconLabel = new JLabel(info.getOsName());
		iconLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		iconLabel.setPreferredSize(new Dimension(200, 200));
		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.CENTER;
		c.gridheight = 1;
		c.insets = new Insets(0, 50, 0, 0);
		panel.add(iconLabel, c);
	}

	private String getIconString(String os) {
		if(os.contains("Windows"))
			return "windows.png";
		else if (os.contains("OS"))
			return "mac.png";
		else 
			return "linux.png";
	}
}
