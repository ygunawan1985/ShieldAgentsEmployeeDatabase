package com.example.shieldagentsemployeedatabase.datasource.local.database;

import android.content.Context;

import androidx.room.Room;

public class ShieldAgentsDatabaseClient {

    private Context mContext;
    private static ShieldAgentsDatabaseClient mInstance;

    private ShieldAgentsDatabase shieldAgentsDatabase;

    public ShieldAgentsDatabaseClient(Context mContext) {
        this.mContext = mContext;

        shieldAgentsDatabase = Room.databaseBuilder(mContext, ShieldAgentsDatabase.class, "ShieldAgentsDatabase").fallbackToDestructiveMigration().build();
    }

    public static synchronized ShieldAgentsDatabaseClient getInstance(Context mContext){
        if(mInstance == null){
            mInstance = new ShieldAgentsDatabaseClient(mContext);
        }
        return mInstance;
    }

    public ShieldAgentsDatabase getShieldAgentsDatabase(){
        return shieldAgentsDatabase;
    }
}
