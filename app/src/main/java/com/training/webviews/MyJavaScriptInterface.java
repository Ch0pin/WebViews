package com.training.webviews;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyJavaScriptInterface {
    Context mContext;
    /** Instantiate the interface and set the context */
    MyJavaScriptInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public String execCmd(String cmd){
        try {
            Process pr = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(pr.getInputStream()));
            String line,output="";
            while ((line = reader.readLine()) != null)
                output+=line;
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
    }
}
