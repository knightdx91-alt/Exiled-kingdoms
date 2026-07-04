package net.fdgames.GameWorld;

import java.util.Locale;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Area {
    public boolean free;
    public String id;
    private String name;
    public boolean outdoor;
    private int posX;
    private int posY;
    private String region_id;
    public boolean worldmap;

    public Area(String str) {
        String[] strArrSplit = str.replace("\"", "").split("\t", -1);
        this.id = strArrSplit[0];
        this.region_id = strArrSplit[3];
        this.outdoor = false;
        this.worldmap = false;
        this.free = false;
        String str2 = strArrSplit[4];
        Locale locale = Locale.ENGLISH;
        if (str2.toLowerCase(locale).contains("[outdoor]")) {
            this.outdoor = true;
        }
        if (strArrSplit[4].toLowerCase(locale).contains("[worldmap]")) {
            this.worldmap = true;
            this.posX = Integer.parseInt(strArrSplit[1]);
            this.posY = Integer.parseInt(strArrSplit[2]);
        } else {
            this.posX = -1;
            this.posY = -1;
        }
        if (strArrSplit[4].toLowerCase(locale).contains("[free]")) {
            this.free = true;
        }
        switch (Settings.h()) {
            case 1:
                this.name = strArrSplit[5];
                break;
            case 2:
                this.name = strArrSplit[6];
                break;
            case 3:
                this.name = strArrSplit[7];
                break;
            case 4:
                this.name = strArrSplit[8];
                break;
            case 5:
                this.name = strArrSplit[9];
                break;
            case 6:
                this.name = strArrSplit[10];
                break;
            case 7:
                this.name = strArrSplit[11];
                break;
            case 8:
                this.name = strArrSplit[12];
                break;
            case 9:
                this.name = strArrSplit[13];
                break;
            default:
                this.name = strArrSplit[5];
                break;
        }
        if (this.name.trim().equals("")) {
            this.name = strArrSplit[5];
        }
    }

    public final String a() {
        return this.name;
    }

    public final Coords b() {
        return new Coords(this.posX, this.posY);
    }

    public final String c() {
        return GameWorld.f3192f.i(this.region_id);
    }
}
