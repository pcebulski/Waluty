package wersja_piotr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ProsteOkno {

	private JFrame frmKalkulatorWalut;
	private JTextField txtKwotapln;
	private JTextField txtKwotawaluta;
	private Tabela tabela;
	private JLabel lblWybrana;
	private Waluta wybranaWaluta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("Pobieranie walut");
		final Tabela t = ObslugaNBP.pobierzBiezaceKursy();
		System.out.println("Pobrano waluty " + t);
		if (t == null) {
			System.err.println("Nie udało się pobrać walut");
			return;
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProsteOkno window = new ProsteOkno(t);
					window.frmKalkulatorWalut.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the application.
	 */
	public ProsteOkno(Tabela t) {
		this.tabela = t;
		// dzieki temuy wewnatrz initialize mozemy zalozyc ze tabela jest ustawiona
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKalkulatorWalut = new JFrame();
		frmKalkulatorWalut.setTitle("Kalkulator walut");
		frmKalkulatorWalut.getContentPane().setFont(new Font("Arial", Font.PLAIN, 18));
		frmKalkulatorWalut.setBounds(100, 100, 676, 393);
		frmKalkulatorWalut.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblWybierzWalut = new JLabel("Wybierz walutę:");
		lblWybierzWalut.setFont(new Font("Arial", Font.PLAIN, 18));

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kod = (String) comboBox.getSelectedItem();
				wybranaWaluta = tabela.znajdz(kod);
				lblWybrana.setText(wybranaWaluta.toString());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<>(tabela.getKodyWalut()));
		comboBox.setMaximumRowCount(40);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 17));

		JLabel lblKwotaWPln = new JLabel("Kwota w PLN");
		lblKwotaWPln.setFont(new Font("Arial", Font.PLAIN, 18));

		JLabel lblKwotaW = new JLabel("Kwota w ...");
		lblKwotaW.setFont(new Font("Arial", Font.PLAIN, 18));

		txtKwotapln = new JTextField();
		txtKwotapln.setFont(new Font("Arial", Font.BOLD, 18));
		txtKwotapln.setText("kwotaPLN");
		txtKwotapln.setColumns(10);

		txtKwotawaluta = new JTextField();
		txtKwotawaluta.setFont(new Font("Arial", Font.BOLD, 18));
		txtKwotawaluta.setText("kwotaWaluta");
		txtKwotawaluta.setColumns(10);

		JLabel lblWybranaWaluta = new JLabel("Wybrana waluta");
		lblWybranaWaluta.setFont(new Font("Arial", Font.PLAIN, 18));

		lblWybrana = new JLabel("wybrana");
		lblWybrana.setFont(new Font("Arial", Font.BOLD, 18));
		
		JButton btnWalutaNaZlote = new JButton("⇧");
		btnWalutaNaZlote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wybranaWaluta != null) {
					BigDecimal kwota = new BigDecimal(txtKwotawaluta.getText());
					BigDecimal wynik = wybranaWaluta.przeliczNaZlote(kwota);
					txtKwotapln.setText(wynik.toString());
				}
			}
		});
		
		JButton btnZloteNaWalute = new JButton("⇩");
		btnZloteNaWalute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wybranaWaluta != null) {
					BigDecimal kwota = new BigDecimal(txtKwotapln.getText());
					BigDecimal wynik = wybranaWaluta.przeliczNaWalute(kwota);
					txtKwotawaluta.setText(wynik.toString());
			}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmKalkulatorWalut.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblWybranaWaluta)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblWybierzWalut)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(178)
										.addComponent(lblWybrana, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))))
							.addContainerGap(20, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblKwotaW)
							.addGap(90)
							.addComponent(txtKwotawaluta)
							.addGap(296))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblKwotaWPln)
							.addGap(70)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnWalutaNaZlote, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
									.addComponent(btnZloteNaWalute, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtKwotapln, Alignment.TRAILING))
							.addGap(296))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWybierzWalut)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWybranaWaluta)
						.addComponent(lblWybrana))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKwotaWPln)
						.addComponent(txtKwotapln, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnZloteNaWalute, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnWalutaNaZlote, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKwotaW)
						.addComponent(txtKwotawaluta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		frmKalkulatorWalut.getContentPane().setLayout(groupLayout);
	}
}
