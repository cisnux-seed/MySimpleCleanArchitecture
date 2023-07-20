package com.dicoding.mysimplecleanarchitecture.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MessageUseCaseTest {

    private lateinit var messageUseCase: MessageUseCase

    @Mock
    lateinit var messageRepository: IMessageRepository

    @BeforeEach
    fun init() {
        messageUseCase = MessageInteractor(messageRepository)
        val dummyMessage = MessageEntity("Hello $NAME Welcome to Clean Architecture")
        `when`(messageRepository.getWelcomeMessage(NAME)).thenReturn(dummyMessage)
    }

    @Test
    fun `should get data from repository`() {
        val message = messageUseCase.getMessage(NAME)

        verify(messageRepository).getWelcomeMessage(NAME)
        verifyNoMoreInteractions(messageRepository)
        Assertions.assertEquals("Hello $NAME Welcome to Clean Architecture", message.welcomeMessage)
    }

    companion object {
        const val NAME = "Dicoding"
    }
}