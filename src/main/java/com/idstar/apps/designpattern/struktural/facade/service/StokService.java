package com.idstar.apps.designpattern.struktural.facade.service;

import com.idstar.apps.designpattern.struktural.facade.service.model.Barang;

import java.util.Map;

public interface StokService {
    public Map add(Barang request);
    public Boolean chekStok(Barang request, Long qtyOrder);
}
