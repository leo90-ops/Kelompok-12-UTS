package com.example.mykartunama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mykartunama.ui.theme.MyKartuNamaTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyKartuNamaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF1A3A1A) // hijau gelap harmonis
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF1A3A1A), // hijau gelap
                                        Color(0xFF2D5D2D), // hijau medium
                                        Color(0xFF4CAF50)  // hijau segar
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        BusinessCard()
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(800)) +
                slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(800)),
        exit = fadeOut()
    ) {
        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(12.dp, RoundedCornerShape(28.dp)),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)) // putih gading lembut
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto Bundar dengan Border
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4CAF50).copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_android_black_24dp),
                        contentDescription = "Android Logo",
                        modifier = Modifier.size(80.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nama & Profesi
                Text(
                    text = "Lionel",
                    fontSize = 34.sp,
                    color = Color(0xFF1B5E20), // hijau tua lembut
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Android Developer Pro",
                    fontSize = 18.sp,
                    color = Color(0xFF388E3C) // hijau medium
                )

                Divider(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(0.6f),
                    color = Color(0xFFC8E6C9),
                    thickness = 1.dp
                )

                // Deskripsi Singkat
                Text(
                    text = "Pengembang Android yang membuat aplikasi modern, responsif, dan user-friendly. Berpengalaman dengan Jetpack Compose, Kotlin, dan desain UI/UX profesional.",
                    textAlign = TextAlign.Justify,
                    color = Color(0xFF2E3A2E), // hijau gelap nyaman dibaca
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Kontak
                ContactTable(
                    phone = "+62 812-3965-5375",
                    username = "@Lionel_mesi",
                    email = "lionel_mesi@gmail.com",
                    linkedin = "linkedin.com/in/lionel",

                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Interaktif
                Button(
                    onClick = { /* aksi panggil/email */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                ) {
                    Text(text = "Hubungi Saya", color = Color(0xFFF1F8E9))
                }
            }
        }
    }
}

@Composable
fun ContactTable(
    phone: String,
    username: String,
    email: String,
    linkedin: String,

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        TableRow("📞 Telepon", phone)
        TableRow("💬 Username", username)
        TableRow("✉️ Email", email)
        TableRow("🔗 LinkedIn", linkedin)

    }
}

@Composable
fun TableRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF388E3C),
            fontSize = 15.sp
        )
        Text(
            text = value,
            textAlign = TextAlign.End,
            color = Color(0xFF1B5E20),
            fontSize = 15.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MyKartuNamaTheme {
        Surface(color = Color(0xFF1A3A1A)) {
            BusinessCard()
        }
    }
}
