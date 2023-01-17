package com.game.entity;

import com.game.controller.PlayerOrder;

public class PlayerFilter {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long after;
    private Long before;
    private Boolean banned;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer minLevel;
    private Integer maxLevel;

    public PlayerFilter() {}

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

    public Long getAfter() {
        return after;
    }

    public void setAfter(Long after) {
        this.after = after;
    }

    public Long getBefore() {
        return before;
    }

    public void setBefore(Long before) {
        this.before = before;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }



    @Override
    public String toString(){
        return "toString::  getName: " + this.getName() + "\n" +
                " getTitle: " + this.getTitle() + "\n" +
                " getProfession: " + this.getProfession() + "\n" +
                " getRace: " + this.getRace() + "\n" +
                " getAfter: " + this.getAfter()+ "\n" +
                " getBefore: " + this.getBefore()+ "\n" +
                " getBanned: " + this.getBanned() + "\n" +
                " getMinExperience: " + this.getMinExperience() + "\n" +
                " getMaxExperience: " + this.getMaxExperience() + "\n" +
                " getLevel: " + this.getMinLevel() + "\n" +
                " getLevel: " + this.getMaxLevel() + "\n";
    }
}
