package com.idstar.apps.designpattern.struktural.facade.service;

import com.idstar.apps.designpattern.struktural.facade.service.model.Barang;

import java.util.Map;

public interface PaymentService {
    public Map payment(Barang request, Long qty);
}
