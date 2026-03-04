package org.eternity.practice.client;

import static org.junit.jupiter.api.Assertions.*;

import org.eternity.money.Money;
import org.eternity.practice.Factory;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    public void testAvatarFeeByHiddenDependency() {
        Client client = new Client();
        Money fee = client.getAvatarFeeByHiddenDependency();

        assertEquals(Money.wons(10_000), fee);
    }

    @Test
    public void testAvatarFeeByFactory() {
        Client client = new Client(new Factory());
        Money fee = client.getAvatarFeeByFactory();
        assertEquals(Money.wons(10_000), fee);
    }

    @Test
    public void testAvatarByServiceLocator() {
        Client client = new Client();
        Money fee = client.getAvatarByServiceLocator();
        assertEquals(Money.wons(10_000), fee);
    }

}