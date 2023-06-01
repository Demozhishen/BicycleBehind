package com.springcloud.filter;

import com.springcloud.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;




@Component

public class AuthorizeFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. ��ȡ����
        ServerHttpRequest request = exchange.getRequest();
        //2. ���ȡ��Ӧ
        ServerHttpResponse response = exchange.getResponse();
        //3. ����ǵ�¼���������
        if (request.getURI().getPath().contains("/login")) {
            return chain.filter(exchange);
        }
        //4. ��ȡ����ͷ
        HttpHeaders headers = request.getHeaders();
        //5. ����ͷ�л�ȡ����
        String token = headers.getFirst(AUTHORIZE_TOKEN);

        //6. �ж�����ͷ���Ƿ�������
        if (StringUtils.isEmpty(token)) {
            //7. ��Ӧ�з��뷵�ص�״̬��, û��Ȩ�޷���
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //8. ����
            return response.setComplete();
        }

        //9. �������ͷ�����������������
        try {
            JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //10. ����jwt���Ƴ���, ˵�����ƹ��ڻ���α��Ȳ��Ϸ��������
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //11. ����
            return response.setComplete();
        }
        //12. ����
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
