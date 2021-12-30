package com.example.hrsystem;

import com.googlecode.flyway.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrSystemApplication {

    public static void main(String[] args) {

        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost:3306/hr","root","123456789");
        flyway.migrate();
        SpringApplication.run(HrSystemApplication.class, args);

    }

}
