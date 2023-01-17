package com.game.service;

import com.game.entity.PlayerForm;
import com.game.entity.PlayerEntity;
import com.game.entity.PlayerFilter;
import com.game.entity.PlayerPageable;
import com.game.exception.NotValidIdException;
import com.game.exception.PlayerNotFoundException;
import com.game.repository.CustomPlayerRepo;
import com.game.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private CustomPlayerRepo customPlayerRepo;

    @PersistenceContext
    private EntityManager entityManager;
    public PlayerEntity getPlayerById(Long id) throws NotValidIdException, PlayerNotFoundException
    {
        if(id <= 0 || id == null)
            throw new NotValidIdException("Id не валидный");
        PlayerEntity playerEntity = playerRepo.findById(id).orElse(null);
        if(playerEntity != null)
            return playerEntity;
        else
            throw new PlayerNotFoundException("Игрок не найден");
    }

    public void deletePlayerById(Long id) throws NotValidIdException, PlayerNotFoundException
    {
        this.getPlayerById(id);
        playerRepo.deleteById(id);
    }

    public PlayerEntity createPlayer(PlayerForm playerForm) throws IllegalArgumentException
    {
        try {
            PlayerEntity playerEntity = new PlayerEntity(playerForm);
            playerRepo.save(playerEntity);

            return playerEntity;
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Cant save: " + e.getMessage());
        }
    }

    public PlayerEntity updatePlayer(Long id, PlayerForm playerForm) throws NotValidIdException, IllegalArgumentException, PlayerNotFoundException
    {
            PlayerEntity playerEntity = this.getPlayerById(id);
            playerEntity.update(playerForm);
            playerRepo.save(playerEntity);

            return playerEntity;
    }

    public Integer getCount(PlayerFilter filter) throws IllegalArgumentException
    {
        List<PlayerEntity> results = customPlayerRepo.getFiltered(filter);

        return results.size();
    }

    public Page<PlayerEntity> getPlayersFiltered(PlayerFilter filter, PlayerPageable pagination) throws IllegalArgumentException
    {
        Page<PlayerEntity> results = customPlayerRepo.getPaginated(filter, pagination.getPageable());

        return results;
    }











    //Predicate way
//    public Integer getCount(PlayerFilter playerFilter)
//    {
//        //https://www.baeldung.com/hibernate-criteria-queries
//        Session session = entityManager.unwrap(Session.class);
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<PlayerEntity> cr = cb.createQuery(PlayerEntity.class);
//        Root<PlayerEntity> root = cr.from(PlayerEntity.class);
//
//        ArrayList<Predicate> predicates = new ArrayList<>();
//        if(playerFilter.getName() != null){
//            predicates.add(
//                    cb.like(root.get("name"), "%" + playerFilter.getName() + "%")
//            );
//        }
//        if(playerFilter.getTitle() != null){
//            predicates.add(
//                    cb.like(root.get("title"), "%" + playerFilter.getTitle() + "%")
//            );
//        }
//
//        Predicate[] conditions = new Predicate[predicates.size()];
//        predicates.toArray(conditions);
//
//        cr.select(root).where(conditions);
//        Query<PlayerEntity> query = session.createQuery(cr);
//        List<PlayerEntity> results = query.getResultList();
//
//        return results.size();
//
//    }
}
