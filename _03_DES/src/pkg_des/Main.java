package pkg_des;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {


        //programa aque cire arcvhivos
        //mediante el uso de DES

        if(args.length!=1){
            mensajeAyuda();

            System.out.println(1);

        }
        System.out.println("1.- Genersar claces de dDES");

        KeyGenerator generatorDES = KeyGenerator.getInstance("DES");
        generatorDES.init(56);

        //generador la vable secreta

        SecretKey clave = generatorDES.generateKey();

        System.out.println("la clave es : " + clave);

        //tenemos que aploicar formato a ala clave

        mostrarBytes(clave.getEncoded());

        System.out.println("");
        //tenemos que establecer el agoritmo de cifrado de acuerdo a su procedimiento

        //primero tenemos que recordarr que des es un algoritmo por bloques, asi que teneoms
        /*qie definir el algoritmo en modo a bloqiue
        MODO ECB (electronic code book)
        Estandar PKCS5  Relleno
         */

        System.out.println("CIfrar el archivo" + args[0]
        + " , y vamos a dejar el resultado en " + args[0 + ".cifrado");
    }


    //definamos al algoritmo

    Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");

    cifrado.init(Cipher.ENCRYPT_MODE, clave);

    byte[] buffer = new byte[1000];
    byte[] bufferCifrado;

    //genero mius archivos

    FileInputStream in = new FileInputStream(args[0]);
    FileOutputStream out = new FileOutputStream(args[0]+".cifrado");

    //debo de ller ela rchivo

    int bytesleidos = in.read(buffer,0,1000);
    while(bytesleidos != -1 ){
         bufferCifrado = cifrado.update(
                 buffer, 0, bytesleidos);
         out.write(bufferCifrado);
         bytesleidos = in.read(buffer,0,1000);
         )

        bufferCifrado = cifrado.doFinal();
        out.write(buffer(Cifrado));

        in.close();
        out.close();

        System.out.println("hr;;p");
    }
    private static void mensajeAyuda(){

    }

    private static void mostrarBytes(){

    }
}