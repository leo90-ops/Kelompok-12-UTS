package com.example.mykartunama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mykartunama.ui.theme.MyKartuNamaTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyKartuNamaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFD4EAD9) // warna hijau muda lembut
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bagian logo dan nama
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Android Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = "Lionel",
            fontSize = 36.sp,
            color = Color(0xFF3E4A3D)
        )
        Text(
            text = "Android Developer Pro",
            color = Color(0xFF006400),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 40.dp)
        )

        // Bagian informasi kontak
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ContactRow(R.drawable.baseline_local_phone_24, "+62 (123) 444 555 666")
            ContactRow(R.drawable.outline_airplane_ticket_24, "@AndroidLeo")
            ContactRow(R.drawable.outline_alternate_email_24, "Leo@android.com")
        }
    }
}

@Composable
fun ContactRow(iconId: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color(0xFF3E4A3D),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color(0xFF3E4A3D)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MyKartuNamaTheme {
        Surface(color = Color(0xFFD4EAD9)) {
            BusinessCard()
        }
    }
}