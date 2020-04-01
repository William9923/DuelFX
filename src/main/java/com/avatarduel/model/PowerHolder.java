package com.avatarduel.model;

public class PowerHolder {
    protected int current_fire;
    protected int current_water;
    protected int current_earth;
    protected int current_air;
    protected int total_fire;
    protected int total_water;
    protected int total_earth;
    protected int total_air;

    //constructor
    public PowerHolder(){
        this.current_fire=0;
        this.current_water=0;
        this.current_earth=0;
        this.current_air=0;
        this.total_fire=0;
        this.total_water=0;
        this.total_earth=0;
        this.total_air=0;
    }

    //GETTER
    public int getCurrent_fire(){ return this.current_fire; }

    public int getCurrent_water(){ return this.current_water; }

    public int getCurrent_earth(){ return this.current_earth; }

    public int getCurrent_air(){ return this.current_air; }

    public int getTotal_fire(){ return this.total_fire; }

    public int getTotal_water(){ return this.total_water; }

    public int getTotal_earth(){ return this.total_earth; }

    public int getTotal_air(){ return this.total_air; }

    //SETTER
    public void setCurrent_fire(int current_Fire){ this.current_fire = current_Fire; }

    public void setCurrent_water(int current_Water){ this.current_water = current_Water; }

    public void setCurrent_earth(int current_Earth){ this.current_earth = current_Earth; }

    public void setCurrent_air(int current_Air){ this.current_air = current_Air; }

    public void setTotal_fire(int total_Fire){ this.total_fire = total_Fire; }

    public void setTotal_water(int total_Water){ this.total_water = total_Water; }

    public void setTotal_earth(int total_Earth){ this.total_earth = total_Earth; }

    public void setTotal_air(int total_Air){ this.total_air = total_Air; }

    //To add power
    public int add(Element type, int power){
        if(type==Element.AIR){
           return this.total_air +=  power;
        }
        else if (type==Element.EARTH){
            return this.total_earth += power;
        }
        else if (type==Element.FIRE){
            return this.total_fire += power;
        }
        else if(type==Element.WATER){
            return this.total_water += power;
        }
    }

    //To reduce or or delete power
    public int delete(Element type, int power){
        if(type==Element.AIR){
            return this.current_air -= power;
        }
        else if (type==Element.EARTH){
            return this.current_earth -= power;
        }
        else if (type==Element.FIRE){
            return this.current_fire -= power;
        }
        else if(type==Element.WATER){
            return this.current_water -= power;
        }
    }

    // To reset every new phase
    public void refresh(){
        setCurrent_air(getTotal_air());
        setCurrent_earth(getTotal_earth());
        setCurrent_fire(getTotal_fire());
        setCurrent_water(getTotal_water());
    }
}
