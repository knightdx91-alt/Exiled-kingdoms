package net.fdgames.Rules;

import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class ItemAttributes {
    public int antirad;
    public int arcane;
    public int banishing;
    public int beast_slayer;
    public int blessed;
    public int cover;
    public int detection;
    public int emp;
    public int gossip;
    public boolean hasAttributes;
    public int holy;
    public int offhand;
    public int orb;
    public int orc_slayer;
    public int paralysis;
    public int shield;
    public int silver;
    public int slow;
    public int stability;
    public int staff;
    public int stun;
    public int stunimmunity;
    public int tinkering;
    public int vicious;
    public int vorpal;
    public int wand;
    public int wisdom;

    public ItemAttributes(String str) {
        this.hasAttributes = false;
        if (str.trim() == "") {
            return;
        }
        this.hasAttributes = true;
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        if (lowerCase.contains("[orcslayer,1]")) {
            this.orc_slayer = 1;
        }
        if (lowerCase.contains("[orcslayer,2]")) {
            this.orc_slayer = 2;
        }
        if (lowerCase.contains("[orcslayer,3]")) {
            this.orc_slayer = 3;
        }
        if (lowerCase.contains("[orcslayer,4]")) {
            this.orc_slayer = 4;
        }
        if (lowerCase.contains("[orcslayer,5]")) {
            this.orc_slayer = 5;
        }
        if (lowerCase.contains("[arcane,1]")) {
            this.arcane = 1;
        }
        if (lowerCase.contains("[arcane,2]")) {
            this.arcane = 2;
        }
        if (lowerCase.contains("[arcane,3]")) {
            this.arcane = 3;
        }
        if (lowerCase.contains("[arcane,4]")) {
            this.arcane = 4;
        }
        if (lowerCase.contains("[arcane,5]")) {
            this.arcane = 5;
        }
        if (lowerCase.contains("[holy,1]")) {
            this.holy = 1;
        }
        if (lowerCase.contains("[holy,2]")) {
            this.holy = 2;
        }
        if (lowerCase.contains("[holy,3]")) {
            this.holy = 3;
        }
        if (lowerCase.contains("[holy,4]")) {
            this.holy = 4;
        }
        if (lowerCase.contains("[holy,5]")) {
            this.holy = 5;
        }
        if (lowerCase.contains("[emp,1]")) {
            this.emp = 1;
        }
        if (lowerCase.contains("[emp,2]")) {
            this.emp = 2;
        }
        if (lowerCase.contains("[emp,3]")) {
            this.emp = 3;
        }
        if (lowerCase.contains("[banishing,1]")) {
            this.banishing = 1;
        }
        if (lowerCase.contains("[banishing,2]")) {
            this.banishing = 2;
        }
        if (lowerCase.contains("[banishing,3]")) {
            this.banishing = 3;
        }
        if (lowerCase.contains("[banishing,4]")) {
            this.banishing = 4;
        }
        if (lowerCase.contains("[banishing,5]")) {
            this.banishing = 5;
        }
        if (lowerCase.contains("[beastslayer,1]")) {
            this.beast_slayer = 1;
        }
        if (lowerCase.contains("[beastslayer,2]")) {
            this.beast_slayer = 2;
        }
        if (lowerCase.contains("[beastslayer,3]")) {
            this.beast_slayer = 3;
        }
        if (lowerCase.contains("[beastslayer,4]")) {
            this.beast_slayer = 4;
        }
        if (lowerCase.contains("[beastslayer,5]")) {
            this.beast_slayer = 5;
        }
        if (lowerCase.contains("[slow,1]")) {
            this.slow = 1;
        }
        if (lowerCase.contains("[slow,2]")) {
            this.slow = 2;
        }
        if (lowerCase.contains("[slow,3]")) {
            this.slow = 3;
        }
        if (lowerCase.contains("[slow,4]")) {
            this.slow = 4;
        }
        if (lowerCase.contains("[slow,5]")) {
            this.slow = 5;
        }
        if (lowerCase.contains("[stun,1]")) {
            this.stun = 1;
        }
        if (lowerCase.contains("[stun,2]")) {
            this.stun = 2;
        }
        if (lowerCase.contains("[stun,3]")) {
            this.stun = 3;
        }
        if (lowerCase.contains("[stun,4]")) {
            this.stun = 4;
        }
        if (lowerCase.contains("[stun,5]")) {
            this.stun = 5;
        }
        if (lowerCase.contains("[paralysis,1]")) {
            this.paralysis = 1;
        }
        if (lowerCase.contains("[paralysis,2]")) {
            this.paralysis = 2;
        }
        if (lowerCase.contains("[paralysis,3]")) {
            this.paralysis = 3;
        }
        if (lowerCase.contains("[paralysis,4]")) {
            this.paralysis = 4;
        }
        if (lowerCase.contains("[paralysis,5]")) {
            this.paralysis = 5;
        }
        if (lowerCase.contains("[vorpal]")) {
            this.vorpal = 1;
        }
        if (lowerCase.contains("[vorpal,1]")) {
            this.vorpal = 1;
        }
        if (lowerCase.contains("[vorpal,2]")) {
            this.vorpal = 2;
        }
        if (lowerCase.contains("[vorpal,3]")) {
            this.vorpal = 3;
        }
        if (lowerCase.contains("[vorpal,4]")) {
            this.vorpal = 4;
        }
        if (lowerCase.contains("[vorpal,5]")) {
            this.vorpal = 5;
        }
        if (lowerCase.contains("[silver]")) {
            this.silver = 1;
        }
        if (lowerCase.contains("[stability]")) {
            this.stability = 1;
        }
        if (lowerCase.contains("[stunimmunity]")) {
            this.stunimmunity = 1;
        }
        if (lowerCase.contains("[rad]")) {
            this.antirad = 1;
        }
        if (lowerCase.contains("[wand]")) {
            this.wand = 1;
        }
        if (lowerCase.contains("[staff]")) {
            this.staff = 1;
        }
        if (lowerCase.contains("[vicious,1]")) {
            this.vicious = 1;
        }
        if (lowerCase.contains("[vicious,2]")) {
            this.vicious = 2;
        }
        if (lowerCase.contains("[vicious,3]")) {
            this.vicious = 3;
        }
        if (lowerCase.contains("[detection,1]")) {
            this.detection = 1;
        }
        if (lowerCase.contains("[detection,2]")) {
            this.detection = 2;
        }
        if (lowerCase.contains("[detection,3]")) {
            this.detection = 3;
        }
        if (lowerCase.contains("[tinkering,1]")) {
            this.tinkering = 1;
        }
        if (lowerCase.contains("[tinkering,2]")) {
            this.tinkering = 2;
        }
        if (lowerCase.contains("[tinkering,3]")) {
            this.tinkering = 3;
        }
        if (lowerCase.contains("[gossip,1]")) {
            this.gossip = 1;
        }
        if (lowerCase.contains("[gossip,2]")) {
            this.gossip = 2;
        }
        if (lowerCase.contains("[gossip,3]")) {
            this.gossip = 3;
        }
        if (lowerCase.contains("[wisdom,1]")) {
            this.wisdom = 1;
        }
        if (lowerCase.contains("[wisdom,2]")) {
            this.wisdom = 2;
        }
        if (lowerCase.contains("[wisdom,3]")) {
            this.wisdom = 3;
        }
        if (lowerCase.contains("[wisdom,4]")) {
            this.wisdom = 4;
        }
        if (lowerCase.contains("[wisdom,5]")) {
            this.wisdom = 5;
        }
        if (lowerCase.contains("[blessed,1]")) {
            this.blessed = 1;
        }
        if (lowerCase.contains("[blessed,2]")) {
            this.blessed = 2;
        }
        if (lowerCase.contains("[blessed,3]")) {
            this.blessed = 3;
        }
        if (lowerCase.contains("[cover,1]")) {
            this.cover = 1;
        }
        if (lowerCase.contains("[cover,2]")) {
            this.cover = 2;
        }
        if (lowerCase.contains("[cover,3]")) {
            this.cover = 3;
        }
        if (lowerCase.contains("[arcane,1]")) {
            this.arcane = 1;
        }
        if (lowerCase.contains("[arcane,2]")) {
            this.arcane = 2;
        }
        if (lowerCase.contains("[arcane,3]")) {
            this.arcane = 3;
        }
        if (lowerCase.contains("[shield]")) {
            this.shield = 1;
        }
        if (lowerCase.contains("[offhand]")) {
            this.offhand = 1;
        }
        if (lowerCase.contains("[orb]")) {
            this.orb = 1;
        }
    }
}
