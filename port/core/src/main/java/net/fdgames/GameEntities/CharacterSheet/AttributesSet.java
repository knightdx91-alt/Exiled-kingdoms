package net.fdgames.GameEntities.CharacterSheet;

import a.a;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class AttributesSet {
    public boolean aberration;
    public boolean beast;
    public boolean detector;
    public boolean explosive;
    public boolean fire_elemental;
    public boolean ghost;
    public boolean giant;
    public boolean goblin;
    public boolean growth;
    public boolean ice_elemental;
    public boolean immortal;
    public float last_toxic_aura;
    public boolean nocturne;
    public boolean orc;
    public boolean outsider;
    public boolean regeneration;
    public boolean robot;
    public boolean shadow;
    public boolean shapechanger;
    public boolean shield;
    public boolean shock_elemental;
    public boolean silver;
    public boolean toxic_aura;
    public boolean undead;
    public boolean vampire;

    public final boolean a(String str) {
        if (str.equals("undead")) {
            return this.undead;
        }
        if (str.equals("goblin")) {
            return this.goblin;
        }
        if (str.equals("orc")) {
            return this.orc;
        }
        if (str.equals("beast")) {
            return this.beast;
        }
        if (str.equals("detector")) {
            return this.detector;
        }
        if (str.equals("outsider")) {
            return this.outsider;
        }
        if (str.equals("aberration")) {
            return this.aberration;
        }
        if (str.equals("nocturne")) {
            return this.nocturne;
        }
        if (str.equals("regeneration")) {
            return this.regeneration;
        }
        if (str.equals("fire_elemental")) {
            return this.fire_elemental;
        }
        if (str.equals("ice_elemental")) {
            return this.ice_elemental;
        }
        if (str.equals("shock_elemental")) {
            return this.shock_elemental;
        }
        if (str.equals("ghost")) {
            return this.ghost;
        }
        if (str.equals("toxic_aura")) {
            return this.toxic_aura;
        }
        if (str.equals("shield")) {
            return this.shield;
        }
        if (str.equals("giant")) {
            return this.giant;
        }
        if (str.equals("shapechanger")) {
            return this.shapechanger;
        }
        if (str.equals("immortal")) {
            return this.immortal;
        }
        if (str.equals("explosive")) {
            return this.explosive;
        }
        if (str.equals("robot")) {
            return this.robot;
        }
        if (str.equals("growth")) {
            return this.growth;
        }
        if (str.equals("shadow")) {
            return this.shadow;
        }
        if (str.equals("vampire")) {
            return this.shadow;
        }
        return false;
    }

    public final String toString() {
        String strK = this.undead ? "undead " : "";
        if (this.goblin) {
            strK = a.k(strK, "goblin ");
        }
        if (this.orc) {
            strK = a.k(strK, "orc ");
        }
        if (this.beast) {
            strK = a.k(strK, "beast ");
        }
        if (this.detector) {
            strK = a.k(strK, "detector ");
        }
        if (this.outsider) {
            strK = a.k(strK, "outsider ");
        }
        if (this.aberration) {
            strK = a.k(strK, "aberration ");
        }
        if (this.nocturne) {
            strK = a.k(strK, "nocturne ");
        }
        if (this.regeneration) {
            strK = a.k(strK, "regeneration ");
        }
        if (this.fire_elemental) {
            strK = a.k(strK, "fire_elemental ");
        }
        if (this.ice_elemental) {
            strK = a.k(strK, "ice_elemental ");
        }
        if (this.shock_elemental) {
            strK = a.k(strK, "shock_elemental ");
        }
        if (this.ghost) {
            strK = a.k(strK, "ghost ");
        }
        if (this.toxic_aura) {
            strK = a.k(strK, "toxic_aura ");
        }
        if (this.shield) {
            strK = a.k(strK, "shield ");
        }
        if (this.giant) {
            strK = a.k(strK, "giant ");
        }
        if (this.shapechanger) {
            strK = a.k(strK, "shapechanger ");
        }
        if (this.immortal) {
            strK = a.k(strK, "immortal ");
        }
        if (this.silver) {
            strK = a.k(strK, "silver ");
        }
        if (this.explosive) {
            strK = a.k(strK, "explosive ");
        }
        if (this.robot) {
            strK = a.k(strK, "robot ");
        }
        if (this.growth) {
            strK = a.k(strK, "growth ");
        }
        if (this.shadow) {
            strK = a.k(strK, "shadow ");
        }
        if (this.vampire) {
            strK = a.k(strK, "vampire ");
        }
        return strK.equals("") ? "None" : strK.trim();
    }
}
