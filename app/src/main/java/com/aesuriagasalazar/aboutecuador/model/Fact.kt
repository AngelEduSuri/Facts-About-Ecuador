package com.aesuriagasalazar.aboutecuador.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Fact(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
)