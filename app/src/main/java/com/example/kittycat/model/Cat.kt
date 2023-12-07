import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * [Affirmation] is the data class to represent the Affirmation text and imageResourceId
 */
data class Cat(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
