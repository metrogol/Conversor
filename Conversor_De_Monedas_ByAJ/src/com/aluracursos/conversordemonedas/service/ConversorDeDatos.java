package com.aluracursos.conversordemonedas.service;

import com.aluracursos.conversordemonedas.modelo.TasaDeConversionApi;
import com.aluracursos.conversordemonedas.modelo.TasaDeConversion;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConversorDeDatos {

    //En Allen Bradley Esto Seria Declarar Un ADDON El Cual Esta Echo Para Realizar Una Funcion En Expecifico.
    public TasaDeConversionApi convierteMoneda(String json){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        TasaDeConversionApi tasaDeConversionApi = gson.fromJson(json, TasaDeConversionApi.class);
        TasaDeConversion tasaDeConversion = new TasaDeConversion(tasaDeConversionApi);

        return tasaDeConversionApi;

    }
}
