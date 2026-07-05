package net.fdgames.ek;

import net.fdgames.ek.ControllerCommand;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class KeyboardConfig {
    public ControllerCommand attack;
    public ControllerCommand character;
    public ControllerCommand companion;
    public ControllerCommand down;
    public ControllerCommand escape;
    public ControllerCommand interact;
    public ControllerCommand invDrop;
    public ControllerCommand invTake;
    public ControllerCommand left;
    public ControllerCommand log;
    public ControllerCommand map;
    public ControllerCommand quickItem1;
    public ControllerCommand quickItem2;
    public ControllerCommand quickItem3;
    public ControllerCommand quickItem4;
    public ControllerCommand quickItem5;
    public ControllerCommand quicksave;
    public ControllerCommand recovery;
    public ControllerCommand right;
    public ControllerCommand skill1;
    public ControllerCommand skill2;
    public ControllerCommand skill3;
    public ControllerCommand skill4;
    public ControllerCommand skill5;
    public ControllerCommand skill6;
    public ControllerCommand skill7;
    public ControllerCommand skill8;
    public ControllerCommand up;

    public KeyboardConfig() {
        ControllerCommand.commandType commandtype = ControllerCommand.commandType.f3368d;
        this.up = new ControllerCommand(commandtype, 19);
        this.down = new ControllerCommand(commandtype, 20);
        this.left = new ControllerCommand(commandtype, 21);
        this.right = new ControllerCommand(commandtype, 22);
        this.interact = new ControllerCommand(commandtype, 61);
        this.attack = new ControllerCommand(commandtype, 62);
        this.recovery = new ControllerCommand(commandtype, 46);
        this.map = new ControllerCommand(commandtype, 41);
        this.character = new ControllerCommand(commandtype, 31);
        this.companion = new ControllerCommand(commandtype, 42);
        this.log = new ControllerCommand(commandtype, 40);
        this.quicksave = new ControllerCommand(commandtype, 252);
        this.escape = new ControllerCommand(commandtype, 131);
        this.invTake = new ControllerCommand(commandtype, 44);
        this.invDrop = new ControllerCommand(commandtype, 43);
        this.skill1 = new ControllerCommand(commandtype, 8);
        this.skill2 = new ControllerCommand(commandtype, 9);
        this.skill3 = new ControllerCommand(commandtype, 10);
        this.skill4 = new ControllerCommand(commandtype, 11);
        this.skill5 = new ControllerCommand(commandtype, 12);
        this.skill6 = new ControllerCommand(commandtype, 13);
        this.skill7 = new ControllerCommand(commandtype, 14);
        this.skill8 = new ControllerCommand(commandtype, 15);
        this.quickItem1 = new ControllerCommand(commandtype, 244);
        this.quickItem2 = new ControllerCommand(commandtype, 245);
        this.quickItem3 = new ControllerCommand(commandtype, 246);
        this.quickItem4 = new ControllerCommand(commandtype, 247);
        this.quickItem5 = new ControllerCommand(commandtype, 248);
    }
}
