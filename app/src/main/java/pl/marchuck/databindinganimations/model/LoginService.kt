package pl.marchuck.databindinganimations.model

import kotlinx.coroutines.experimental.Deferred

interface LoginService {

    fun requestLogin(loginRequest: LoginRequest): Deferred<LoginResponse>
}