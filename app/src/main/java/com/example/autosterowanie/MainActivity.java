package com.example.autosterowanie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {


    static public EditText IP;
    static public EditText port;
    DatabaseReference RoofRef;
    static public int SERVER_PORT;
    Button Lewo,Prawo,Przód,Tył;
    UDP_Client Client;
    UDP_Server Server;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        port = (EditText)  findViewById(R.id.port);
        IP = (EditText)  findViewById(R.id.IP);

        Lewo=(Button) findViewById(R.id.lewo);
        Prawo=(Button) findViewById(R.id.prawo);
        Przód=(Button) findViewById(R.id.przod);
        Tył=(Button) findViewById(R.id.tyl);
        Client = new UDP_Client();
        SERVER_PORT=5000;
        Server = new UDP_Server();
        Server.runUdpServer();

        Lewo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ByteArrayOutputStream output = new ByteArrayOutputStream();

                output.write(0x01);

                byte[] out = output.toByteArray();
                Client.Message = out;
                //Send message
                Client.NachrichtSenden();


            }

        });
        Prawo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();

                output.write(0x02);

                byte[] out = output.toByteArray();
                Client.Message = out;
                //Send message
                Client.NachrichtSenden();
            }

        });
        Przód.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ByteArrayOutputStream output = new ByteArrayOutputStream();

                output.write(0x03);

                byte[] out = output.toByteArray();
                Client.Message = out;
                //Send message
                Client.NachrichtSenden();

            }

        });
        Tył.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ByteArrayOutputStream output = new ByteArrayOutputStream();

                output.write(0x04);

                byte[] out = output.toByteArray();
                Client.Message = out;
                //Send message
                Client.NachrichtSenden();


            }
        });

        JoystickView joystick = (JoystickView) findViewById(R.id.joystickView);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();

                output.write(0x01);

                output.write((byte)((angle >> 8) & 0xff));
                output.write((byte)((angle >> 0) & 0xff));

                output.write((byte)((strength >> 0) & 0xff));
                byte[] out = output.toByteArray();
                Client.Message = out;
                //Send message
                Client.NachrichtSenden();

           }

       });

    }
}