package pl.marchuck.databindinganimations.ui.main

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Patterns
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import pl.marchuck.databindinganimations.model.LoginRequest
import pl.marchuck.databindinganimations.model.LoginService
import pl.marchuck.databindinganimations.model.MockLoginService

class MainViewModel(val loginService: LoginService = MockLoginService()) : ViewModel() {

    val email = ObservableField<String>()
    val emailError = ObservableField<String>()

    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()

    val progressVisible = ObservableField(false)
    val result = ObservableField<String>()

    fun onLoginClick() {

        if (email.get()?.isValidEmail() != true) {
            emailError.set("Email invalid")
            return
        } else {
            emailError.set(null)
        }
        if (password.get()?.isValidPassword() != true) {
            passwordError.set("Password too short")
            return
        } else {
            passwordError.set(null)
        }
        progressVisible.set(true)

        async(UI) {
            val loginResponse = loginService.requestLogin(LoginRequest(email = email.get()!!, password = password.get()!!)).await()
            progressVisible.set(false)
            result.set("response: ${loginResponse.email}, ${loginResponse.name}, ${loginResponse.surname} ")
        }
    }
}

fun String.isValidEmail() = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.isValidPassword() = this.length >= 6