package com.idstar.apps.service;

import com.idstar.apps.entity.Barang;

import java.util.Map;

public interface BarangService {
    public Map save(Barang obj);
    public Map update(Barang obj);
    public Map delete(Long obj);
    public Map getById(Long obj);

    //@Transactional

    public void saveBarang(Barang barang);
    public Map deleteBarangTransactional(Long id);
    public void createBarangDeclarativeWithRuntimeException(Barang barang);
    public void createBarangDeclarativeWithRuntimeException2(Barang barang);
    public void createBarangDeclarativeWithRuntimeException3(Barang barang);


    public  void testTransacsional(Barang barang);


}
