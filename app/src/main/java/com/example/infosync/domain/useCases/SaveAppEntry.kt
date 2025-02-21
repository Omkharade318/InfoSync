package com.example.infosync.domain.useCases

import com.example.infosync.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}