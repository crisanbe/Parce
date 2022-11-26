package com.gerotac.components_ui.componets.contenmodal

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerotac.components_ui.componets.button.BotonConTexto
import com.gerotac.components_ui.componets.imagesEncode.ImagenDecodeLocal
import com.gerotac.components_ui.componets.text.TextBasic

@Composable
fun ContenidoModal(
    modifier: Modifier = Modifier,
    shape: androidx.compose.ui.graphics.Shape,
    onClick: () -> Unit,
    color: androidx.compose.ui.graphics.Color,
    paddingValues: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 42.dp),
    colorBoton: Color,
    colorTextoBoton: Color,
    textoBoton: String,
    tituloPrincipal: String,
    descripcion: String,
    colorTextoPrinciapl: Color,
    colorTextoDescripcion: Color
) {
    Card(
        modifier = modifier,
        shape = shape,
        backgroundColor = color,
        elevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagenDecodeLocal(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(48.dp),
                urlImagen = painterResource(id = com.gerotac.components_ui.R.drawable.description),
                escalaImagen = ContentScale.Crop,
                descripcionImagen = descripcion
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextBasic(
                text = tituloPrincipal,
                textColor = androidx.compose.ui.graphics.Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextBasic(
                text = descripcion,
                textColor = androidx.compose.ui.graphics.Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            BotonConTexto(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                color = colorBoton,
                onClick = { onClick() },
                textoBoton = textoBoton,
                colorTexto = colorTextoBoton,
                alinearTexto = TextAlign.Center,
                tamañoFuente = 16.sp,
                pesoFuente = FontWeight.Bold
            )

        }
    }
}