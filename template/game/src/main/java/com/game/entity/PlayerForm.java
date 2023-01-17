package com.game.entity;

import java.lang.reflect.Field;

public class PlayerForm {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
    private Boolean banned;
    private Integer experience;

    private static Boolean DEFAULT_BANNED_VALUE = false;

    public PlayerForm(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = (banned != null) ? banned : DEFAULT_BANNED_VALUE;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public String toString(){
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            String res = "toString:: ";

            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(this);
                res += name + ": " + value.toString() + "; ";
            }

            return res;
        } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
}