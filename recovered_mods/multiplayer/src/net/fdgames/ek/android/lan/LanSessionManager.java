package net.fdgames.ek.android.lan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.badlogic.gdx.graphics.GL20;
import com.google.android.gms.drive.MetadataChangeSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterStats;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.DamageEffect;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameVariables;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes2.dex */
public final class LanSessionManager {
    public static final int CHAT_PORT = 32124;
    private static final String DISCOVERY_MAGIC = "EK_DISCOVER";
    public static final int DISCOVERY_PORT = 32123;
    private static final String HOST_MAGIC = "EK_HOST";
    private static final int MAX_CHAT_HISTORY = 120;
    private static final int MAX_DISCOVERY_RESULTS = 20;
    private static final int MAX_PLAYERS = 6;
    private static LanSessionManager instance;
    private Thread acceptThread;
    private final Context appContext;
    private Thread clientReadThread;
    private Socket clientSocket;
    private PrintWriter clientWriter;
    private boolean connected;
    private DatagramSocket hostDiscoverySocket;
    private Thread hostDiscoveryThread;
    private ServerSocket hostServerSocket;
    private boolean hosting;
    private int lastAppliedPlayerDamageSeq;
    private String lastNpcStateData;
    private PlayerState localState;
    private Thread scanThread;
    private String sessionName;
    private boolean stopRequested;
    private UiListener uiListener;
    private int unreadChatCount;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Object lock = new Object();
    private final ArrayList<String> players = new ArrayList<>();
    private final ArrayList<String> chatHistory = new ArrayList<>();
    private final ArrayList<DiscoveryResult> discoveryResults = new ArrayList<>();
    private final ArrayList<ClientPeer> hostPeers = new ArrayList<>();
    private final ArrayList<String> pendingCombatPackets = new ArrayList<>();
    private final ArrayList<String> pendingPlayerDamagePackets = new ArrayList<>();
    private final LinkedHashMap<String, DiscoveryResult> discoveryByKey = new LinkedHashMap<>();
    private final LinkedHashMap<String, PlayerState> peerStates = new LinkedHashMap<>();
    private String localPlayerName = lanString("lan_default_player_name", "Player");
    private String statusDiag = "";
    private String hostAddress = "";
    private int hostPort = CHAT_PORT;
    private int maxPlayers = 6;
    private int nextPlayerDamageSeq = 1;
    private String lastAppliedPlayerDamageLevelId = "";

    private static final class ClientPeer {
        private final String playerName;
        private final Socket socket;
        private final PrintWriter writer;

        private ClientPeer(Socket socket, PrintWriter printWriter, String str) {
            this.socket = socket;
            this.writer = printWriter;
            this.playerName = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void close() {
            if (this.writer != null) {
                this.writer.close();
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e2) {
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void send(String str) {
            if (this.writer != null) {
                this.writer.print(str);
                this.writer.print('\n');
                this.writer.flush();
            }
        }
    }

    public static final class DiscoveryResult {
        public final String address;
        public final int maxPlayers;
        public final int playerCount;
        public final int port;
        public final String sessionName;

        public DiscoveryResult(String str, String str2, int i2, int i3, int i4) {
            this.sessionName = str;
            this.address = str2;
            this.port = i2;
            this.playerCount = i3;
            this.maxPlayers = i4;
        }
    }

    public static final class FollowerState {
        public int stateTimeMs;
        public String spawnId = "";
        public String tag = "";
        public String name = "";
        public String spriteIndexCsv = "";
        public String spriteName = "";
        public String facingName = "";
        public String actorStateName = "";

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f3653x = -1;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f3654y = -1;
    }

    public static final class PlayerState {
        public int actionSeq;
        public int companionStateTimeMs;
        public int difficulty;
        public int gold;
        public int level;
        public int missingHp;
        public int missingMana;
        public int portraitIndex;
        public int repIlmara;
        public int repMercia;
        public int repThree;
        public int repVarsilia;
        public int repWizards;
        public long sampleTimeMs;
        public int slot;
        public float speedX;
        public float speedY;
        public int spellTarget;
        public int stateTimeMs;
        public int stealthSkillLevel;
        public int summonStateTimeMs;
        public int visualFxMask;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f3655x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f3656y;
        public String actorStateName = "";
        public String playerName = "";
        public String characterName = "";
        public String raceName = "";
        public String currentLevelId = "";
        public String currentMapName = "";
        public String combatEffectsSnapshot = "";
        public String className = "";
        public String classEnumName = "";
        public String genderName = "";
        public String spellId = "";
        public String skillSnapshot = "";
        public String spriteIndexCsv = "";
        public String spriteName = "";
        public String facingName = "";
        public String companionSpawnId = "";
        public String companionTag = "";
        public String companionName = "";
        public String companionSpriteIndexCsv = "";
        public String companionSpriteName = "";
        public String companionFacingName = "";
        public String companionActorStateName = "";
        public String summonSpawnId = "";
        public String summonTag = "";
        public String summonName = "";
        public String summonSpriteIndexCsv = "";
        public String summonSpriteName = "";
        public String summonFacingName = "";
        public String summonActorStateName = "";
        public ArrayList<FollowerState> followers = new ArrayList<>();
        public int slotBodyItemId = -1;
        public int slotFeetItemId = -1;
        public int slotHandsItemId = -1;
        public int slotHeadItemId = -1;
        public int slotLegsItemId = -1;
        public int slotMainhandItemId = -1;
        public int slotOffhandItemId = -1;
        public int actionOriginX = -1;
        public int actionOriginY = -1;
        public int companionX = -1;
        public int companionY = -1;
        public int summonX = -1;
        public int summonY = -1;
    }

    public interface UiListener {
        void onChatUpdated(List<String> list);

        void onDiscoveryUpdated(List<DiscoveryResult> list);

        void onPlayersChanged(List<String> list);

        void onStateChanged(String str);

        void onToast(String str);
    }

    private LanSessionManager(Context context) {
        this.sessionName = "";
        this.appContext = context;
        this.sessionName = "";
    }

    private void addChatLineLocked(String str, String str2) {
        if (str2 == null || str2.trim().isEmpty()) {
            return;
        }
        appendChatLocked(str + ": " + str2);
    }

    private void addHeuristicBroadcasts(LinkedHashSet linkedHashSet, String str) {
        byte[] address;
        if (linkedHashSet == null || str == null) {
            return;
        }
        try {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            InetAddress byName = InetAddress.getByName(strTrim);
            if ((byName instanceof Inet4Address) && (address = byName.getAddress()) != null && address.length == 4) {
                byte[] bArr = (byte[]) address.clone();
                bArr[3] = -1;
                linkedHashSet.add(InetAddress.getByAddress(bArr));
                int i2 = address[0] & 255;
                int i3 = address[1] & 255;
                if (i2 == 10 || (i2 == 192 && i3 == 168)) {
                    byte[] bArr2 = (byte[]) address.clone();
                    bArr2[2] = -1;
                    bArr2[3] = -1;
                    linkedHashSet.add(InetAddress.getByAddress(bArr2));
                }
                if (i2 == 172 && i3 >= 16 && i3 <= 31) {
                    byte[] bArr3 = (byte[]) address.clone();
                    bArr3[2] = -1;
                    bArr3[3] = -1;
                    linkedHashSet.add(InetAddress.getByAddress(bArr3));
                }
            }
        } catch (Exception e2) {
        }
    }

    private void addSystemLineLocked(String str) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        appendChatLocked("[" + (pt() ? "Sistema" : "System") + "] " + str);
    }

    private void appendChatLocked(String str) {
        this.chatHistory.add(str);
        while (this.chatHistory.size() > MAX_CHAT_HISTORY) {
            this.chatHistory.remove(0);
        }
    }

    private void appendDamagesFromCsv(DamageData damageData, String str) {
        if (damageData == null || str == null || str.trim().isEmpty()) {
            return;
        }
        for (String str2 : str.split(";")) {
            if (str2 != null) {
                String[] strArrSplit = str2.split(":", -1);
                if (strArrSplit.length >= 3) {
                    damageData.a(Damage.b(strArrSplit[0]), parseInt(strArrSplit[1], 0), parseInt(strArrSplit[2], 0) != 0);
                }
            }
        }
    }

    private void appendProcsFromCsv(DamageData damageData, String str) {
        if (damageData == null || str == null || str.trim().isEmpty()) {
            return;
        }
        for (String str2 : str.split(";")) {
            if (str2 != null) {
                String[] strArrSplit = str2.split(":", -1);
                if (strArrSplit.length >= 2) {
                    damageData.procs.add(new DamageEffect(DamageEffect.EffectType.valueOf(strArrSplit[0]), parseInt(strArrSplit[1], 0), 100));
                }
            }
        }
    }

    private void appendSerializedFollowerState(StringBuilder sb, String str, FollowerState followerState) {
        if (sb == null || followerState == null) {
            return;
        }
        sb.append(str).append(encode(followerState.spawnId)).append(str).append(encode(followerState.tag)).append(str).append(encode(followerState.name)).append(str).append(followerState.f3653x).append(str).append(followerState.f3654y).append(str).append(encode(followerState.spriteIndexCsv)).append(str).append(encode(followerState.spriteName)).append(str).append(encode(followerState.facingName)).append(str).append(encode(followerState.actorStateName)).append(str).append(followerState.stateTimeMs);
    }

    private void applyAuthoritativeVitals(Character character, int i2, int i3) {
        CharacterSheet characterSheet;
        CharacterStats characterStats;
        if (character == null || (characterSheet = character.sheet) == null || (characterStats = characterSheet.stats) == null) {
            return;
        }
        int i4 = i2;
        if (i4 < 0) {
            i4 = 0;
        }
        characterStats.missingHP = i4;
        int i5 = i3;
        if (i5 < 0) {
            i5 = 0;
        }
        characterStats.missingMana = i5;
    }

    private void applyCombatPacket(String str) {
        if (str == null || !str.startsWith("PACT\t")) {
            return;
        }
        String strDecode = decode(tokenAt(str, 1));
        int i2 = parseInt(tokenAt(str, 2), 0);
        String strDecode2 = decode(tokenAt(str, 3));
        int i3 = parseInt(tokenAt(str, 4), 0);
        String strDecode3 = decode(tokenAt(str, 5));
        int i4 = parseInt(tokenAt(str, 6), -1);
        int i5 = parseInt(tokenAt(str, 7), -1);
        String strDecode4 = decode(tokenAt(str, 8));
        String strDecode5 = decode(tokenAt(str, 9));
        int i6 = parseInt(tokenAt(str, 10), 0);
        int i7 = parseInt(tokenAt(str, 11), 0);
        String str2 = this.localPlayerName;
        if (str2 == null || !str2.equals(strDecode)) {
            LanGameBridge.receiveRemoteCombat(strDecode, i2, strDecode2, i3, strDecode3, i4, i5, strDecode4, strDecode5, i6, i7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0077 A[Catch: all -> 0x00db, TryCatch #0 {, blocks: (B:20:0x0069, B:22:0x006d, B:26:0x007d, B:29:0x0085, B:31:0x008f, B:33:0x0095, B:35:0x0099, B:38:0x00a4, B:40:0x00a8, B:41:0x00ac, B:37:0x009f, B:53:0x00d9, B:24:0x0077), top: B:58:0x0069 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyPlayerDamagePacket(String str) {
        Player player;
        String strTrim;
        String name;
        if (str == null || !str.startsWith("PDMG2\t")) {
            return;
        }
        String[] strArrSplit = str.split("\t", -1);
        if (strArrSplit.length >= 13) {
            String strDecode = decode(strArrSplit[1]);
            String strDecode2 = decode(strArrSplit[2]);
            int i2 = parseInt(strArrSplit[3], 0);
            int i3 = parseInt(strArrSplit[4], 0);
            int i4 = parseInt(strArrSplit[11], 0);
            int i5 = parseInt(strArrSplit[12], 0);
            GameData gameDataV = GameData.v();
            if (gameDataV == null || (player = gameDataV.player) == null) {
                return;
            }
            String str2 = gameDataV.CurrentLevel;
            if (str2 != null) {
                strTrim = str2.trim();
                if (strTrim.isEmpty()) {
                    GameData gameDataV2 = GameData.v();
                    if (gameDataV2 == null) {
                        return;
                    } else {
                        strTrim = gameDataV2.currentMapName;
                    }
                }
            }
            synchronized (this.lock) {
                String str3 = this.localPlayerName;
                if (str3 != null) {
                    name = str3.trim();
                    if (name.isEmpty()) {
                        name = player.getName();
                    }
                }
                if (name != null && strDecode.equals(name) && strTrim != null) {
                    String strTrim2 = strTrim.trim();
                    if (!strTrim2.isEmpty() && strTrim2.equals(strDecode2)) {
                        String str4 = this.lastAppliedPlayerDamageLevelId;
                        if (str4 == null || !str4.equals(strTrim2)) {
                            this.lastAppliedPlayerDamageLevelId = strTrim2;
                            this.lastAppliedPlayerDamageSeq = 0;
                        }
                        if (i2 > this.lastAppliedPlayerDamageSeq) {
                            this.lastAppliedPlayerDamageLevelId = strDecode2;
                            this.lastAppliedPlayerDamageSeq = i2;
                            String strDecode3 = decode(strArrSplit[6]);
                            if (strDecode3 == null || strDecode3.trim().isEmpty()) {
                                strDecode3 = "HIT";
                            }
                            String strDecode4 = decode(strArrSplit[5]);
                            if (strDecode4 == null) {
                                strDecode4 = "";
                            }
                            player.v(strDecode3, i3, strDecode4, buildDamageDataFromParts(strArrSplit));
                            applyAuthoritativeVitals(player, i4, i5);
                        }
                    }
                }
            }
        }
    }

    private void broadcastToClients(String str) {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.hostPeers);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ClientPeer) it.next()).send(str);
        }
    }

