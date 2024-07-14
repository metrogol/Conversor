//Esta Clase Es Igual A Una Nueva Rutina En Allen Bradley PLC

package com.aluracursos.conversordemonedas.modelo;

// Public = A Una Variable Que Se Puede Cambiar Desde HMI En Allen Bradley PLC
public class TasaDeConversion {

    //Private = A Una Variable Que Unicamente Puede Ser Cambiada Desde Programacion, Puede Verse En HMI Pero No Puede Cambiarce A Menos De Que Cambies El Codigo.
    private final String time_last_update_utc;

    private final String base_code;

    private final String target_code;

    private final Double conversion_rate;

    private final Double conversion_result;

    //Variables Que Seran LLamadas Desde El Programa Prinispal(Main) En Allen Bradley Serian Variables Declaradas En Un Data Type, Dotandolas De Un Formato Expecifico.
    public TasaDeConversion(TasaDeConversionApi tasaDeConversionApi){

        this.time_last_update_utc = tasaDeConversionApi.time_last_update_utc();
        this.base_code = tasaDeConversionApi.base_code();
        this.target_code = tasaDeConversionApi.target_code();
        this.conversion_rate = tasaDeConversionApi.conversion_rate();
        this.conversion_result = tasaDeConversionApi.conversion_result();
    }
//Modelo Para Poder Visualisar Los Valores Puestos En Private

    public String getTime_last_update_utc() {
        return time_last_update_utc;
    }

    public String getBase_code() {
        return base_code;
    }

    public String getTarget_code() {
        return target_code;
    }

    public double getConversion_rate() {
        return conversion_rate;
    }

    public Double getConversion_result() {
        return conversion_result;
    }


    //Funcion SMOV En Allen Bradley  PLC
    @Override
    public String toString() {

        //String Formado Con Los Resulatados De Todas Las Variables Declaradas Anteriormente En Allen Bradley Es Un CONCAT De Varios Strings Lo Cual Se Visualisa En El HMI.
        return "TasaDeConversion{" +
                " Fecha de la ultima actualizacion:'" + time_last_update_utc + '\'' +
                ", Moneda base:'" + base_code + '\'' +
                ", Moneda destino:'" + target_code + '\'' +
                ", Tasa de conversion: " + conversion_rate +
                ", Resultado de la conversion: $" + conversion_result +
                '}';
    }
}
