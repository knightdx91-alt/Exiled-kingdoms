package net.fdgames.ek;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class SettingsData {
    public boolean GPGSAutoConnect;
    public float MusicVolume;
    public float SoundVolume;
    public int activationCode;
    public boolean attackInteracts;
    public boolean combatLog;
    private int installID;
    public int language;
    private boolean licenseCode;
    public boolean licenseFull;
    private boolean licenseTest;
    public float lowMControls;
    public String orderID;
    public String registrationDate;
    public boolean showControls;
    public boolean showNumbersBars;
    public float sideMControls;
    public float sizeControls;
    public boolean useOldFonts;

    public SettingsData() {
        this.language = 1;
        if (ExiledKingdoms.f3376f.equals("es")) {
            this.language = 2;
        }
        if (ExiledKingdoms.f3376f.equals("pt")) {
            this.language = 4;
        }
        if (ExiledKingdoms.f3376f.equals("ru")) {
            this.language = 3;
        }
        if (ExiledKingdoms.f3376f.equals("de")) {
            this.language = 5;
        }
        if (ExiledKingdoms.f3376f.equals("it")) {
            this.language = 9;
        }
        if (ExiledKingdoms.f3376f.equals("pl")) {
            this.language = 6;
        }
        if (ExiledKingdoms.f3376f.equals("cs")) {
            this.language = 7;
        }
        if (ExiledKingdoms.f3376f.equals("tr")) {
            this.language = 8;
        }
        this.SoundVolume = 0.6f;
        this.MusicVolume = 0.6f;
        this.licenseTest = false;
        this.licenseFull = false;
        this.licenseCode = false;
        this.GPGSAutoConnect = false;
        this.sizeControls = 0.0f;
        this.sideMControls = 1.0f;
        this.lowMControls = 1.0f;
        this.orderID = "";
        this.showControls = true;
        if (ExiledKingdoms.f3378h) {
            this.showControls = false;
        }
        this.attackInteracts = false;
        this.useOldFonts = false;
        this.showNumbersBars = false;
    }
}
