package com.idstar.apps.entity;

import java.util.*;

public class Tes {
    public static void main(String[] args){
        Map<String , String> data = new HashMap<String, String>();
        // Masukkan key dan value ke dalam mapInt_String
        data.put("nama", "riki aldi pari");
        data.put("nik", "123");
        data.put("alamat", "jakarta");

        // Tampilkan value data
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }



    }

}
