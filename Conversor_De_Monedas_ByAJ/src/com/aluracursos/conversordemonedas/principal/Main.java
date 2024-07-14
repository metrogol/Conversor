package com.aluracursos.conversordemonedas.principal;

// Manda LLamar Java Class Que Seran Usadas--Analogia a "JSR"(Jump Sub Rutine) Usado En PLC Allen Bradley

import com.aluracursos.conversordemonedas.service.ConversorDeDatos;
import com.aluracursos.conversordemonedas.service.ConsumoApi;

// Manda LLamar Java Collections Que Seran Usadas--Analogia a Funciones Usado en PLC Allen Bradley

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.net.URI;
import java.text.BreakIterator;
import java.util.InputMismatchException;
import java.util.Scanner;

//Clase Main Aqui Comienza la Programacion --- Analogo a Rutina Main "Aqui Comienza El Ladder" en PLC Allen Bradley

public class Main {
    public static void main(String[] args) {

        //Variables Locales solo para uso en Main -- Analogia PLC Allen Bradley
        ConsumoApi consumoApi = new ConsumoApi();
        ConversorDeDatos conversor = new ConversorDeDatos();
        Scanner teclado = new Scanner(System.in);

        // String De Mensaje Usado Para Mostrar En La Pantalla --- Analogia A Mensaje Mostrado En HMI Usando Un "COP" PLC Allen Bradley
        String menu = """
                ____________________________________________
                    Bienvenido/a Al Conversor de Monedas
                       Seleccione La Opccion Deceada
                       1) Dolar ---> Peso Argentino
                       2) Peso Argentino ---> Dolar
                       3) Dolar ---> Real Brasileno
                       4) Real Brasileno ---> Dolar
                       5) Dolar ---> Peso Colombiano
                       6) Peso Colombiano ---> Dolar
                       7) Dolar ---> Peso Mexicano
                       8) Peso Mexicano ---> Dolar
                       9) Salir
                _____________________________________________""";

        String solicitudAConvertir = "Ingresa El Valor Que Deseas Convertir";

        while (true){

            try {
                System.out.println(menu);
                var solicitudUsuario = teclado.nextInt();

                if (solicitudUsuario == 9) {
                    break;
                }if (solicitudUsuario < 1){
                    System.out.println("Opción Invalida, Seleccione Correctamente La Opccion.");
                    break;
                }if (solicitudUsuario > 9){
                    System.out.println("Opcion Invalida, Seleccione Correctamente La Opccion.");
                    break;
                }

                System.out.println(solicitudAConvertir);
                var montoUsuario = teclado.nextDouble();


                String monedaBase = "";
                String monedaDestino = switch (solicitudUsuario) {

                    case 1 -> {
                        monedaBase = "USD";
                        yield "ARS";
                    }
                    case 2 -> {
                        monedaBase = "ARS";
                        yield "USD";
                    }
                    case 3 -> {
                        monedaBase = "USD";
                        yield "BRL";
                    }
                    case 4 -> {
                        monedaBase = "BRL";
                        yield "USD";
                    }
                    case 5 -> {
                        monedaBase = "USD";
                        yield "COP";
                    }
                    case 6 -> {
                        monedaBase = "COP";
                        yield "USD";
                    }
                    case 7 -> {
                        monedaBase = "USD";
                        yield "MXN";
                    }
                    case 8 -> {
                        monedaBase = "MXN";
                        yield "USD";
                    }
                    default -> "";
                };


                //Analogia PLC Allen Bradley Path Conexion Por Protocolo TCP-IP (Ip + Port) CONCAT en  PLC Allen Bradley

                String urlBase = "https://v6.exchangerate-api.com/v6/";
                String apiKey = "8c04ff0cf421e99778a9d9c7";
                String urlRespuesta = "/pair/";

                URI direccion = URI.create(urlBase + apiKey + urlRespuesta + monedaBase + "/" + monedaDestino + "/" + montoUsuario);

                //System.out.println(direccion);
                String json = consumoApi.obtenerDatosApi(direccion);
                var conversion = conversor.convierteMoneda(json);

                //Concadena Strings En Un Solo String Analogia PLC Allen Bradley
                BigDecimal resultado = BigDecimal.valueOf(montoUsuario * conversion.conversion_rate());
                System.out.println("El Valor De: $" + montoUsuario + " [" + monedaBase + "] Corresponde Al Valor -----> $" + resultado.setScale(2, RoundingMode.HALF_UP) + " [" + monedaDestino + "].");

                var comprobacionResultado = conversion.conversion_result();

            } catch (InputMismatchException e) {
                System.out.println("Solo Se Aceptan Valores Numericos, Ejecute nuevamente");
                break;
            }
        }
    }
}
