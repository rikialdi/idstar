package com.idstar.apps.controller.rest;

import com.idstar.apps.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/role-test-global/")
public class TestRoleGlobalController {

    @Autowired
    public Response response;
//     .antMatchers("/v1/role-test-global/list-barang").hasAnyAuthority("ROLE_READ")
//                .antMatchers("/v1/role-test-global/post-barang").hasAnyAuthority("ROLE_WRITE")
//                .antMatchers("/v1/role-test-global/post-barang-user").hasAnyAuthority("ROLE_USER")
//                .antMatchers("/v1/role-test-global/post-barang-admin").hasAnyAuthority("ROLE_ADMIN")

    @GetMapping(value = {"/list-barang", "/list-barang/"})
    public ResponseEntity<Map> list() {
        return new ResponseEntity<Map>(response.sukses("GetMapping - list-barang - ROLE_READ "), HttpStatus.OK);
    }

    @PostMapping(value = {"/post-barang", "/post-barang/"})
    public ResponseEntity<Map> save() {
        return new ResponseEntity<Map>(response.sukses("PostMapping - post-barang - ROLE_WRITE "), HttpStatus.OK);
    }

    @PostMapping(value = {"/post-barang-user", "/post-barang-user/"})
    public ResponseEntity<Map> saveuser() {
        return new ResponseEntity<Map>(response.sukses("PostMapping - post-barang-user - ROLE_USER "), HttpStatus.OK);
    }

    @PostMapping(value = {"/post-barang-admin", "/post-barang-admin/"})
    public ResponseEntity<Map> saveadmin() {
        return new ResponseEntity<Map>(response.sukses("PostMapping - post-barang-admin - ROLE_ADMIN "), HttpStatus.OK);
    }

}
