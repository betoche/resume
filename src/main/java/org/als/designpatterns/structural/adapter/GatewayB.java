package org.als.designpatterns.structural.adapter;

public class GatewayB {
    public void charge(double amount) {
        System.out.println("Charging payment with GatewayB: $" + amount);
    }
}