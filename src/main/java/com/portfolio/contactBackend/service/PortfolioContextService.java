package com.portfolio.contactBackend.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;


@Service
public class PortfolioContextService {

    public String getContext() throws IOException {

        InputStream inputStream =
                getClass().getClassLoader()
                          .getResourceAsStream("portfolio_context.txt");

        if (inputStream == null) {
            throw new RuntimeException("portfolio_context.txt not found");
        }

        return new String(
                inputStream.readAllBytes(),
                StandardCharsets.UTF_8
        );
    }
}