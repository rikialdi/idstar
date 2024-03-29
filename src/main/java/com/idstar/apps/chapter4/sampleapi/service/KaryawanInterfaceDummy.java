package com.idstar.apps.chapter4.sampleapi.service;

import com.idstar.apps.chapter4.sampleapi.model.KaryawanDummy;

import java.util.Map;

public interface KaryawanInterfaceDummy {
    public Map save(KaryawanDummy obj);

    public Map list();

    public Map update(KaryawanDummy obj);

    public void delete(Long id);
}
