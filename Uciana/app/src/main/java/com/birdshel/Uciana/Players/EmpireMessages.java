package com.birdshel.Uciana.Players;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.R;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EmpireMessages {
    public static final int NEGATIVE = 0;
    public static final int NEUTRAL = 1;
    public static final int POSITIVE = 2;

    public static String getAskForPeaceMessage(int i, int i2) {
        return GameData.activity.getString(Functions.getResId("empire_message_propose_peace_empire_" + i, R.string.class));
    }

    public static String getBreakTreatyMessage(int i, int i2, String str) {
        return GameData.activity.getString(Functions.getResId("empire_message_break_treaty_empire_" + i, R.string.class), new Object[]{str});
    }

    public static String getDeclareWarMessage(int i) {
        return GameData.activity.getString(Functions.getResId("empire_message_war_declared_empire_" + i, R.string.class));
    }

    public static String getDeclaredWarOnMessage(int i) {
        return GameData.activity.getString(Functions.getResId("empire_message_declare_war_empire_" + i, R.string.class));
    }

    public static String getDiplomaticSceneGreeting(int i, int i2) {
        String str;
        if (i2 == 0) {
            str = "empire_message_diplomatic_greeting_negative_empire_" + i;
        } else if (i2 == 1) {
            str = "empire_message_diplomatic_greeting_neutral_empire_" + i;
        } else if (i2 != 2) {
            str = "";
        } else {
            str = "empire_message_diplomatic_greeting_positive_empire_" + i;
        }
        return GameData.activity.getString(Functions.getResId(str, R.string.class));
    }

    public static String getFirstContactMessage(int i, int i2) {
        String str;
        if (i2 == 0) {
            str = "empire_message_first_contact_negative_empire_" + i;
        } else if (i2 == 1) {
            str = "empire_message_first_contact_neutral_empire_" + i;
        } else if (i2 != 2) {
            str = "";
        } else {
            str = "empire_message_first_contact_positive_empire_" + i;
        }
        return GameData.activity.getString(Functions.getResId(str, R.string.class));
    }

    public static String getGiftMessage(int i, int i2) {
        String str;
        if (i2 == 0) {
            str = "empire_message_gift_message_negative_empire_" + i;
        } else if (i2 == 1) {
            str = "empire_message_gift_message_neutral_empire_" + i;
        } else if (i2 != 2) {
            str = "";
        } else {
            str = "empire_message_gift_message_positive_empire_" + i;
        }
        return GameData.activity.getString(Functions.getResId(str, R.string.class));
    }

    public static String getProposalAcceptedMessage(int i, int i2) {
        String str;
        if (i2 == 0) {
            str = "empire_message_proposal_accept_negative_empire_" + i;
        } else if (i2 == 1) {
            str = "empire_message_proposal_accept_neutral_empire_" + i;
        } else if (i2 != 2) {
            str = "";
        } else {
            str = "empire_message_proposal_accept_positive_empire_" + i;
        }
        return GameData.activity.getString(Functions.getResId(str, R.string.class));
    }

    public static String getProposalAcceptedResponseMessage(int i, int i2) {
        return GameData.activity.getString(Functions.getResId("empire_message_proposal_accepted_empire_" + i, R.string.class));
    }

    public static String getProposalRejectedMessage(int i, int i2) {
        String str;
        if (i2 == 0) {
            str = "empire_message_proposal_reject_negative_empire_" + i;
        } else if (i2 == 1) {
            str = "empire_message_proposal_reject_neutral_empire_" + i;
        } else if (i2 != 2) {
            str = "";
        } else {
            str = "empire_message_proposal_reject_positive_empire_" + i;
        }
        return GameData.activity.getString(Functions.getResId(str, R.string.class));
    }

    public static String getProposalRejectedResponseMessage(int i, int i2) {
        return GameData.activity.getString(Functions.getResId("empire_message_proposal_rejected_empire_" + i, R.string.class));
    }

    public static String getProposeTreatyMessage(int i, int i2, String str) {
        return GameData.activity.getString(Functions.getResId("empire_message_propose_treaty_empire_" + i, R.string.class), new Object[]{str});
    }
}
