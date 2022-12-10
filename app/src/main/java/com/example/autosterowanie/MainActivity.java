package com.example.autosterowanie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {


    static public EditText IP;
    static public EditText port;
 //   DatabaseReference RoofRef;
    static public int SERVER_PORT;
    Button Lewo,Prawo,Przód,Tył;
    UDP_Client Client;
    UDP_Server Server;

    Switch auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        port = (EditText)  findViewById(R.id.port);
        IP = (EditText)  findViewById(R.id.IP);


        auto = (Switch) findViewById(R.id.auto);
        Prawo=(Button) findViewById(R.id.prawo);
        Przód=(Button) findViewById(R.id.przod);
        Tył=(Button) findViewById(R.id.tyl);
        Client = new UDP_Client();
        SERVER_PORT=5000;
        Server = new UDP_Server();
        Server.runUdpServer();


        auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Client.Message_String = "A1";


                } else {
                    Client.Message_String = "A0";

                }
                Client.Send();
            }
        });



        Prawo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        Przód.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }

        });
        Tył.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });

        JoystickView joystick = (JoystickView) findViewById(R.id.joystickView);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
            Client.Message_String="K"+ Integer.toString(angle)+"S"+ Integer.toString(strength);
            Client.Send();
           }

       },200);

    }
}