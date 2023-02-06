package com.idstar.apps.entity;

import lombok.Data;

import java.util.Optional;

@Data
public class Barang2 {

    public  Barang2(Long id, Double harga){
        this.id = id;
        this.harga = harga;
    }

    public  Barang2(){
    }
    public Long id;
    public  Double harga;

    public  DetailBarang detailBarang;

    Optional<DetailBarang> detailOptional;
}
