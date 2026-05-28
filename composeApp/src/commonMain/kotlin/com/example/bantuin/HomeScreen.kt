package com.example.bantuin
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bantuin.model.Garage
import com.example.bantuin.model.ServiceCategory

data class ProfileVehicleItem(val id: Int, val type: String, val brand: String, val model: String, val plate: String)

@Composable
fun HomeScreen(onLogoutSuccess: () -> Unit) {
    val navyBlue = Color(0xFF1A237E)
    val amberOrange = Color(0xFFFF8F00)

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BantuinBottomNav(
                selectedTab = selectedTab,
                onTabSelected = { index -> selectedTab = index }
            )
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> {
                // ================= TAB 0: HOME SCREEN =================
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color(0xFFF5F5F5))
                ) {
                    item { HeaderSection(navyBlue) }
                    item { PromoSlider(amberOrange) }
                    item { MainServicesSection(navyBlue) }
                    item { EmergencyCard(amberOrange, navyBlue) }
                    item { NearbyGaragesSection(navyBlue) }
                }
            }
            1 -> {
                // ================= TAB 1: PESANAN =================
                Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text("Halaman Riwayat Pesanan Aktif", fontWeight = FontWeight.Medium, color = Color.Gray)
                }
            }
            2 -> {
                // ================= TAB 2: CHAT PESAN =================
                Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text("Halaman Kotak Masuk Pesan", fontWeight = FontWeight.Medium, color = Color.Gray)
                }
            }
            3 -> {
                // ================= TAB 3: PROFIL USER =================

                val modifiedPadding = PaddingValues(
                    start = paddingValues.calculateStartPadding(androidx.compose.ui.unit.LayoutDirection.Ltr),
                    end = paddingValues.calculateEndPadding(androidx.compose.ui.unit.LayoutDirection.Ltr),
                    top = 0.dp,
                    bottom = 0.dp
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(modifiedPadding)
                        .background(Color(0xFFF5F5F5))
                ) {
                    ProfileScreen(
                        onLogoutClick = { onLogoutSuccess() },
                        onAddVehicleClick = { /* Navigasi to Form add Kendaraan */ },
                        onHistoryClick = { /* Navigasi to Riwayat Detail Perbaikan */ }
                    )
                }
            }
        }
    }
}

/*@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    iconTint: Color = Color(0xFF1A237E),
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = iconTint, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = textColor, modifier = Modifier.weight(1fr))
        Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, null, tint = Color.LightGray, modifier = Modifier.size(14.dp))
    }
}*/

// ==================== KOMPONEN ASLI CORE HOMESCREEN TAB ====================
@Composable
fun HeaderSection(tint: Color) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bantuin.",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = tint
                )
            )
            Row {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = tint)
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Default.AccountCircle, contentDescription = null, tint = tint)
            }
        }

        Spacer(Modifier.height(16.dp))

        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray)
                Text(" Kirim montir ke: Jl. Merdeka No. 10...", color = Color.Gray)
            }
        }
    }
}

@Composable
fun EmergencyCard(bg: Color, btnColor: Color) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bg),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "BUTUH BANTUAN SEKARANG?",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text("(Panggilan Darurat)", color = Color.White)
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = btnColor)
            ) {
                Text("Bantu Saya")
            }
        }
    }
}

@Composable
fun MainServicesSection(navy: Color) {
    val services = listOf(
        ServiceCategory("Ganti Ban", Icons.Default.Build),
        ServiceCategory("Jumper Aki", Icons.Default.Bolt),
        ServiceCategory("Service Rutin", Icons.Default.Settings),
        ServiceCategory("Toko Sparepart", Icons.Default.ShoppingCart)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Layanan Utama", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ServiceItem(services[0], Modifier.weight(1f), navy)
                ServiceItem(services[1], Modifier.weight(1f), navy)
            }
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ServiceItem(services[2], Modifier.weight(1f), navy)
                ServiceItem(services[3], Modifier.weight(1f), navy)
            }
        }
    }
}

@Composable
fun ServiceItem(service: ServiceCategory, modifier: Modifier, tint: Color) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(service.icon, contentDescription = null, tint = tint, modifier = Modifier.size(32.dp))
            Spacer(Modifier.height(8.dp))
            Text(service.name, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun PromoSlider(accentColor: Color) {
    val promos = listOf("Diskon Ganti Oli", "Promo Ban Baru", "Servis Hemat")
    val pagerState = rememberPagerState(pageCount = { promos.size })

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.height(180.dp)
        ) { page ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = promos[page],
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        Row(
            Modifier.height(24.dp).fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(promos.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) accentColor else Color.LightGray
                Box(
                    modifier = Modifier.padding(2.dp).clip(CircleShape).background(color).size(8.dp)
                )
            }
        }
    }
}

@Composable
fun NearbyGaragesSection(navyColor: Color) {
    val garages = listOf(
        Garage("Bengkel A - Jakarta", 4.8, "2.1 km", "Jakarta Selatan"),
        Garage("Bengkel B - Bekasi", 4.5, "5.3 km", "Bekasi Barat"),
        Garage("Bengkel C - Depok", 4.6, "6.0 km", "Margonda")
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Bengkel Terdekat",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = navyColor
        )
        Spacer(Modifier.height(12.dp))

        garages.forEach { garage ->
            GarageItem(garage)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun GarageItem(garage: Garage) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)).background(Color.LightGray))
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(garage.name, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, "Rating", tint = Color(0xFFFFB300), modifier = Modifier.size(16.dp))
                    Text(" ${garage.rating} • ${garage.distance}", style = MaterialTheme.typography.bodySmall)
                }
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Detail", tint = Color.Gray)
        }
    }
}

// ==================== REUSABLE NAVIGATION BOTTOM BAR ====================
@Composable
fun BantuinBottomNav(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            icon = { Icon(Icons.AutoMirrored.Filled.List, null) },
            label = { Text("Pesanan") }
        )
        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            icon = { Icon(Icons.Default.Email, null) },
            label = { Text("Pesan") }
        )
        NavigationBarItem(
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Profil") }
        )
    }
}