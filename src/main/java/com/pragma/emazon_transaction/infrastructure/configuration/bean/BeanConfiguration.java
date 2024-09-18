package com.pragma.emazon_transaction.infrastructure.configuration.bean;

import com.pragma.emazon_transaction.domain.api.SupplyServicePort;
import com.pragma.emazon_transaction.domain.spi.FeignClientPort;
import com.pragma.emazon_transaction.domain.spi.SupplyPersistencePort;
import com.pragma.emazon_transaction.domain.usecase.SupplyUseCase;
import com.pragma.emazon_transaction.infrastructure.configuration.security.exceptionhandler.CustomAuthenticationEntryPoint;
import com.pragma.emazon_transaction.infrastructure.feing.StockFeignClient;
import com.pragma.emazon_transaction.infrastructure.out.jpa.adapter.FeignClientAdapter;
import com.pragma.emazon_transaction.infrastructure.out.jpa.adapter.SupplyJpaAdapter;
import com.pragma.emazon_transaction.infrastructure.out.jpa.mapper.SupplyEntityMapper;
import com.pragma.emazon_transaction.infrastructure.out.jpa.mapper.SupplyStockRequestMapper;
import com.pragma.emazon_transaction.infrastructure.out.jpa.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final SupplyRepository supplyRepository;
    private final SupplyEntityMapper supplyEntityMapper;

    private final StockFeignClient stockFeignClient;
    private final SupplyStockRequestMapper supplyStockRequestMapper;

    @Bean
    public SupplyPersistencePort supplyPersistencePort() {
        return new SupplyJpaAdapter(supplyRepository, supplyEntityMapper);
    }

    @Bean
    public FeignClientPort feignClientPort() {
        return new FeignClientAdapter(stockFeignClient, supplyStockRequestMapper);
    }

    @Bean
    public SupplyServicePort supplyServicePort() {
        return new SupplyUseCase(supplyPersistencePort(), feignClientPort());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

}
