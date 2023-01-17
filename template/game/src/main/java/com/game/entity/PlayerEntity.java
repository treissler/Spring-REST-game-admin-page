package com.game.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.Math.sqrt;

@Entity
@Table(name = "player")
public class PlayerEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String title;
    @Column
    @Enumerated(EnumType.STRING)
    private Race race;
    @Column
    @Enumerated(EnumType.STRING)
    private Profession profession;
    @Column
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column
    private Boolean banned;
    @Column
    private Integer experience;
    @Column
    private Integer level;
    @Column
    private Integer untilNextLevel;

    public PlayerEntity(){}

    public PlayerEntity (PlayerForm playerForm) throws IllegalArgumentException {

        if(playerForm.getName() == null)
            throw new IllegalArgumentException("Name expected");
        if(playerForm.getTitle() == null)
            throw new IllegalArgumentException("Title expected");
        if(playerForm.getRace() == null)
            throw new IllegalArgumentException("Race expected");
        if(playerForm.getProfession() == null)
            throw new IllegalArgumentException("Profession expected");
        if(playerForm.getBirthday() == null)
            throw new IllegalArgumentException("Birthday expected");
        if(playerForm.getExperience() == null)
            throw new IllegalArgumentException("Experience expected");

        this.setName(playerForm.getName());
        this.setTitle(playerForm.getTitle());
        this.setRace(playerForm.getRace());
        this.setProfession(playerForm.getProfession());
        this.setBirthday(playerForm.getBirthday());
        this.setBanned(playerForm.getBanned());
        this.setExperience(playerForm.getExperience());
        this.setLevel();
        this.setUntilNextLevel();
    }

    public void update(PlayerForm playerForm) {

        if(playerForm.getName() != null)  {
            this.setName(playerForm.getName());
        }

        if(playerForm.getTitle() != null){
            this.setTitle(playerForm.getTitle());
        }

        if(playerForm.getRace() != null)
            this.setRace(playerForm.getRace());

        if(playerForm.getProfession() != null)
            this.setProfession(playerForm.getProfession());

        if(playerForm.getBirthday() != null){
            this.setBirthday(playerForm.getBirthday());
        }

        if(playerForm.getExperience() != null){
            this.setExperience(playerForm.getExperience());
        }

        if(playerForm.getBanned() != null){
            this.setBanned(playerForm.getBanned());
        }

        this.setLevel();
        this.setUntilNextLevel();

        /* equivalent but too complicated
        Field[] fieldsData = PlayerForm.class.getDeclaredFields();
        Field[] fieldsEntity = PlayerEntity.class.getDeclaredFields();

        for (Field newData : fieldsData) {
            newData.setAccessible(true);

            if(!(newData.get(playerForm) == null)){
                Object newValue = newData.get(playerForm);
                Field updated = Arrays.stream(fieldsEntity).
                        filter(f -> f.getName().equals(newData.getName())).
                        findFirst().
                        get();

                updated.setAccessible(true);
                updated.set(this, newValue);
            }
        }
        */

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        if(name.length() > 12)
            throw new IllegalArgumentException("Name(12) is too long");
        if(name.equals(""))
            throw new IllegalArgumentException("Name couldn't be empty");
        this.name = name;
    }

    public void setTitle(String title) {
        if(title.length() > 30)
            throw new IllegalArgumentException("Title(30) is too long");
        this.title = title;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setExperience(Integer experience) {
        if(experience < 0 || experience > 10000000)
            throw new IllegalArgumentException("experience has to be in [0 .. 10,000,000]");
        this.experience = experience;
    }

    public void setLevel() {
        this.level =  (int) (sqrt(2500 + 200 * this.experience) - 50)/100;
    }

    public void setUntilNextLevel() {
        this.untilNextLevel =  50 * (level + 1) * (level + 2) - experience;
    }

    public void setBirthday(Long birthday) {
        if(birthday < 0)
            throw new IllegalArgumentException("birthday has to be above 0");

        Calendar c = GregorianCalendar.getInstance();
        c.setTimeInMillis(birthday);
        int year = c.get(Calendar.YEAR);
        if(year < 2000 || year > 3000)
            throw new IllegalArgumentException("birthday year has to be in [2000 .. 3000]");

        this.birthday = new Date(birthday);
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }
}
