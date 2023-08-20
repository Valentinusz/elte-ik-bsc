package game;

import game.utils.Vehicle;

class Player {
    private String name;
    private String IP;
    private int HP;
    private Vehicle playerVehicle;

    public Player(String name, String IP, int HP, Vehicle playerVehicle) {
        this.name = name;
        this.IP = IP;
        this.HP = HP;
        this.playerVehicle = playerVehicle;
    }

    public String toString() {
        String info = "Név: " + this.name + "\nIP: " + this.IP + "\nHP: " + this.HP;
        if (this.playerVehicle == null) {
            return info;
        } else {
            return info + "\nJármű: " + this.playerVehicle.getRegNum();
        }
    }
}