/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg03des;

/**
 *
 * @author Alumno
 */
import java.security.*;
import javax.crypto.*;
import java.io.*;





public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //vamos a cargar un archivo para cifrar
        //comprobar el argumento del archivo o fichero
        if(args.length != 1){
            //no archivito
            mensajeAyuda();
            System.exit(1);
        }
        
        /*
        Lo primero que tenemos que hacer es cargar una instancia del
        proveedor del cifrado que vamos a ocupar en esta ocacion vamos a ocupar
        un cifrado DES
        */
        
        System.out.println("1.- Generar clave DES: ");
        
        //la clase que se encarga de generar llaves
        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
        
        //inicializar el tamaño de la llave del generador
        generadorDES.init(56); //56 bits
        
        //Generar la clave
        SecretKey clave = generadorDES.generateKey(); //es generar las 16 subllaves
        
        System.out.println("Clave es: " + clave);
        
        //la llave no tiene formato, como tal son los bits en bruto wiiii
        mostrarBytes(clave.getEncoded());
        
        System.out.println("");
        
        //algoritmo
        /*
        El tipo de algoritmo que vamos a ocupar
        EL modo del cifrado de dicho algoritmo
        Si existe o no alguna norma para dicho algoritmo, PKCS (estandares)
        */
        
        Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
        
        /*
        Vamos a ocupar una instancia del algortimo DES
        en el modo de cifrado por bloques electronico
        vamos aplicar PCS5 utilizando el relleno para los bloques
        
        ALGORITMO : DES
        MODO: ECB (Electronic Code Book
        RELLENO: PKCS5
        */
        
        System.out.println("2.- Cifrar con DES el archivo : " + args[0]
                +", dejar el resultado en : " + args[0]+".cifrado");
        
        cifrado.init(Cipher.ENCRYPT_MODE, clave);
        
        //el problema es como vamos a leer los bloques
        
        //buffer para la entrada y salida de los bits
        
        byte[] buffer = new byte[1000];  //vamos a leer cada mil
        byte[] bufferCifrado;
        
        
        //generar mis archivos
        
        FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out = new FileOutputStream(args[0]+".cifrado");
        
        //lectura
        
        int bytesleidos = in.read(buffer, 0, 1000);
        //mientras no este al final del fichero o del archivo
        while(bytesleidos != -1){
            //pasar al texto claro ledio al cifrador
            bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
            //generar la salida
            out.write(bufferCifrado);
            bytesleidos = in.read(buffer, 0, 1000);
        }
        //vamos a reunir los bloques del cifrado
        bufferCifrado = cifrado.doFinal();
        //ya puedo escribir el archivo cifrado
        out.write(bufferCifrado);
        
        in.close();
        out.close();
        
        
        //vamos a descifrar
        
        System.out.println("3.- Descifrar con DES el archivo : " + args[0]
                +".cifrado " +", vamos a ver el resultado en el archivo:" 
                + args[0]+".descifrado");
        
        cifrado.init(Cipher.DECRYPT_MODE, clave);
        
        //buffer para la entrada y salida de los bits
        
        
        byte[] bufferPlano;
        
        
        //generar mis archivos
        
         in = new FileInputStream(args[0]+".cifrado");
         out = new FileOutputStream(args[0]+".descifrado");
        
        //lectura
        
        bytesleidos = in.read(buffer, 0, 1000);
        //mientras no este al final del fichero o del archivo
        while(bytesleidos != -1){
            //pasar al texto claro ledio al cifrador
            bufferPlano = cifrado.update(buffer, 0, bytesleidos);
            //generar la salida
            out.write(bufferPlano);
            bytesleidos = in.read(buffer, 0, 1000);
        }
        //vamos a reunir los bloques del cifrado
        bufferPlano = cifrado.doFinal();
        //ya puedo escribir el archivo cifrado
        out.write(bufferPlano);
        
        in.close();
        out.close();
        
        
        
    }

    public static void mensajeAyuda() {
        System.out.println("Ejemplo de cifrado DES para archivos .txt");
        System.out.println("Cuidado con la llave solo debe de ser de 8 caracteres");
        System.out.println("Compruebe que si cargo el fichero o archivo para cifrar, sino no se puede");
        System.out.println("A mimir :3");
    }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}
    

