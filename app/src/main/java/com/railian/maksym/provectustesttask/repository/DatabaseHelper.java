package com.railian.maksym.provectustesttask.repository;

import android.util.Log;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

public class DatabaseHelper {
    public void insert(List<DatabaseModel> items) {
        FlowManager.getDatabase(Database.class).beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                (ProcessModelTransaction.ProcessModel<DatabaseModel>) BaseModel::save).addAll(items).build()).error((transaction, error) -> Log.e("FAILED SELECT: " + transaction, error.toString())).success(new Transaction.Success() {
            @Override
            public void onSuccess(Transaction transaction) {
                Log.e("SUCESS SELECT", transaction.toString());
            }
        }).build().execute();
    }


    public List<DatabaseModel> select() {
        return new Select().from(DatabaseModel.class).queryList();
    }

    public DatabaseModel selectByID(int id) {
        return (new Select().from(DatabaseModel.class).where(DatabaseModel_Table.Id.is(id + 1)).querySingle());
    }

    public void deleteAll() {
        FlowManager.getDatabase(Database.class).beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                (ProcessModelTransaction.ProcessModel<DatabaseModel>) BaseModel::delete).addAll(select()).build()).error((transaction, error) -> Log.e("FAILED FELETE: " + transaction, error.toString())).success(new Transaction.Success() {
            @Override
            public void onSuccess(Transaction transaction) {
                Log.e("SUCESS DELETE", transaction.toString());
            }
        }).build().execute();
    }
}