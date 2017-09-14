package com.example.mike.web3jtest;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mike.ExampleContract;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    TextView homeScore, awayScore, homeTeam, awayTeam;
    Web3j web3j;
    ExampleContract example;
    Credentials credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeScore = (TextView) findViewById(R.id.homeTeamScore);
        awayScore = (TextView) findViewById(R.id.awayTeamScore);
        homeTeam = (TextView) findViewById(R.id.homeTeamName);
        awayTeam = (TextView) findViewById(R.id.awayTeamName);

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String walletFilePath = sp.getString("wallet", null);
        try {
            if(walletFilePath == null)
            {
                File dir = getApplicationContext().getDir("testWalletFile", MODE_PRIVATE);

                walletFilePath = WalletUtils.generateNewWalletFile(
                        "staywoke",
                        dir, false);
                walletFilePath = new File(dir.getAbsolutePath(), walletFilePath).getAbsolutePath();
                sp.edit().putString("wallet", walletFilePath).apply();
            }
            credentials = WalletUtils.loadCredentials(
                    "staywoke",
                    walletFilePath);
        } catch (CipherException | IOException | InvalidAlgorithmParameterException | NoSuchProviderException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        new AsyncTask<Void, Void, Web3j>() {
            @Override
            protected Web3j doInBackground(Void... a) {
                return Web3jFactory.build(new HttpService("https://ropsten.infura.io/uKNKPMg61M00bdiCyHgY"));  // defaults to http://localhost:8545/
            }
            @Override
            protected void onPostExecute(Web3j w) {
                web3j = w;
                // todo background
                example = ExampleContract.load("0x8b490ccdfd8191ac816d6d11f10172e35329aed1", web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
                DoInBackground(homeScore);
                DoInBackground(awayScore);
                DoInBackground(homeTeam);
                DoInBackground(awayTeam);
            }
        }.execute();
    }

    private void DoInBackground(final TextView targetView)
    {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... a) {
                return readSmartContract(targetView);
            }
            @Override
            protected void onPostExecute(String s) {
                targetView.setText(s);
            }
        }.execute();
    }

    private String readSmartContract(TextView view) {
            try {
                if(view == homeScore)
                {
                    return example.goalsHomeTeam().get().toString();
                }
                else if(view == awayScore)
                {
                    return example.goalsAwayTeam().get().toString();
                }
                else if(view == homeTeam)
                {
                    return example.nameHomeTeam().get().toString();
                }
                else if(view == awayTeam)
                {
                    return example.nameAwayTeam().get().toString();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return "error";
    }
}
