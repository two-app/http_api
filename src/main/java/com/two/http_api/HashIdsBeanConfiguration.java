package com.two.http_api;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashIdsBeanConfiguration {

    @Bean
    public Hashids getHashids() {
        return new Hashids("asodhaoHSDoIAHsdo12313hh1h321h3£££**£!", 6);
    }

}
