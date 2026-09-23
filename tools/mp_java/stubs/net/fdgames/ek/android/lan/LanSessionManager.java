package net.fdgames.ek.android.lan; public class LanSessionManager {
 public static class PlayerState { public String playerName; public int x; public int y; }
 public static LanSessionManager getInstanceIfReady() { return null; }
 public boolean isInSession() { return false; } public int getPlayerCount() { return 0; } public int getUnreadChatCount() { return 0; } }
