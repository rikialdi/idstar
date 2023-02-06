//package com.idstar5.apps.controller.web;
//
//import com.idstar5.apps.entity.Karyawan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/")
//public class IndexMainController {
//    private Logger logger = LoggerFactory.getLogger(IndexMainController.class);
//
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String pricacy() {
//        try {
//            logger.info("sukses privacy");// sukses
//            if (true) { // ada warning
//                logger.warn("warning privacy"); // kuning
//            }
//        } catch (Exception e) {
//            logger.error("warning privacy");// merah
//        }
//        return "index";
//    }
//}
