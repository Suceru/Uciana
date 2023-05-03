package com.birdshel.Uciana.Elements.Battle;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class Hex {
    private String object = "none";
    private boolean canMove = false;
    private boolean canAttack = false;
    private boolean canSpecial = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.canAttack;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        return this.canMove;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.canSpecial;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return !this.object.equals("none");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.object = "none";
    }

    public String getObject() {
        return this.object;
    }

    public void setAttack(boolean z) {
        this.canAttack = z;
    }

    public void setMove(boolean z) {
        this.canMove = z;
    }

    public void setObject(String str) {
        this.object = str;
    }

    public void setSpecial(boolean z) {
        this.canSpecial = z;
    }
}
