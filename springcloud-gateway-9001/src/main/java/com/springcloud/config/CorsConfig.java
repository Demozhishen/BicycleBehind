package com.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author oolong
 */
@Configuration
public class CorsConfig {
    /**
     * springboot�ṩ��CorsWebFilter���п���
     *
     * @return
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // ���ÿ���
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // �����ĸ�����ͷ
        corsConfiguration.addAllowedHeader("*");
        // �����ĸ��������п���
        corsConfiguration.addAllowedMethod("*");
        // �����ĸ�������Դ���п���
        // corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOriginPattern("*");
        // �Ƿ�����Я��cookie���п���
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }
}
