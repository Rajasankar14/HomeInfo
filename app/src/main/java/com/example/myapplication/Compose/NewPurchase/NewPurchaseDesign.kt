package com.example.myapplication.Compose.NewPurchase

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.compose.AppTheme
import com.example.myapplication.R
import com.example.myapplication.newPurchase.NewPurchaseProduct
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewPurchaseDesign(onBackPress : () -> Unit,
                      newPurchaseViewModel: NewPurchaseViewModel) {
    val context = LocalContext.current
    val materialWhite= Color(0xFFF7F6F4)


    val productNameState = remember {
        ProductNameState(
            productNameValue = mutableStateOf(""),
            isShowProductNameError = mutableStateOf(false),
            productNameError = mutableStateOf("Please enter Product Name")
        )
    }

    val productQtyState = remember {
        ProductQtyState(
            productQtyValueText = mutableStateOf(""),
            showProductQtyError = mutableStateOf(false),
            productQtyErrorText = mutableStateOf("Please enter Purchase qty")
        )
    }

    val productPriceState = remember {
        ProductPriceState(
            productPriceValueText = mutableStateOf(""),
            showProductPriceValueError = mutableStateOf(false),
            productPriceErrorText =mutableStateOf( "Please enter Purchase price")
        )
    }

    val productCategoryState  = remember {
        ProductCategoryState(
            productCategoryValueText = mutableStateOf(""),
            showCategoryValueError = mutableStateOf(false),
            productCategoryErrorText = mutableStateOf("Please Select Category Name")
        )
    }


    val productPurchasedByState  = remember {
        ProductPurchasedByState(
            productPurchasedByValueText = mutableStateOf(""),
            showPurchasedByValueError = mutableStateOf(false),
            productPurchasedErrorText = mutableStateOf("Please Select Purchased By")
        )
    }

    val productPurchasedDateState = remember {
        ProductPurchasedDate(
            productPurchasedDateValueText = mutableStateOf(""),
            showPurchasedDateValueError = mutableStateOf(false),
            productPurchasedDateErrorText = mutableStateOf("Please Select Purchase date")
        )
    }

    val productExpiryDateState = remember {
        ProductExpiryDate(
            productExpiryDateValueText = mutableStateOf(""),
            showExpiryDateValueError = mutableStateOf(false),
            productExpiryDateErrorText = mutableStateOf("Please Select Expiry date")
        )
    }

    Scaffold(
        topBar = {
            val bodyLarge = TextStyle(fontSize = 18.sp)
            TopAppBar(
                title = { Text("New Purchase", color = Color.White, style =bodyLarge )},
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.primaryContainer,
                navigationIcon = {
                    IconButton(onClick = onBackPress
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Menu",
                            tint = materialWhite
                        )
                    }
                }
            )
        },bottomBar = {
            /*  Row {
                  Button(
                      onClick = {},
                      modifier = Modifier
                          .fillMaxWidth()
                  ) {
                      Text(text = "Add Product")
                  }
              }*/

            BottomAppBar(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Button(
                    onClick = {
                        validateNewPurchaseForm(context,productNameState,productQtyState, productPriceState, productCategoryState, productPurchasedByState, productPurchasedDateState, productExpiryDateState, newPurchaseViewModel)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Add Product")
                }
            }
        },
        content = {
            Column(modifier = Modifier
                .padding(8.dp, 70.dp, 8.dp, 0.dp)
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(8.dp) ) {

                SimpleOutlinedTextFieldSample(NewPurchaseEntryType.PRODUCT_NAME,productNameState.productNameValue, productNameState.isShowProductNameError, productNameState.productNameError, newPurchaseViewModel,"Product Name",ImageVector.vectorResource(id = R.drawable.ic_product),KeyboardOptions(keyboardType = KeyboardType.Text ), false)
                DropdownMenuBox(ArrayList(listOf("Food","Vegetable","Home Needs")),"Select Category",ImageVector.vectorResource(id = R.drawable.ic_category),productCategoryState)
                SimpleOutlinedTextFieldSample(NewPurchaseEntryType.PRODUCT_QTY, productQtyState.productQtyValueText,productQtyState.showProductQtyError,productQtyState.productQtyErrorText,newPurchaseViewModel,"Purchase Qty",ImageVector.vectorResource(id = R.drawable.ic_qty),KeyboardOptions(keyboardType = KeyboardType.Number ), false)
                SimpleOutlinedTextFieldSample(NewPurchaseEntryType.PRODUCT_PRICE,productPriceState.productPriceValueText,productPriceState.showProductPriceValueError,productPriceState.productPriceErrorText,newPurchaseViewModel,"Purchase Price",ImageVector.vectorResource(id = R.drawable.ic_product_price) ,KeyboardOptions(keyboardType = KeyboardType.Number), true)
                DropdownMenuBox(ArrayList(listOf("MySelf")),"Select Purchased By", ImageVector.vectorResource(id = R.drawable.ic_product_person),productPurchasedByState)
                SimpleOutlinedTextFieldDate("Purchased Date",ImageVector.vectorResource(id = R.drawable.ic_purchase_date),KeyboardOptions(keyboardType = KeyboardType.Text ), productPurchasedDateState)
                SimpleOutlinedTextFieldDate("Purchased Expiry Date",ImageVector.vectorResource(id = R.drawable.ic_update),KeyboardOptions(keyboardType = KeyboardType.Text ), productExpiryDateState)
            }

        }
    )


}

