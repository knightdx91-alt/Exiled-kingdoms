package net.fdgames.GameWorld;

import net.fdgames.GameEntities.Helpers.DamageData;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
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
