package net.fdgames.ek.android.lan; public class LanSessionManager {
 public static class PlayerState { public String playerName; public int x; public int y; }
 public static LanSessionManager getInstanceIfReady() { return null; }
 public static LanSessionManager get(android.content.Context c) { return null; } public boolean isHosting() { return false; } public boolean ekConnected() { return false; } public void startHosting(String n, int max) {} public void stopAll() {}
 public boolean isInSession() { return false; } public void sendChat(String s) {} public void sendChatAsync(String s) {} public boolean isSessionRunning() { return false; } public int getPlayerCount() { return 0; } public int getUnreadChatCount() { return 0; } }
