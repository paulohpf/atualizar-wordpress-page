package br.com.bauru.betel;

import java.sql.*;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

public class FuncoesBanco {

	public static void alteraBanco(Connection conn, JEditorPane postPane) {

		String sql = "UPDATE wp_posts SET post_content='" + postPane.getText()
				+ "' WHERE ID = 2 ";

		PreparedStatement stmt;
		try {

			stmt = conn.prepareStatement(sql);

			stmt.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "alert",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static String BaixaBanco(Connection conn, String post_content) {

		try {
			String sql = "SELECT post_content FROM wp_posts  WHERE ID = 2";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				post_content = rs.getString("post_content");
			}

			System.out.println(post_content);
			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.err.println("Não foi possível estabelecer conexão com o BD");
			System.out.println(e);
		}
		return post_content;
	}

	public static void desconectaBanco(Connection conn) {

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) { /* ignore close errors */
				JOptionPane.showMessageDialog(null, e, "alert",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		JOptionPane.showMessageDialog(null, "Executado com Sucesso", "Operação Compluida",JOptionPane.DEFAULT_OPTION);
	}
}