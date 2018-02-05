package dev.theodo.red.messnger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    public void onSendMessage(View view){
//        Intent intent = new Intent(this, ReceiveMessageActivity.class);
//        TextView message = findViewById(R.id.sendingMessage);
//        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, message.getText().toString());
//        startActivity(intent);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        TextView message = findViewById(R.id.sendingMessage);
        intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
        startActivity(intent);
    }
}
