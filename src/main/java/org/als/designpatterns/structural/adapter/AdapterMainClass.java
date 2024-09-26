package org.als.designpatterns.structural.adapter;

public class AdapterMainClass {

    public static void main(String[] args) {
        PaymentGateway gateway1 = new GatewayA();
        PaymentGateway gateway2 = new GatewayBAdapter(new GatewayB());

        double amount = 100.0;

        gateway1.processPayment(amount);
        gateway2.processPayment(amount);
    }
}
