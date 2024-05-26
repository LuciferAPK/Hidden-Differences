package com.cyrus.hidden_differences.enums

enum class GameViewType(val viewType: Int, val nameGame: String) {
    TAB_TYPE(viewType = 0, nameGame = "tab"),
    TAP_NUMBER_TYPE(viewType = 1, nameGame = "number"),
    TAP_TEXT_TYPE(viewType = 2, nameGame = "text"),
    TAP_IMAGE_TYPE(viewType = 3, nameGame = "image"),
    TAP_FRUIT_TYPE(viewType = 4, nameGame = "fruit"),
    TAP_ULTRA_HARD_TYPE(viewType = 5, nameGame = "ultra_hard")
}