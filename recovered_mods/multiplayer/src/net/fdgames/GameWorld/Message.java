package net.fdgames.GameWorld;

import net.fdgames.GameEntities.Helpers.DamageData;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Message implements Comparable<Message> {
    public DamageData damageData;
    public String data;
    public float deliveryTime;
    public String name;
    public int receiver;
    public int sender;

    @Override // java.lang.Comparable
    public final int compareTo(Message message) {
        return Float.compare(this.deliveryTime, message.deliveryTime);
    }
}
