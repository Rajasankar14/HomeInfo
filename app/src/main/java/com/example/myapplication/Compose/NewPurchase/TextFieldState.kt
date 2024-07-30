package com.example.myapplication.Compose.NewPurchase

import androidx.compose.runtime.MutableState


sealed class TextFieldState(
    var value: MutableState<String>,
    var showError: MutableState<Boolean>,
    val errorText: MutableState<String>
)

data class ProductNameState(
    val productNameValue: MutableState<String>,
    val isShowProductNameError: MutableState<Boolean>,
    val productNameError: MutableState<String>
) : TextFieldState(productNameValue, isShowProductNameError, productNameError)

data class ProductQtyState(
    val productQtyValueText: MutableState<String>,
    val showProductQtyError: MutableState<Boolean>,
    val productQtyErrorText: MutableState<String>
) : TextFieldState(productQtyValueText, showProductQtyError, productQtyErrorText)

data class ProductPriceState(
    val productPriceValueText: MutableState<String>,
    val showProductPriceValueError: MutableState<Boolean>,
    val productPriceErrorText: MutableState<String>
) : TextFieldState(productPriceValueText, showProductPriceValueError, productPriceErrorText)


data class ProductCategoryState(
    val productCategoryValueText: MutableState<String>,
    val showCategoryValueError: MutableState<Boolean>,
    val productCategoryErrorText: MutableState<String>
) : TextFieldState(productCategoryValueText, showCategoryValueError, productCategoryErrorText)

data class ProductPurchasedByState(
    val productPurchasedByValueText: MutableState<String>,
    val showPurchasedByValueError: MutableState<Boolean>,
    val productPurchasedErrorText: MutableState<String>
) : TextFieldState(productPurchasedByValueText, showPurchasedByValueError, productPurchasedErrorText)

data class ProductPurchasedDate(
    val productPurchasedDateValueText: MutableState<String>,
    val showPurchasedDateValueError: MutableState<Boolean>,
    val productPurchasedDateErrorText: MutableState<String>
) : TextFieldState(productPurchasedDateValueText, showPurchasedDateValueError, productPurchasedDateErrorText)

data class ProductExpiryDate(
    val productExpiryDateValueText: MutableState<String>,
    val showExpiryDateValueError: MutableState<Boolean>,
    val productExpiryDateErrorText: MutableState<String>
) : TextFieldState(productExpiryDateValueText, showExpiryDateValueError, productExpiryDateErrorText)