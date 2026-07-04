package net.fdgames.ek;

import com.google.android.gms.games.quest.Quests;
import net.fdgames.ek.ControllerCommand;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class ControllerConfig {
    public ControllerCommand action;
    public ControllerCommand cancel;
    public ControllerCommand down;
    public ControllerCommand invDrop;
    public ControllerCommand invTake;
    public ControllerCommand left;
    public ControllerCommand nextItem;
    public ControllerCommand prevItem;
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
    public ControllerCommand useItem;

    public ControllerConfig() {
        ControllerCommand.commandType commandtype = ControllerCommand.commandType.NEGATIVE_AXIS;
        this.up = new ControllerCommand(commandtype, 1);
        ControllerCommand.commandType commandtype2 = ControllerCommand.commandType.POSITIVE_AXIS;
        this.down = new ControllerCommand(commandtype2, 1);
        this.left = new ControllerCommand(commandtype, 0);
        this.right = new ControllerCommand(commandtype2, 0);
        ControllerCommand.commandType commandtype3 = ControllerCommand.commandType.BUTTON;
        this.action = new ControllerCommand(commandtype3, 96);
        this.cancel = new ControllerCommand(commandtype3, 97);
        this.skill1 = new ControllerCommand(commandtype3, 99);
        this.skill2 = new ControllerCommand(commandtype3, 100);
        this.skill3 = new ControllerCommand(commandtype3, Quests.SELECT_ENDING_SOON);
        this.skill4 = new ControllerCommand(commandtype3, Quests.SELECT_RECENTLY_FAILED);
        this.skill5 = new ControllerCommand(commandtype3, Quests.SELECT_COMPLETED_UNCLAIMED);
        this.skill6 = new ControllerCommand(commandtype3, Quests.SELECT_COMPLETED_UNCLAIMED);
        this.skill7 = new ControllerCommand(commandtype3, Quests.SELECT_COMPLETED_UNCLAIMED);
        this.skill8 = new ControllerCommand(commandtype3, Quests.SELECT_COMPLETED_UNCLAIMED);
        this.invTake = new ControllerCommand(commandtype3, 104);
        this.invDrop = new ControllerCommand(commandtype3, 105);
        this.nextItem = new ControllerCommand(commandtype2, 6);
        this.prevItem = new ControllerCommand(commandtype, 6);
        this.useItem = new ControllerCommand(commandtype2, 7);
        this.recovery = new ControllerCommand(commandtype, 7);
    }
}
