package pl.marchuck.databindinganimations.model

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import java.util.concurrent.TimeUnit

class MockLoginService : LoginService {

    override fun requestLogin(loginRequest: LoginRequest): Deferred<LoginResponse> {
        return async {

            delay(3, TimeUnit.SECONDS)

            return@async LoginResponse(email = loginRequest.email, name = "Joe", surname = "Doe")
        }
    }

}