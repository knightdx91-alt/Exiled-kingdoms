package net.fdgames.GameEntities.CharacterSheet;

import a.a;
import androidx.appcompat.widget.LW.RCsZWh;
import com.badlogic.gdx.math.tuHF.LpvdRIktEBw;
import com.google.android.gms.internal.p000authapi.KJ.ZeMa;
import o.Oeoo.vIBRkbZbNjpf;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
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
        if (str.equals(RCsZWh.KhsDzAe)) {
            return this.orc;
        }
        if (str.equals(LpvdRIktEBw.mPGqVdVMAxb)) {
            return this.beast;
        }
        if (str.equals("detector")) {
            return this.detector;
        }
        if (str.equals("outsider")) {
            return this.outsider;
        }
        if (str.equals(ZeMa.pAL)) {
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
        if (str.equals(vIBRkbZbNjpf.QJnsLuykuxWsulA)) {
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
        if (str.equals("shadow") || str.equals("vampire")) {
            return this.shadow;
        }
        return false;
    }

    public final String toString() {
        String strZ = this.undead ? "undead " : "";
        if (this.goblin) {
            strZ = a.z(strZ, "goblin ");
        }
        if (this.orc) {
            strZ = a.z(strZ, "orc ");
        }
        if (this.beast) {
            strZ = a.z(strZ, "beast ");
        }
        if (this.detector) {
            strZ = a.z(strZ, "detector ");
        }
        if (this.outsider) {
            strZ = a.z(strZ, "outsider ");
        }
        if (this.aberration) {
            strZ = a.z(strZ, "aberration ");
        }
        if (this.nocturne) {
            strZ = a.z(strZ, "nocturne ");
        }
        if (this.regeneration) {
            strZ = a.z(strZ, "regeneration ");
        }
        if (this.fire_elemental) {
            strZ = a.z(strZ, "fire_elemental ");
        }
        if (this.ice_elemental) {
            strZ = a.z(strZ, "ice_elemental ");
        }
        if (this.shock_elemental) {
            strZ = a.z(strZ, "shock_elemental ");
        }
        if (this.ghost) {
            strZ = a.z(strZ, "ghost ");
        }
        if (this.toxic_aura) {
            strZ = a.z(strZ, "toxic_aura ");
        }
        if (this.shield) {
            strZ = a.z(strZ, "shield ");
        }
        if (this.giant) {
            strZ = a.z(strZ, "giant ");
        }
        if (this.shapechanger) {
            strZ = a.z(strZ, "shapechanger ");
        }
        if (this.immortal) {
            strZ = a.z(strZ, vIBRkbZbNjpf.aFnVrQkpzd);
        }
        if (this.silver) {
            strZ = a.z(strZ, "silver ");
        }
        if (this.explosive) {
            strZ = a.z(strZ, "explosive ");
        }
        if (this.robot) {
            strZ = a.z(strZ, "robot ");
        }
        if (this.growth) {
            strZ = a.z(strZ, "growth ");
        }
        if (this.shadow) {
            strZ = a.z(strZ, "shadow ");
        }
        if (this.vampire) {
            strZ = a.z(strZ, "vampire ");
        }
        return strZ.equals("") ? "None" : strZ.trim();
    }
}
