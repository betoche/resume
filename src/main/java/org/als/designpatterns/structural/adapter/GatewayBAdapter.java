package org.als.designpatterns.structural.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GatewayBAdapter implements PaymentGateway {
    private GatewayB gatewayB;

    @Override
    public void processPayment(double amount) {
        gatewayB.charge(amount);
    }
}
