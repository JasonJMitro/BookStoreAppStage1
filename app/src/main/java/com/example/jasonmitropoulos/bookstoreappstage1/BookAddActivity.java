package com.example.jasonmitropoulos.bookstoreappstage1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jasonmitropoulos.bookstoreappstage1.data.BookContract.BookEntry;
import com.example.jasonmitropoulos.bookstoreappstage1.data.BookDbHelper;

public class BookAddActivity extends AppCompatActivity {


    private EditText mProductNameEditText;

    private EditText mPriceEditText;

    private EditText mQuantityEditText;

    private EditText mSupplierNameEditText;

    private EditText mSupplierPhoneNumberEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);


        mProductNameEditText = (EditText) findViewById(R.id.edit_product_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
        mQuantityEditText = (EditText) findViewById(R.id.edit_quantity);
        mSupplierNameEditText = (EditText) findViewById(R.id.edit_supplier_name);
        mSupplierPhoneNumberEditText = (EditText) findViewById(R.id.edit_supplier_phone_number);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_add, menu);
        return true;
    }

    private void insertBook() {
        String productNameString = mProductNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        String quantityString = mQuantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String supplierNameString = mSupplierNameEditText.getText().toString().trim();
        String supplierPhoneNumberString = mSupplierPhoneNumberEditText.getText().toString().trim();
        int number = Integer.parseInt(supplierPhoneNumberString);

        BookDbHelper mDbHelper = new BookDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_PRODUCT_NAME, productNameString);
        values.put(BookEntry.COLUMN_PRICE, price);
        values.put(BookEntry.COLUMN_QUANTITY, quantity);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(BookEntry.COLUMN_SUPPLIER_PHONE, number);

        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error saving book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save:
                insertBook();
                finish();
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
