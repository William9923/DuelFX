package com.avatarduel.model.player_component;

import com.avatarduel.model.type.Element;

public class PowerManager {
    protected int current_fire;
    protected int current_water;
    protected int current_earth;
    protected int current_air;
    protected int total_fire;
    protected int total_water;
    protected int total_earth;
    protected int total_air;
    protected int total_energy;
    protected int current_energy;

    //constructor
    public PowerManager(){
        this.current_fire=0;
        this.current_water=0;
        this.current_earth=0;
        this.current_air=0;
        this.current_energy=0;

        this.total_fire=0;
        this.total_water=0;
        this.total_earth=0;
        this.total_air=0;
        this.total_energy=0;
    }

    //GETTER
    public int getCurrent_fire(){ return this.current_fire; }

    public int getCurrent_water(){ return this.current_water; }

    public int getCurrent_earth(){ return this.current_earth; }

    public int getCurrent_air(){ return this.current_air; }

    public int getCurrent_energy() { return this.current_energy; }


    public int getCurrent(Element elem) {
        switch (elem) {
            case AIR: return getCurrent_air();
            case FIRE:return getCurrent_fire();
            case EARTH: return getCurrent_earth();
            case WATER:return getCurrent_water();
            case ENERGY: return getCurrent_energy();
        }
        return -1; // throw element error
    }

    public int getTotal_fire(){ return this.total_fire; }

    public int getTotal_water(){ return this.total_water; }

    public int getTotal_earth(){ return this.total_earth; }

    public int getTotal_air(){ return this.total_air; }

    public int getTotal_energy() {
        return this.total_energy;
    }

    //SETTER

    public void setCurrent(Element elem, int value) {
        switch (elem) {
            case AIR: setCurrent_air(value); break;
            case FIRE: setCurrent_fire(value); break;
            case EARTH: setCurrent_earth(value); break;
            case WATER: setCurrent_water(value); break;
            case ENERGY: setCurrent_energy(value);break;
        }
    }
    public void setCurrent_fire(int current_Fire){ this.current_fire = current_Fire; }

    public void setCurrent_water(int current_Water){ this.current_water = current_Water; }

    public void setCurrent_earth(int current_Earth){ this.current_earth = current_Earth; }

    public void setCurrent_air(int current_Air){ this.current_air = current_Air; }

    public void setCurrent_energy(int current_energy) {
        this.current_energy = current_energy;
    }

    public void setTotal_fire(int total_Fire){ this.total_fire = total_Fire; }

    public void setTotal_water(int total_Water){ this.total_water = total_Water; }

    public void setTotal_earth(int total_Earth){ this.total_earth = total_Earth; }

    public void setTotal_air(int total_Air){ this.total_air = total_Air; }

    //To add power
    public void add(Element type, int power){
        switch (type) {
            case WATER: total_water += power; current_water += power; break;
            case EARTH: total_earth += power; current_earth += power; break;
            case FIRE: total_fire += power; current_fire += power; break;
            case AIR: total_air += power; current_air += power; break;
            case ENERGY: total_energy += power; current_energy += power; break;
        }
    }

    public int getTotal() {
        return total_air + total_fire + total_earth + total_water +total_energy;
    }

    //To reduce or or delete power
    public boolean reduce(Element type, int power){
        if(type==Element.AIR){
            if (this.current_air >= power) {
                this.current_air -= power;
                return true;
            }
            return false;
        }
        else if (type==Element.EARTH){
            if (this.current_earth >= power) {
                this.current_earth -= power;
                return true;
            }
            return false;
        }
        else if (type==Element.FIRE){
            if (this.current_fire >= power) {
                this.current_fire -= power;
                return true;
            }
            return false;
        }
        else if(type==Element.WATER) {
            if (this.current_water >= power) {
                this.current_water -= power;
                return true;
            }
            return false;
        }
        else if (type==Element.ENERGY) {
            if (this.current_energy >= power) {
                this.current_energy -= power;
                return true;
            }
        }
        return false; // throw
    }

    // To reset every new phase
    public void refresh(){
        setCurrent_air(getTotal_air());
        setCurrent_earth(getTotal_earth());
        setCurrent_fire(getTotal_fire());
        setCurrent_water(getTotal_water());
        setCurrent_energy(getTotal_energy());
    }


}
