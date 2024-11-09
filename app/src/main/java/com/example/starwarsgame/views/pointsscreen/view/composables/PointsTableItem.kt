package com.example.starwarsgame.views.pointsscreen.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter



@Composable
fun PointsTableItem(
    imageUrl: String,
    heroName: String,
    heroPoints: Int,
    onClick: () -> Unit,
) {
    val painter = rememberAsyncImagePainter(imageUrl)
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp).clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "Image from URL",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop  // Adjust content scale as needed
            )
        }
        Text(
            heroName, fontSize = 25.sp, modifier = Modifier.padding(
                start = 20.dp
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            heroPoints.toString(),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Preview(showBackground = true)
@Composable

fun PointsTableItemPreview() {
    PointsTableItem(
        "",
        "Gatrix",
        123
    ) {

    }
}
