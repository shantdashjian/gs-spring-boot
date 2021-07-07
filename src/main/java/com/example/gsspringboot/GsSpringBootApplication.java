package com.example.gsspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class GsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsSpringBootApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) throws IOException {
        File file = new File("beans.txt");
        FileWriter writer = new FileWriter(file);
        return args -> {
            writer.write("All the Beans:\n");
            String[] beans = ctx.getBeanDefinitionNames();
            Arrays.stream(beans)
                .forEach(item -> {
                    try {
                        writer.write(item + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            writer.flush();
            writer.close();
        };
    }
}
