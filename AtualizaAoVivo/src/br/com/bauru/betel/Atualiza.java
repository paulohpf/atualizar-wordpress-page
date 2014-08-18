package br.com.bauru.betel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Atualiza extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * 
	 * Version : 1.0 Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atualiza frame = new Atualiza();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Atualiza() {
		setTitle("Atualiza Ao Vivo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnNewButton = new JButton("Sobre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane
						.showMessageDialog(
								null,
								"Criado Por Paulo Henrique Palmeira Franco\npaulohpfranco@uol.com.br\nFacebook: paulohpfranco\nTwitter: @paulohpfranco",
								"Sobre", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		menuBar.add(btnNewButton);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Cole abaixo o link Embed:");

		final JEditorPane postPane = new JEditorPane();

		JButton SalvarButton = new JButton("Salvar");
		SalvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int validacao;
				validacao = JOptionPane.showConfirmDialog(null,
						"Deseja Continuar?");

				if (validacao == JOptionPane.YES_OPTION) {

					Connection conn = null;

					conn = Conexao.conexao();

					System.out.println(conn);

					FuncoesBanco.alteraBanco(conn, postPane);

					FuncoesBanco.desconectaBanco(conn);

				} else {
				}
			}
		});

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				postPane.setText("");

			}
		});

		JButton btnBaixarInformaes = new JButton("Baixar Informa\u00E7\u00F5es");
		btnBaixarInformaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection conn = null;
				String post_content = null;

				conn = Conexao.conexao();

				postPane.setText(FuncoesBanco.BaixaBanco(conn, post_content));

				FuncoesBanco.desconectaBanco(conn);

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addComponent(postPane, GroupLayout.DEFAULT_SIZE, 432,
						Short.MAX_VALUE)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addComponent(btnLimpar)
								.addGap(71)
								.addComponent(btnBaixarInformaes)
								.addPreferredGap(ComponentPlacement.RELATED,
										104, Short.MAX_VALUE)
								.addComponent(SalvarButton))
				.addGroup(
						gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(postPane,
										GroupLayout.PREFERRED_SIZE, 184,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_contentPane
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(SalvarButton)
												.addComponent(btnLimpar)
												.addComponent(
														btnBaixarInformaes))
								.addGap(5)));
		contentPane.setLayout(gl_contentPane);
	}
}