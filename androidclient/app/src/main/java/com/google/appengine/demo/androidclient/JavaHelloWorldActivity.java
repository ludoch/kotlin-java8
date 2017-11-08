package com.google.appengine.demo.androidclient;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;
import static android.view.inputmethod.EditorInfo.IME_ACTION_UNSPECIFIED;
import static java.nio.charset.StandardCharsets.UTF_8;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class JavaHelloWorldActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hello_world);
    EditText nameInput = (EditText) findViewById(R.id.nameInput);
    nameInput.setOnEditorActionListener(
        (textView, actionId, keyEvent) -> {
          if (actionId == IME_ACTION_DONE || actionId == IME_ACTION_UNSPECIFIED) {
            new LookUpName().execute(textView.getText().toString());
            return true;
          } else {
            return false;
          }
        });
  }

  private class LookUpName extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
      try {
        URL url = new URL("http://kotlin-java8.appspot.com/lookup/" + strings[0]);
        byte[] bytes;
        try (ByteArrayOutputStream bout = new ByteArrayOutputStream();
             InputStream in = url.openStream()) {
          int b;
          while ((b = in.read()) >= 0) {
            bout.write(b);
          }
          bytes = bout.toByteArray();
        }
        return "Java: " + new String(bytes, UTF_8);
      } catch (Exception e) {
        return e.toString();
      }
    }

    @Override
    protected void onPostExecute(String s) {
      TextView resultView = (TextView) findViewById(R.id.resultView);
      resultView.setText(s);
    }
  }
}
