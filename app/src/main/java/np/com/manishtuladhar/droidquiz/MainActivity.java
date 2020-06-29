package np.com.manishtuladhar.droidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    // current state
    private  int mCurrentState;

    // hidden or shown
    private final int STATE_HIDDEN = 0;
    private final int STATE_SHOWN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button_next);
    }


    /**
     *  When button is clicked on
     */
    public void onButtonClick(View view) {
        //change state
        switch (mCurrentState){
            case STATE_HIDDEN:
                showDefinition();
                break;

            case STATE_SHOWN:
                nextWord();
                break;
        }
    }

    /**
     *  helps to show the definition of the word
     */
    private void showDefinition() {
        mButton.setText(getString(R.string.next_word));
        mCurrentState = STATE_SHOWN;
    }

    /**
     *  helps to go to the next word
     */
    private void nextWord() {
        mButton.setText(getString(R.string.show_definition));
        mCurrentState = STATE_HIDDEN;
    }
}