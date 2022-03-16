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
        InetSocketAddress addr = new InetSocketAddress("localhost", 2001);

        cliente.connect(addr);

        InputStream entrada = cliente.getInputStream();
        OutputStream salida = cliente.getOutputStream();

        Interfaz chat = new Interfaz(salida);
        chat.setVisible(true);
        String nick =chat.nick.getText();
        salida.write(nick.getBytes());
        byte [] mensaje = new byte[140];
        entrada.read(mensaje);
        chat.campoChat.append(new String(mensaje).trim()+"\n");
        Lectura lectura = new Lectura(entrada, chat);
        lectura.start();





        //2) A continuación solicitarase o nickname que se empregará para identificar as mensaxes do usuario, e se realizará a conexión co servidor.



        //System.out.println("Cerrando el socket cliente");
        //cliente.close();
        //System.out.println("Terminado");


    }
}
