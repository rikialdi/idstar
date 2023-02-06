package com.idstar.apps.service.impl;

import com.idstar.apps.entity.Barang;
import com.idstar.apps.entity.Supplier;
import com.idstar.apps.repository.BarangRepo;
import com.idstar.apps.repository.SupplierRepo;
import com.idstar.apps.service.BarangService;
import com.idstar.apps.utils.Config;
import com.idstar.apps.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
@Service
public class BarangImpl implements BarangService {

    @Autowired
    public BarangRepo barangRepo;

    @Autowired
    public Response response;

    @Autowired
    public SupplierRepo supplierRepo;

    private static Logger logger = LoggerFactory.getLogger(BarangImpl.class);


    @Override
    public Map save(Barang obj) {
        try {
            // not null
            if(response.isRequired(obj.getNama())){
                return response.error("Nama wajib diisi.", Config.ERROR_403);
            }

            if(response.isRequired(obj.getSupplier())){
                return response.error("Supplier Id wajib diisi.", Config.ERROR_403);
            }

            if(response.isRequired(obj.getSupplier().getId())){
                return response.error("Supplier Id wajib diisi.", Config.ERROR_403);
            }

            //wajib : jika berelasi ke tabel
            Supplier chekSupplier = supplierRepo.getById(obj.getSupplier().getId());
            if(response.isRequired(chekSupplier)){
                return response.error("Supplier Id tidak ditemukan.", Config.ERROR_403);
            }
            obj.setSupplier(chekSupplier);

            return response.sukses(barangRepo.save(obj));
        }catch (Exception e){
            logger.error("eror save: {}"+e.getMessage());
            return response.error(e.getMessage(), Config.ERROR_500);
        }

    }

    @Override
    public Map update(Barang obj) {
        return null;
    }

    @Override
    public Map delete(Long obj) {
        return null;
    }

    @Override
    public Map getById(Long obj) {
        return null;
    }
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Transactional(readOnly = true)// jika read ONLy maka hanya dapat lakkan  transaksi read, dan tidak bisa lakukan deleted, insert, update
    @Override
    public Map deleteBarangTransactional(Long id) {
        try {
            // not null
            if(response.isRequired(id)){
                return response.error("Id wajib diisi.", Config.ERROR_403);
            }
            //wajib : jika berelasi ke tabel
            Barang chekBarang = barangRepo.getByID(id);
            if(response.isRequired(chekBarang)){
                return response.error("Barang Id tidak ditemukan.", Config.ERROR_403);
            }
            barangRepo.delete(chekBarang);
            return response.sukses("Sukses Deleted");
        }catch (Exception e){
            logger.error("eror save: {}"+e.getMessage());
            return response.error(e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public void saveBarang(Barang barang) {
        barangRepo.save(barang);
    }
    @Transactional
    @Override
    public void createBarangDeclarativeWithRuntimeException(Barang barang) {
        barangRepo.save(barang);
        throw new DataIntegrityViolationException("Throwing exception for demoing Rollback!!!");
    }

    @Transactional(rollbackFor = { RuntimeException.class,Error.class })
    @Override
    public void createBarangDeclarativeWithRuntimeException2(Barang barang) {
        barangRepo.save(barang);
        throw new DataIntegrityViolationException("Throwing exception for demoing Rollback!!!");
    }

    @Transactional(noRollbackFor = { SQLException.class })
    @Override
    public void createBarangDeclarativeWithRuntimeException3(Barang barang) {
        barangRepo.save(barang);
        throw new DataIntegrityViolationException("Throwing exception for demoing Rollback!!!");
    }

    @Transactional
    @Override
    public void testTransacsional(Barang barang) {
        try {
            // transaksi 1 : sukses
            Barang doupdatde = barangRepo.getByID(110L);
            doupdatde.setNama("update ="+new Date());
            barangRepo.save(doupdatde);  // jika terjadi eror: update ini akan rollback

            //transaksi 2 : failed karena null
            barangRepo.save(null);

            /*
            coba testing
            1.@Transactional dihilangkan dan lakukan
            2.lakukan testing  barangService.createCourseDefaultRatingProgramatic(null);
            3. lihat yang terjadi di database. transaksi update nama tetap terjadi, namun transaksi save barang terjadi kegagalan.
            4. kesimpulan : @transaksi wajib ada

            jika tampa @transaksi : tidak melakukan rolback walaupun terjadi eror di salahs satu transaksi

            kapan melakukan commit  ? saat transaksi tidak ada yang eror
             */
        } catch (Exception e) {
            System.out.println("eror disini createCourseDefaultRatingProgramatic="+e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
