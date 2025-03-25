package com.test.crypto.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.Cryptotest.data.model.Asset
import com.test.crypto.R
import com.test.crypto.ui.theme.CryptoTestTheme
import com.test.crypto.ui.viewmodel.AssetsUiState
import com.test.crypto.ui.viewmodel.AssetsViewModel

@Composable
fun HomeScreen(
    assetsUiState: AssetsUiState,
    modifier: Modifier = Modifier,
) {
    when (assetsUiState) {
        is AssetsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())

        is AssetsUiState.Success -> ResultScreen(
            assetsUiState.assets, modifier = modifier.fillMaxWidth()
        )

        is AssetsUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    val assetsViewModel: AssetsViewModel = viewModel()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))

        Button(onClick = { assetsViewModel.getAssets() }) {
            Text(stringResource(R.string.try_again))
        }
    }
}

@Composable
fun ResultScreen(assets: List<Asset>, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Assets(assets)
    }
}

@Composable
fun Assets(assets: List<Asset>) {
    LazyColumn {
        items(assets) { asset ->
            AssetCard(asset)
        }
    }
}

@Composable
fun AssetCard(asset: Asset) {
    Row(
        modifier = Modifier
            .padding(all = 4.dp)
            .clickable {
                //open details screen
            }
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 1.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .animateContentSize()
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(all = 16.dp)

            ) {
                Text(
                    text = "${asset.name} (${asset.symbol}) ",
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = String.format("%.2f", asset.priceUsd),
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "${String.format("%.2f", asset.changePercent24Hr)}%",
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (asset.changePercent24Hr >= 0) Color.Green else Color.Red
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    CryptoTestTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    CryptoTestTheme {
        ErrorScreen()
    }
}