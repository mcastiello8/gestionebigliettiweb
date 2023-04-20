package it.gestionebigliettiweb.service;

import java.util.List;

import it.gestionebigliettiweb.dao.BigliettoDAO;
import it.gestionebigliettiweb.model.Biglietto;


public interface BigliettoService {
	// questo mi serve per injection
		public void setBigliettoDao(BigliettoDAO bigliettoDao);

		public List<Biglietto> listAll() throws Exception;

		public Biglietto caricaSingoloElemento(Long idInput) throws Exception;

		public void aggiorna(Biglietto input) throws Exception;

		public void inserisciNuovo(Biglietto input) throws Exception;

		public void rimuovi(Long idBigliettoToRemove) throws Exception;

		public List<Biglietto> findByExample(Biglietto input) throws Exception;

	}
