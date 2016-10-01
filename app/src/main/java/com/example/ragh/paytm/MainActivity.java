package com.example.ragh.paytm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.paytm.pgsdk.PaytmMerchant;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartTransactions(View view){
        PaytmPGService Service = PaytmPGService.getStagingService();
        Map<String, String> paramMap = new HashMap<String, String>();

        paramMap.put("ORDER_ID","order");
        paramMap.put("MID","WorldP64425807474247");
        paramMap.put("CUST_ID","cust1234");
        paramMap.put("CHANNEL_ID","WAP");
        paramMap.put("INDUSTRY_TYPE_ID","Retail");
        paramMap.put("WEBSITE","worldpressplg");
        paramMap.put("TXN_AMOUNT","1.0");
        paramMap.put("THEME","merchant" );
        paramMap.put("EMAIL","abc@gmail.com");
        paramMap.put("MOBILE_NO","7777777777");
        paramMap.put("URL","https://pguat.paytm.com/oltp-web/processTransaction");
        paramMap.put("KEY","kbzk1DSbJiV_O3p5");

        PaytmOrder Order = new PaytmOrder(paramMap);

        PaytmMerchant Merchant = new PaytmMerchant(
                "https://pguat.paytm.com/paytmchecksum/paytmCheckSumGenerator.jsp",
                "https://pguat.paytm.com/paytmchecksum/paytmCheckSumVerify.jsp");
        Service.initialize(Order, Merchant, null);
        Service.startPaymentTransaction(this, true, true,
                new PaytmPaymentTransactionCallback() {
                    @Override
                    public void onTransactionSuccess(Bundle bundle) {
                        Toast.makeText(MainActivity.this,"onTransaction",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onTransactionFailure(String s, Bundle bundle) {
                        Toast.makeText(MainActivity.this,"onTransactionFailure",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void networkNotAvailable() {
                        Toast.makeText(MainActivity.this,"NoInternet",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void clientAuthenticationFailed(String s) {
                        Toast.makeText(MainActivity.this,"onAuthenticationFailed",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void someUIErrorOccurred(String s) {
                        Toast.makeText(MainActivity.this,"UI ka dhoka",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onErrorLoadingWebPage(int i, String s, String s1) {
                        Toast.makeText(MainActivity.this,"webpage ka dhoka",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBackPressedCancelTransaction() {
                        Toast.makeText(MainActivity.this,"back pressed",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
