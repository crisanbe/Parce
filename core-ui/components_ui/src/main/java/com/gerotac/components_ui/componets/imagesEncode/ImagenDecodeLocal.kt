package com.gerotac.components_ui.componets.imagesEncode

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale

@Composable
fun ImagenDecodeLocal(
    modifier: Modifier,
    urlImagen: Painter? = null,
    imagenVector: ImageVector? = null,
    escalaImagen: ContentScale,
    descripcionImagen: String
) {
    if (urlImagen != null) {
        Image(
            painter = urlImagen,
            contentDescription = descripcionImagen,
            modifier = modifier,
            contentScale = escalaImagen
        )
    }else if (imagenVector != null){
        Image(
            painter = rememberVectorPainter(image = imagenVector),
            contentDescription = descripcionImagen,
            modifier = modifier,
            contentScale = escalaImagen
        )
    }
}
