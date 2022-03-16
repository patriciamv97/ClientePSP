package com.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Lectura  extends Thread{
    final InputStream entrada;
    private final Interfaz chat;
    private final Socket cliente;

    public Lectura(InputStream entrada, Interfaz chat, Socket cliente) {
        this.entrada= entrada;
        this.chat = chat;
        this.cliente =cliente;
    }

    public void run(){
        try {
            while (true){
                byte [] mensaje = new byte[140];
                entrada.read(mensaje);
                chat.campoChat.append(new String(mensaje).trim() + "\n");
                if(new String(mensaje).trim().equals("/bye")){
                    cliente.close();
                    System.exit(0);
                }


            }

        } catch (IOException ioException) {
            ioException.printStackTrace();dumpStack();
        }
    }
}
