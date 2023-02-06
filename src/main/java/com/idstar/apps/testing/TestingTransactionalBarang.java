package com.idstar.apps.testing;

import com.idstar.apps.entity.Barang;
import com.idstar.apps.service.BarangService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingTransactionalBarang {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    public BarangService barangService;


    @Test
    public void testDeletedTransactional() {
      Map mao =  barangService.deleteBarangTransactional(110L);
        System.out.println("response 1 =" + mao);
//        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void test01() {
        Barang barang = new Barang() ;
        barang.setHarga(10.0);
        barang.setNama("test1");
        barang.setSatuan("pcs");
        barangService.saveBarang(barang);
    }

    @Test
    public void test1() {
        Barang barang = new Barang() ;
        barang.setHarga(10.0);
        barang.setNama("test1");
        barang.setSatuan("pcs");
        barangService.createBarangDeclarativeWithRuntimeException(barang);
    }

    @Test
    public void test2() {
        Barang barang = new Barang() ;
        barang.setHarga(10.0);
        barang.setNama("test2");
        barang.setSatuan("pcs");
        barangService.createBarangDeclarativeWithRuntimeException2(barang);
    }

    @Test
    public void test3() {
        Barang barang = new Barang() ;
        barang.setHarga(10.0);
        barang.setNama("test3");
        barang.setSatuan("pcs");
barang.setCreated_date(new Date());
        barang.setDeleted_date(new Date());
        barang.setUpdated_date(new Date());
        barang.setStok(1);
        barangService.createBarangDeclarativeWithRuntimeException3(barang);
    }

    @Test
    public void test4() {
        Barang barang = new Barang() ;
        barangService.testTransacsional(barang);
    }




}

