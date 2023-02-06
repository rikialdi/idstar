package com.idstar.apps.controller.rest;

import com.idstar.apps.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/role-test-method/")
public class TestRoleMethodController {

    @Autowired
    public Response response;
//     .antMatchers("/v1/role-test-global/list-barang").hasAnyAuthority("ROLE_READ")
//                .antMatchers("/v1/role-test-global/post-barang").hasAnyAuthority("ROLE_WRITE")
//                .antMatchers("/v1/role-test-global/post-barang-user").hasAnyAuthority("ROLE_USER")
//                .antMatchers("/v1/role-test-global/post-barang-admin").hasAnyAuthority("ROLE_ADMIN")

//    @PreAuthorize("hasAuthority('DELETE_AUTHORITY')") // untuk delete

    @GetMapping(value = {"/list-barang", "/list-barang/"})
    @PreAuthorize("hasRole('READ')")
    public ResponseEntity<Map> list() {
        return new ResponseEntity<Map>(response.sukses("GetMapping - list-barang - ROLE_READ "), HttpStatus.OK);
    }


    @PostMapping(value = {"/post-barang", "/post-barang/"})
    @PreAuthorize("hasRole('WRITE')")
    public ResponseEntity<Map> save() {
        return new ResponseEntity<Map>(response.sukses("PostMapping - post-barang - ROLE_WRITE "), HttpStatus.OK);
    }


    @PostMapping(value = {"/post-barang-user", "/post-barang-user/"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map> saveuser() {
        return new ResponseEntity<Map>(response.sukses("PostMapping - post-barang-user - ROLE_USER "), HttpStatus.OK);
    }


    @PostMapping(value = {"/post-barang-admin", "/post-barang-admin/"})
//    @PreAuthorize("hasAuthority('ADMIN')")
//    https://www.appsdeveloperblog.com/spring-security-preauthorize-annotation-example/
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map> saveadmin() {
        return new ResponseEntity<Map>(response.sukses("PostMapping - post-barang-admin - ROLE_ADMIN "), HttpStatus.OK);
    }

    @PostMapping(value = {"/post-barang-admin-andauthority", "/post-barang-admin-andauthority/"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Map> saveadminandauthority() {
        return new ResponseEntity<Map>(response.sukses("PostMapping - post-barang-admin - ROLE_ADMIN "), HttpStatus.OK);
    }

}
