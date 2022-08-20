package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream
import kotlin.random.Random


fun main() {

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData(
            "content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken("cKnoFFcQRIad-P6c8GOgXE:APA91bEiniAoVBNsyM-hK56rFKybU_jCqaS3zQ7hBhm9scBeTJr9Q-m8r9Yh_urvidVmVNByFHaaA9oOTWMii6S7HHWMjSo3FZvONxhgO6H10MJ_cp_0YZo07k8EoYq1BAh0fuhMlJZf")
        .build()

    val postContent = "Мы знаем, что в каждом из нас уже есть та внутренняя сила, " +
            "которая заставляет всегда хотеть больше, целиться выше, бежать быстрее. " +
            "Наша миссия — помочь встать на путь роста и пройти с человеком по этому " +
            "пути как можно дальше. Сделать так, чтобы желание перемен стало сильнее страха " +
            "перемен. Обучить новому, влюбить в знания. Стать импульсом к действию."

    val postMessage = Message.builder()
        .putData("action", "NEW_POST")
        .putData(
            "content", """{
          "id": ${Random.nextLong(1_000)},
          "author": "Netology",
          "content": "${postContent}",
          "published": "19.08.2022 10:15"
        }""".trimIndent()
        )
        .setToken("cKnoFFcQRIad-P6c8GOgXE:APA91bEiniAoVBNsyM-hK56rFKybU_jCqaS3zQ7hBhm9scBeTJr9Q-m8r9Yh_urvidVmVNByFHaaA9oOTWMii6S7HHWMjSo3FZvONxhgO6H10MJ_cp_0YZo07k8EoYq1BAh0fuhMlJZf")
        .build()



    FirebaseMessaging.getInstance().send(postMessage)
}
