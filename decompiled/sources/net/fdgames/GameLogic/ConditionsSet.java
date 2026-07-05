package net.fdgames.GameLogic;

import a.a;
import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ConditionsSet {
    private ArrayList<Condition> conditions = new ArrayList<>();

    public ConditionsSet() {
    }

    public final Boolean a() {
        Iterator<Condition> it = this.conditions.iterator();
        while (it.hasNext()) {
            if (!it.next().a().booleanValue()) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public final String toString() {
        String string = "";
        for (Condition condition : this.conditions) {
            if (!string.equals("")) {
                string = string.concat(";");
            }
            StringBuilder sbS = a.s(string);
            sbS.append(condition.toString());
            string = sbS.toString();
        }
        return string;
    }

    public ConditionsSet(String str) {
        Iterator it = FDUtils.s(str).iterator();
        while (it.hasNext()) {
            String[] strArrSplit = ((String) it.next()).split("#", -1);
            this.conditions.add(new Condition(strArrSplit[0], strArrSplit[1]));
        }
    }
}
