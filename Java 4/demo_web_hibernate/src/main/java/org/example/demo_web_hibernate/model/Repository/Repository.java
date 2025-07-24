package org.example.demo_web_hibernate.model.Repository;


import org.example.demo_web_hibernate.model.Model.Cupcake;
import org.example.demo_web_hibernate.model.Model.RetroGame;
import org.example.demo_web_hibernate.model.Model.TalentPet;
import org.example.demo_web_hibernate.model.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
public class Repository {


    public List<Cupcake> getAllCupcake() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Cupcake> query = session.createQuery("FROM Cupcake", Cupcake.class);
            return query.getResultList();
        }
    }

    public List<RetroGame> getAllRetroGame() {
        try (Session session = HibernateUtil.getSession()) {
            Query<RetroGame> query = session.createQuery("FROM RetroGame", RetroGame.class);
            return query.getResultList();
        }
    }

    public List<TalentPet> getAllPetsSortedByScore() {
        Session session = HibernateUtil.getSession();
        Query<TalentPet> query = session.createQuery("FROM TalentPet p ORDER BY p.score DESC", TalentPet.class);
        List<TalentPet> sortedPets = query.getResultList();
        return query.getResultList();
    }


}
