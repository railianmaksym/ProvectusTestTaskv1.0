package com.railian.maksym.provectustesttask;

import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
/*
*
 * Created by fluffy on 30.06.17.
 */


public class DatabaseHelper {
    JsonMapper jsonMapper=new JsonMapper();

    // Insert list of DatabaseModels in DatabaseModel_Table
    public void insert(List<DatabaseModel> items) {
        FlowManager.getDatabase(Database.class).beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                new ProcessModelTransaction.ProcessModel<DatabaseModel>() {

                    @Override
                    public void processModel(DatabaseModel model) {
                        model.save();
                    }
                }).addAll(items).build()).error(new Transaction.Error() {
            @Override
            public void onError(Transaction transaction, Throwable error) {
                Log.e("FAILED SELECT: " + transaction, error.toString());
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(Transaction transaction) {
                Log.e("SUCESS SELECT", transaction.toString());
            }
        }).build().execute();

        // Debug log for insert control
        Long n = new Select().from(DatabaseModel.class).count();
        Log.d("COUNT ", n.toString());
    }


    public List<DatabaseModel> select() {

        //Setect all items from DatabaseModel_Table
        List<DatabaseModel> items= new Select().from(DatabaseModel.class).queryList();

        return items;
    }

    public DatabaseModel selectByID(int id) {

        //Setect single item from DatabaseModel_Table by Id
        return (new Select().from(DatabaseModel.class).where(DatabaseModel_Table.Id.is(id + 1)).querySingle());

    }

    public void deleteAll() {

        // Delete all items from DatabaseModel_Table
        FlowManager.getDatabase(Database.class).beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                new ProcessModelTransaction.ProcessModel<DatabaseModel>() {

                    @Override
                    public void processModel(DatabaseModel model) {
                        model.delete();
                    }
                }).addAll(select()).build()).error(new Transaction.Error() {
            @Override
            public void onError(Transaction transaction, Throwable error) {
                Log.e("FAILED FELETE: " + transaction, error.toString());
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(Transaction transaction) {
                Log.e("SUCESS DELETE", transaction.toString());
            }
        }).build().execute();

    }
}