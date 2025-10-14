package com.example.mykartunama

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import android.net.Uri
import androidx.compose.foundation.clickable

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
                        painter = painterResource(id = R.drawable.outline_account_circle_24),
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
                    text = "Kartu Nama",
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
                    text = "Seorang Mahasiswa",
                    textAlign = TextAlign.Justify,
                    color = Color(0xFF2E3A2E), // hijau gelap nyaman dibaca
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Kontak
                ContactTable(
                    phone = "+62 812-3965-5375",
                    username = "@Lionel mesi",
                    email = "lionelmesi@gmail.com",
                    instagram = "@Instagram.com/lionelmesi",

                )

                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    }
}

@Composable
fun ContactTable(
    phone: String,
    username: String,
    email: String,
    instagram: String,

) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        TableRow("📞 No hp", phone) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            context.startActivity(intent)
        }
        TableRow("💬 Username", username)
        TableRow("✉️ Email", email) {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
            context.startActivity(intent)
        }
        TableRow("📸 Instagram", instagram) {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/leomessi?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw==${username.removePrefix("@")}"))
            context.startActivity(intent)
        }
    }
}

@Composable
fun TableRow(label: String, value: String, onclick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onclick()}, // Agar bisa di klik
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
