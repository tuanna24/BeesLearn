package fpl.md07.beeslearn.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.CustomPasswordField
import fpl.md07.beeslearn.components.CustomTextField
import fpl.md07.beeslearn.requests.ChangePasswordRequest
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.ChangePasswordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    navController: NavController,
    viewModel: ChangePasswordViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(Color(0xffffffff))
            .padding(horizontal = 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(267.dp)
                .padding(bottom = 50.dp)
        )

        CustomTextField(
            labelText = "Email",
            value = email,
            onValueChange = { email = it },
            placeholder = "Nhập email"
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            labelText = "Mật khẩu cũ",
            value = oldPassword,
            onValueChange = { oldPassword = it },
            placeholder = "Nhập mật khẩu cũ"
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomPasswordField(
            labelText = "Mật khẩu mới",
            value = newPassword,
            onValueChange = { newPassword = it },
            placeholder = "Nhập mật khẩu mới"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomPasswordField(
            labelText = "Xác nhận mật khẩu mới",
            value = confirmNewPassword,
            onValueChange = { confirmNewPassword = it },
            placeholder = "Nhập lại mật khẩu mới"
        )

        Button(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
            onClick = {
                when {
                    email.isBlank() -> {
                        Toast.makeText(context, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show()
                    }
                    oldPassword.isBlank() -> {
                        Toast.makeText(context, "Vui lòng nhập mật khẩu cũ!", Toast.LENGTH_SHORT).show()
                    }
                    newPassword.isBlank() -> {
                        Toast.makeText(context, "Vui lòng nhập mật khẩu mới!", Toast.LENGTH_SHORT).show()
                    }
                    confirmNewPassword.isBlank() -> {
                        Toast.makeText(context, "Vui lòng xác nhận mật khẩu mới!", Toast.LENGTH_SHORT).show()
                    }
                    newPassword != confirmNewPassword -> {
                        Toast.makeText(context, "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        val request = ChangePasswordRequest(
                            email = email,
                            oldPassword = oldPassword,
                            newPassword = newPassword
                        )
                        viewModel.changePassword(
                            request = request,
                            onSuccess = {
                                Toast.makeText(context, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show()
                                // Điều hướng đến LoginScreen
                                navController.navigate("LoginScreen") {
                                    popUpTo(0) // Xóa toàn bộ back stack
                                }
                            },
                            onError = { error ->
                                // Hiển thị lỗi từ ViewModel
                                Toast.makeText(context, "Lỗi: $error", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD528)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "XÁC NHẬN",
                color = Color.White,
                fontFamily = Nunito_Bold
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    val navController = rememberNavController()
    ChangePasswordScreen(navController)
}