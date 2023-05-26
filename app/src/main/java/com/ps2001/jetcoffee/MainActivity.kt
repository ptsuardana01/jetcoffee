package com.ps2001.jetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ps2001.jetcoffe.R
import com.ps2001.jetcoffee.model.Menu
import com.ps2001.jetcoffee.model.dummyBestSellerMenu
import com.ps2001.jetcoffee.model.dummyCategory
import com.ps2001.jetcoffee.model.dummyMenu
import com.ps2001.jetcoffee.ui.components.CategoryItem
import com.ps2001.jetcoffee.ui.components.HomeSection
import com.ps2001.jetcoffee.ui.components.MenuItem
import com.ps2001.jetcoffee.ui.components.SearchBar
import com.ps2001.jetcoffee.ui.components.SectionText
import com.ps2001.jetcoffee.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Banner()
        //contoh penggunaan named parameter
        //BEST PRACTICE
        HomeSection(
            title = stringResource(R.string.section_category),
            content = { CategoryRow() }
        )

        //memasukkan argument satu per satu
        HomeSection(
            title = stringResource(R.string.section_favorite_menu),
            Modifier, { MenuRow(dummyMenu) }
        )

        //jika lambda ada di akhir parameter, ia dapat dikeluarkan setelah parenthesis
        HomeSection(title = stringResource(R.string.section_best_seller_menu)) {
            MenuRow(dummyBestSellerMenu)
        }
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu,
            modifier.background(Color.White))
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3A)
@Composable
fun JetCoffeeAppPrev() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryRowPrev() {
    JetCoffeeTheme {
        CategoryRow()
    }
}

