package frame.insert;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bean.Member;
import bean.Customer.Sex;
import main.Insert;
import service.MemberService;

public class MemberInsert {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtSd;
	private JTextField name;
	private JTextField sex;
	private JTextField address;
	private JTextField city;
	private JTextField phone;
	
	private MemberService memberService;
	private Member member;
	private JTextField country;
	private JTextField dateOfBirth;
	private JTextField joinedDate;
	private JTextField ssn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				run();
			}
		});
	}
	
	public void run() {
		try {
			MemberInsert window = new MemberInsert();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public MemberInsert() {
		initialize();
		memberService = new MemberService();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 329);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(getContentPane());
		frame.getContentPane().setLayout(null);
		Dimension screenSize=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize= frame.getSize();
		frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
	}
	
	private JPanel getContentPane() {
		if(contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBackground(new Color(153, 204, 102));
			
			JLabel lblMember = new JLabel("Member");
			lblMember.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			lblMember.setForeground(new Color(51, 102, 153));
			lblMember.setBackground(Color.BLACK);
			lblMember.setBounds(32, 25, 106, 18);
			contentPane.add(lblMember);
			
			JLabel lblMemNumber = new JLabel("memNumber:");
			lblMemNumber.setForeground(new Color(0, 0, 153));
			lblMemNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblMemNumber.setBounds(14, 61, 99, 18);
			contentPane.add(lblMemNumber);
			
			txtSd = new JTextField();
			txtSd.setBounds(112, 60, 106, 24);
			contentPane.add(txtSd);
			txtSd.setColumns(10);
			
			JLabel lblName = new JLabel("name:");
			lblName.setForeground(new Color(0, 0, 153));
			lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblName.setBounds(58, 97, 40, 18);
			contentPane.add(lblName);
			
			name = new JTextField();
			name.setBounds(112, 96, 86, 24);
			contentPane.add(name);
			name.setColumns(10);
			
			JLabel lblSex = new JLabel("sex:");
			lblSex.setForeground(new Color(0, 0, 153));
			lblSex.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblSex.setBounds(273, 97, 37, 18);
			contentPane.add(lblSex);
			
			sex = new JTextField();
			sex.setBounds(311, 96, 86, 24);
			contentPane.add(sex);
			sex.setColumns(10);
			
			JLabel lblAddress = new JLabel("address:");
			lblAddress.setForeground(new Color(0, 0, 153));
			lblAddress.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblAddress.setBounds(41, 131, 72, 18);
			contentPane.add(lblAddress);
			
			address = new JTextField();
			address.setBounds(112, 130, 86, 24);
			contentPane.add(address);
			address.setColumns(10);
			
			JLabel lblPhone = new JLabel("phone:");
			lblPhone.setForeground(new Color(0, 0, 153));
			lblPhone.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblPhone.setBounds(52, 163, 48, 18);
			contentPane.add(lblPhone);
			
			JLabel lblCity = new JLabel("city:");
			lblCity.setForeground(new Color(0, 0, 153));
			lblCity.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblCity.setBounds(273, 131, 40, 18);
			contentPane.add(lblCity);
			
			city = new JTextField();
			city.setBounds(311, 130, 86, 24);
			contentPane.add(city);
			city.setColumns(10);
			
			phone = new JTextField();
			phone.setBounds(112, 162, 86, 24);
			contentPane.add(phone);
			phone.setColumns(10);
			
			JButton btnInsert = new JButton("Insert");
			btnInsert.setFont(new Font("Arial Black", Font.BOLD, 15));
			btnInsert.setBackground(new Color(153, 204, 102));
			btnInsert.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					String txt = txtSd.getText();
					int memNum = Integer.parseInt(txt);
					member = new Member();
					member.updateSSN(Integer.parseInt(ssn.getText()));
					member.updateMemNumber(memNum);
					member.updateName(name.getText());
					String se = sex.getText();
					try{
						if(se.equals("MALE")) {
							member.updateSex(Sex.MALE);
						} else if(se.equals("FEMALE")) {
							member.updateSex(Sex.FEMALE);
						} else if(se.equals("OTHER")) {
							member.updateSex(Sex.OTHER);
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
					member.updateAddress(address.getText());
					member.updateCity(city.getText());
					member.updateCountry(country.getText());
					member.updatePhone(Integer.parseInt(phone.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date date = sdf.parse(dateOfBirth.getText());
						Date date2 = sdf.parse(joinedDate.getText());
						member.updateDateOfBirth(date);
						member.updateJoinedDate(date2);
						memberService.regist(member);
						JOptionPane.showMessageDialog(null, "Insert success", "message", JOptionPane.INFORMATION_MESSAGE);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
			});
			btnInsert.setBounds(170, 242, 113, 27);
			contentPane.add(btnInsert);
			
			JButton btnBack = new JButton("back");
			btnBack.setFont(new Font("Arial Black", Font.BOLD, 15));
			btnBack.setBackground(new Color(153, 204, 102));
			btnBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Insert insert = new Insert();
					insert.run();
					frame.dispose();
				}
			});
			btnBack.setBounds(14, 242, 113, 27);
			contentPane.add(btnBack);
			
			JButton btnExit = new JButton("exit");
			btnExit.setFont(new Font("Arial Black", Font.BOLD, 15));
			btnExit.setBackground(new Color(153, 204, 102));
			btnExit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
				}
			});
			btnExit.setBounds(330, 242, 113, 27);
			contentPane.add(btnExit);
			
			JLabel lblCountry = new JLabel("country:");
			lblCountry.setForeground(new Color(0, 0, 153));
			lblCountry.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblCountry.setBounds(245, 162, 72, 18);
			contentPane.add(lblCountry);
			
			country = new JTextField();
			country.setBounds(311, 161, 86, 24);
			contentPane.add(country);
			country.setColumns(10);
			
			JLabel lblDateofbirth = new JLabel("dateOfBirth:");
			lblDateofbirth.setForeground(new Color(0, 0, 153));
			lblDateofbirth.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblDateofbirth.setBounds(14, 199, 106, 18);
			contentPane.add(lblDateofbirth);
			
			dateOfBirth = new JTextField();
			dateOfBirth.setBounds(112, 198, 86, 24);
			contentPane.add(dateOfBirth);
			dateOfBirth.setColumns(10);
			
			JLabel lblJoineddate = new JLabel("joinedDate:");
			lblJoineddate.setForeground(new Color(0, 0, 153));
			lblJoineddate.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblJoineddate.setBounds(222, 198, 86, 21);
			contentPane.add(lblJoineddate);
			
			joinedDate = new JTextField();
			joinedDate.setBounds(311, 198, 86, 24);
			contentPane.add(joinedDate);
			joinedDate.setColumns(10);
			
			JLabel lblSsn = new JLabel("ssn:");
			lblSsn.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblSsn.setForeground(new Color(0, 0, 153));
			lblSsn.setBounds(277, 61, 40, 18);
			contentPane.add(lblSsn);
			
			ssn = new JTextField();
			ssn.setBounds(311, 60, 86, 24);
			contentPane.add(ssn);
			ssn.setColumns(10);
		}
		return contentPane;
	}
}
