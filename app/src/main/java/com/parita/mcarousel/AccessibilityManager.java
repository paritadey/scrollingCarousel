package com.parita.mcarousel;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

public class AccessibilityManager {
    private static boolean isAccessibilityEnabled;
    private static boolean AccessibilityRuleOnlyCheck;

    public static void setAccessibility(Boolean isEnabled) {
        AccessibilityManager.isAccessibilityEnabled = isEnabled;
    }

    public static boolean getAccessibilityEnabled() {
        return isAccessibilityEnabled;
    }

    public static void addAccessInfoRoleDesc(View v, String RoleDesc) {
        if (v != null && RoleDesc != null && !RoleDesc.isEmpty()) {
            ViewCompat.setAccessibilityDelegate(v, new AccessibilityDelegateCompat() {
                @Override
                public void onInitializeAccessibilityNodeInfo(@NonNull View host, @NonNull AccessibilityNodeInfoCompat info) {
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setRoleDescription(RoleDesc);
                }
            });
        }
    }

    public static void addAccessInfoContentDesc(View v, String ContentDesc) {
        if (v != null && ContentDesc != null && !ContentDesc.isEmpty()) {
            ViewCompat.setAccessibilityDelegate(v, new AccessibilityDelegateCompat() {
                @Override
                public void onInitializeAccessibilityNodeInfo(@NonNull View host, @NonNull AccessibilityNodeInfoCompat info) {
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setContentDescription(ContentDesc);
                }
            });
        }
    }

    public static void setAccessAnnouncement(View v, String announcement) {
        if (v != null && announcement != null && !announcement.isEmpty()) {
            v.announceForAccessibility(announcement);
        }
    }

    public static boolean getAccessibilityEnabledRuleOnlyCheck() {
        return AccessibilityRuleOnlyCheck;
    }
}

