package com.example.autosterowanie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import java.net.InetAddress;


public class UDP_Client {
    private AsyncTask<Void, Void, Void> async_client;
    public byte[]  Message;


    @SuppressLint("NewApi")
    public void NachrichtSenden() {
        async_client = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                DatagramSocket ds = null;

                try {
                    ds = new DatagramSocket();
                    DatagramPacket dp;
                    dp = new DatagramPacket(Message, Message.length, InetAddress.getByName(MainActivity.IP.getText().toString()), new Integer(MainActivity.port.getText().toString()).intValue());
                    ds.setBroadcast(true);
                    ds.send(dp);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (ds != null) {
                        ds.close();
                    }
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };

        if (Build.VERSION.SDK_INT >= 11)
            async_client.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else async_client.execute();
    }
}