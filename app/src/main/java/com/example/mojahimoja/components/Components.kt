package com.example.mojahimoja.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpace(
    space: Int
) {
    Spacer(modifier = Modifier.width(space.dp))
}