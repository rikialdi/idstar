package com.idstar.apps.controller.rest;


import com.idstar.apps.dto.LoginModel;
import com.idstar.apps.repository.oauth.UserRepository;
import com.idstar.apps.service.UserService;
import com.idstar.apps.utils.Config;
import com.idstar.apps.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-login/")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    Config config = new Config();

    @Autowired
    public UserService serviceReq;

    @Autowired
    public Response templateCRUD;

    @PostMapping("/login")
    public ResponseEntity<Map> login(@Valid @RequestBody LoginModel objModel) {
        Map map = serviceReq.login(objModel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("APA KABR" + new Date());
        return new ResponseEntity<String>("APA KABAR", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/test-req-complex")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> repairGoogleSigninAction(@RequestBody Map<String, Object> parameters)
            throws IOException {
        // mangambil data : lakukan do looping
//        for(Map.Entry<String, Object> map2 : parameters){
//
//        }
        return new ResponseEntity<Map<String, Object>>(parameters, HttpStatus.OK);
        /*
        sample request
        {
  "name": "string",
  "image_1": "file, png|jpg|jpeg",
  "image_2": "file, png|jpg|jpeg",
  "type": "string, enum",
  "description": "string",
  "location": {
    "long": "string",
    "lat": "string",
    "address": "string",
    "province": "string",
    "city": "string",
    "district": "string",
    "note": "string"
  },
  "payment_scheme": [
    {
      "id": "number"
    }
  ],
  "rules": [
    {
      "id": "number"
    }
  ],
  "additional_rule": "string",
  "facilities": [
    {
      "id": "number"
    }
  ]
}
         */
    }
}
