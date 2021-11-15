package com.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.faktury.Item;

import org.junit.jupiter.api.Test;

class InvoiceTest {

    @Test
    void calcTaxes() {
        assertEquals(23.0, Item.calculateTax(1, 100.0));
    }

}
