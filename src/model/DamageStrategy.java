/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Brandy
 */
public interface DamageStrategy {
    void doDamageTo(GameFigureWithHealth target);
    int getDamage();
    void setDamage(int newDamage);
    void update();
}
