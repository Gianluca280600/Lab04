package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	 
	public Studente getNMStudenti(Integer mat) {
		Studente stud=null;
		final String sql = "select * from studente where matricola=?";
				

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,mat);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				stud=new Studente(mat,  rs.getString("nome"),rs.getString("cognome"), rs.getString("cds"));	
			}

			conn.close();
			
			return stud;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Corso> getCorsiFromStudente(Studente studente) {
		
		final String sql = "SELECT * "
				+ "FROM iscrizione i, corso c "
				+ "WHERE i.matricola=? AND c.codins=c.codins";
	
		
		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,studente.getMatricola());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Corso s=new Corso(  rs.getString("codins"), rs.getInt("crediti"),  rs.getString("nome"),rs.getInt("pd"));
				corsi.add(s);
			}

			conn.close();
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}

		
	}
	
	public boolean IsIscritto(Studente s, Corso c) {
		
		final String sql="SELECT * "
				+ "FROM iscrizione "
				+ "WHERE matricola=? AND codins=?";
		boolean b=false;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,s.getMatricola());
			st.setString(2,c.getCodins());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				b=true;
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return b;
	}
	
	
}
