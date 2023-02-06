package com.idstar.apps.service;

import com.idstar.apps.entity.Supplier;

import java.util.Map;

public interface SupplierService {
    /*
    dibuatkan method yang dibutuhkan saja:
    tujuan : untuk mempermudah developer melihat flow bisnis
     */
    public Map save(Supplier request);

    public  Map update(Supplier request);

    public Map delete(Supplier request);

    public Map getById(Long request);

    //get list  dari controller by repositorynya
}
