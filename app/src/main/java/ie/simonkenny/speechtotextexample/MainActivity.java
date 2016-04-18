package ie.simonkenny.speechtotextexample;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button captureSpeechButton = (Button) findViewById(R.id.action_button);
        if (captureSpeechButton != null) {
            captureSpeechButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    captureSpeech();
                }
            });
        }
    }

    private static final int SPEECH_REQUEST_CODE = 0;

    private void captureSpeech() {
        // Create an intent that can start the Speech Recognizer activity
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            // Get result from Extras
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Use result to set text of TextView
            TextView textView = (TextView)findViewById(R.id.speech_text_view);
            if (textView != null) {
                textView.setText(spokenText);
            }
        }
        // We should also call the super method
        super.onActivityResult(requestCode, resultCode, data);
    }
}
