package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		this.corsoDao=new CorsoDAO();
		this.studenteDao=new StudenteDAO();
		
	}
	
	public List<Corso> getTuttiICorsi() {
		return this.corsoDao.getTuttiICorsi();
	}
	
	public Studente getNMStudenti(Integer mat) {
		return this.studenteDao.getNMStudenti(mat);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso c) {
		return this.corsoDao.getStudentiIscrittiAlCorso(c);
	}
	
	public List<Corso> getCorsiFromStudente(Studente studente) {
		return this.studenteDao.getCorsiFromStudente(studente);
	}
	
	public boolean IsIscritto(Studente s, Corso c) {
		return this.studenteDao.IsIscritto(s, c);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return this.corsoDao.inscriviStudenteACorso(studente, corso);
	}

	
	
}
