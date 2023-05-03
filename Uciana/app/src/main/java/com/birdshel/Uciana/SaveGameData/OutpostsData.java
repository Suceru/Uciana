package com.birdshel.Uciana.SaveGameData;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.Game;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class OutpostsData {
    private static final String OUTPOSTS = "outposts";
    private SQLiteDatabase db;
    private final Game game;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutpostsData(Game game) {
        this.game = game;
    }

    private void loadOutposts() {
        Cursor query = this.db.query(OUTPOSTS, new String[]{"empireID", "systemID", "orbit"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("empireID");
        int columnIndex2 = query.getColumnIndex("systemID");
        int columnIndex3 = query.getColumnIndex("orbit");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            this.game.outposts.add(new Outpost(this.game.galaxy.getStarSystem(query.getInt(columnIndex2)).getSystemObject(query.getInt(columnIndex3)), query.getInt(columnIndex)));
            query.moveToNext();
        }
        query.close();
    }

    private void saveOutposts() {
        this.db.beginTransaction();
        for (Outpost outpost : this.game.outposts.getOutposts()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("empireID", Integer.valueOf(outpost.getEmpireID()));
            contentValues.put("systemID", Integer.valueOf(outpost.getSystemID()));
            contentValues.put("orbit", Integer.valueOf(outpost.getOrbit()));
            this.db.insert(OUTPOSTS, null, contentValues);
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        this.game.outposts.clear();
        loadOutposts();
    }

    public void save(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        saveOutposts();
    }
}
