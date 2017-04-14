/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author saulu
 */
@Path("Divisa")
public class DivisaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DivisaResource
     */
    public DivisaResource() {
    }

    /**
     * Retrieves representation of an instance of ws.DivisaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of DivisaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    final double dol = 18.90; //VALOR DEL DOLAR (EUA)
    final double eur = 20.06; //VALOR DEL EURO (UNION EUROPEA)
    final double yen = 0.17; //VALOR DEL YEN (JAPÓN)
    final double rea = 6.01; //VALOR DEL REAL (BRAZIL)
    final double cor = 2.09; //VALOR DE LA CORONA (SUECIA)
        
    //SE RECIVEN LAS VARIABLES DE LA URL
    //SE LLAMA AL METODO CONVERTIR Y SE MANDA COMO PARAMETROS DICHAS VARIABLES
    //LA VARIABLE MONEDA INDICA EL TIPO DE MONEDA EN QUE SE CAMBIARA EL PESO
    //LA VARIABLE NUMERO INDICA EL VALOR EN PESOS MEXICANOS QUE SE DESEA CONVERTIR
    //ESCRIBE EL RESULTADO DE LA CONVERSION EN TEXTO PLANO
    
    @GET
    @Path("/conv/moneda={moneda}&numero={numero}")
    @Produces(MediaType.TEXT_PLAIN)
    public String conv(@PathParam("moneda") String moneda, @PathParam("numero") String numero) {

        String res = "";
        String conv = convertir(moneda, numero);
        res = "$" + numero + " pesos mexicanos es igual a " + conv;
        return res;
    }
    
    
    //METODO QUE RECIVE LAS VARIABLES DESCRITAS ARRIBA
    //EL SWITCH FILTRA LA VARIBLE MONEDA PAA SABER QUE VALOR UTILIZAR EN LA DIVISIÓN
    //EN CASO DE QUE EL VALOR SEA MENOR A 1 O MAYOR A CINCO SE INDICA QUE DICHA MONEDA NO EXISTE
    //MATH.RINT PERMITE REDONDEAR A DOS DECIMALES CUAL QUIER CANTIDAD DOUBLE QUE SAQUE COMO RESULTADO
    
    public String convertir(String moneda, String num) {
        String conv = "";
        double div = 0.0;
        int mon = Integer.parseInt(moneda);

        switch (mon) {
            case 1:
                div = Integer.parseInt(num) / dol;
                div = Math.rint(div*100)/100;
                conv = div + " dolares";
                break;
            case 2:
                div = Integer.parseInt(num) / eur;
                div = Math.rint(div*100)/100;
                conv = div + " euros";
                break;
            case 3:
                div = Integer.parseInt(num) / yen;
                div = Math.rint(div*100)/100;
                conv = div + " yenes";
                break;  
            case 4:
                div = Integer.parseInt(num) / rea;
                div = Math.rint(div*100)/100;
                conv = div + " reales";
                break;  
            case 5:
                div = Integer.parseInt(num) / cor;
                div = Math.rint(div*100)/100;
                conv = div + " coronas";
                break;
            default:
                conv = "moneda no encontrada";
        }
        return conv;
    }
}
