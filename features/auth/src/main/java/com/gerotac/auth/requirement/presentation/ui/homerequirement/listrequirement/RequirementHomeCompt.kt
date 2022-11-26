package com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerotac.auth.requirement.domain.model.getrequirement.Intervention
import com.gerotac.auth.requirement.domain.model.getrequirement.Result
import com.gerotac.auth.theme.ShimmerColorShades

fun mToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

@Composable
fun AnimationEffect() {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing,
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )
    ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(brush)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(fraction = 0.7f)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(fraction = 0.9f)
                    .background(brush)
            )
        }
    }
}

@Composable
fun HomeRequirements(
    modifier: Modifier = Modifier,
    resultRequirement: Result,
    onItemClicked: (Int) -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = modifier
                .clickable(onClick = {
                    mToast(context, resultRequirement.id.toString())
                })
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF5F5F5))
                .fillMaxWidth()
                .height(130.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(1.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .widthIn(40.dp)
                            .heightIn(20.dp),
                        backgroundColor = Color.Black,
                        shape = RoundedCornerShape(20.dp),
                        elevation = 2.dp
                    ) {
                        Text(
                            text = resultRequirement.id.toString(),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = resultRequirement.areaintervention.name ?: "name",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = resultRequirement.description,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray,
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(end = 5.dp),
                    onClick = { onItemClicked(resultRequirement.id) },
                    colors = ButtonDefaults.buttonColors(Color(0xFF21120B)),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text(
                        text = "Detalles",
                        color = Color.White
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun HomeInterventions(
    modifier: Modifier = Modifier,
    resultInterventions: com.gerotac.auth.requirement.domain.model.detailrequirement.Intervention,
    onItemClicked: (Int) -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        elevation = 10.dp,
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(
            modifier = modifier
                .clickable(onClick = {
                    mToast(context, resultInterventions.id.toString())
                })
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF5F5F5))
                .fillMaxWidth()
                .height(130.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(1.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .widthIn(40.dp)
                            .heightIn(20.dp),
                        backgroundColor = Color.Black,
                        shape = RoundedCornerShape(20.dp),
                        elevation = 2.dp
                    ) {
                        Text(
                            text = resultInterventions.id.toString(),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = resultInterventions.type_intervention.toString() ?: "name",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = resultInterventions.description,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray,
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(end = 5.dp),
                    onClick = { onItemClicked(resultInterventions.id) },
                    colors = ButtonDefaults.buttonColors(Color(0xFF21120B)),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text(
                        text = "Detalles",
                        color = Color.White
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}