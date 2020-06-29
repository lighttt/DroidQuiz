package com.udacity.example.droidtermsprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class DroidTermsExampleContract implements BaseColumns {

    //inner class
    //authority
    public  static final String CONTENT_AUTHORITY = "com.example.udacity.droidtermsexample";


    //---------------------- CONTENTS ----------------------

    //if we have to access content from content provider: we have define uri
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //paths from the uri
    private static final String PATH_TERMS = "terms";

    // content uri : total full list of definitions and items
    //content://com.example.udacity.droidtermsexample/terms
    private static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TERMS).build();

    //content type : list of definition
    //content://com.example.udacity.droidtermsexample/terms/vnd.android.cursor.dir
    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TERMS;

    //content item type: single item ko definition
    //content://com.example.udacity.droidtermsexample/terms/vnd.android.cursor.item
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TERMS;


    //---------------------- DATABASE ----------------------
    // sql type :  Content Provider

    private static final int DATABASE_VERISION = 1;
    private static final String DATABASE_NAME = "terms";

    //table name
    public static final String TERMS_TABLE = "term_entries";

    //columns
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_DEFINITION = "definition";
    public static final String[] COLUMNS =
            {_ID, COLUMN_WORD, COLUMN_DEFINITION};

    //indexs
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_WORD = 1;
    public static final int COLUMN_INDEX_DEFINITION = 2;


    /**
     * This method creates a Uri link between our app and droidsapp and get word/defintion by id
     * @param id
     * @return
     */
    public static Uri buildTermUriWithId(long id)
    {
       return ContentUris.withAppendedId(CONTENT_URI,id);
    }

}
