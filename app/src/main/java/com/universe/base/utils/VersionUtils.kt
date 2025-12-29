package com.universe.base.utils

import android.os.Build

object VersionUtils {
    /** Android 25 - Nougat MR1 (7.1) */
    fun hasNougatMR(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

    /** Android 26 - Oreo (8.0) */
    fun hasOreo(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    /** Android 27 - Oreo MR1 (8.1) */
    fun hasOreoMR1(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

    /** Android 28 - Pie (9) */
    fun hasPie(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    /** Android 29 - Q (10) */
    fun hasQ(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    /** Android 30 - R (11) */
    fun hasR(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    /** Android 31 - S (12) */
    fun hasS(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    /** Android 32 - S_v2 (12L) */
    fun hasSV2(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2

    /** Android 33 - Tiramisu (13) */
    fun hasTiramisu(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    /** Android 34 - Upside Down Cake (Android 14) */
    fun hasUpsideDownCake(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

    /** Android 35 - Vanilla Ice Cream (Android 15) */
    fun hasVanillaIceCream(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM
}