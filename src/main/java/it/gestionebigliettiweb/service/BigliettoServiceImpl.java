package it.gestionebigliettiweb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import it.gestionebigliettiweb.dao.BigliettoDAO;
import it.gestionebigliettiweb.model.Biglietto;
import it.gestionebigliettiweb.web.listener.LocalEntityManagerFactoryListener;

public class BigliettoServiceImpl implements BigliettoService {

	private BigliettoDAO bigliettoDao;

	@Override
	public void setBigliettoDao(BigliettoDAO bigliettoDao) {
		this.bigliettoDao = bigliettoDao;
	}

	@Override
	public List<Biglietto> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			bigliettoDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return bigliettoDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Biglietto caricaSingoloElemento(Long idInput) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			bigliettoDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return bigliettoDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Biglietto input) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			bigliettoDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			bigliettoDao.update(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Biglietto input) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			bigliettoDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			bigliettoDao.insert(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Long idBigliettoToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			bigliettoDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			bigliettoDao.delete(bigliettoDao.findOne(idBigliettoToRemove));

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Biglietto> findByExample(Biglietto input) throws Exception {
		List<Biglietto> result = new ArrayList<>();

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			bigliettoDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			result = bigliettoDao.findByExample(input);

			entityManager.getTransaction().commit();

			return result;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
