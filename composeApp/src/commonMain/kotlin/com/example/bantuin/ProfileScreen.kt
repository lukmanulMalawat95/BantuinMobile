package com.example.bantuin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class VehicleProfileDummy(val id: Int, val type: String, val brand: String, val model: String, val plate: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogoutClick: () -> Unit,
    onAddVehicleClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    val customerName = "Lukman"
    val customerEmail = "lukman@example.com"
    val loyaltyPoints = 350

    val myVehicles = listOf(
        VehicleProfileDummy(1, "CAR", "Toyota", "Fortuner", "B 1234 ABC"),
        VehicleProfileDummy(2, "MOTORCYCLE", "Honda", "Vario 160", "B 5678 XYZ")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        // ================= SECTION 1: HEADER PROFIL (BERIRISAN DENGAN BACKGROUND BIRU) =================
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A237E))
                    .padding(top = 40.dp, bottom = 20.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .offset(y = 40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0E0E0)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Avatar",
                                tint = Color.Gray,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(customerName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(customerEmail, fontSize = 14.sp, color = Color.Gray)

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(Color(0xFFFFF9C4))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = "Points",
                                    tint = Color(0xFFFBC02D),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    "$loyaltyPoints Poin Bantuin",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFF57F17)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }

        // ================= SECTION 2: KENDARAAN SAYA =================
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Kendaraan Saya", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                TextButton(onClick = onAddVehicleClick) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Tambah")
                }
            }

            if (myVehicles.isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Text("Belum ada kendaraan terdaftar", modifier = Modifier.padding(16.dp).fillMaxWidth(), color = Color.Gray)
                }
            } else {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    items(myVehicles) { vehicle ->
                        Card(
                            modifier = Modifier.width(220.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (vehicle.type == "CAR") Icons.Default.DirectionsCar else Icons.Default.TwoWheeler,
                                        contentDescription = "Tipe",
                                        tint = Color(0xFF1A237E)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(vehicle.brand, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(vehicle.model, fontSize = 14.sp, color = Color.Gray)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    vehicle.plate,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp,
                                    modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(Color(0xFFE0E0E0)).padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // ================= SECTION 3: MENU OPSI =================
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text("Menu Akun", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column {
                    ProfileMenuItem(icon = Icons.Default.Build, title = "Riwayat Perbaikan Bengkel", onClick = onHistoryClick)
                    HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ProfileMenuItem(icon = Icons.Default.Edit, title = "Ubah Profil & Alamat", onClick = {})
                    HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ProfileMenuItem(icon = Icons.Default.Notifications, title = "Notifikasi", onClick = {})
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                ProfileMenuItem(
                    icon = Icons.Default.ExitToApp,
                    title = "Keluar dari Akun",
                    iconTint = Color.Red,
                    textColor = Color.Red,
                    onClick = onLogoutClick
                )
            }

            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

@Composable
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
        Icon(icon, contentDescription = title, tint = iconTint, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = textColor, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ArrowForwardIos, contentDescription = "Arrow", tint = Color.LightGray, modifier = Modifier.size(14.dp))
    }
}