    private void broadcastToClientsExcept(String str, ClientPeer clientPeer) {
        ArrayList<ClientPeer> arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.hostPeers);
        }
        for (ClientPeer clientPeer2 : arrayList) {
            if (clientPeer2 != clientPeer) {
                clientPeer2.send(str);
            }
        }
    }

    private DamageData buildDamageDataFromParts(String[] strArr) {
        DamageData damageData = new DamageData();
        if (strArr != null) {
            if (parseInt(strArr[7], 0) != 0) {
                damageData.critical = true;
            }
            damageData.weapon_item_id = parseInt(strArr[8], -1);
            appendDamagesFromCsv(damageData, decode(strArr[9]));
            appendProcsFromCsv(damageData, decode(strArr[10]));
        }
        return damageData;
    }

    private String buildStateTextLocked() {
        if (this.hosting) {
            return lanString("lan_state_hosting", "Hosting") + " " + this.sessionName + " @ " + (this.hostAddress.isEmpty() ? "0.0.0.0" : this.hostAddress) + ":" + this.hostPort + " (" + this.players.size() + "/" + this.maxPlayers + ")";
        }
        if (this.connected) {
            return lanString("lan_state_connected", "Connected to") + " " + this.sessionName + " @ " + (this.hostAddress.isEmpty() ? "?" : this.hostAddress) + ":" + this.hostPort + " (" + this.players.size() + "/" + this.maxPlayers + ")";
        }
        return lanString("lan_status_offline", "LAN offline. Host a room or join by IP.");
    }

    private void clearDiscoveryLocked() {
        this.discoveryByKey.clear();
        this.discoveryResults.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeQuietly(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e2) {
            }
        }
    }

    private void closeQuietly(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeQuietly(DatagramSocket datagramSocket) {
        if (datagramSocket != null) {
            datagramSocket.close();
        }
    }

    private void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LinkedHashSet<InetAddress> collectBroadcastAddresses() {
        LinkedHashSet<InetAddress> linkedHashSet = new LinkedHashSet<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return linkedHashSet;
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement != null && networkInterfaceNextElement.isUp() && !networkInterfaceNextElement.isLoopback()) {
                    for (InterfaceAddress interfaceAddress : networkInterfaceNextElement.getInterfaceAddresses()) {
                        if (interfaceAddress != null) {
                            InetAddress broadcast = interfaceAddress.getBroadcast();
                            if (broadcast == null) {
                                broadcast = computeDirectedBroadcast(interfaceAddress);
                            }
                            if (broadcast != null) {
                                linkedHashSet.add(broadcast);
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
        }
        addHeuristicBroadcasts(linkedHashSet, findLocalIpv4());
        return linkedHashSet;
    }

    private InetAddress computeDirectedBroadcast(InterfaceAddress interfaceAddress) {
        short networkPrefixLength;
        byte[] address;
        if (interfaceAddress == null) {
            return null;
        }
        try {
            InetAddress address2 = interfaceAddress.getAddress();
            if ((address2 instanceof Inet4Address) && (networkPrefixLength = interfaceAddress.getNetworkPrefixLength()) >= 0 && networkPrefixLength <= 32 && (address = address2.getAddress()) != null && address.length == 4) {
                int i2 = ((address[0] & 255) << 24) | ((address[1] & 255) << 16) | ((address[2] & 255) << 8) | (address[3] & 255) | (~(networkPrefixLength == 0 ? 0 : (-1) << (32 - networkPrefixLength)));
                return InetAddress.getByAddress(new byte[]{(byte) (i2 >> 24), (byte) (i2 >> 16), (byte) (i2 >> 8), (byte) i2});
            }
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private FollowerState copyFollowerState(FollowerState followerState) {
        if (followerState == null) {
            return null;
        }
        FollowerState followerState2 = new FollowerState();
        followerState2.spawnId = followerState.spawnId;
        followerState2.tag = followerState.tag;
        followerState2.name = followerState.name;
        followerState2.f3653x = followerState.f3653x;
        followerState2.f3654y = followerState.f3654y;
        followerState2.spriteIndexCsv = followerState.spriteIndexCsv;
        followerState2.spriteName = followerState.spriteName;
        followerState2.facingName = followerState.facingName;
        followerState2.actorStateName = followerState.actorStateName;
        followerState2.stateTimeMs = followerState.stateTimeMs;
        return followerState2;
    }

    private PlayerState copyState(PlayerState playerState) {
        PlayerState playerState2 = new PlayerState();
        playerState2.playerName = playerState.playerName;
        playerState2.characterName = playerState.characterName;
        playerState2.currentLevelId = playerState.currentLevelId;
        playerState2.currentMapName = playerState.currentMapName;
        playerState2.slot = playerState.slot;
        playerState2.f3655x = playerState.f3655x;
        playerState2.f3656y = playerState.f3656y;
        playerState2.level = playerState.level;
        playerState2.className = playerState.className;
        playerState2.classEnumName = playerState.classEnumName;
        playerState2.raceName = playerState.raceName;
        playerState2.genderName = playerState.genderName;
        playerState2.portraitIndex = playerState.portraitIndex;
        playerState2.slotBodyItemId = playerState.slotBodyItemId;
        playerState2.slotFeetItemId = playerState.slotFeetItemId;
        playerState2.slotHandsItemId = playerState.slotHandsItemId;
        playerState2.slotHeadItemId = playerState.slotHeadItemId;
        playerState2.slotLegsItemId = playerState.slotLegsItemId;
        playerState2.slotMainhandItemId = playerState.slotMainhandItemId;
        playerState2.slotOffhandItemId = playerState.slotOffhandItemId;
        playerState2.spriteIndexCsv = playerState.spriteIndexCsv;
        playerState2.spriteName = playerState.spriteName;
        playerState2.facingName = playerState.facingName;
        playerState2.actorStateName = playerState.actorStateName;
        playerState2.stateTimeMs = playerState.stateTimeMs;
        playerState2.speedX = playerState.speedX;
        playerState2.speedY = playerState.speedY;
        playerState2.sampleTimeMs = playerState.sampleTimeMs;
        playerState2.companionSpawnId = playerState.companionSpawnId;
        playerState2.companionTag = playerState.companionTag;
        playerState2.companionName = playerState.companionName;
        playerState2.companionX = playerState.companionX;
        playerState2.companionY = playerState.companionY;
        playerState2.companionSpriteIndexCsv = playerState.companionSpriteIndexCsv;
        playerState2.companionSpriteName = playerState.companionSpriteName;
        playerState2.companionFacingName = playerState.companionFacingName;
        playerState2.companionActorStateName = playerState.companionActorStateName;
        playerState2.companionStateTimeMs = playerState.companionStateTimeMs;
        playerState2.summonSpawnId = playerState.summonSpawnId;
        playerState2.summonTag = playerState.summonTag;
        playerState2.summonName = playerState.summonName;
        playerState2.summonX = playerState.summonX;
        playerState2.summonY = playerState.summonY;
        playerState2.summonSpriteIndexCsv = playerState.summonSpriteIndexCsv;
        playerState2.summonSpriteName = playerState.summonSpriteName;
        playerState2.summonFacingName = playerState.summonFacingName;
        playerState2.summonActorStateName = playerState.summonActorStateName;
        playerState2.summonStateTimeMs = playerState.summonStateTimeMs;
        playerState2.actionSeq = playerState.actionSeq;
        playerState2.spellId = playerState.spellId;
        playerState2.spellTarget = playerState.spellTarget;
        playerState2.visualFxMask = playerState.visualFxMask;
        playerState2.stealthSkillLevel = playerState.stealthSkillLevel;
        playerState2.skillSnapshot = playerState.skillSnapshot;
        playerState2.missingHp = playerState.missingHp;
        playerState2.missingMana = playerState.missingMana;
        playerState2.combatEffectsSnapshot = playerState.combatEffectsSnapshot;
        playerState2.actionOriginX = playerState.actionOriginX;
        playerState2.actionOriginY = playerState.actionOriginY;
        playerState2.gold = playerState.gold;
        playerState2.repVarsilia = playerState.repVarsilia;
        playerState2.repMercia = playerState.repMercia;
        playerState2.repIlmara = playerState.repIlmara;
        playerState2.repWizards = playerState.repWizards;
        playerState2.repThree = playerState.repThree;
        playerState2.difficulty = playerState.difficulty;
        ArrayList<FollowerState> arrayList = playerState2.followers;
        if (arrayList != null) {
            arrayList.clear();
        } else {
            playerState2.followers = new ArrayList<>();
        }
        ArrayList<FollowerState> arrayList2 = playerState.followers;
        if (arrayList2 != null) {
            Iterator<FollowerState> it = arrayList2.iterator();
            while (it.hasNext()) {
                FollowerState followerStateCopyFollowerState = copyFollowerState(it.next());
                if (followerStateCopyFollowerState != null) {
                    playerState2.followers.add(followerStateCopyFollowerState);
                }
            }
        }
        return playerState2;
    }

    private String decode(String str) {
        return str == null ? "" : str.replace("%0D", "\r").replace("%0A", "\n").replace("%09", "\t").replace("%25", "%");
    }

    private String describeDatagramSocketState(DatagramSocket datagramSocket) {
        return datagramSocket == null ? "socket=null" : "local=" + String.valueOf(datagramSocket.getLocalSocketAddress()) + " bound=" + datagramSocket.isBound() + " closed=" + datagramSocket.isClosed();
    }

    private String describeLocalIpv4s() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                    if (networkInterfaceNextElement != null && networkInterfaceNextElement.isUp() && !networkInterfaceNextElement.isLoopback()) {
                        Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddressNextElement = inetAddresses.nextElement();
                            if ((inetAddressNextElement instanceof Inet4Address) && !inetAddressNextElement.isLoopbackAddress() && !inetAddressNextElement.isLinkLocalAddress()) {
                                if (sb.length() != 0) {
                                    sb.append(" | ");
                                }
                                String name = networkInterfaceNextElement.getName();
                                if (name == null) {
                                    name = "iface";
                                }
                                sb.append(name).append("=").append(inetAddressNextElement.getHostAddress());
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            if (sb.length() == 0) {
                sb.append("unavailable");
            }
        }
        String string = sb.toString();
        return string.isEmpty() ? "none" : string;
    }

    private String describeSocketState(Socket socket) {
        return socket == null ? "socket=null" : "local=" + String.valueOf(socket.getLocalSocketAddress()) + " remote=" + String.valueOf(socket.getRemoteSocketAddress()) + " bound=" + socket.isBound() + " connected=" + socket.isConnected() + " closed=" + socket.isClosed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchUi() {
        final UiListener uiListener;
        final String strBuildStateTextLocked;
        final ArrayList arrayList;
        final ArrayList arrayList2;
        final ArrayList arrayList3;
        synchronized (this.lock) {
            uiListener = this.uiListener;
            strBuildStateTextLocked = buildStateTextLocked();
            String str = this.statusDiag;
            if (str != null && !str.isEmpty()) {
                strBuildStateTextLocked = strBuildStateTextLocked + '\n' + str;
            }
            arrayList = new ArrayList();
            for (String str2 : this.players) {
                arrayList.add(formatPlayerLineLocked(str2, str2.equals(this.localPlayerName) ? this.localState : this.peerStates.get(str2)));
            }
            arrayList2 = new ArrayList(this.chatHistory);
            arrayList3 = new ArrayList(this.discoveryResults);
        }
        if (uiListener == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: net.fdgames.ek.android.lan.LanSessionManager.5
            @Override // java.lang.Runnable
            public void run() {
                uiListener.onStateChanged(strBuildStateTextLocked);
                uiListener.onPlayersChanged(arrayList);
                uiListener.onChatUpdated(arrayList2);
                uiListener.onDiscoveryUpdated(arrayList3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String encode(String str) {
        return str == null ? "" : str.replace("%", "%25").replace("\t", "%09").replace("\n", "%0A").replace("\r", "%0D");
    }

    private String findLocalIpv4() {
        String hostAddress;
        String str = null;
        String str2 = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return "0.0.0.0";
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement != null && networkInterfaceNextElement.isUp() && !networkInterfaceNextElement.isLoopback()) {
                    boolean zIsLikelyVpnInterface = isLikelyVpnInterface(networkInterfaceNextElement);
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if ((inetAddressNextElement instanceof Inet4Address) && !inetAddressNextElement.isLoopbackAddress() && (hostAddress = inetAddressNextElement.getHostAddress()) != null) {
                            if (zIsLikelyVpnInterface) {
                                return hostAddress;
                            }
                            if (inetAddressNextElement.isSiteLocalAddress()) {
                                if (str == null) {
                                    str = hostAddress;
                                }
                            } else if (str2 == null) {
                                str2 = hostAddress;
                            }
                        }
                    }
                }
            }
            return str != null ? str : str2 != null ? str2 : "0.0.0.0";
        } catch (Exception e2) {
            return "0.0.0.0";
        }
    }

    private String firstStackFrame(Throwable th) {
        StackTraceElement[] stackTrace;
        return (th == null || (stackTrace = th.getStackTrace()) == null || stackTrace.length <= 0) ? "none" : String.valueOf(stackTrace[0]);
    }

    private String formatPlayerLineLocked(String str, PlayerState playerState) {
        if (playerState == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (playerState.level > 0) {
            sb.append(pt() ? " - Nv " : " - Lv ").append(playerState.level);
        }
        if (playerState.className != null && !playerState.className.isEmpty()) {
            sb.append(" - ").append(playerState.className);
        }
        if (playerState.currentLevelId != null && !playerState.currentLevelId.isEmpty()) {
            sb.append(" - ").append(playerState.currentLevelId);
        }
        sb.append(" - G ").append(playerState.gold);
        return sb.toString();
    }

    public static synchronized LanSessionManager get(Context context) {
        if (instance == null) {
            instance = new LanSessionManager(context.getApplicationContext());
        }
        return instance;
    }

    public static synchronized LanSessionManager getInstanceIfReady() {
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDiscoveryReply(InetAddress inetAddress, String str) {
        if (inetAddress == null || str == null || !str.startsWith("EK_HOST\t")) {
            return;
        }
        String[] strArrSplit = str.split("\t", -1);
        if (strArrSplit.length < 5) {
            return;
        }
        String strDecode = decode(strArrSplit[1]);
        int i2 = parseInt(strArrSplit[2], CHAT_PORT);
        int i3 = parseInt(strArrSplit[3], 1);
        int i4 = parseInt(strArrSplit[4], 6);
        String str2 = inetAddress.getHostAddress() + ":" + i2;
        synchronized (this.lock) {
            this.discoveryByKey.put(str2, new DiscoveryResult(strDecode, str2, i2, i3, i4));
            while (this.discoveryByKey.size() > 20) {
                this.discoveryByKey.remove(this.discoveryByKey.keySet().iterator().next());
            }
            rebuildDiscoveryListLocked();
        }
        showLanDiag("scan reply=" + str2 + " name=" + strDecode);
        dispatchUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIncomingClient(Socket socket) throws Throwable {
        PrintWriter printWriter;
        ClientPeer clientPeer;
        Throwable th;
        BufferedReader bufferedReader;
        ClientPeer clientPeer2;
        IOException e2;
        String line;
        String strResolveChatDisplayNameLocked;
        GameData gameDataV;
        GameVariables gameVariables;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                try {
                    printWriter = new PrintWriter((Writer) new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                    try {
                        socket.setTcpNoDelay(true);
                        socket.setReceiveBufferSize(MetadataChangeSet.INDEXABLE_TEXT_SIZE_LIMIT_BYTES);
                        socket.setSendBufferSize(MetadataChangeSet.INDEXABLE_TEXT_SIZE_LIMIT_BYTES);
                        socket.setTrafficClass(16);
                        line = bufferedReader.readLine();
                    } catch (IOException e3) {
                        clientPeer2 = null;
                        e2 = e3;
                    } catch (Throwable th2) {
                        clientPeer = null;
                        th = th2;
                        if (clientPeer != null) {
                            removeHostPeer(clientPeer, true);
                        } else {
                            closeQuietly(printWriter);
                            closeQuietly(bufferedReader);
                            closeQuietly(socket);
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    clientPeer2 = null;
                    e2 = e4;
                    printWriter = null;
                } catch (Throwable th3) {
                    clientPeer = null;
                    th = th3;
                    printWriter = null;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e5) {
            printWriter = null;
            clientPeer2 = null;
            e2 = e5;
            bufferedReader = null;
        } catch (Throwable th5) {
            printWriter = null;
            clientPeer = null;
            th = th5;
            bufferedReader = null;
        }
        if (line != null && line.startsWith("JOIN\t")) {
            String strDecode = decode(tokenAt(line, 1));
            synchronized (this.lock) {
                try {
                    if (this.players.size() >= this.maxPlayers) {
                        sendLine(printWriter, "CLOSE\t" + encode(pt() ? "Sala cheia." : "Room is full."));
                        closeQuietly(printWriter);
                        closeQuietly(bufferedReader);
                        closeQuietly(socket);
                        return;
                    }
                    String strMakeUniquePlayerNameLocked = makeUniquePlayerNameLocked(sanitizePlayerName(strDecode));
                    clientPeer2 = new ClientPeer(socket, printWriter, strMakeUniquePlayerNameLocked);
                    try {
                        this.hostPeers.add(clientPeer2);
                        this.players.add(strMakeUniquePlayerNameLocked);
                        addSystemLineLocked((pt() ? new StringBuilder().append(strMakeUniquePlayerNameLocked).append(" entrou na sala.") : new StringBuilder().append(strMakeUniquePlayerNameLocked).append(" joined the room.")).toString());
                        try {
                            LanGameBridge.postGameLog("[CYAN]LAN[] " + strMakeUniquePlayerNameLocked + (pt() ? " entrou na sessao." : " joined the LAN session."));
                            StringBuilder sbAppend = new StringBuilder().append("WELCOME\t").append(encode(this.sessionName)).append("\t").append(encode(clientPeer2.playerName));
                            sbAppend.append("\t2");
                            clientPeer2.send(sbAppend.toString());
                            pushPlayersToClients();
                            pushStateToClients();
                            pushStateSnapshotToClients();
                            dispatchUi();
                            while (true) {
                                String line2 = bufferedReader.readLine();
                                if (line2 == null) {
                                    break;
                                }
                                if (line2.startsWith("CHAT\t")) {
                                    String strSanitizeChat = sanitizeChat(decode(tokenAt(line2, 1)));
                                    if (!strSanitizeChat.isEmpty()) {
                                        synchronized (this.lock) {
                                            strResolveChatDisplayNameLocked = resolveChatDisplayNameLocked(clientPeer2.playerName);
                                            addChatLineLocked(strResolveChatDisplayNameLocked, strSanitizeChat);
                                            incrementUnreadChatLocked();
                                        }
                                        postChatAlert(strResolveChatDisplayNameLocked, strSanitizeChat);
                                        if (strSanitizeChat.contains("eliminated") && strSanitizeChat.contains("[PVP]") && (gameDataV = GameData.v()) != null && (gameVariables = gameDataV.gameVariables) != null) {
                                            gameVariables.e(1, "pvp_arena_won");
                                        }
                                        broadcastToClients("CHAT\t" + encode(clientPeer2.playerName) + "\t" + encode(strSanitizeChat));
                                        dispatchUi();
                                    }
                                } else if (line2.startsWith("PSTATE\t")) {
                                    PlayerState state = parseState(line2);
                                    if (state != null) {
                                        synchronized (this.lock) {
                                            LinkedHashMap<String, PlayerState> linkedHashMap = this.peerStates;
                                            String str = clientPeer2.playerName;
                                            state.playerName = str;
                                            linkedHashMap.put(str, state);
                                        }
                                        broadcastToClientsExcept(serializeState(state), clientPeer2);
                                        dispatchUi();
                                    } else {
                                        continue;
                                    }
                                } else if (line2.startsWith("PACT\t")) {
                                    receiveCombatPacket(line2);
                                    broadcastToClientsExcept(line2, clientPeer2);
                                } else if (line2.startsWith("NPCSTATE2\t")) {
                                    synchronized (this.lock) {
                                        this.lastNpcStateData = line2;
                                    }
                                    broadcastToClientsExcept(line2, clientPeer2);
                                } else if ("LEAVE".equals(line2)) {
                                    break;
                                }
                            }
                        } catch (IOException e6) {
                            e2 = e6;
                            if (!isSocketClosedException(e2)) {
                                toast((pt() ? "Conexao LAN perdida: " : "LAN connection lost: ") + safeMessage(e2));
                                LanGameBridge.postGameLog("[CYAN]LAN[] HOST " + (pt() ? "saiu da sessao." : "left the LAN session."));
                            }
                            if (clientPeer2 == null) {
                                closeQuietly(printWriter);
                                closeQuietly(bufferedReader);
                                closeQuietly(socket);
                                return;
                            }
                        }
                        removeHostPeer(clientPeer2, true);
                        return;
                    } catch (Throwable th6) {
                        th = th6;
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }
        }
        sendLine(printWriter, "CLOSE\t" + encode(pt() ? "Entrada LAN invalida." : "Invalid LAN join."));
        closeQuietly(printWriter);
        closeQuietly(bufferedReader);
        closeQuietly(socket);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleServerMessage(String str) {
        boolean zEquals;
        String strResolveChatDisplayNameLocked;
        GameData gameDataV;
        GameVariables gameVariables;
        if (str.startsWith("WELCOME\t")) {
            synchronized (this.lock) {
                this.sessionName = decode(tokenAt(str, 1));
                String strTrim = decode(tokenAt(str, 2)).trim();
                if (!strTrim.isEmpty()) {
                    this.localPlayerName = strTrim;
                }
            }
            dispatchUi();
            return;
        }
        if (str.startsWith("STATE\t")) {
            this.maxPlayers = parseInt(tokenAt(str, 2), 6);
            dispatchUi();
            return;
        }
        if (str.startsWith("PLAYERS\t")) {
            String[] strArrSplit = str.split("\t", -1);
            synchronized (this.lock) {
                this.players.clear();
                ArrayList arrayList = new ArrayList();
                for (int i2 = 1; i2 < strArrSplit.length; i2++) {
                    String strDecode = decode(strArrSplit[i2]);
                    if (!strDecode.isEmpty()) {
                        this.players.add(strDecode);
                        arrayList.add(strDecode);
                    }
                }
                this.peerStates.keySet().retainAll(arrayList);
            }
            dispatchUi();
            return;
        }
        if (str.startsWith("CHAT\t")) {
            String strDecode2 = decode(tokenAt(str, 1));
            String strDecode3 = decode(tokenAt(str, 2));
            synchronized (this.lock) {
                String str2 = this.localPlayerName;
                zEquals = str2 != null ? str2.equals(strDecode2) : false;
                strResolveChatDisplayNameLocked = resolveChatDisplayNameLocked(strDecode2);
                addChatLineLocked(strResolveChatDisplayNameLocked, strDecode3);
                if (!zEquals) {
                    incrementUnreadChatLocked();
                }
            }
            if (!zEquals) {
                postChatAlert(strResolveChatDisplayNameLocked, strDecode3);
                if (strDecode3.contains("eliminated") && strDecode3.contains("[PVP]") && (gameDataV = GameData.v()) != null && (gameVariables = gameDataV.gameVariables) != null) {
                    gameVariables.e(1, "pvp_arena_won");
                }
            }
            dispatchUi();
            return;
        }
        if (str.startsWith("PSTATE\t")) {
            PlayerState state = parseState(str);
            if (state == null || state.playerName.equals(this.localPlayerName)) {
                return;
            }
            synchronized (this.lock) {
                this.peerStates.put(state.playerName, state);
            }
            dispatchUi();
            return;
        }
        if (str.startsWith("PACT\t")) {
            receiveCombatPacket(str);
            return;
        }
        if (str.startsWith("PDMG2\t")) {
            receivePlayerDamagePacket(str);
            return;
        }
        if (str.startsWith("SYSTEM\t")) {
            synchronized (this.lock) {
                addSystemLineLocked(decode(tokenAt(str, 1)));
            }
            dispatchUi();
            return;
        }
        if (str.startsWith("CLOSE\t")) {
            String strDecode4 = decode(tokenAt(str, 1));
            synchronized (this.lock) {
                addSystemLineLocked(strDecode4);
            }
            dispatchUi();
            toast(strDecode4);
            stopAllInternal(false, false);
            return;
        }
        if (str.startsWith("ERROR\t")) {
            str = decode(tokenAt(str, 1));
            toast(str);
        }
        if (str.startsWith("NPCSTATE2\t") || str.startsWith("NPCSTATE\t")) {
            this.lastNpcStateData = str;
        }
    }

    private void incrementUnreadChatLocked() {
        this.unreadChatCount++;
    }

    private boolean isLikelyVpnInterface(NetworkInterface networkInterface) {
        if (networkInterface == null) {
            return false;
        }
        try {
            if (!networkInterface.isVirtual() && !isLikelyVpnLabel(networkInterface.getName())) {
                if (!isLikelyVpnLabel(networkInterface.getDisplayName())) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private boolean isLikelyVpnLabel(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("zerotier") || lowerCase.contains("wireguard") || lowerCase.contains("tailscale") || lowerCase.contains("utun") || lowerCase.contains("tun") || lowerCase.contains("tap") || lowerCase.contains("vpn") || lowerCase.contains("ppp") || lowerCase.contains("wg") || lowerCase.contains("zt");
    }

    private boolean isLocalPlayerName(String str) {
        boolean zEquals;
        if (str == null) {
            return false;
        }
        synchronized (this.lock) {
            String str2 = this.localPlayerName;
            zEquals = str2 != null ? str2.equals(str) : false;
        }
        return zEquals;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSocketClosedException(IOException iOException) {
        String lowerCase = safeMessage(iOException).toLowerCase(Locale.US);
        return lowerCase.contains("closed") || lowerCase.contains("socket");
    }

    private String lanString(String str, String str2) {
        int identifier;
        String string;
        Context context = this.appContext;
        return (context == null || (identifier = context.getResources().getIdentifier(str, "string", context.getPackageName())) == 0 || (string = context.getString(identifier)) == null) ? str2 : string;
    }

    private void logLanDebug(String str) {
        LanGameBridge.postGameLog(str);
        Log.d("EK-LAN", str);
    }

    private void logLanError(String str, Throwable th) {
        LanGameBridge.postGameLog(str);
        Log.e("EK-LAN", str, th);
    }

    private String makeUniquePlayerNameLocked(String str) {
        String strSanitizePlayerName = sanitizePlayerName(str);
        if (!this.players.contains(strSanitizePlayerName)) {
            return strSanitizePlayerName;
        }
        for (int i2 = 2; i2 <= 99; i2++) {
            String str2 = strSanitizePlayerName + " " + i2;
            if (!this.players.contains(str2)) {
                return str2;
            }
        }
        return strSanitizePlayerName + " " + System.currentTimeMillis();
    }

    private float parseFloat(String str, float f2) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e2) {
            return f2;
        }
    }

    private FollowerState parseFollowerState(String[] strArr, int i2) {
        if (strArr.length < i2 + 10) {
            return null;
        }
        FollowerState followerState = new FollowerState();
        followerState.spawnId = decode(strArr[i2]);
        followerState.tag = decode(strArr[i2 + 1]);
        followerState.name = decode(strArr[i2 + 2]);
        followerState.f3653x = parseInt(strArr[i2 + 3], -1);
        followerState.f3654y = parseInt(strArr[i2 + 4], -1);
        followerState.spriteIndexCsv = decode(strArr[i2 + 5]);
        followerState.spriteName = decode(strArr[i2 + 6]);
        followerState.facingName = decode(strArr[i2 + 7]);
        followerState.actorStateName = decode(strArr[i2 + 8]);
        followerState.stateTimeMs = parseInt(strArr[i2 + 9], 0);
        return followerState;
    }

    private int parseInt(String str, int i2) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e2) {
            return i2;
        }
    }

    private long parseLong(String str, long j2) {
        try {
            return Long.parseLong(str);
        } catch (Exception e2) {
            return j2;
        }
    }

    private PlayerState parseState(String str) {
        int i2;
        String[] strArrSplit = str.split("\t", -1);
        if (strArrSplit.length < 29) {
            return null;
        }
        PlayerState playerState = new PlayerState();
        playerState.playerName = decode(strArrSplit[1]);
        playerState.currentLevelId = decode(strArrSplit[2]);
        playerState.currentMapName = decode(strArrSplit[3]);
        playerState.slot = parseInt(strArrSplit[4], 0);
        playerState.f3655x = parseInt(strArrSplit[5], 0);
        playerState.f3656y = parseInt(strArrSplit[6], 0);
        playerState.level = parseInt(strArrSplit[7], 0);
        playerState.className = decode(strArrSplit[8]);
        playerState.gold = parseInt(strArrSplit[9], 0);
        playerState.repVarsilia = parseInt(strArrSplit[10], 0);
        playerState.repMercia = parseInt(strArrSplit[11], 0);
        playerState.repIlmara = parseInt(strArrSplit[12], 0);
        playerState.repWizards = parseInt(strArrSplit[13], 0);
        playerState.repThree = parseInt(strArrSplit[14], 0);
        playerState.classEnumName = decode(strArrSplit[15]);
        playerState.raceName = decode(strArrSplit[16]);
        playerState.genderName = decode(strArrSplit[17]);
        playerState.portraitIndex = parseInt(strArrSplit[18], 0);
        playerState.spriteIndexCsv = decode(strArrSplit[19]);
        playerState.spriteName = decode(strArrSplit[20]);
        playerState.facingName = decode(strArrSplit[21]);
        playerState.actorStateName = decode(strArrSplit[22]);
        playerState.stateTimeMs = parseInt(strArrSplit[23], 0);
        playerState.characterName = decode(strArrSplit[24]);
        playerState.actionSeq = parseInt(strArrSplit[25], 0);
        playerState.spellId = decode(strArrSplit[26]);
        playerState.actionOriginX = parseInt(strArrSplit[27], 0);
        playerState.actionOriginY = parseInt(strArrSplit[28], 0);
        if (strArrSplit.length > 29) {
            playerState.speedX = parseFloat(strArrSplit[29], 0.0f);
        }
        if (strArrSplit.length > 30) {
            playerState.speedY = parseFloat(strArrSplit[30], 0.0f);
        }
        if (strArrSplit.length > 31) {
            playerState.sampleTimeMs = parseLong(strArrSplit[31], 0L);
        }
        if (strArrSplit.length > 32) {
            playerState.summonSpawnId = decode(strArrSplit[32]);
        }
        if (strArrSplit.length > 33) {
            playerState.summonTag = decode(strArrSplit[33]);
        }
        if (strArrSplit.length > 34) {
            playerState.summonName = decode(strArrSplit[34]);
        }
        if (strArrSplit.length > 35) {
            playerState.summonX = parseInt(strArrSplit[35], -1);
        }
        if (strArrSplit.length > 36) {
            playerState.summonY = parseInt(strArrSplit[36], -1);
        }
        if (strArrSplit.length > 37) {
            playerState.summonSpriteIndexCsv = decode(strArrSplit[37]);
        }
        if (strArrSplit.length > 38) {
            playerState.summonSpriteName = decode(strArrSplit[38]);
        }
        if (strArrSplit.length > 39) {
            playerState.summonFacingName = decode(strArrSplit[39]);
        }
        if (strArrSplit.length > 40) {
            playerState.summonActorStateName = decode(strArrSplit[40]);
        }
        if (strArrSplit.length > 41) {
            playerState.summonStateTimeMs = parseInt(strArrSplit[41], 0);
        }
        if (strArrSplit.length > 42) {
            playerState.companionSpawnId = decode(strArrSplit[42]);
        }
        if (strArrSplit.length > 43) {
            playerState.companionTag = decode(strArrSplit[43]);
        }
        if (strArrSplit.length > 44) {
            playerState.companionName = decode(strArrSplit[44]);
        }
        if (strArrSplit.length > 45) {
            playerState.companionX = parseInt(strArrSplit[45], -1);
        }
        if (strArrSplit.length > 46) {
            playerState.companionY = parseInt(strArrSplit[46], -1);
        }
        if (strArrSplit.length > 47) {
            playerState.companionSpriteIndexCsv = decode(strArrSplit[47]);
        }
        if (strArrSplit.length > 48) {
            playerState.companionSpriteName = decode(strArrSplit[48]);
        }
        if (strArrSplit.length > 49) {
            playerState.companionFacingName = decode(strArrSplit[49]);
        }
        if (strArrSplit.length > 50) {
            playerState.companionActorStateName = decode(strArrSplit[50]);
        }
        if (strArrSplit.length > 51) {
            playerState.companionStateTimeMs = parseInt(strArrSplit[51], 0);
        }
        int i3 = 52;
        if (strArrSplit.length > 52) {
            int i4 = parseInt(strArrSplit[52], 0);
            i3 = 52 + 1;
            if (i4 > 0) {
                while (i4 > 0) {
                    FollowerState followerState = parseFollowerState(strArrSplit, i3);
                    if (followerState == null) {
                        break;
                    }
                    playerState.followers.add(followerState);
                    i3 += 10;
                    i4--;
                }
            }
        }
        if (strArrSplit.length > i3 && (i2 = parseInt(strArrSplit[i3], 0)) > 0) {
            i3++;
            if (i2 >= 1 && strArrSplit.length > i3) {
                playerState.slotBodyItemId = parseInt(strArrSplit[i3], -1);
                i3++;
                if (i2 >= 2 && strArrSplit.length > i3) {
                    playerState.slotFeetItemId = parseInt(strArrSplit[i3], -1);
                    i3++;
                    if (i2 >= 3 && strArrSplit.length > i3) {
                        playerState.slotHandsItemId = parseInt(strArrSplit[i3], -1);
                        i3++;
                        if (i2 >= 4 && strArrSplit.length > i3) {
                            playerState.slotHeadItemId = parseInt(strArrSplit[i3], -1);
                            i3++;
                            if (i2 >= 5 && strArrSplit.length > i3) {
                                playerState.slotLegsItemId = parseInt(strArrSplit[i3], -1);
                                i3++;
                                if (i2 >= 6 && strArrSplit.length > i3) {
                                    playerState.slotMainhandItemId = parseInt(strArrSplit[i3], -1);
                                    i3++;
                                    if (i2 >= 7 && strArrSplit.length > i3) {
                                        playerState.slotOffhandItemId = parseInt(strArrSplit[i3], -1);
                                        i3++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (strArrSplit.length > i3) {
            playerState.visualFxMask = parseInt(strArrSplit[i3], 0);
        }
        int i5 = i3 + 1;
        if (strArrSplit.length > i5) {
            playerState.stealthSkillLevel = parseInt(strArrSplit[i5], 0);
        }
        int i6 = i5 + 1;
        if (strArrSplit.length > i6) {
            playerState.skillSnapshot = decode(strArrSplit[i6]);
        }
        int i7 = i6 + 1;
        if (strArrSplit.length > i7) {
            playerState.missingHp = parseInt(strArrSplit[i7], 0);
        }
        int i8 = i7 + 1;
        if (strArrSplit.length > i8) {
            playerState.missingMana = parseInt(strArrSplit[i8], 0);
        }
        int i9 = i8 + 1;
        if (strArrSplit.length > i9) {
            playerState.combatEffectsSnapshot = decode(strArrSplit[i9]);
        }
        String str2 = playerState.currentLevelId;
        if (str2 == null || str2.trim().isEmpty()) {
            playerState.currentLevelId = playerState.currentMapName;
        }
        String str3 = playerState.currentMapName;
        if (str3 == null || str3.trim().isEmpty()) {
            playerState.currentMapName = playerState.currentLevelId;
        }
        String str4 = playerState.characterName;
        if (str4 == null || str4.trim().isEmpty()) {
            playerState.characterName = playerState.playerName;
        }
        String str5 = playerState.playerName;
        if (str5 == null || str5.trim().isEmpty()) {
            return null;
        }
        return playerState;
    }

    private void postChatAlert(String str, String str2) {
        if (str2 == null || str2.trim().isEmpty()) {
            return;
        }
        LanGameBridge.postGameLog("[CYAN]LAN[] " + str + ": " + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean pt() {
        return Locale.getDefault().getLanguage().toLowerCase(Locale.US).startsWith("pt");
    }

    private void pushPlayersToClients() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.players);
        }
        StringBuilder sb = new StringBuilder("PLAYERS");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append('\t').append(encode((String) it.next()));
        }
        broadcastToClients(sb.toString());
    }

    private void pushStateSnapshotToClients() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList();
            PlayerState playerState = this.localState;
            if (playerState != null) {
                arrayList.add(serializeState(playerState));
            }
            Iterator<PlayerState> it = this.peerStates.values().iterator();
            while (it.hasNext()) {
                arrayList.add(serializeState(it.next()));
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            broadcastToClients((String) it2.next());
        }
    }

    private void pushStateToClients() {
        int size;
        int i2;
        synchronized (this.lock) {
            size = this.players.size();
            i2 = this.maxPlayers;
        }
        broadcastToClients("STATE\t" + size + "\t" + i2);
    }

    private void queueCombatPacket(String str) {
        if (str == null) {
            return;
        }
        synchronized (this.lock) {
            this.pendingCombatPackets.add(str);
        }
    }

    private void queuePlayerDamagePacket(String str) {
        if (str == null) {
            return;
        }
        synchronized (this.lock) {
            this.pendingPlayerDamagePackets.add(str);
        }
    }

    private void rebuildDiscoveryListLocked() {
        this.discoveryResults.clear();
        this.discoveryResults.addAll(this.discoveryByKey.values());
    }

    private void receiveCombatPacket(String str) {
        if (str == null || !str.startsWith("PACT\t")) {
            return;
        }
        queueCombatPacket(str);
    }

    private void receivePlayerDamagePacket(String str) {
        if (str == null || !str.startsWith("PDMG2\t")) {
            return;
        }
        broadcastToClients(str);
        queuePlayerDamagePacket(str);
    }

    private void removeHostPeer(ClientPeer clientPeer, boolean z2) {
        boolean z3;
        synchronized (this.lock) {
            if (this.hostPeers.remove(clientPeer)) {
                this.players.remove(clientPeer.playerName);
                this.peerStates.remove(clientPeer.playerName);
                if (z2) {
                    addSystemLineLocked((pt() ? new StringBuilder().append(clientPeer.playerName).append(" saiu da sala.") : new StringBuilder().append(clientPeer.playerName).append(" left the room.")).toString());
                }
                z3 = true;
            } else {
                z3 = false;
            }
        }
        clientPeer.close();
        if (z3) {
            LanGameBridge.postGameLog("[CYAN]LAN[] " + clientPeer.playerName + (pt() ? " saiu da sessao." : " left the LAN session."));
            pushPlayersToClients();
            pushStateToClients();
            pushStateSnapshotToClients();
            dispatchUi();
        }
    }

    private String resolveChatDisplayNameLocked(String str) {
        if (str == null) {
            return "";
        }
        PlayerState playerState = str.equals(this.localPlayerName) ? this.localState : this.peerStates.get(str);
        if (playerState != null) {
            String str2 = playerState.characterName;
            if (str2 != null && !str2.trim().isEmpty()) {
                return str2;
            }
            String str3 = playerState.playerName;
            return (str3 == null || str3.trim().isEmpty()) ? str : str3;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InetAddress resolvePreferredIpv4Address() {
        try {
            String strFindLocalIpv4 = findLocalIpv4();
            if (strFindLocalIpv4 != null && !strFindLocalIpv4.isEmpty() && !"0.0.0.0".equals(strFindLocalIpv4)) {
                InetAddress byName = InetAddress.getByName(strFindLocalIpv4);
                if (byName instanceof Inet4Address) {
                    if (!byName.isAnyLocalAddress()) {
                        return byName;
                    }
                }
            }
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String safeMessage(Throwable th) {
        if (th == null) {
            return "";
        }
        String simpleName = th.getClass().getSimpleName();
        String message = th.getMessage();
        return (message == null || message.trim().isEmpty()) ? simpleName : simpleName + ": " + message;
    }

    private String sanitizeChat(String str) {
        String strReplace = (str == null ? "" : str.trim()).replace('\n', ' ').replace('\r', ' ').replace('\t', ' ');
        return strReplace.length() > 180 ? strReplace.substring(0, 180).trim() : strReplace;
    }

    private String sanitizePlayerName(String str) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.isEmpty()) {
            strTrim = pt() ? "Jogador" : "Player";
        }
        String strReplace = strTrim.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ');
        if (strReplace.length() > 20) {
            strReplace = strReplace.substring(0, 20).trim();
        }
        return strReplace.isEmpty() ? pt() ? "Jogador" : "Player" : strReplace;
    }

    private void sendLine(PrintWriter printWriter, String str) {
        if (printWriter == null || str == null) {
            return;
        }
        printWriter.print(str);
        printWriter.print('\n');
        printWriter.flush();
    }

    private String serializeState(PlayerState playerState) {
        if (playerState == null) {
            return "PSTATE";
        }
        StringBuilder sbAppend = new StringBuilder().append("PSTATE\t").append(encode(playerState.playerName)).append("\t").append(encode(playerState.currentLevelId)).append("\t").append(encode(playerState.currentMapName)).append("\t").append(playerState.slot).append("\t").append(playerState.f3655x).append("\t").append(playerState.f3656y).append("\t").append(playerState.level).append("\t").append(encode(playerState.className)).append("\t").append(playerState.gold).append("\t").append(playerState.repVarsilia).append("\t").append(playerState.repMercia).append("\t").append(playerState.repIlmara).append("\t").append(playerState.repWizards).append("\t").append(playerState.repThree).append("\t").append(encode(playerState.classEnumName)).append("\t").append(encode(playerState.raceName)).append("\t").append(encode(playerState.genderName)).append("\t").append(playerState.portraitIndex).append("\t").append(encode(playerState.spriteIndexCsv)).append("\t").append(encode(playerState.spriteName)).append("\t").append(encode(playerState.facingName)).append("\t").append(encode(playerState.actorStateName)).append("\t").append(playerState.stateTimeMs).append("\t").append(encode(playerState.characterName)).append("\t").append(playerState.actionSeq).append("\t").append(encode(playerState.spellId)).append("\t").append(playerState.actionOriginX).append("\t").append(playerState.actionOriginY).append("\t").append(playerState.speedX).append("\t").append(playerState.speedY).append("\t").append(playerState.sampleTimeMs).append("\t").append(encode(playerState.summonSpawnId)).append("\t").append(encode(playerState.summonTag)).append("\t").append(encode(playerState.summonName)).append("\t").append(playerState.summonX).append("\t").append(playerState.summonY).append("\t").append(encode(playerState.summonSpriteIndexCsv)).append("\t").append(encode(playerState.summonSpriteName)).append("\t").append(encode(playerState.summonFacingName)).append("\t").append(encode(playerState.summonActorStateName)).append("\t").append(playerState.summonStateTimeMs).append("\t").append(encode(playerState.companionSpawnId)).append("\t").append(encode(playerState.companionTag)).append("\t").append(encode(playerState.companionName)).append("\t").append(playerState.companionX).append("\t").append(playerState.companionY).append("\t").append(encode(playerState.companionSpriteIndexCsv)).append("\t").append(encode(playerState.companionSpriteName)).append("\t").append(encode(playerState.companionFacingName)).append("\t").append(encode(playerState.companionActorStateName)).append("\t").append(playerState.companionStateTimeMs);
        ArrayList<FollowerState> arrayList = playerState.followers;
        int size = arrayList != null ? arrayList.size() : 0;
        StringBuilder sbAppend2 = sbAppend.append("\t").append(size);
        if (size > 0) {
            Iterator<FollowerState> it = playerState.followers.iterator();
            while (it.hasNext()) {
                appendSerializedFollowerState(sbAppend2, "\t", it.next());
            }
        }
        StringBuilder sbAppend3 = sbAppend2.append("\t").append(7).append("\t").append(playerState.slotBodyItemId).append("\t").append(playerState.slotFeetItemId).append("\t").append(playerState.slotHandsItemId).append("\t").append(playerState.slotHeadItemId).append("\t").append(playerState.slotLegsItemId).append("\t").append(playerState.slotMainhandItemId).append("\t").append(playerState.slotOffhandItemId).append("\t").append(playerState.visualFxMask).append("\t").append(playerState.stealthSkillLevel).append("\t").append(encode(playerState.skillSnapshot)).append("\t").append(playerState.missingHp).append("\t").append(playerState.missingMana).append("\t");
        sbAppend3.append(encode(playerState.combatEffectsSnapshot));
        return sbAppend3.toString();
    }

    private void setStatusDiag(String str) {
        String strTrim;
        if (str == null) {
            strTrim = "";
        } else {
            strTrim = str.trim();
            if (!strTrim.isEmpty()) {
                if (!strTrim.startsWith("LAN DIAG ")) {
                    strTrim = "LAN DIAG " + strTrim;
                }
                if (strTrim.length() > 450) {
                    strTrim = strTrim.substring(0, 450);
                }
            }
        }
        synchronized (this.lock) {
            this.statusDiag = strTrim;
        }
        dispatchUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLanDiag(String str) {
        if (str == null) {
            return;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return;
        }
        if (!strTrim.startsWith("LAN DIAG ")) {
            strTrim = "LAN DIAG " + strTrim;
        }
        if (strTrim.length() > 450) {
            strTrim = strTrim.substring(0, 450);
        }
        logLanDebug(strTrim);
        String str2 = strTrim;
        synchronized (this.lock) {
            String str3 = this.statusDiag;
            String strSubstring = (str3 == null || str3.isEmpty()) ? str2 : str3.equals(str2) ? str3 : str3 + '\n' + str2;
            int length = strSubstring.length();
            if (length > 720) {
                strSubstring = strSubstring.substring(length - 720);
            }
            this.statusDiag = strSubstring;
            addSystemLineLocked(str2);
        }
        dispatchUi();
    }

    private void startAcceptThread(final ServerSocket serverSocket) {
        Thread thread = new Thread(new Runnable() { // from class: net.fdgames.ek.android.lan.LanSessionManager.2
            @Override // java.lang.Runnable
            public void run() {
                while (!serverSocket.isClosed()) {
                    try {
                        final Socket socketAccept = serverSocket.accept();
                        socketAccept.setTcpNoDelay(true);
                        LanSessionManager.this.showLanDiag("host accepted local=" + String.valueOf(socketAccept.getLocalSocketAddress()) + " remote=" + String.valueOf(socketAccept.getRemoteSocketAddress()));
                        Thread thread2 = new Thread(new Runnable() { // from class: net.fdgames.ek.android.lan.LanSessionManager.2.1
                            @Override // java.lang.Runnable
                            public void run() throws Throwable {
                                LanSessionManager.this.handleIncomingClient(socketAccept);
                            }
                        }, "ek-lan-client-" + socketAccept.getInetAddress().getHostAddress());
                        thread2.setDaemon(true);
                        thread2.start();
                    } catch (IOException e2) {
                        if (serverSocket.isClosed()) {
                            return;
                        }
                        LanSessionManager.this.toast((LanSessionManager.this.pt() ? "Falha no servidor LAN: " : "LAN server error: ") + LanSessionManager.this.safeMessage(e2));
                        return;
                    }
                }
            }
        }, "ek-lan-accept");
        thread.setDaemon(true);
        synchronized (this.lock) {
            this.acceptThread = thread;
        }
        thread.start();
    }

    private void startClientReader(final Socket socket) {
        Thread thread = new Thread(new Runnable() { // from class: net.fdgames.ek.android.lan.LanSessionManager.4
            /* JADX WARN: Removed duplicated region for block: B:61:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() throws Throwable {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader = null;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = null;
                    LanSessionManager.this.closeQuietly(bufferedReader);
                    LanSessionManager.this.closeQuietly(socket);
                    synchronized (LanSessionManager.this.lock) {
                    }
                }
                while (true) {
                    try {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            } else {
                                LanSessionManager.this.handleServerMessage(line);
                            }
                        } catch (IOException e3) {
                            e = e3;
                            if (!LanSessionManager.this.isSocketClosedException(e)) {
                                LanSessionManager.this.toast((LanSessionManager.this.pt() ? "Conexao com o host perdida: " : "Connection to host lost: ") + LanSessionManager.this.safeMessage(e));
                            }
                            LanSessionManager.this.closeQuietly(bufferedReader);
                            LanSessionManager.this.closeQuietly(socket);
                            synchronized (LanSessionManager.this.lock) {
                                LanSessionManager.this.clientSocket = null;
                                LanSessionManager.this.clientWriter = null;
                                LanSessionManager.this.hosting = false;
                                LanSessionManager.this.connected = false;
                                LanSessionManager.this.players.clear();
                                LanSessionManager.this.peerStates.clear();
                                LanSessionManager.this.localState = null;
                            }
                        }
                        LanSessionManager.this.dispatchUi();
                    } catch (Throwable th2) {
                        th = th2;
                        LanSessionManager.this.closeQuietly(bufferedReader);
                        LanSessionManager.this.closeQuietly(socket);
                        synchronized (LanSessionManager.this.lock) {
                            LanSessionManager.this.clientSocket = null;
                            LanSessionManager.this.clientWriter = null;
                            LanSessionManager.this.hosting = false;
                            LanSessionManager.this.connected = false;
                            LanSessionManager.this.players.clear();
                            LanSessionManager.this.peerStates.clear();
                            LanSessionManager.this.localState = null;
                        }
                        LanSessionManager.this.dispatchUi();
                        throw th;
                    }
                }
                LanSessionManager.this.toast(LanSessionManager.this.pt() ? "Conexao com o host encerrada." : "Connection to host closed.");
                LanSessionManager.this.closeQuietly(bufferedReader);
                LanSessionManager.this.closeQuietly(socket);
                synchronized (LanSessionManager.this.lock) {
                    LanSessionManager.this.clientSocket = null;
                    LanSessionManager.this.clientWriter = null;
                    LanSessionManager.this.hosting = false;
                    LanSessionManager.this.connected = false;
                    LanSessionManager.this.players.clear();
                    LanSessionManager.this.peerStates.clear();
                    LanSessionManager.this.localState = null;
                }
                LanSessionManager.this.dispatchUi();
            }
        }, "ek-lan-client-reader");
        thread.setDaemon(true);
        synchronized (this.lock) {
            this.clientReadThread = thread;
        }
        thread.start();
    }

    private void startDiscoveryResponder() throws SocketException {
        try {
            final DatagramSocket datagramSocket = new DatagramSocket((SocketAddress) null);
            datagramSocket.setReuseAddress(true);
            datagramSocket.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), DISCOVERY_PORT));
            datagramSocket.setBroadcast(true);
            datagramSocket.setReuseAddress(true);
            synchronized (this.lock) {
                this.hostDiscoverySocket = datagramSocket;
            }
            showLanDiag("host udp=" + String.valueOf(datagramSocket.getLocalSocketAddress()) + " preferred=" + this.hostAddress);
            Thread thread = new Thread(new Runnable() { // from class: net.fdgames.ek.android.lan.LanSessionManager.3
                @Override // java.lang.Runnable
                public void run() {
                    String str;
                    byte[] bArr = new byte[GL20.GL_NEVER];
                    while (!datagramSocket.isClosed()) {
                        DatagramPacket datagramPacket = new DatagramPacket(bArr, GL20.GL_NEVER);
                        try {
                            datagramSocket.receive(datagramPacket);
                            if (new String(datagramPacket.getData(), 0, datagramPacket.getLength(), "UTF-8").startsWith(LanSessionManager.DISCOVERY_MAGIC)) {
                                synchronized (LanSessionManager.this.lock) {
                                    str = "EK_HOST\t" + LanSessionManager.this.encode(LanSessionManager.this.sessionName) + "\t" + LanSessionManager.this.hostPort + "\t" + LanSessionManager.this.players.size() + "\t" + LanSessionManager.this.maxPlayers;
                                }
                                byte[] bytes = str.getBytes("UTF-8");
                                datagramSocket.send(new DatagramPacket(bytes, bytes.length, datagramPacket.getAddress(), datagramPacket.getPort()));
                            }
                        } catch (IOException e2) {
                            if (datagramSocket.isClosed()) {
                                return;
                            }
                            LanSessionManager.this.toast((LanSessionManager.this.pt() ? "Falha na descoberta LAN: " : "LAN discovery error: ") + LanSessionManager.this.safeMessage(e2));
                            return;
                        }
                    }
                }
            }, "ek-lan-discovery-host");
            thread.setDaemon(true);
            synchronized (this.lock) {
                this.hostDiscoveryThread = thread;
            }
            thread.start();
        } catch (SocketException e2) {
            String str = "DISCOVERY responder unavailable localIps=" + describeLocalIpv4s() + " reason=" + safeMessage(e2);
            logLanError(str);
            showLanDiag(str);
        }
    }

    private void stopAllInternal(boolean z2, boolean z3) {
        ServerSocket serverSocket;
        DatagramSocket datagramSocket;
        Socket socket;
        PrintWriter printWriter;
        ArrayList<ClientPeer> arrayList;
        synchronized (this.lock) {
            this.stopRequested = true;
            serverSocket = this.hostServerSocket;
            this.hostServerSocket = null;
            datagramSocket = this.hostDiscoverySocket;
            this.hostDiscoverySocket = null;
            socket = this.clientSocket;
            this.clientSocket = null;
            printWriter = this.clientWriter;
            this.clientWriter = null;
            arrayList = new ArrayList(this.hostPeers);
            this.hostPeers.clear();
            this.hosting = false;
            this.connected = false;
            if (z3) {
                addSystemLineLocked(pt() ? "Sessao LAN encerrada." : "LAN session closed.");
            }
            this.players.clear();
            this.peerStates.clear();
            this.pendingCombatPackets.clear();
            this.pendingPlayerDamagePackets.clear();
            this.localState = null;
            this.lastNpcStateData = null;
            this.lastAppliedPlayerDamageSeq = 0;
            this.nextPlayerDamageSeq = 1;
            this.lastAppliedPlayerDamageLevelId = "";
            if (z2) {
                clearDiscoveryLocked();
            }
            this.sessionName = "";
            this.hostAddress = "";
            this.hostPort = CHAT_PORT;
        }
        if (printWriter != null) {
            try {
                sendLine(printWriter, "LEAVE");
            } catch (Exception e2) {
            }
        }
        for (ClientPeer clientPeer : arrayList) {
            try {
                clientPeer.send("CLOSE\t" + encode(pt() ? "Host encerrou a sala." : "Host closed the room."));
            } catch (Exception e3) {
            }
            clientPeer.close();
        }
        closeQuietly(socket);
        closeQuietly(serverSocket);
        closeQuietly(datagramSocket);
        dispatchUi();
        if (z3) {
            LanGameBridge.postGameLog("[CYAN]LAN[] " + (pt() ? "Sessao LAN encerrada." : "LAN session closed."));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toast(final String str) {
        final UiListener uiListener;
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        synchronized (this.lock) {
            uiListener = this.uiListener;
        }
        if (uiListener == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: net.fdgames.ek.android.lan.LanSessionManager.6
            @Override // java.lang.Runnable
            public void run() {
                uiListener.onToast(str);
            }
        });
    }

    private String tokenAt(String str, int i2) {
        String[] strArrSplit = str.split("\t", -1);
        return strArrSplit.length > i2 ? strArrSplit[i2] : "";
    }

    public void broadcastNpcState(String str) {
        if (str != null) {
            if (!this.hosting) {
                sendNpcStateToHost(str);
                return;
            }
            broadcastToClients("NPCSTATE2\t" + str);
        }
    }

    public void flushPendingCombatPackets() {
        synchronized (this.lock) {
            ArrayList<String> arrayList = this.pendingCombatPackets;
            if (arrayList.isEmpty()) {
                return;
            }
            ArrayList arrayList2 = new ArrayList(this.pendingCombatPackets);
            arrayList.clear();
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                applyCombatPacket((String) it.next());
            }
        }
    }

    public void flushPendingPlayerDamagePackets() {
        synchronized (this.lock) {
            ArrayList<String> arrayList = this.pendingPlayerDamagePackets;
            if (arrayList.isEmpty()) {
                return;
            }
            ArrayList arrayList2 = new ArrayList(this.pendingPlayerDamagePackets);
            arrayList.clear();
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                applyPlayerDamagePacket((String) it.next());
            }
        }
    }

    public List<String> getChatSnapshot() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.chatHistory);
        }
        return arrayList;
    }

    public List<DiscoveryResult> getDiscoverySnapshot() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.discoveryResults);
        }
        return arrayList;
    }

    public String getLastNpcStateData() {
        return this.lastNpcStateData;
    }

    public String getLocalPlayerName() {
        String str;
        synchronized (this.lock) {
            str = this.localPlayerName;
        }
        return str;
    }

    public List<PlayerState> getPeerStatesSnapshot() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.peerStates.values());
        }
        return arrayList;
    }

    public int getPlayerCount() {
        return this.players.size();
    }

    public List<String> getPlayersSnapshot() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList();
            for (String str : this.players) {
                arrayList.add(formatPlayerLineLocked(str, str.equals(this.localPlayerName) ? this.localState : this.peerStates.get(str)));
            }
        }
        return arrayList;
    }

    public String getStateText() {
        String strBuildStateTextLocked;
        synchronized (this.lock) {
            strBuildStateTextLocked = buildStateTextLocked();
            String str = this.statusDiag;
            if (str != null && !str.isEmpty()) {
                strBuildStateTextLocked = strBuildStateTextLocked + '\n' + str;
            }
        }
        return strBuildStateTextLocked;
    }

    public int getUnreadChatCount() {
        int i2;
        synchronized (this.lock) {
            i2 = this.unreadChatCount;
        }
        return i2;
    }

    public boolean isHosting() {
        return this.hosting;
    }

    public boolean isInSession() {
        return this.hosting || this.connected;
    }

    public boolean isSessionRunning() {
        boolean z2;
        synchronized (this.lock) {
            z2 = this.hosting || this.connected;
        }
        return z2;
    }

    public void joinHost(String str, String str2, int i2) {
        int i3;
        String strSanitizePlayerName = sanitizePlayerName(str);
        String strTrim = str2 == null ? "" : str2.trim();
        if (strTrim.isEmpty()) {
            toast(pt() ? "Informe um IP valido." : "Enter a valid IP.");
            return;
        }
        int iLastIndexOf = strTrim.lastIndexOf(58);
        if (iLastIndexOf > 0 && iLastIndexOf == strTrim.indexOf(58) && iLastIndexOf < strTrim.length() - 1 && (i3 = parseInt(strTrim.substring(iLastIndexOf + 1), CHAT_PORT)) > 0 && i3 <= 65535) {
            i2 = i3;
            strTrim = strTrim.substring(0, iLastIndexOf).trim();
            if (strTrim.isEmpty()) {
                toast(pt() ? "Informe um IP valido." : "Enter a valid IP.");
                return;
            }
        }
        stopAllInternal(false, false);
        synchronized (this.lock) {
            this.stopRequested = false;
            this.hosting = false;
            this.statusDiag = "";
            this.connected = true;
            this.localPlayerName = strSanitizePlayerName;
            this.hostAddress = strTrim;
            if (i2 <= 0) {
                i2 = CHAT_PORT;
            }
            this.hostPort = i2;
            this.sessionName = strTrim;
            this.players.clear();
            this.players.add(this.localPlayerName);
            this.chatHistory.clear();
            this.unreadChatCount = 0;
            this.peerStates.clear();
            this.localState = null;
            addSystemLineLocked((pt() ? "Conectando em " : "Connecting to ") + strTrim + ":" + this.hostPort + "...");
        }
        dispatchUi();
        logLanDebug("JOIN attempt target=" + strTrim + ":" + this.hostPort + " timeoutMs=10000 localIps=" + describeLocalIpv4s());
        Socket socket = new Socket();
        showLanDiag("tcp pre-connect target=" + strTrim + ":" + this.hostPort + " socket=" + describeSocketState(socket));
        try {
            socket.connect(new InetSocketAddress(strTrim, this.hostPort), 10000);
            socket.setTcpNoDelay(true);
            socket.setReceiveBufferSize(65536);
            socket.setSendBufferSize(65536);
            socket.setTrafficClass(16);
            showLanDiag("tcp connected target=" + this.hostAddress + ":" + this.hostPort + " socket=" + describeSocketState(socket));
            PrintWriter printWriter = new PrintWriter((Writer) new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
            synchronized (this.lock) {
                this.clientSocket = socket;
                this.clientWriter = printWriter;
            }
            sendLine(printWriter, "JOIN\t" + encode(strSanitizePlayerName));
            startClientReader(socket);
            toast(lanString("lan_toast_connected_room", "Connected to LAN room."));
            LanGameBridge.postGameLog("[CYAN]LAN[] " + lanString("lan_log_connected_session", "Connected to a LAN session."));
        } catch (IOException e2) {
            String str3 = "tcp connect failed target=" + this.hostAddress + ":" + this.hostPort + " socket=" + describeSocketState(socket) + " error=" + safeMessage(e2) + " first=" + firstStackFrame(e2);
            showLanDiag(str3);
            closeQuietly(socket);
            String str4 = "JOIN fail target=" + this.hostAddress + ":" + this.hostPort + " localIps=" + describeLocalIpv4s() + " reason=" + safeMessage(e2);
            logLanError(str4, e2);
            setStatusDiag(str3);
            synchronized (this.lock) {
                addSystemLineLocked(str4);
                dispatchUi();
                toast(lanString("lan_error_join_prefix", "Unable to join room: ") + safeMessage(e2));
                stopAllInternal(false, false);
            }
        }
    }

    public void markChatRead() {
        synchronized (this.lock) {
            this.unreadChatCount = 0;
        }
    }

    public void publishCombatAction(String str, int i2, String str2, int i3, String str3, int i4, int i5, String str4, String str5, int i6, int i7) {
        boolean z2;
        PrintWriter printWriter;
        boolean z3;
        if (str == null || str2 == null) {
            return;
        }
        String str6 = "PACT\t" + encode(str) + "\t" + i2 + "\t" + encode(str2) + "\t" + i3 + "\t" + encode(str3) + "\t" + i4 + "\t" + i5 + "\t" + encode(str4) + "\t" + encode(str5) + "\t" + i6 + "\t" + i7;
        synchronized (this.lock) {
            z2 = false;
            printWriter = null;
            z3 = false;
            if (this.hosting) {
                z2 = true;
            } else if (!this.connected || (printWriter = this.clientWriter) == null) {
                z3 = true;
            }
        }
        if (z3) {
            return;
        }
        if (z2) {
            broadcastToClients(str6);
        } else if (printWriter != null) {
            sendLine(printWriter, str6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void publishLiveState(PlayerState playerState) {
        String strSerializeState;
        PrintWriter printWriter;
        boolean z2;
        if (playerState != null) {
            if (playerState.playerName == null || playerState.playerName.trim().isEmpty()) {
                String str = this.localPlayerName;
                if (str != null) {
                    String strTrim = str.trim();
                    if (strTrim.isEmpty()) {
                        String str2 = playerState.characterName;
                        if (str2 == null) {
                            return;
                        }
                        String strTrim2 = str2.trim();
                        if (strTrim2.isEmpty()) {
                            return;
                        } else {
                            playerState.playerName = strTrim2;
                        }
                    } else {
                        playerState.playerName = strTrim;
                    }
                }
            }
            synchronized (this.lock) {
                this.localState = copyState(playerState);
                strSerializeState = serializeState(this.localState);
                printWriter = null;
                if (this.hosting) {
                    z2 = true;
                } else if (!this.connected || this.clientWriter == null) {
                    z2 = false;
                } else {
                    printWriter = this.clientWriter;
                    z2 = false;
                }
            }
            dispatchUi();
            if (z2) {
                broadcastToClients(strSerializeState);
            } else if (printWriter != null) {
                sendLine(printWriter, strSerializeState);
            }
        }
    }

    public void publishPlayerDamage(String str, String str2, int i2, String str3, String str4, int i3, int i4, String str5, String str6, int i5, int i6) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        synchronized (this.lock) {
            if (this.hosting) {
                int i7 = this.nextPlayerDamageSeq;
                this.nextPlayerDamageSeq = i7 + 1;
                StringBuilder sbAppend = new StringBuilder().append("PDMG2\t").append(encode(str)).append("\t").append(encode(str2)).append("\t").append(i7).append("\t").append(i2).append("\t").append(encode(str3)).append("\t").append(encode(str4)).append("\t").append(i3).append("\t").append(i4).append("\t").append(encode(str5)).append("\t").append(encode(str6)).append("\t").append(i5).append("\t");
                sbAppend.append(i6);
                String string = sbAppend.toString();
                if (this.hosting) {
                    broadcastToClients(string);
                    return;
                }
                PrintWriter printWriter = this.clientWriter;
                if (printWriter != null) {
                    sendLine(printWriter, string);
                }
            }
        }
    }

    public void scanLan(String str) {
        Thread thread;
        synchronized (this.lock) {
            this.localPlayerName = sanitizePlayerName(str);
            clearDiscoveryLocked();
        }
        dispatchUi();
        synchronized (this.lock) {
            thread = this.scanThread;
        }
        if (thread != null && thread.isAlive()) {
            toast(pt() ? "A busca LAN ja esta em andamento." : "LAN scan is already running.");
            return;
        }
        Thread thread2 = new Thread(new Runnable() { // from class: net.fdgames.ek.android.lan.LanSessionManager.1
            /* JADX WARN: Removed duplicated region for block: B:78:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() throws Throwable {
                DatagramSocket datagramSocket;
                try {
                    InetAddress inetAddressResolvePreferredIpv4Address = LanSessionManager.this.resolvePreferredIpv4Address();
                    if (inetAddressResolvePreferredIpv4Address != null) {
                        DatagramSocket datagramSocket2 = new DatagramSocket((SocketAddress) null);
                        datagramSocket2.setReuseAddress(true);
                        datagramSocket2.bind(new InetSocketAddress(inetAddressResolvePreferredIpv4Address, 0));
                        datagramSocket = datagramSocket2;
                    } else {
                        datagramSocket = new DatagramSocket();
                    }
                    try {
                        try {
                            datagramSocket.setBroadcast(true);
                            datagramSocket.setReuseAddress(true);
                            datagramSocket.setSoTimeout(1500);
                            LanSessionManager.this.showLanDiag("scan udp=" + String.valueOf(datagramSocket.getLocalSocketAddress()));
                            byte[] bytes = "EK_DISCOVER\t1".getBytes("UTF-8");
                            LinkedHashSet linkedHashSetCollectBroadcastAddresses = LanSessionManager.this.collectBroadcastAddresses();
                            linkedHashSetCollectBroadcastAddresses.add(InetAddress.getByName("255.255.255.255"));
                            Iterator it = linkedHashSetCollectBroadcastAddresses.iterator();
                            int i2 = 0;
                            while (it.hasNext()) {
                                try {
                                    datagramSocket.send(new DatagramPacket(bytes, bytes.length, (InetAddress) it.next(), LanSessionManager.DISCOVERY_PORT));
                                    i2++;
                                } catch (Exception e2) {
                                }
                            }
                            LanSessionManager.this.showLanDiag("scan probes=" + i2 + " udp=" + String.valueOf(datagramSocket.getLocalSocketAddress()));
                            long jCurrentTimeMillis = System.currentTimeMillis() + 6000;
                            while (System.currentTimeMillis() < jCurrentTimeMillis) {
                                DatagramPacket datagramPacket = new DatagramPacket(new byte[GL20.GL_NEVER], GL20.GL_NEVER);
                                try {
                                    datagramSocket.receive(datagramPacket);
                                    LanSessionManager.this.handleDiscoveryReply(datagramPacket.getAddress(), new String(datagramPacket.getData(), 0, datagramPacket.getLength(), "UTF-8"));
                                } catch (SocketException e3) {
                                } catch (IOException e4) {
                                }
                            }
                            LanSessionManager.this.closeQuietly(datagramSocket);
                            synchronized (LanSessionManager.this.lock) {
                                if (Thread.currentThread() == LanSessionManager.this.scanThread) {
                                    LanSessionManager.this.scanThread = null;
                                }
                            }
                        } catch (Exception e5) {
                            e = e5;
                            LanSessionManager.this.toast((LanSessionManager.this.pt() ? "Falha ao buscar salas LAN: " : "Failed to scan LAN rooms: ") + LanSessionManager.this.safeMessage(e));
                            LanSessionManager.this.closeQuietly(datagramSocket);
                            synchronized (LanSessionManager.this.lock) {
                                if (Thread.currentThread() == LanSessionManager.this.scanThread) {
                                    LanSessionManager.this.scanThread = null;
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        LanSessionManager.this.closeQuietly(datagramSocket);
                        synchronized (LanSessionManager.this.lock) {
                            if (Thread.currentThread() == LanSessionManager.this.scanThread) {
                                LanSessionManager.this.scanThread = null;
                            }
                        }
                        LanSessionManager.this.dispatchUi();
                        throw th;
                    }
                } catch (Exception e6) {
                    e = e6;
                    datagramSocket = null;
                } catch (Throwable th2) {
                    th = th2;
                    datagramSocket = null;
                    LanSessionManager.this.closeQuietly(datagramSocket);
                    synchronized (LanSessionManager.this.lock) {
                    }
                }
                LanSessionManager.this.dispatchUi();
            }
        }, "ek-lan-scan");
        thread2.setDaemon(true);
        synchronized (this.lock) {
            this.scanThread = thread2;
        }
        thread2.start();
    }

    public void sendChat(String str) {
        PrintWriter printWriter;
        boolean z2;
        String strSanitizeChat = sanitizeChat(str);
        if (strSanitizeChat.isEmpty()) {
            return;
        }
        synchronized (this.lock) {
            printWriter = null;
            if (this.hosting) {
                String strResolveChatDisplayNameLocked = resolveChatDisplayNameLocked(this.localPlayerName);
                addChatLineLocked(strResolveChatDisplayNameLocked, strSanitizeChat);
                postChatAlert(strResolveChatDisplayNameLocked, strSanitizeChat);
                z2 = true;
            } else if (!this.connected || this.clientWriter == null) {
                z2 = false;
            } else {
                printWriter = this.clientWriter;
                z2 = false;
            }
        }
        if (z2) {
            broadcastToClients("CHAT\t" + encode(this.localPlayerName) + "\t" + encode(strSanitizeChat));
            dispatchUi();
        } else if (printWriter != null) {
            sendLine(printWriter, "CHAT\t" + encode(strSanitizeChat));
        } else {
            toast(lanString("lan_error_send_join_first", "Join a LAN room before sending messages."));
        }
    }

    public void sendChatAsync(String str) {
        Thread thread = new Thread(new LanSessionManagerChatSendRunnable(this, str), "ek-lan-chat-send");
        thread.setDaemon(true);
        thread.start();
    }

    public void sendNpcStateToHost(String str) {
        PrintWriter printWriter;
        if (str == null || this.hosting || !this.connected || (printWriter = this.clientWriter) == null) {
            return;
        }
        sendLine(printWriter, "NPCSTATE2\t" + str);
    }

    public void setUiListener(UiListener uiListener) {
        synchronized (this.lock) {
            this.uiListener = uiListener;
        }
        dispatchUi();
    }

    public void startHosting(String str, int i2) {
        String strSanitizePlayerName = sanitizePlayerName(str);
        stopAllInternal(false, false);
        synchronized (this.lock) {
            this.stopRequested = false;
            this.hosting = true;
            this.connected = false;
            this.statusDiag = "";
            this.localPlayerName = strSanitizePlayerName;
            this.sessionName = (pt() ? new StringBuilder().append("Sala de ").append(strSanitizePlayerName) : new StringBuilder().append(strSanitizePlayerName).append("'s Room")).toString();
            this.hostPort = CHAT_PORT;
            this.maxPlayers = Math.max(2, Math.min(6, i2));
            this.players.clear();
            this.players.add(this.localPlayerName);
            this.chatHistory.clear();
            this.unreadChatCount = 0;
            this.peerStates.clear();
            this.localState = null;
            addSystemLineLocked(pt() ? "Sala LAN criada. Ate 6 jogadores podem entrar." : "LAN room created. Up to 6 players can join.");
            clearDiscoveryLocked();
        }
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            InetAddress inetAddressResolvePreferredIpv4Address = resolvePreferredIpv4Address();
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), CHAT_PORT));
            synchronized (this.lock) {
                this.hostServerSocket = serverSocket;
                this.hostAddress = inetAddressResolvePreferredIpv4Address != null ? inetAddressResolvePreferredIpv4Address.getHostAddress() : findLocalIpv4();
            }
            showLanDiag("host tcp=" + String.valueOf(serverSocket.getLocalSocketAddress()) + " preferred=" + this.hostAddress + " localIps=" + describeLocalIpv4s());
            startAcceptThread(serverSocket);
            startDiscoveryResponder();
            showLanDiag("host tcp=" + String.valueOf(serverSocket.getLocalSocketAddress()) + " udp=" + describeDatagramSocketState(this.hostDiscoverySocket) + " preferred=" + this.hostAddress + " localIps=" + describeLocalIpv4s());
            dispatchUi();
            logLanDebug("HOST ready port=" + this.hostPort + " bind=0.0.0.0 preferred=" + this.hostAddress + " localIps=" + describeLocalIpv4s());
            toast(pt() ? "Sala LAN hospedada com sucesso." : "LAN room hosted successfully.");
            LanGameBridge.postGameLog("[CYAN]LAN[] " + (pt() ? "Sessao LAN hospedada." : "LAN session hosted."));
        } catch (IOException e2) {
            String str2 = "HOST fail port=" + this.hostPort + " preferred=" + this.hostAddress + " localIps=" + describeLocalIpv4s() + " reason=" + safeMessage(e2);
            logLanError(str2, e2);
            setStatusDiag(str2);
            synchronized (this.lock) {
                addSystemLineLocked(str2);
                dispatchUi();
                toast(lanString("lan_error_host_prefix", "Unable to host room: ") + safeMessage(e2));
                stopAllInternal(false, false);
            }
        }
    }

    public void stopAll() {
        stopAllInternal(true, true);
    }
}
