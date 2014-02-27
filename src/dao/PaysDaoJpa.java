/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.persistence.Query;
import model.Pays;

/**
 *
 * @author Thomas
 */
public class PaysDaoJpa extends PaysDao {
    
    @Override
    public DaoError creerPays(Pays pays) {
        try {
            JpaUtil.obtenirEntityManager().persist(pays);
            error = DaoError.OK;
        }
        catch(Exception e) {
            error = DaoError.GENERIC_ERROR;
            errorMessage = e.getMessage();
        }
        return error;
    }

    @Override
    public Pays majPays(Pays pays) {
        Pays ret;
        try {
            ret = (Pays)JpaUtil.obtenirEntityManager().merge(pays);
            error = DaoError.OK;
        }
        catch(Exception e)
        {
            ret = null;
            error = DaoError.GENERIC_ERROR;
            errorMessage = e.getMessage();
        }
        return ret;
    }

    @Override
    public DaoError supprimerPays(Pays pays) {
        try {
            JpaUtil.obtenirEntityManager().remove(pays);
            error = DaoError.OK;
        }
        catch(Exception e)
        {
            error = DaoError.GENERIC_ERROR;
            errorMessage = e.getMessage();
        }
        return error;
    }

    @Override
    public Pays trouverPaysParNum(int numPays) {
        Pays ret = null;
        try {
            Query q = JpaUtil.obtenirEntityManager().createQuery("select c from Pays c where c.numPays = :numPays");
            q.setParameter("numVoyage", numPays);
            ret = (Pays)q.getSingleResult();
            error = DaoError.OK;
        }
        catch(Exception e)
        {
            error = DaoError.GENERIC_ERROR;
            errorMessage = e.getMessage();
        }
        return ret;
    }
    
    @Override
    public Pays trouverPaysParNom(String unNom) {
        Pays ret = null;
        try {
            Query q = JpaUtil.obtenirEntityManager().createQuery("select c from Pays c where c.nom = :unNom");
            q.setParameter("ref", unNom);
            ret = (Pays)q.getSingleResult();
            error = DaoError.OK;
        }
        catch(Exception e)
        {
            error = DaoError.GENERIC_ERROR;
            errorMessage = e.getMessage();
        }
        return ret;
    }
    
   
    
    @Override
    public List<Pays> listerPays() {
        List<Pays> ret = null;
        try {
            Query q = JpaUtil.obtenirEntityManager().createQuery("select c from Pays c");
            ret = (List<Pays>) q.getResultList();
            error = DaoError.OK;
        }
        catch(Exception e)
        {
            error = DaoError.GENERIC_ERROR;
            errorMessage = e.getMessage();
        }
        return ret;
    }
    
}
