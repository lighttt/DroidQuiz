package np.com.manishtuladhar.droidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.udacity.example.droidtermsprovider.DroidTermsExampleContract;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mDefinitionTextView,mWordTextView;

    //cursor data
    private Cursor mData;
    private  int mDefCol,mWordCol;

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
        mDefinitionTextView = findViewById(R.id.text_view_definition);
        mWordTextView = findViewById(R.id.text_view_word);

        //database operation to get cursor or data from the content provider
        new WordFetchTask().execute();
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
       if(mData!=null)
       {
           mDefinitionTextView.setVisibility(View.VISIBLE);
           mButton.setText(getString(R.string.next_word));
           mCurrentState = STATE_SHOWN;
       }
    }

    /**
     *  helps to go to the next word
     */
    private void nextWord() {
        if(mData!=null)
        {
            // move to next position if there is any one left or not , move to first
            if(!mData.moveToNext())
            {
                mData.moveToFirst();
            }
            //hide definition
            mDefinitionTextView.setVisibility(View.INVISIBLE);
            mButton.setText(getString(R.string.show_definition));

            // get the data
            mWordTextView.setText(mData.getString(mWordCol));
            mDefinitionTextView.setText(mData.getString(mDefCol));

            mCurrentState = STATE_HIDDEN;
        }
    }


    // ------------------------- CONTENT PROVIDER CONNECTION -------------------------

    /**
     *  Async task for getting the data from the content provider
     */
    public class WordFetchTask extends AsyncTask<Void,Void, Cursor>
    {

        @Override
        protected Cursor doInBackground(Void... voids) {

            // Content Resolver = gets the cursor from content provider and we need query Uri to it
            ContentResolver resolver = getContentResolver();

            //uri pass
            Cursor cursor = resolver.query(DroidTermsExampleContract.CONTENT_URI,null,null,null,null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mData = cursor;

            //word and definition
            mDefCol = mData.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION);
            mWordCol = mData.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD);

            //get the next word
            nextWord();
        }
    }

}