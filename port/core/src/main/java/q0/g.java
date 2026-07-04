package q0;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ControllerCommand;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: ControllerSettingButton.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class g extends TextButton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ControllerCommand f3866a;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ControllerSettingButton.java */
    public enum a {
        UP, DOWN, LEFT, RIGHT, ATTACK, CANCEL, SKILL1, SKILL2, SKILL3, SKILL4, SKILL5, SKILL6, SKILL7, SKILL8, NEXTITEM, PREVITEM, USEITEM, RECOVERY, TAKE, DROP;
    }

    public g(a aVar) {
        super("", Assets.g(), "menuButton");
        switch (aVar.ordinal()) {
            case 0:
                this.f3866a = Settings.f3418a.up;
                break;
            case 1:
                this.f3866a = Settings.f3418a.down;
                break;
            case 2:
                this.f3866a = Settings.f3418a.left;
                break;
            case 3:
                this.f3866a = Settings.f3418a.right;
                break;
            case 4:
                this.f3866a = Settings.f3418a.action;
                break;
            case 5:
                this.f3866a = Settings.f3418a.cancel;
                break;
            case 6:
                this.f3866a = Settings.f3418a.skill1;
                break;
            case 7:
                this.f3866a = Settings.f3418a.skill2;
                break;
            case 8:
                this.f3866a = Settings.f3418a.skill3;
                break;
            case 9:
                this.f3866a = Settings.f3418a.skill4;
                break;
            case 10:
                this.f3866a = Settings.f3418a.skill5;
                break;
            case 11:
                this.f3866a = Settings.f3418a.skill6;
                break;
            case 12:
                this.f3866a = Settings.f3418a.skill7;
                break;
            case 13:
                this.f3866a = Settings.f3418a.skill8;
                break;
            case 14:
                this.f3866a = Settings.f3418a.nextItem;
                break;
            case 15:
                this.f3866a = Settings.f3418a.prevItem;
                break;
            case 16:
                this.f3866a = Settings.f3418a.useItem;
                break;
            case 17:
                this.f3866a = Settings.f3418a.recovery;
                break;
            case 18:
                this.f3866a = Settings.f3418a.invTake;
                break;
            case 19:
                this.f3866a = Settings.f3418a.invDrop;
                break;
        }
        a();
    }

    public final void a() {
        StringBuilder sb = new StringBuilder("[BLACK]");
        ControllerCommand controllerCommand = this.f3866a;
        sb.append(Settings.c(controllerCommand));
        sb.append("[]");
        sb.append(System.getProperty("line.separator"));
        sb.append("[BLUE]");
        sb.append(controllerCommand.toString());
        sb.append("[]");
        setText(sb.toString());
    }
}
