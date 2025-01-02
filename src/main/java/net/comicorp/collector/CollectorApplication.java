package net.comicorp.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableCaching
public class CollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectorApplication.class, args);
    }

}
