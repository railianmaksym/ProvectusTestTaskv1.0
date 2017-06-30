package com.railian.maksym.provectustesttask;

import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

/**
 * Created by fluffy on 30.06.17.
 */

public class DatabaseHelper {

    public void insert(List<UsersItem> items){
        Log.e("LIST ",items.toString());
        FlowManager.getDatabase(Database.class).beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                new ProcessModelTransaction.ProcessModel<UsersItem>(){

                    @Override
                    public void processModel(UsersItem model) {
                        model.save();
                    }
                }).addAll(items).build()).error(new Transaction.Error(){
            @Override
            public void onError(Transaction transaction, Throwable error) {
            Log.e("Error Transaction: "+transaction,error.toString());
            }
        }).success(new Transaction.Success(){
            @Override
            public void onSuccess(Transaction transaction) {
            Log.e("SUCESS ",transaction.toString());
            }
        }).build().execute();

        Long n= new Select().from(UsersItem.class).count();
        Log.e("COUNT ",n.toString());
    }

    public List<UsersItem> select(){

        List<UsersItem> items = new Select().from(UsersItem.class).queryList();
        Log.e("WTF: ",String.valueOf(items.size()));

        return items;
    }
}
