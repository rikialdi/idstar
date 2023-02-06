package com.idstar.apps.designpattern.struktural.facade.service;

import com.idstar.apps.designpattern.struktural.facade.service.model.Barang;

import java.util.Map;

public interface OrderService {
    public Map orderBarangTotal(Barang request, Long qtyOrder);
}
