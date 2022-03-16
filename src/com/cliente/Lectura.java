package com.cliente;

import java.io.IOException;
import java.io.InputStream;

public class Lectura  extends Thread{
    final InputStream entrada;
    private final Interfaz chat;

    public Lectura( InputStream entrada, Interfaz chat) {
        this.entrada= entrada;
        this.chat = chat;
    }

    public void run(){
        try {
            while (true){
                byte [] mensaje = new byte[140];
                entrada.read(mensaje);
                chat.campoChat.append(new String(mensaje).trim() + "\n");
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
