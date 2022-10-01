package com.aesuriagasalazar.aboutecuador.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aesuriagasalazar.aboutecuador.model.Fact
import com.aesuriagasalazar.aboutecuador.model.FactAboutEcuadorData
import com.aesuriagasalazar.aboutecuador.ui.theme.AboutEcuadorTheme

@Composable
fun EcuadorBodyScreen(modifier: Modifier = Modifier) {
    EcuadorFactList(factList = FactAboutEcuadorData.listOfFacts, modifier = modifier)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EcuadorFactList(factList: List<Fact>, modifier: Modifier = Modifier) {

    val visibleState: MutableTransitionState<Boolean> = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(tween(delayMillis = 200))
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.surface
                        ),
                        startY = 50.0f
                    )
                ),
            color = Color.Transparent
        ) {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(space = 8.dp)
            ) {
                itemsIndexed(items = factList) { index, item ->
                    EcuadorFactItem(
                        fact = item,
                        modifier = Modifier.animateEnterExit(
                            enter = slideInHorizontally(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessVeryLow
                                ),
                                initialOffsetX = {
                                    if (index % 2 == 0) it else -it
                                }
                            )
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EcuadorFactItem(fact: Fact, modifier: Modifier = Modifier) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()
    val transition by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 180.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            ),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small.copy(all = CornerSize(size = 8.dp)),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding(all = 8.dp)) {
                Text(
                    text = stringResource(id = fact.title),
                    style = MaterialTheme.typography.h3
                )
            }
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = fact.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            AnimatedVisibility(
                visible = isExpanded,
                enter = scaleIn(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow,

                        )
                ),
            ) {
                Box(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(id = fact.description),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                IconButton(
                    modifier = Modifier.offset(y = transition.dp),
                    onClick = { isExpanded = !isExpanded }
                ) {
                    Icon(
                        imageVector = if (!isExpanded) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
                        contentDescription = null,
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun EcuadorFactListPreview() {
    AboutEcuadorTheme {
        EcuadorFactList(factList = FactAboutEcuadorData.listOfFacts)
    }
}

@Preview
@Composable
fun EcuadorFactItemPreview() {
    AboutEcuadorTheme {
        EcuadorFactItem(fact = FactAboutEcuadorData.listOfFacts[0])
    }
}