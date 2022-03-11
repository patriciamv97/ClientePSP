package com.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in); //Se crea el lector
        // Creando socket cliente

        Socket cliente = new Socket();

        // 1) Ao arrancar solicitarase o enderezo IP e o porto de conexión do servidor.
        System.out.println("Introduce tu IP :");
        String ip = sc.nextLine();
        System.out.println("Introduce el puerto por el que desea conectarse al servidor :");
        String puerto = sc.nextLine();
        InetSocketAddress addr = new InetSocketAddress(ip, Integer.parseInt(puerto));

        cliente.connect(addr);

        InputStream entrada = cliente.getInputStream();
        OutputStream salida = cliente.getOutputStream();

        //Len envio la IP
        salida.write(ip.getBytes());
        //Le envio el puerto
        salida.write(puerto.getBytes());


        //2) A continuación solicitarase o nickname que se empregará para identificar as mensaxes do usuario, e se realizará a conexión co servidor.

        System.out.println("Introduce tu nickname :");
        String nickname = sc.nextLine();


        byte[] ms;
        String mensaje = "";

        while (!mensaje.equalsIgnoreCase("fin")) {
            mensaje = nickname + ": " + sc.nextLine();
            System.out.println("Mensaje enviado ");
            salida.write(mensaje.getBytes());
            ms = new byte[140];
            entrada.read(ms);
            if (new String(ms).trim().equalsIgnoreCase("fin")) {
                break;
            }
            System.out.println("Servidor: " + new String(ms).trim());


        }

        System.out.println("Cerrando el socket cliente");
        cliente.close();
        System.out.println("Terminado");


    }
}
