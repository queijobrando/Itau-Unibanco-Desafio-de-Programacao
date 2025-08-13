package com.example.demo;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class GerarDataHora {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        String dataFormatada = OffsetDateTime.now().format(formatter);
        System.out.println(dataFormatada);
    }

}
