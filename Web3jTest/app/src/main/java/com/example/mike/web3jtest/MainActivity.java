package com.example.mike.web3jtest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/uKNKPMg61M00bdiCyHgY"));  // defaults to http://localhost:8545/

                try {
                    return web3.web3ClientVersion().sendAsync().get().getWeb3ClientVersion();
                } catch (InterruptedException | ExecutionException e) {
                    Log.e("web3j version", "excep", e);
                }
                return "error";
            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("web3j version", s);
            }
        }.execute();


    }
}
