package Autoservice;

import Autoservice.Damages.Damage;

/**
 * Класс Парковка(гараж) со своействами <b>Damages</b>, <b>nowSize</b>.
 * @autor Андрей Соловьем
 */
public class Parking {

    private Damage[] Damages;
    /**
     * Отвечает за то,чтобы когда мастер брал машину, новые не въезжали на парковку
     */
    private int nowSize;
    /**
     * Идея была в том, чтобы в массив из очереди кидать машину на парковочное место.
     */
    public Parking(int size) {
        this.Damages = new Damage[size];
        this.nowSize=0;
    }

    public boolean isParkFree() {
        return nowSize<Damages.length;
    }

    public void addNewDamage() {
        this.nowSize++;
    }
    public void deleteDamage(){
        this.nowSize--;
    }

    public Damage[] getDamages() {
        return Damages;
    }

    public void setDamages(Damage[] Damages) {
        this.Damages = Damages;
    }

    public void setDamage(int element,Damage Damage){
        this.Damages[element]=Damage;
    }
}
