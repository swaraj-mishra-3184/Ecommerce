package com.lucky.ecommmerce.ui.components

// SearchBar.kt
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Search products...") },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    onSearch(query)
                }
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
    )
}
