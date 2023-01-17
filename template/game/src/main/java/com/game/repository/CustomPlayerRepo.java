package com.game.repository;
import com.game.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
//https://reflectoring.io/spring-data-specifications/
public class CustomPlayerRepo {

    @Autowired
    private PlayerRepo repo;

    public List<PlayerEntity> getFiltered(PlayerFilter filter) {
        return repo.findAll(createSpecification(filter));
    }

    public Page<PlayerEntity> getPaginated(PlayerFilter filter, PageRequest pageable) {
        return repo.findAll(createSpecification(filter), pageable);
    }


    public Specification<PlayerEntity> createSpecification(PlayerFilter playerFilter){
        return where(nameLike(playerFilter.getName()))
                        .and(titleLike(playerFilter.getTitle()))
                        .and(raceIs(playerFilter.getRace()))
                        .and(professionIs(playerFilter.getProfession()))
                        .and(birthdayAfter(playerFilter.getAfter()))
                        .and(birthdayBefore(playerFilter.getBefore()))
                        .and(bannedIs(playerFilter.getBanned()))
                        .and(experienceLessThan(playerFilter.getMaxExperience()))
                        .and(experienceMoreThan(playerFilter.getMinExperience()))
                        .and(levelLessThan(playerFilter.getMaxLevel()))
                        .and(levelMoreThan(playerFilter.getMinLevel()));
    }

    private Specification<PlayerEntity> nameLike(String name){
        return (root, query, criteriaBuilder) -> name != null ? criteriaBuilder.like(root.get(PlayerEntity_.NAME), "%"+name+"%")
        : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> titleLike(String title){
        return (root, query, criteriaBuilder) -> title != null ? criteriaBuilder.like(root.get(PlayerEntity_.TITLE), "%"+title+"%")
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> raceIs(Race race){
        return (root, query, criteriaBuilder) -> race != null ? criteriaBuilder.equal(root.get(PlayerEntity_.RACE), race)
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> professionIs(Profession profession){
        return (root, query, criteriaBuilder) -> profession != null ? criteriaBuilder.equal(root.get(PlayerEntity_.PROFESSION), profession)
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> birthdayBefore(Long before){
        return (root, query, criteriaBuilder) -> before != null ? criteriaBuilder.lessThanOrEqualTo(root.get(PlayerEntity_.BIRTHDAY), new Date(before))
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> birthdayAfter(Long after){
        return (root, query, criteriaBuilder) -> after != null ? criteriaBuilder.greaterThanOrEqualTo(root.get(PlayerEntity_.BIRTHDAY), new Date(after))
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> bannedIs(Boolean banned){
        return (root, query, criteriaBuilder) -> banned != null ? criteriaBuilder.equal(root.get(PlayerEntity_.BANNED), banned)
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> experienceLessThan(Integer maxExperience){
        return (root, query, criteriaBuilder) -> maxExperience != null ? criteriaBuilder.lessThanOrEqualTo(root.get(PlayerEntity_.EXPERIENCE), maxExperience)
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> experienceMoreThan(Integer minExperience){
        return (root, query, criteriaBuilder) -> minExperience != null ? criteriaBuilder.greaterThanOrEqualTo(root.get(PlayerEntity_.EXPERIENCE), minExperience)
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> levelLessThan(Integer maxLevel){
        return (root, query, criteriaBuilder) -> maxLevel != null ? criteriaBuilder.lessThanOrEqualTo(root.get(PlayerEntity_.LEVEL), maxLevel)
                : criteriaBuilder.conjunction();
    }

    private Specification<PlayerEntity> levelMoreThan(Integer minLevel){
        return (root, query, criteriaBuilder) -> minLevel != null ? criteriaBuilder.greaterThanOrEqualTo(root.get(PlayerEntity_.LEVEL), minLevel)
                : criteriaBuilder.conjunction();
    }

}