@Composable
fun SimpleOutlinedTextFieldSample(newPurchaseEntryType: NewPurchaseEntryType,productName: MutableState<String>, showProductNameError: MutableState<Boolean>, productNameError: MutableState<String>,newPurchaseViewModel: NewPurchaseViewModel,labelName: String,imageLabel: ImageVector, keyboardOptions: KeyboardOptions, isAmount: Boolean) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.secondary
        ),
        leadingIcon = {
            Icon(
                imageVector = imageLabel,
                contentDescription = null
            )
        },
        isError = showProductNameError.value,
            modifier = Modifier.fillMaxWidth(),
            value = productName.value,
            label = { Text(labelName) },
            onValueChange = {
                    it ->
                val filteredValue  : String = when(newPurchaseEntryType) {
                    NewPurchaseEntryType.PRODUCT_NAME-> {
                        it.filter { it.isLetterOrDigit() || it==' ' }
                    }

                    NewPurchaseEntryType.PRODUCT_QTY-> {
                        it.filter { it.isDigit() }
                    }

                    NewPurchaseEntryType.PRODUCT_PRICE-> {
                        it.filter { it.isDigit() || it =='.' }
                    }else -> {
                        it
                    }
                }
                productName.value = filteredValue
                showProductNameError.value = false


                            },
            enabled = true,
            keyboardOptions = keyboardOptions
                )

                if (showProductNameError.value) {
                    Text(
                        text = productNameError.value,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
        }


fun validateNewPurchaseForm(context: Context, productNameState: ProductNameState, productQtyState: ProductQtyState,
                            productPriceState: ProductPriceState, productCategoryState: ProductCategoryState,
                            productPurchasedByState : ProductPurchasedByState,
                            productPurchasedDate: ProductPurchasedDate, productExpiryDate: ProductExpiryDate
                            , newPurchaseViewModel: NewPurchaseViewModel){

    if(productNameState.productNameValue.value.isEmpty()) {
        productNameState.isShowProductNameError.value = true
        productNameState.productNameError.value = "Please Enter Product Name First"
    }else if(productQtyState.productQtyValueText.value.isEmpty()) {
        productQtyState.showProductQtyError.value = true
        productQtyState.productQtyErrorText.value = "Please Enter Product Name First"
    }else if(productPriceState.productPriceValueText.value.isEmpty()) {
        productPriceState.showProductPriceValueError.value = true
        productPriceState.productPriceErrorText.value = "Please Enter Product Name First"
    }else if(productCategoryState.productCategoryValueText.value.isEmpty()) {
        productCategoryState.showCategoryValueError.value = true
        productCategoryState.productCategoryErrorText.value = "Please select Category Name"
    }
    else if(productPurchasedByState.productPurchasedByValueText.value.isEmpty()) {
        productPurchasedByState.showPurchasedByValueError.value = true
        productPurchasedByState.productPurchasedErrorText.value = "Please select Purchased by"
    }else if(productPurchasedDate.productPurchasedDateValueText.value.isEmpty()) {
        productPurchasedDate.showPurchasedDateValueError.value = true
        productPurchasedDate.productPurchasedDateErrorText.value = "Please select Purchased Date"
    }else if(productExpiryDate.productExpiryDateValueText.value.isEmpty()) {
        productExpiryDate.showExpiryDateValueError.value = true
        productExpiryDate.productExpiryDateErrorText.value = "Please select Expiry Date"
    }else{
        newPurchaseViewModel.addNewPurchaseProduct(NewPurchaseProduct( productNameState.productNameValue.value, productCategoryState.productCategoryValueText.value,productPurchasedDate.productPurchasedDateValueText.value,productExpiryDate.productExpiryDateValueText.value,productQtyState.productQtyValueText.value,productPurchasedByState.productPurchasedByValueText.value,productPriceState.productPriceValueText.value))
       // showToastMessage(context,toastMessage = "Product Added successfully")
    }
}

fun showToastMessage(context: Context,toastMessage: String){
    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(suggestions: ArrayList<String>, dropDownLabel: String, imageVector: ImageVector,textFieldState: TextFieldState ) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(textFieldState.value.value) }


    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.KeyboardArrowDown


    Box {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it
                textFieldState.value.value = selectedText
                },
            readOnly = true,
            leadingIcon = {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text(dropDownLabel)}, // Corrected label parameter
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                .padding(0.dp)
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(text = { Text(text = label)},
                    onClick =
                {
                    selectedText = label
                    textFieldState.value.value = selectedText
                    textFieldState.showError.value = false

                    expanded = false
                }, modifier = Modifier
                        .padding(0.dp)
                        .clip(RoundedCornerShape(30.dp)))
            }
        }
    }


    if (textFieldState.showError.value) {
        Text(
            text = textFieldState.errorText.value,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldDate(labelName: String,imageLabel: ImageVector, keyboardOptions: KeyboardOptions, textFieldState: TextFieldState) {
    var text by remember { mutableStateOf(textFieldState.value.value) }
    var showDatePicker by remember { mutableStateOf(false) }

    Box (modifier = Modifier
        .fillMaxWidth()
        .clickable { showDatePicker = true }){
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            readOnly = true,
            label = { Text(labelName) },
            leadingIcon = {
                Icon(
                    imageVector = imageLabel,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            value = text,

            onValueChange = {  },
            enabled = false,
            keyboardOptions = keyboardOptions
        )
        if (showDatePicker) {
            DatePickerView(
                onDateSelected = { selectedDate ->
                    text = selectedDate // Update text field with selected date
                    textFieldState.value.value = text
                    textFieldState.showError.value = false
                    showDatePicker = false // Close date picker
                },
                onDismiss = { showDatePicker = false }
            )
        }
    }

    if (textFieldState.showError.value) {
        Text(
            text = textFieldState.errorText.value,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}





@ExperimentalMaterial3Api
@Composable
fun DatePickerView( onDismiss :() -> Unit,  onDateSelected : (String) -> Unit){
    val datePickerState = rememberDatePickerState(selectableDates =  object  : SelectableDates{
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }

    })

    val selectedCalenderDate =datePickerState.selectedDateMillis?.let { convertMillisToDate(it) }
    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { if (selectedCalenderDate != null) {
        onDateSelected(selectedCalenderDate)
    }
    onDismiss()}) {

                Text(text = "Select Purchase Date")
    }
        }, dismissButton = {
            Button(onClick = { onDismiss() }) {
               Text(text = "Dismiss")
            }
        }) {
        DatePicker(state = datePickerState)

    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}

@Preview("New Purchase design")
@Preview("new Purchase design", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNewPurchaseDesign(){
    /*val viewModela = remember { NewPurchaseViewModel() }
    AppTheme {
        NewPurchaseDesign(onBackPress = {},viewModela)
    }*/

}