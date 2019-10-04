package com.example.android_example_view04;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint("Registered")
public class MyContentProvider extends ContentProvider {

    public static final int TABLE_DIR = 1;
    public static final int TABLE_ITEM = 2;
    public static final int TABLE2_DIR = 3;
    public static final int TABLE2_ITEM = 4;
    private static UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI("com.example.android_example_view04", "table", TABLE_DIR);
        mUriMatcher.addURI("com.example.android_example_view04", "table/#", TABLE_ITEM);
        mUriMatcher.addURI("com.example.android_example_view04", "table2", TABLE2_DIR);
        mUriMatcher.addURI("com.example.android.example_view04", "table2/#", TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
        // if  return false init failed else if true representable success
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (mUriMatcher.match(uri)) {
            case TABLE_DIR:

                break;
            case TABLE_ITEM:

                break;
            case TABLE2_DIR:

                break;
            case TABLE2_ITEM:

                break;
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (mUriMatcher.match(uri)) {
            case TABLE_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.android_example_view04.table1";
            case TABLE_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.android_example_view04.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.android_example_view04.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.android_example_view04.table2";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
