package com.idstar.apps;

import com.idstar.apps.controller.fileupload.FileStorageProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;


//@EnableSwagger2
//@EnableTransactionManagement//for transaction management
@OpenAPIDefinition
@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true) // <--
//@EnableJpaRepositories(basePackages = {"com.idstar5.apps.repository"})
//@EntityScan(basePackages = "com.idstar5.apps.entity")
//@EnableTransactionManagement
@EnableScheduling
public class AppsApplication {
    private static Logger logger = LoggerFactory.getLogger(AppsApplication.class);


    @Value("${appsname}")
    static String APLIKASI_SAYA;

    @Value("${BASEURL}")
    @Autowired
    static   String URL_STATIC;

    public static void main(String[] args) {
        SpringApplication.run(AppsApplication.class, args);
        System.out.println("hello word=" + APLIKASI_SAYA);
        logger.info("APLIKASI_SAYA2=" + URL_STATIC);
        logger.warn("test Warning logging: ");
    }

    //menampilkan bardasarkan path tertentu saja swagger
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.idstar5.apps.controller.rest"))
//                .paths(PathSelectors.ant("/api/v1/idstar/supplier/**"))
//                .build();
//    }

}
