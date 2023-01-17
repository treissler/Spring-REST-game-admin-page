package com.game.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;
@StaticMetamodel(PlayerEntity.class)
public abstract class PlayerEntity_ {
    public static volatile SingularAttribute<PlayerEntity, Long> id;
    public static volatile SingularAttribute<PlayerEntity, String> name;
    public static volatile SingularAttribute<PlayerEntity, String> title;
    public static volatile SingularAttribute<PlayerEntity, Race> race;
    public static volatile SingularAttribute<PlayerEntity, Profession> profession;
    public static volatile SingularAttribute<PlayerEntity, Date> birthday;
    public static volatile SingularAttribute<PlayerEntity, Boolean> banned;
    public static volatile SingularAttribute<PlayerEntity, Integer> experience;
    public static volatile SingularAttribute<PlayerEntity, Integer> level;
    public static volatile SingularAttribute<PlayerEntity, Integer> untilNextLevel;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TITLE = "title";
    public static final String RACE = "race";
    public static final String PROFESSION = "profession";
    public static final String BIRTHDAY = "birthday";
    public static final String BANNED = "banned";
    public static final String EXPERIENCE = "experience";
    public static final String LEVEL = "level";
    public static final String UNTIL_NEXT_LEVEL = "untilNextLevel";

}