
package com.example.autosterowanie;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;


public class UDP_Server
{
    private AsyncTask<Void, Void, Void> async;
    private boolean Server_aktiv = true;

    @SuppressLint("NewApi")
    public void runUdpServer()
    {
        async = new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                byte[] lMsg = new byte[4096];
                DatagramPacket dp = new DatagramPacket(lMsg, lMsg.length);
                DatagramSocket ds = null;

                try
                {
                    ds = new DatagramSocket(MainActivity.SERVER_PORT);

                    while(Server_aktiv)
                    {
                        ds.receive(dp);
                        Log.i("test", new String(lMsg, 0, dp.getLength()));
                //  #      Intent i = new Intent();
                //  #      i.setAction(MainActivity.MESSAGE_RECEIVED);
                //  #      i.putExtra(MainActivity.MESSAGE_STRING, new String(lMsg, 0, dp.getLength()));
                //  #      MainActivity.MainContext.getApplicationContext().sendBroadcast(i);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (ds != null)
                    {
                        ds.close();
                    }
                }

                return null;
            }
        };

        if (Build.VERSION.SDK_INT >= 11) async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else async.execute();
    }

    public void stop_UDP_Server()
    {
        Server_aktiv = false;
    }
}