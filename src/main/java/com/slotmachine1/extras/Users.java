package com.slotmachine1.extras;

public class Users {

    int user_id,vouchers,wins,loses,limits,timer;
    String username,password;

    public Users(int user_id, String username, String password,int vouchers,int wins,int loses,int limits,int timer){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.vouchers = vouchers;
        this.wins = wins;
        this.loses = loses;
        this.limits = limits;
        this.timer=timer;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setVouchers(int vouchers) {this.vouchers = vouchers;}
    public void setWins(int wins){this.wins = wins;}
    public void setLoses(int loses){this.loses = loses;}

    public void setLimits(int limits){this.limits = limits;}
    public void setTimer(int timer){this.timer = timer;}


    public int getId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getVouchers() {return vouchers;}
    public int getWins(){return wins;}
    public int getLoses(){return loses;}
    public int getLimits(){return limits;}

    public int getTimer(){return timer;}

}
