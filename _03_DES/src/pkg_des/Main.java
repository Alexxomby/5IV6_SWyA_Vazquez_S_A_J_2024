package pkg_des;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {


        //programa aque cire arcvhivos
        //mediante el uso de DES

        if(args.length!=1){
            mensajeAyuda();

            System.out.println(1);

        }
        /*
        Lo primero que tenmos que hacer es cargar una instancia
        del provedor del cifrado que vamos a ocupar
        en esta ocasion es un cifrado DES
         */
        System.out.println("1.- Genersar claces de dDES");
        //la c lase que se encargara de ggenerar las llaves
        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
       //inicializar el tamano de la llave del generador
        generadorDES.init(56);

        //generador la vable secreta
        SecretKey clave = generadorDES.generateKey();

        System.out.println("la clave es : " + clave);

        //tenemos que aploicar formato a ala clave

        mostrarBytes(clave.getEncoded());

        System.out.println("");
        //algoritmo
        //tenemos que establecer el agoritmo de cifrado de acuerdo a su procedimiento
        /*
        el tipo de algoritmo que vamos a ocupar
        el modo de cifrado de dicho algoritmo
        si existe o no alguno
         */

        Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding")

        /*
        //primero tenemos que recordarr que des es un algoritmo por bloques, asi que teneoms
        qie definir el algoritmo en modo a bloqiue
        MODO ECB (electronic code book)
        Estandar PKCS5  Relleno
         */

        /*
        Vamos a ocuipar una instancia del agoritmo DES
        en el metodo de cfrado por bloques electrinico
        vamos a aplicar PCs5 utilizando el rellenos para los bloques

        ALgoritmo : DES
        MODO : ECB( Electronic Code Book)
        Relleno: PKCS5
         */
        System.out.println("2.- CIfrar el archivo : " + args[0]
        + " , y vamos a dejar el resultado en " + args[0] + ".cifrado");

        cifrado.init(Cipher.ENCRYPT_MODE, clave);

        //El problema sera como ller loos bloques

        //buffer para la entrada y salida de los bit





    //definamos al algoritmo


    cifrado.init(Cipher.ENCRYPT_MODE, clave);

    byte[] buffer = new byte[1000];
    byte[] bufferCifrado;

    //genero mius archivos

    FileInputStream in = new FileInputStream(args[0]);
    FileOutputStream out = new FileOutputStream(args[0]+".cifrado");

    //debo de ller ela rchivo

    int bytesleidos = in.read(buffer,0,1000);
//mientras no este al final del archivo o chichero
    while(bytesleidos != -1 ) {
        //pasar al texto clarao leido al cifradir

        bufferCifrado = cifrado.update(
                buffer, 0, bytesleidos);
        //generar salida
        out.write(bufferCifrado);
        bytesleidos = in.read(buffer, 0, 1000);
    }
    //vamos a reunir los bloques del cifrado
    bufferCifrado = cifrado.doFinal();
    //ya puedo escribir el ar chivo cifrado
        out.write(bufferCifrado);

        in.close();
        out.close();
//vamos a decifrar
        System.out.println("hr;;p");


    }

    private static void mensajeAyuda(){
        System.out.println("Ejemplo de cifrado des");

    }

    public static void mostrarBytes(byte[] buffer){
        System.out.write(buffer, 0 , buffer.length);}

}


