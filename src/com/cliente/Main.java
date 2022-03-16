package com.cliente;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static String nick;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in); //Se crea el lector
        // Creando socket cliente
        nick = JOptionPane.showInputDialog("Introduce el nick del chat");
        Socket cliente = new Socket();
        InetSocketAddress addr = new InetSocketAddress("localhost", 2001);
        cliente.connect(addr);
        InputStream entrada = cliente.getInputStream();
        OutputStream salida = cliente.getOutputStream();
        salida.write(nick.getBytes());
        byte [] conectado = new byte[140];
        entrada.read(conectado);
        Interfaz chat = new Interfaz(salida);
        chat.setVisible(true);
        chat.nick.setText(nick);
        chat.campoChat.setText(new String(conectado).trim()+"\n");
        Lectura lectura = new Lectura(entrada, chat);
        lectura.start();

    }
}
